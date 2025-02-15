package org.example.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RESOURCE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "event_type", discriminatorType = DiscriminatorType.STRING)
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
	@Column(name = "TYPE", nullable = false)
	private String type;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "QUANTITY", nullable = false)
	private int quantity;

	public Resource() {
	}

	public Resource(String category, List<Attack> attacks, int level, String name, String type) {
		this.category = category;
		this.attacks = attacks;
		this.level = level;
		this.name = name;
		this.type = type;
	}

	@Override
	public void updateQuantity(String name, int qnt) {

	}

	@Override
	public void setUp(String type, Mode mode) {

	}

	public long getId() {
		return id;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
//
//	public String getCategory() {
//		return category;
//	}
//
//	public List<Attack> getAttacks() {
//		return attacks;
//	}
//
//	public int getLevel() {
//		return level;
//	}

	@Override
	public String toString() {
		return "Resource{" +
				"id=" + id +
				", category='" + category + '\'' +
				", level=" + level +
				", name='" + name + '\'' +
				", type='" + type + '\'' +
				", description='" + description + '\'' +
				", quantity=" + quantity +
				'}';
	}
}