package Model;

import javax.persistence.*;

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

}