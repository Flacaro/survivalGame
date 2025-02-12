package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CHECKPOINT")
public class Checkpoint {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "EXP", nullable = false)
	private int exp;

	//relazione uno a uno con area
	@OneToOne(mappedBy = "checkpoint")
	private Area area;

	//test uno a uno con skill
	@OneToOne
	@JoinColumn(name = "id_skill",referencedColumnName = "id")
	private Skill skill;

}