package model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CLIMATE")
public class Climate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "TYPE", nullable = false)
	private String type;

	@Column(name = "DAMAGE", nullable = false)
	private double damage;

	@Column(name = "LEVEL", nullable = false)
	private int level = 1;

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