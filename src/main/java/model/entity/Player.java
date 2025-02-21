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

	@Column(name = "POSITION")
	private long position;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_inventory", referencedColumnName = "id")
	private Inventory inventory;

	//uno a uno con la skill, ma la skill non ne tiene traccia
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Skill> skills= new ArrayList<>();


}