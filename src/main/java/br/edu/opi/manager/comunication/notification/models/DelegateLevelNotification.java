package br.edu.opi.manager.comunication.notification.models;

import br.edu.opi.manager.delegate.models.Delegate;
import br.edu.opi.manager.project_patterns.models.Model;
import br.edu.opi.manager.project_patterns.models.history.Auditing;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_delegate_level_notification")
@EntityListeners(DelegateLevelNotificationListener.class)
public class DelegateLevelNotification extends Auditing implements Serializable, Model<Long> {

	private static final long serialVersionUID = -881039449598159569L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delegate_id", nullable = false)
	private Delegate delegate;

	@Column(name = "year", nullable = false)
	private Integer year; // TODO: change to competition after talking with Rohit about

	@Column(name = "level_one")
	private Boolean levelOne;

	@Column(name = "level_two")
	private Boolean levelTwo;

	@Transient
	private String schoolName;

	public DelegateLevelNotification(Delegate delegate, Integer year, Boolean levelOne, Boolean levelTwo, String schoolName) {
		this.delegate = delegate;
		this.year = year;
		this.levelOne = levelOne;
		this.levelTwo = levelTwo;
		this.schoolName = schoolName;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Delegate getDelegate() {
		return delegate;
	}

	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Boolean getLevelOne() {
		return levelOne;
	}

	public void setLevelOne(Boolean levelOne) {
		this.levelOne = levelOne;
	}

	public Boolean getLevelTwo() {
		return levelTwo;
	}

	public void setLevelTwo(Boolean levelTwo) {
		this.levelTwo = levelTwo;
	}

	public String getSchoolName() {
		return this.schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

}
