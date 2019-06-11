package br.edu.opi.manager.word_io;

import br.edu.opi.manager.excel_io.services.TargetXlsx;
import org.apache.poi.xwpf.usermodel.IRunBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/*
 * @see https://stackoverflow.com/questions/6201736/javausing-apache-poi-how-to-convert-ms-word-file-to-pdf
 * @see https://stackoverflow.com/questions/31981007/editing-word-document-using-apache-poi
 * @see https://stackoverflow.com/a/46894499
 * @see https://www.baeldung.com/java-microsoft-word-with-apache-poi
 * @see https://www.baeldung.com/docx4j
 */
@Service
public class WordParserService {

	public Resource downloadSheet(TargetXlsx targetXlsx) {
		String fileName = solveFileName(targetXlsx);
		try {
			Path filePath = null;
			if (fileName.startsWith("http")) {
				URL url = new URL(fileName);
				ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
				Path temp = Files.createTempFile("Renomear_Modelo", ".xlsx");
				FileOutputStream fileOutputStream = new FileOutputStream(temp.toString());
				FileChannel fileChannel = fileOutputStream.getChannel();
				fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE); // TODO: improve this...
				filePath = temp.toAbsolutePath();
			} else {
				Path resourcesPath = ResourceUtils.getFile("classpath:files").toPath();
				filePath = resourcesPath.resolve(fileName);
			}
			Path certifiedPath = generateCertified(filePath);
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

	private String solveFileName(TargetXlsx targetXlsx) {
		return "OPI_Modelo_Certificado.docx";
	}

	private Path generateCertified(Path msWordPath) throws IOException, XmlException {
		XWPFDocument document = new XWPFDocument(Files.newInputStream(msWordPath));
		String someWords = "NOME_COMPETIDOR";
		boolean found = false;
		for (XWPFParagraph paragraph : document.getParagraphs()) {
			if (found) break;
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
				String text = bufferrun.getText(0);
				if (text != null && text.contains(someWords)) {
					text = text.replace(someWords, "Competidor de Teste");
					bufferrun.setText(text, 0);
					found = true;
				}
				obj.set(bufferrun.getCTR());
				if (found) break;
			}
		}
		Path output = Files.createTempFile("certified", ".docx");
		document.write(new FileOutputStream(output.toFile().getAbsolutePath()));
		document.close();
		return output;
	}

}
