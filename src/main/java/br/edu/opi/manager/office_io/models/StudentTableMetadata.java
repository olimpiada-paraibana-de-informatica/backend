package br.edu.opi.manager.office_io.models;

import br.edu.opi.manager.project_patterns.models.Model;
import br.edu.opi.manager.project_patterns.models.history.Auditing;
import br.edu.opi.manager.school.models.School;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "tb_student_table_metadata")
public class StudentTableMetadata extends Auditing implements Serializable, Model<Long> {

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
	private List<StudentTableRow> rows;

	public StudentTableMetadata() {
		super();
		this.rows = new LinkedList<>();
	}

	public StudentTableMetadata(int year, School school) {
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

	public List<StudentTableRow> getRows() {
		return rows;
	}

	public void setRows(List<StudentTableRow> rows) {
		this.rows = rows;
	}

	public void addRow(StudentTableRow studentTableRow) {
		this.rows.add(studentTableRow);
	}

}
