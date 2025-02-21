package model.domain;


import java.util.Objects;

public class AreaDomain {

    private long id;

    private String name;

    private long idEvent;

    private String description;

    private CheckpointDomain checkpoint;

    private ClimateDomain climate;


    public AreaDomain() {
    }

    public AreaDomain(String name, String description) {
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


    public CheckpointDomain getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(CheckpointDomain checkpoint) {
        this.checkpoint = checkpoint;
    }

    public ClimateDomain getClimate() {
        return climate;
    }

    public void setClimate(ClimateDomain climate) {
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
        AreaDomain areaDomain = (AreaDomain) o;
        return id == areaDomain.id && idEvent == areaDomain.idEvent && Objects.equals(name, areaDomain.name) && Objects.equals(description, areaDomain.description) && Objects.equals(checkpoint, areaDomain.checkpoint) && Objects.equals(climate, areaDomain.climate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, idEvent, description, checkpoint, climate);
    }
}
