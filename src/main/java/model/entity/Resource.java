package model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "RESOURCE")
public class Resource extends Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "CATEGORY", nullable = false)
	private String category;

	//test 1 a molti con attack
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Attack> attacks;

	@Column(name = "LEVEL", nullable = false)
	private int level = 1;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "QUANTITY", nullable = false)
	private int quantity;

	@Column(name = "TYPE", nullable = false)
	private String type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Attack> getAttacks() {
		return attacks;
	}

	public void setAttacks(List<Attack> attacks) {
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}