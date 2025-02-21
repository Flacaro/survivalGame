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


	@Override
	public void updateQuantity(String name, int qnt) {

	}

	@Override
	public void setUp(String type, Mode mode) {

	}
}