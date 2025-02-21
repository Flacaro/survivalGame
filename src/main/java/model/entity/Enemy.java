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

	@Override
	public void updateQuantity(String name, int qnt) {

	}

	@Override
	public void setUp(String type, Mode mode) {

	}
}