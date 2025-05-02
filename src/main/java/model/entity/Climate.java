package model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CLIMATE")
@IdClass(Event.class)
public class Climate extends Event{

	@Column(name = "DAMAGE", nullable = false)
	private double damage;


	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}
}