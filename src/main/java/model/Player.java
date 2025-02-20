package model;

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

	@Column(name = "POSITION")
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

	public Player(String nickname, float health, int level,Game game) {
		this.nickname = nickname;
		this.health = health;
		this.level = level;
		this.game = game;
	}

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

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

	public Map getMap() {
		return map;
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

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setPosition(Area position) {
		this.position=position.getId();
	}

	public void setMap(Map map) {
		this.map = map;
	}
}