package model.entity;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "MODE")
public class Mode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "NUM_RESOURCES")
	private int numResources;

	@Column(name = "NUM_ENEMY")
	private int numEnemy;

	@Column(name = "TOTAL_AREA")
	private long totalArea;

	public Mode() {
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Mode mode = (Mode) o;
		return id == mode.id && numResources == mode.numResources && numEnemy == mode.numEnemy && totalArea == mode.totalArea && Objects.equals(description, mode.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, description, numResources, numEnemy, totalArea);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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


	public Mode(long id) {
		this.id = id;
		setResourcesAndArea(id);
	}

	public void setResourcesAndArea(long id) {
		if(id == 1) {
			totalArea = 9;
			numResources = 3;
			numEnemy = 3;
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
}