package Model;

import javax.persistence.*;

@Entity
@Table(name = "MODE")
public class Mode {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "NUM_RESOURCES", nullable = false)
	private int numResources;

	@Column(name = "NUM_ENEMY", nullable = false)
	private int numEnemy;

}