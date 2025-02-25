package model.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

	@Column(name = "ID_AREA")
	private long idArea;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_inventory", referencedColumnName = "id")
	private Inventory inventory;

	//uno a uno con la skill, ma la skill non ne tiene traccia
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Skill> skills= new ArrayList<>();


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getIdArea() {
		return idArea;
	}

	public void setIdArea(long idArea) {
		this.idArea = idArea;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

}