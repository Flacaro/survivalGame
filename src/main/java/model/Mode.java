package model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MODE")
public class Mode {

	@Id
	private long id;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "NUM_RESOURCES")
	private int numResources;

	@Column(name = "NUM_ENEMY")
	private int numEnemy;

	@Column(name = "TOTAL_AREA")
	private long totalArea;

	public Mode(long id) {
		this.id = id;
		setResourcesAndArea(id);
	}

	public void setResourcesAndArea(long id) {
		if(id == 1) {
			totalArea = 9;
			numResources = 4;
		}
		else if(id == 2) {
			totalArea = 18;
			numResources = 8;
		}
		else if(id == 3) {
			totalArea = 36;
			numResources = 12;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumResources() {
		return numResources;
	}

	public void setNumResources(int numResources) {
		this.numResources = numResources;
	}

	public int getNumEnemy() {
		return numEnemy;
	}

	public void setNumEnemy(int numEnemy) {
		this.numEnemy = numEnemy;
	}

	public long getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(long totalArea) {
		this.totalArea = totalArea;
	}
}