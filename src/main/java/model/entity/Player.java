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

	@Column(name = "X_AXIS")
	private long x_axis=0;

	@Column(name = "Y_AXIS")
	private long y_axis=0;

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

	public long getY_axis() {
		return y_axis;
	}

	public void setY_axis(long y_axis) {
		this.y_axis = y_axis;
	}

	public long getX_axis() {
		return x_axis;
	}

	public void setX_axis(long x_axis) {
		this.x_axis = x_axis;
	}
}