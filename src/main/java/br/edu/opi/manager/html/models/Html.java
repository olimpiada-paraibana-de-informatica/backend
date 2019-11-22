package br.edu.opi.manager.html.models;

import br.edu.opi.manager.project_patterns.models.Model;
import br.edu.opi.manager.project_patterns.models.history.Auditing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_html")
public class Html extends Auditing implements Serializable, Model<String> {

	@Id
	@Column(name = "id", length = 10)
	private String id;

	@Column(name = "description")
	private String description;

	@Column(name = "html", length = 5000)
	private String html;

	public Html() {
	}

	public Html(String id, String description, String html) {
		this.id = id;
		this.description = description;
		this.html = html;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
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
