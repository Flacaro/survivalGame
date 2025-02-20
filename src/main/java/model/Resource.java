package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "RESOURCE")
//@DiscriminatorColumn(name = "event_type", discriminatorType = DiscriminatorType.STRING)
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

	public Resource() {
	}

	public Resource(String category, List<Attack> attacks, int level, String name) {
		this.category = category;
		this.attacks = attacks;
		this.level = level;
		this.name = name;
	}

	@Override
	public void updateQuantity(String name, int qnt) {

	}

	@Override
	public void setUp(String type, Mode mode) {

	}

//	public long getId() {
//		return id;
//	}

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

	@Override
	public String toString() {
		return "Resource{" +
//				"id=" + id +
				", category='" + category + '\'' +
				", level=" + level +
				", name='" + name + '\'' +
				", quantity=" + quantity +
				'}';
	}
}