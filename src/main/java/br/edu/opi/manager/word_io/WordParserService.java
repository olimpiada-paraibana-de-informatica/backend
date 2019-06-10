package br.edu.opi.manager.word_io;

import org.apache.poi.xwpf.usermodel.IRunBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileOutputStream;
import java.io.IOException;
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

	public static void main(String[] args) throws IOException, XmlException {
		Path resourcesPath = ResourceUtils.getFile("classpath:files").toPath();
		Path msWordPath = resourcesPath.resolve("OPI_Modelo_Certificado.docx");
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
		document.write(new FileOutputStream("OTHER_THINGS.docx"));
		document.close();
	}

}
