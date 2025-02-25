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

	@Column(name = "CATEGORY")
	private String category;

	//test uno ad uno con checkpoint
	@OneToOne
	@JoinColumn(name = "id_checkpoint",referencedColumnName = "id")
	//nome colonna fk id_checkpoint, usa la colonna id della classe Checkpoint
	private Checkpoint checkpoint;

	//test uno ad uno con climate, però climate non ne tiene traccia
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_climate",referencedColumnName = "id")
	private Climate climate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(long idEvent) {
		this.idEvent = idEvent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Checkpoint getCheckpoint() {
		return checkpoint;
	}

	public void setCheckpoint(Checkpoint checkpoint) {
		this.checkpoint = checkpoint;
	}

	public Climate getClimate() {
		return climate;
	}

	public void setClimate(Climate climate) {
		this.climate = climate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}