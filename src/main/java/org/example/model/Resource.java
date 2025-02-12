package org.example.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RESOURCE")
public class Resource extends Event {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "CATEGORY", nullable = false)
	private String category;



	//test 1 a molti con attack
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Attack> attacks=new ArrayList<>();

	@Column(name = "LEVEL", nullable = false)
	private int level = 1;

	@Override
	public void updateQuantity(String name, int qnt) {

	}

	@Override
	public void setUp(String type, Mode mode) {

	}
}