package br.edu.opi.manager.office_io.services;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;

public class WordMerge {

	private XWPFDocument result;

	public WordMerge() {
		result = null;
	}

	public void add(XWPFDocument document) {
		if (result == null) {
			result = document;
		} else {
			CTBody srcBody = document.getDocument().getBody();
			result.getDocument().addNewBody().set(srcBody);
		}
	}

	public XWPFDocument doResult() {
		return result;
	}

}
