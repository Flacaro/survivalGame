package org.example.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "SKILL")
public class Skill implements Serializable {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "LEVEL", nullable = false)
	private int level = 1;

	//test uno a uno con checkpoint
	@OneToOne(mappedBy = "skill")
	private Checkpoint checkpoint;

}