package br.edu.opi.manager.word_io;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/*
 * @see https://stackoverflow.com/questions/6201736/javausing-apache-poi-how-to-convert-ms-word-file-to-pdf
 * @see https://stackoverflow.com/questions/31981007/editing-word-document-using-apache-poi
 * @see https://www.baeldung.com/java-microsoft-word-with-apache-poi
 * @see https://www.baeldung.com/docx4j
 */
@Service
public class WordParserService {

	public static void main(String[] args) throws IOException, InvalidFormatException {
		Path resourcesPath = ResourceUtils.getFile("classpath:files").toPath();
		Path msWordPath = resourcesPath.resolve("OPI_Modelo_Certificado.doc");
		XWPFDocument document = new XWPFDocument(Files.newInputStream(msWordPath));
		List<XWPFParagraph> paragraphs = document.getParagraphs();
		boolean end = false;
		for (XWPFParagraph paragraph : paragraphs) {
			if (end) break;
			for (XWPFRun run : paragraph.getRuns()) {
				String text = run.getText(0);
				if (text != null && text.contains("NOME_COMPETIDOR")) {
					text = text.replace("NOME_COMPETIDOR", "OTHER_NAME");
					run.setText(text, 0);
					end = true;
					break;
				}
			}
		}
		document.write(new FileOutputStream("TEST_OTHER.doc"));
	}

}
