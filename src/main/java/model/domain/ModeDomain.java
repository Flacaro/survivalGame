package model.domain;

public class ModeDomain {
    private long id;

    private String description;

    private int numResources;

    private int numEnemy;

    private long totalArea;

    public ModeDomain() {
    }

    public ModeDomain(long id) {
        this.id = id;
        setResourcesAndArea(id);
    }

    public void setResourcesAndArea(long id) {
        if(id == 1) {
            totalArea = 9;
            numResources = 4;
        }
        else if(id == 2) {
            totalArea = 18;
            numResources = 8;
        }
        else if(id == 3) {
            totalArea = 36;
            numResources = 12;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumResources() {
        return numResources;
    }

    public void setNumResources(int numResources) {
        this.numResources = numResources;
    }

    public int getNumEnemy() {
        return numEnemy;
    }

    public void setNumEnemy(int numEnemy) {
        this.numEnemy = numEnemy;
    }

    public long getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(long totalArea) {
        this.totalArea = totalArea;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
