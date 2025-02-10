package org.example.model;

import jakarta.persistence.*;
import java.util.ArrayList;

//@Entity
@Table(name = "PLAYER")
public class Player {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "NICKNAME", nullable = false)
	private String nickname;

	@Column(name = "HEALTH", nullable = false)
	private float health;

	@Column(name = "LEVEL", nullable = false)
	private int level = 1;

	@Column(name = "POSITION", nullable = false)
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinTable(name = "MAP",
			joinColumns = @JoinColumn(name = "PLAYER_POSITION"))
	private long position;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinTable(name = "INVENTORY",
			joinColumns = @JoinColumn(name = "ID"))
	private long inventoryId;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SKILL",
			joinColumns = @JoinColumn(name = "ID"))
	private ArrayList<Long> skillIds;


	public boolean pickUp(Resource res) {
		// TODO - implement Player.pickUp
		throw new UnsupportedOperationException();
	}


	public boolean attack(Enemy enemy, Resource res) {
		// TODO - implement Player.attack
		throw new UnsupportedOperationException();
	}


	public void move(int position) {
		// TODO - implement Player.move
		throw new UnsupportedOperationException();
	}


	public void openInventory(Inventory inv) {
		// TODO - implement Player.openInventory
		throw new UnsupportedOperationException();
	}

	public boolean combineResources() {
		// TODO - implement Player.combineResources
		throw new UnsupportedOperationException();
	}


	public void useSkill(String skill) {
		// TODO - implement Player.useSkill
		throw new UnsupportedOperationException();
	}


	public void setPosition(Area[] adjacentArea) {
		// TODO - implement Player.setposition
		throw new UnsupportedOperationException();
	}

}