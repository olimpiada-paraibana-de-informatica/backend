package br.edu.opi.manager.olympiad.models;

import br.edu.opi.manager.project_patterns.models.Model;
import br.edu.opi.manager.project_patterns.models.history.Auditing;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_obi_graph")
public class ObiGraph extends Auditing implements Serializable, Model<ObiGraphPk> {

	@EmbeddedId
	private ObiGraphPk id;

	@Column(name = "total_gold")
	private int totalGold;

	@Column(name = "total_silver")
	private int totalSilver;

	@Column(name = "total_bronze")
	private int totalBronze;

	@Column(name = "total_honorable_mention")
	private int totalHonorableMention;

	public ObiGraph() {
	}

	public ObiGraph(int year, int totalGold, int totalSilver, int totalBronze, int totalHonorableMention, ObiCategory obiCategory) {
		this.id = new ObiGraphPk(year, obiCategory);
		this.totalGold = totalGold;
		this.totalSilver = totalSilver;
		this.totalBronze = totalBronze;
		this.totalHonorableMention = totalHonorableMention;
	}

	@Override
	public ObiGraphPk getId() {
		return id;
	}

	@Override
	public void setId(ObiGraphPk id) {
		this.id = id;
	}

	public int getYear() {
		return id.getYear();
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

	public int getTotalHonorableMention() {
		return totalHonorableMention;
	}

	public void setTotalHonorableMention(int totalHonorableMention) {
		this.totalHonorableMention = totalHonorableMention;
	}

	public ObiCategory getObiCategory() {
		return id.getObiCategory();
	}

	public synchronized void addTotalGold() {
		this.totalGold++;
	}

	public synchronized void addTotalSilver() {
		this.totalSilver++;
	}

	public synchronized void addTotalBronze() {
		this.totalBronze++;
	}

	public synchronized void addTotalHonorableMention() {
		this.totalHonorableMention++;
	}

}
