package org.example.model;

import jakarta.persistence.*;
import java.util.ArrayList;

//@Entity
@Table(name = "ENEMY")
public class Enemy extends Event {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "ATTACK_IDS", nullable = false)
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ATTACK",
			joinColumns = @JoinColumn(name = "ID"))
	private ArrayList<Long> attackIds;

	@Column(name = "TYPE", nullable = false)
	private String type;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "LEVEL", nullable = false)
	private int level = 1;

	@Override
	public void updateQuantity(String name, int qnt) {

	}

	@Override
	public void setUp(String type, Mode mode) {

	}
}