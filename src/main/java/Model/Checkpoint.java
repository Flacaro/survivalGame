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

	@Column(name = "AREA_ID", nullable = false)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "AREA",
			joinColumns = @JoinColumn(name = "ID"))
	private long areaId;

	@Column(name = "SKILL_ID", nullable = false)
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SKILL",
			joinColumns = @JoinColumn(name = "ID"))
	private long skillId;

}