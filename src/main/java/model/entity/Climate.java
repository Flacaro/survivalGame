package model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CLIMATE")
public class Climate extends Event{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String type;
	private String name;
	private String description;

	@Column(name = "DAMAGE", nullable = false)
	private double damage;

	@Column(name = "LEVEL", nullable = false)
	private int level = 1;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}