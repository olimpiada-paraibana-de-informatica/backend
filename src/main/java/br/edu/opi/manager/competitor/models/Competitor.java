package br.edu.opi.manager.competitor.models;

import br.edu.opi.manager.olympiad.models.OpiCategory;
import br.edu.opi.manager.project_patterns.models.Model;
import br.edu.opi.manager.project_patterns.models.history.Auditing;
import br.edu.opi.manager.school.models.Grade;
import br.edu.opi.manager.student.models.Student;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_competitor")
public class Competitor extends Auditing implements Serializable, Model<Long> {

	private static final long serialVersionUID = -9051052759732137812L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", updatable = false)
	private Student student;

//	@ElementCollection(targetClass = Grade.class)
	@Enumerated(EnumType.STRING)
	@Column(name = "grade", nullable = false)
//	@CollectionTable(name = "tb_competitor_grade", joinColumns = @JoinColumn(name = "grade_id"))
	private Grade grade;

//	@ElementCollection(targetClass = OpiCategory.class)
	@Enumerated(EnumType.STRING)
	@Column(name = "category", nullable = false)
//	@CollectionTable(name = "tb_competitor_category", joinColumns = @JoinColumn(name = "competitor_id"))
	private OpiCategory category;

	public Competitor() {

	}

	public Competitor(Long id){
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
}
