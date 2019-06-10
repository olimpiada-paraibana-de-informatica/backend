package br.edu.opi.manager.competitor.models;

import br.edu.opi.manager.competitor.exceptions.StudentOrSchoolNotNullRuntimeException;
import br.edu.opi.manager.competitor.services.CompetitorService;
import br.edu.opi.manager.olympiad.models.OpiAward;
import br.edu.opi.manager.olympiad.models.OpiCategory;
import br.edu.opi.manager.olympiad.models.OpiLevels;
import br.edu.opi.manager.project_patterns.models.Model;
import br.edu.opi.manager.project_patterns.models.history.Auditing;
import br.edu.opi.manager.school.models.Grade;
import br.edu.opi.manager.student.models.Student;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_competitor")
@EntityListeners(CompetitorListener.class)
public class Competitor extends Auditing implements Serializable, Model<Long> {

	private static final long serialVersionUID = -9051052759732137812L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", updatable = false)
	private Student student;

	@Enumerated(EnumType.STRING)
	@Column(name = "grade", nullable = false)
	private Grade grade;

	@Enumerated(EnumType.STRING)
	@Column(name = "category", nullable = false)
	private OpiCategory category;

	@Column(name = "score_level_one")
	private Double scoreLevelOne;

	@Column(name = "score_level_two")
	private Double scoreLevelTwo;

	@Transient
	private Double finalScore;

	@Column(name = "award")
	private OpiAward award = OpiAward.PARTICIPATION;

	@Enumerated(EnumType.STRING)
	@Column(name = "competitor_level", nullable = false)
	private OpiLevels level = OpiLevels.ONE;

	@Column(name = "year", nullable = false)
	private Integer year; // TODO: change to competition after talking with Rohit about

	public Competitor() {
		this.year = Year.now().getValue();
	}

	public Competitor(Student student, Grade grade, Double scoreLevelOne) {
		this();
		this.student = student;
		this.grade = grade;
		this.scoreLevelOne = scoreLevelOne;
	}

	public Competitor(Long id) {
		this();
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public OpiCategory getCategory() {
		return category;
	}

	public void setCategory(OpiCategory category) {
		this.category = category;
	}

	public Double getScoreLevelOne() {
		return scoreLevelOne;
	}

	public void setScoreLevelOne(Double scoreLevelOne) {
		this.scoreLevelOne = scoreLevelOne;
	}

	public Double getScoreLevelTwo() {
		return scoreLevelTwo;
	}

	public void setScoreLevelTwo(Double scoreLevelTwo) {
		this.scoreLevelTwo = scoreLevelTwo;
	}

	public OpiAward getAward() {
		return award;
	}

	public void setAward(OpiAward award) {
		this.award = award;
	}

	public OpiLevels getLevel() {
		return level;
	}

	public void setLevel(OpiLevels level) {
		this.level = level;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Competitor that = (Competitor) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public boolean isSchoolPublic() {
		if (student == null || student.getSchool() == null) {
			throw new StudentOrSchoolNotNullRuntimeException();
		}
		return student.getSchool().isPublic();
	}

	public boolean downLevelOne() {
		setLevel(OpiLevels.ONE);
		return true;
	}

	public boolean upLevelTwo() {
		setLevel(OpiLevels.TWO);
		return true;
	}

	public String getFullName() {
		return student.getFullName();
	}

	public Date getDateBirth() {
		LocalDate dateBirth = student.getDateBirth();
		return Date.from(dateBirth.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public String getGenre() {
		return student.getGenre().getName();
	}

	public Double getFinalScore() {
		return CompetitorService.calculateFinalScore(this);
	}

}
