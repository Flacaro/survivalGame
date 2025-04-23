package model.entity;

import controller.DBController;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

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
	private double health;

	@Column(name = "LEVEL", nullable = false)
	private int level = 1;

	@Column(name = "exp", nullable = true)
	private int exp = 0;

	@Column(name = "ID_AREA")
	@ColumnDefault("1")
	private long idArea;

	@Column(name = "X_AXIS")
	private int x_axis=0;

	@Column(name = "Y_AXIS")
	private int y_axis=0;

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

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
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

	public int getY_axis() {
		return y_axis;
	}

	public void setY_axis(int y_axis) {
		this.y_axis = y_axis;
	}

	public int getX_axis() {
		return x_axis;
	}

	public void setX_axis(int x_axis) {
		this.x_axis = x_axis;
	}


	public long setNewIdAreaxVariant(int x_axis, Game g) {
		List<Area> areaxVariant= new ArrayList<>();
		List<Area> areas=g.getMap().getAreas();
		int range= (int) (g.getMode().getTotalArea()/2);
		areaxVariant=areas.subList(0,range);
		return areaxVariant.get(x_axis).getId();
	}

	public long setNewIdAreayVariant(int y_axis, Game g) {
		List<Area> areayVariant= new ArrayList<>();
		List<Area> areas=g.getMap().getAreas();
		int range= (int) (g.getMode().getTotalArea()/2);
		areayVariant=areas.subList(range+1,areas.size()-1);
		return areayVariant.get(y_axis).getId();
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
}