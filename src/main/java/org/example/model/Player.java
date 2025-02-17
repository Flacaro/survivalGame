package org.example.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PLAYER")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "NICKNAME", nullable = false)
	private String nickname;

	@Column(name = "HEALTH", nullable = false)
	private float health;

	@Column(name = "LEVEL", nullable = false)
	private int level = 1;

	@Column(name = "POSITION", nullable = false)
	private long position;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_map", referencedColumnName = "id")
	private Map map;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_inventory", referencedColumnName = "id")
	private Inventory inventory;


	//uno a uno con la skill, ma la skill non ne tiene traccia
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Skill> skills= new ArrayList<>();

	//uno ad uno con il game
	@OneToOne(mappedBy = "player")
	private Game game;


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