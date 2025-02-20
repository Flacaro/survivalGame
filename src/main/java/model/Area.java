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

//	@Column(name = "EVENT", nullable = false)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "event_id")
	private Event event;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	//test composizione
	@ManyToOne
	@JoinColumn(name = "id",insertable=false, updatable=false)
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
		this.map=map;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public long getId() {
		return id;
	}
}