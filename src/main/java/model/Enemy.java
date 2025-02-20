package model;

import jakarta.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "ENEMY")
public class Enemy extends Event {

	@Id
	@GeneratedValue
	private Long id;

	//test 1 a molti con attack
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private ArrayList<Attack> attacks= new ArrayList<>();

	@Column(name = "LEVEL", nullable = false)
	private int level = 1;

	private String name;

	private String description;

	public Enemy() {
	}

	public Enemy(ArrayList<Attack> attacks, int level, String name) {
		this.attacks = attacks;
		this.level = level;
		this.name = name;
	}

	public ArrayList<Attack> getAttacks() {
		return attacks;
	}

	public void setAttacks(ArrayList<Attack> attacks) {
		this.attacks = attacks;
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
//
//	public long getId() {
//		return id;
//	}

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