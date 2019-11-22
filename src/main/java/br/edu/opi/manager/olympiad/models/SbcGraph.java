package br.edu.opi.manager.olympiad.models;

import br.edu.opi.manager.project_patterns.models.Model;
import br.edu.opi.manager.project_patterns.models.history.Auditing;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_sbc_graph")
public class SbcGraph extends Auditing implements Serializable, Model<Integer> {

	@Id
	@Column(name = "year")
	private int year;

	@Column(name = "total_gold")
	private int totalGold;

	@Column(name = "total_silver")
	private int totalSilver;

	@Column(name = "total_bronze")
	private int totalBronze;

	@Column(name = "universities")
	private String universities;

	@Column(name = "problems")
	private String problems;

	@Column(name = "placing")
	private String placing;

	public SbcGraph() {
	}

	public SbcGraph(int year, int totalGold, int totalSilver, int totalBronze, String universities, String problems, String placing) {
		this.year = year;
		this.totalGold = totalGold;
		this.totalSilver = totalSilver;
		this.totalBronze = totalBronze;
		this.universities = universities;
		this.problems = problems;
		this.placing = placing;
	}

	@Override
	public Integer getId() {
		return year;
	}

	@Override
	public void setId(Integer id) {
		this.year = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getTotalGold() {
		return totalGold;
	}

	public void setTotalGold(int totalGold) {
		this.totalGold = totalGold;
	}

	public int getTotalSilver() {
		return totalSilver;
	}

	public void setTotalSilver(int totalSilver) {
		this.totalSilver = totalSilver;
	}

	public int getTotalBronze() {
		return totalBronze;
	}

	public void setTotalBronze(int totalBronze) {
		this.totalBronze = totalBronze;
	}

	public String getUniversities() {
		return universities;
	}

	public void setUniversities(String universities) {
		this.universities = universities;
	}

	public String getProblems() {
		return problems;
	}

	public void setProblems(String problems) {
		this.problems = problems;
	}

	public String getPlacing() {
		return placing;
	}

	public void setPlacing(String placing) {
		this.placing = placing;
	}

}
