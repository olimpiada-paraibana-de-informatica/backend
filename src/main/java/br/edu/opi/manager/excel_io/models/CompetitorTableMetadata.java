package br.edu.opi.manager.excel_io.models;

import br.edu.opi.manager.project_patterns.models.Model;
import br.edu.opi.manager.project_patterns.models.history.Auditing;
import br.edu.opi.manager.school.models.School;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "tb_competitor_table_metadata")
public class CompetitorTableMetadata extends Auditing implements Serializable, Model<Long> {

	private static final long serialVersionUID = -8017746246759554559L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "year", nullable = false)
	private Integer year;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id")
	private School school;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "student_table_metadata_id")
	private List<CompetitorTableRow> rows;

	public CompetitorTableMetadata() {
		super();
		this.rows = new LinkedList<>();
	}

	public CompetitorTableMetadata(int year, School school) {
		this();
		this.year = year;
		this.school = school;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public List<CompetitorTableRow> getRows() {
		return rows;
	}

	public void setRows(List<CompetitorTableRow> rows) {
		this.rows = rows;
	}

	public void addRow(CompetitorTableRow competitorTableRow) {
		this.rows.add(competitorTableRow);
	}

}
