package br.edu.opi.manager.html.dtos;

public class HtmlDTO {

	private String id;

	private String description;

	private String html;

	public HtmlDTO() {
	}

	public HtmlDTO(String id, String description, String html) {
		this.id = id;
		this.description = description;
		this.html = html;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

}
