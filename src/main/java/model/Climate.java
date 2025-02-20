package model;

import jakarta.persistence.*;

@Entity
@Table(name = "CLIMATE")
public class Climate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "TYPE", nullable = false)
	private String type;

	@Column(name = "DAMAGE", nullable = false)
	private double damage;

	@Column(name = "LEVEL", nullable = false)
	private int level = 1;

}