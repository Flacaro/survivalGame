package model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "AREA")
public class Area implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "ID_EVENT", nullable = false)
	private long idEvent;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	//test uno ad uno con checkpoint
	@OneToOne
	@JoinColumn(name = "id_checkpoint",referencedColumnName = "id")
	//nome colonna fk id_checkpoint, usa la colonna id della classe Checkpoint
	private Checkpoint checkpoint;

	//test uno ad uno con climate, però climate non ne tiene traccia
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_climate",referencedColumnName = "id")
	private Climate climate;


}