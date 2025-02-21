package model.entity;

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

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "TYPE", nullable = false)
	private String type;

	public Enemy() {
	}

	public Enemy(ArrayList<Attack> attacks, int level, String name) {
		this.attacks = attacks;
		this.level = level;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}