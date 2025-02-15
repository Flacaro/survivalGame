package org.example.model;

import jakarta.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "ENEMY")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "event_type", discriminatorType = DiscriminatorType.STRING)
public class Enemy extends Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;


	//test 1 a molti con attack
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private ArrayList<Attack> attacks= new ArrayList<>();

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "LEVEL", nullable = false)
	private int level = 1;

	private String name;

	private String type;

	public Enemy() {
	}

	public Enemy(ArrayList<Attack> attacks, String description, int level, String name, String type) {
		this.attacks = attacks;
		this.description = description;
		this.level = level;
		this.name = name;
		this.type = type;
	}

	public ArrayList<Attack> getAttacks() {
		return attacks;
	}

	public void setAttacks(ArrayList<Attack> attacks) {
		this.attacks = attacks;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	@Override
	public void updateQuantity(String name, int qnt) {

	}

	@Override
	public void setUp(String type, Mode mode) {

	}
//
//	@Override
//	public String getName() {
//		return "";
//	}
//
//	@Override
//	public String getType() {
//		return "";
//	}
//
//	@Override
//	public String getDescription() {
//		return "";
//	}
}