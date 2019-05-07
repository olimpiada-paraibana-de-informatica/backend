package br.edu.opi.manager.excel_io.models;

import br.edu.opi.manager.person.models.Genre;
import br.edu.opi.manager.project_patterns.models.Model;
import br.edu.opi.manager.project_patterns.models.history.Auditing;
import br.edu.opi.manager.school.models.Grade;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tb_competitor_table_row")
public class CompetitorTableRow extends Auditing implements Serializable, Model<Long> {

	private static final long serialVersionUID = 3030331999726512470L;

	public static final double MISSED_STUDENT = -1D;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "date_birth", nullable = false)
	private LocalDate dateBirth;

	@Column(name = "genre", nullable = false)
	private Genre genre;

	@Column(name = "grade", nullable = false)
	private Grade grade;

	@Column(name = "score", nullable = false)
	private Double score;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_table_metadata_id")
	private CompetitorTableMetadata competitorTableMetadata;

	public CompetitorTableRow() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(LocalDate dateBirth) {
		this.dateBirth = dateBirth;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public CompetitorTableMetadata getCompetitorTableMetadata() {
		return competitorTableMetadata;
	}

	public void setCompetitorTableMetadata(CompetitorTableMetadata competitorTableMetadata) {
		this.competitorTableMetadata = competitorTableMetadata;
	}

	@Override
	public String toString() {
		return "name='" + name + '\'' +
				", dateBirth='" + dateBirth + '\'' +
				", genre='" + genre + '\'' +
				", grade='" + grade + '\'' +
				", score='" + score + '\'';
	}

}
