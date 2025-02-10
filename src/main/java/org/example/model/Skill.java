package org.example.model;

import jakarta.persistence.*;

//@Entity
@Table(name = "SKILL")
public class Skill {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "LEVEL", nullable = false)
	private int level = 1;

}