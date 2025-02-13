package org.example.model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "CRAFTING_CATALOG")
public class CraftingCatalog {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "ID_RESOURCES_TO_CRAFT", nullable = false)
    private ArrayList<Long> resourcesIdsToCraft;

    @Column(name = "FINAL_RESOURCE", nullable = false)
    private Long finalResource;

    public long getId() {
        return id;
    }

    public ArrayList<Long> getResourcesIdsToCraft() {
        return resourcesIdsToCraft;
    }

    public void setResourcesIdsToCraft(ArrayList<Long> resourcesIdsToCraft) {
        this.resourcesIdsToCraft = resourcesIdsToCraft;
    }

    public Long getFinalResource() {
        return finalResource;
    }

    public void setFinalResource(Long finalResource) {
        this.finalResource = finalResource;
    }

    @Override
    public String toString() {
        return "CraftingCatalog{" +
                "id=" + id +
                ", resourcesIdsToCraft=" + resourcesIdsToCraft +
                ", finalResource=" + finalResource +
                '}';
    }

    public boolean checkCompatibility(ArrayList<Long> selections, ArrayList<Long> resourcesIdsToCraft) {
        return resourcesIdsToCraft.equals(selections);
    }

}