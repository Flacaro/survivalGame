package model.domain;

import model.entity.Checkpoint;
import model.entity.Climate;

import java.util.Objects;

public class Area {

    private long id;

    private String name;

    private long idEvent;

    private String description;

    private Checkpoint checkpoint;

    private Climate climate;


    public Area() {
    }

    public Area(String name, String description) {
        this.name = name;
        this.description = description;
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

    public void setId(long id) {
        this.id = id;
    }

    public long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(long idEvent) {
        this.idEvent = idEvent;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Area area = (Area) o;
        return id == area.id && idEvent == area.idEvent && Objects.equals(name, area.name) && Objects.equals(description, area.description) && Objects.equals(checkpoint, area.checkpoint) && Objects.equals(climate, area.climate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, idEvent, description, checkpoint, climate);
    }
}
