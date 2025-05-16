package model.entity;

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

    @OneToOne(optional = true)
    @JoinColumn(name = "event_id")
    private Event idEvent;

    @Column(name = "type")
    private String type;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "CATEGORY")
    private String category;

    @OneToOne
    @JoinColumn(name = "id_checkpoint", referencedColumnName = "id")
    private Checkpoint checkpoint;

    public Area(Event idEvent) {
        this.idEvent = idEvent;
    }

//	//test uno ad uno con climate, però climate non ne tiene traccia
//	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name = "id_climate",referencedColumnName = "id")
//	private Climate climate;

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

    public Event getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Event idEvent) {
        this.idEvent = idEvent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public Area() {
    }
}