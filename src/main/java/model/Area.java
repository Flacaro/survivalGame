package model;

import jakarta.persistence.*;

import java.io.Serializable;

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

	//test composizione
	@ManyToOne
	@JoinColumn(name = "map_id",insertable=false, updatable=false,referencedColumnName = "id")
	private Map map;

	//test uno ad uno con checkpoint
	@OneToOne
	@JoinColumn(name = "id_checkpoint",referencedColumnName = "id")
	//nome colonna fk id_checkpoint, usa la colonna id della classe Checkpoint
	private Checkpoint checkpoint;

	//test uno ad uno con climate, però climate non ne tiene traccia
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_climate",referencedColumnName = "id")
	private Climate climate;

	public Area(String name, String description, Map map) {
		this.name = name;
		this.description = description;
		this.map = map;
	}

	public long getEvent() {
		return this.idEvent;
	}

	public void setEvent(long event) {
		this.idEvent = event;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
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
}