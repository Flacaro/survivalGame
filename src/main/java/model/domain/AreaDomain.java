package model.domain;


import java.util.Objects;

public class AreaDomain {

    private long id;

    private String name;

    private long idEvent;

    private String description;

    private CheckpointDomain checkpoint;

    private ClimateDomain climate;

    private String category;


    public AreaDomain() {
    }

    public AreaDomain(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AreaDomain that = (AreaDomain) o;
        return id == that.id && idEvent == that.idEvent && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(checkpoint, that.checkpoint) && Objects.equals(climate, that.climate) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, idEvent, description, checkpoint, climate, category);
    }
}
