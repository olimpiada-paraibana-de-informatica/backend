package br.edu.opi.manager.office_io.services;

import br.edu.opi.manager.competitor.models.Competitor;
import br.edu.opi.manager.competitor.repositories.CompetitorRepository;
import br.edu.opi.manager.competitor.services.CompetitorService;
import br.edu.opi.manager.olympiad.models.OpiAward;
import org.apache.poi.xwpf.usermodel.IRunBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * @see https://stackoverflow.com/questions/6201736/javausing-apache-poi-how-to-convert-ms-word-file-to-pdf
 * @see https://stackoverflow.com/questions/31981007/editing-word-document-using-apache-poi
 * @see https://stackoverflow.com/a/46894499
 * @see https://stackoverflow.com/a/52340817
 */
@Service
public class WordGenerateService {

	static final String NOME = "NOME_COMPETIDOR";
	static final String PREMIO = "PREMIO";
	static final String CATEGORIA = "CATEGORIA";

	private CompetitorRepository competitorRepository;
	private CompetitorService competitorService;
	private static String CERTIFIED_FILE_NAME;

	@Autowired
	public WordGenerateService(
			CompetitorRepository competitorRepository,
			CompetitorService competitorService,
			@Value("${docx.file.certified}") String certifiedFileName) {
		this.competitorRepository = competitorRepository;
		this.competitorService = competitorService;
		this.CERTIFIED_FILE_NAME = certifiedFileName;
	}

	public Resource downloadCertified(String award, Long competitorId) {
		String fileName = this.CERTIFIED_FILE_NAME;
		try {
			Path filePath = solveFilePath(fileName);
			Competitor competitor = competitorService.show(competitorId);
			Path certifiedPath = solvePathCertified(generateCertified(filePath, competitor.getFullName(), award, competitor.getCategory().getName()));
			Resource resource = new UrlResource(certifiedPath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new RuntimeException("Arquivo " + fileName + " não encontrado");
			}
		} catch (MalformedURLException murl) {
			throw new RuntimeException("Arquivo " + fileName + " não encontrado", murl);
		} catch (IOException ioe) {
			throw new RuntimeException("Erro ao buscar arquivo " + fileName, ioe);
		} catch (XmlException xe) {
			throw new RuntimeException("Erro ao processar modelo " + fileName, xe);
		}
	}

	public Resource downloadCertifieds(Map<Long, OpiAward> awardMap) {
		String fileName = this.CERTIFIED_FILE_NAME;
		try {
			Path filePath = solveFilePath(fileName);
			List<Competitor> competitors = competitorRepository.findAllById(awardMap.keySet());
			Path certifiedPath = solvePathCertified(filePath, awardMap, competitors);
			Resource resource = new UrlResource(certifiedPath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new RuntimeException("Arquivo " + fileName + " não encontrado");
			}
		} catch (MalformedURLException murl) {
			throw new RuntimeException("Arquivo " + fileName + " não encontrado", murl);
		} catch (IOException ioe) {
			throw new RuntimeException("Erro ao buscar arquivo " + fileName, ioe);
		} catch (XmlException xe) {
			throw new RuntimeException("Erro ao processar modelo " + fileName, xe);
		}
	}

	private Path solveFilePath(String fileName) throws IOException {
		if (fileName.startsWith("http")) {
			URL url = new URL(fileName);
			ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
			Path temp = Files.createTempFile("Renomear_Modelo", ".xlsx");
			FileOutputStream fileOutputStream = new FileOutputStream(temp.toString());
			fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE); // TODO: improve this...
			return temp.toAbsolutePath();
		} else {
			Path resourcesPath = ResourceUtils.getFile("classpath:files").toPath();
			return resourcesPath.resolve(fileName);
		}
	}

	private XWPFDocument generateCertified(Path msWordPath, String name, String award, String category) throws IOException, XmlException {
		XWPFDocument document = new XWPFDocument(Files.newInputStream(msWordPath));
		boolean foundName = false;
		boolean foundAward = false;
		boolean foundCategory = false;
		for (XWPFParagraph paragraph : document.getParagraphs()) {
			if (foundName && foundAward && foundCategory) break;
			XmlCursor cursor = paragraph.getCTP().newCursor();
			cursor.selectPath("declare namespace w='http://schemas.openxmlformats.org/wordprocessingml/2006/main' .//w:drawing/*/w:txbxContent/w:p/w:r");
			List<XmlObject> ctrsintxtbx = new ArrayList<>();
			while (cursor.hasNextSelection()) {
				cursor.toNextSelection();
				XmlObject obj = cursor.getObject();
				ctrsintxtbx.add(obj);
			}
			for (XmlObject obj : ctrsintxtbx) {
				CTR ctr = CTR.Factory.parse(obj.xmlText());
				//CTR ctr = CTR.Factory.parse(obj.newInputStream());
				XWPFRun bufferrun = new XWPFRun(ctr, (IRunBody) paragraph);
				foundName = (foundName || solveCertifiedField(obj, bufferrun, NOME, name));
				foundAward = (foundAward || solveCertifiedField(obj, bufferrun, PREMIO, award));
				foundCategory = (foundCategory || solveCertifiedField(obj, bufferrun, CATEGORIA, category));
				if (foundName && foundAward && foundCategory) break;
			}
		}
		return document;
	}

	private Path solvePathCertified(XWPFDocument document) throws IOException {
		Path output = Files.createTempFile("certified", ".docx");
		document.write(new FileOutputStream(output.toFile().getAbsolutePath()));
		document.close();
		return output;
	}

	private Path solvePathCertified(Path filePath, Map<Long, OpiAward> awardMap, List<Competitor> competitors) throws IOException, XmlException {
		WordMerge wordMerge = new WordMerge();
		for (Competitor competitor : competitors) {
			final String award = awardMap.get(competitor.getId()).getName();
			XWPFDocument document = generateCertified(filePath, competitor.getFullName(), award, competitor.getCategory().getName());
			wordMerge.add(document);
		}
		Path result = solvePathCertified(wordMerge.doResult());
		return result;
	}

	private boolean solveCertifiedField(XmlObject obj, XWPFRun bufferrun, String target, String replacement) {
		String text = bufferrun.getText(0);
		if (text != null && text.equals(target)) {
			text = text.replace(target, replacement);
			bufferrun.setText(text, 0);
			obj.set(bufferrun.getCTR());
			return true;
		}
		return false;
	}

}
