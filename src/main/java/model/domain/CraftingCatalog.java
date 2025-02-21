package model.domain;

import model.entity.CraftedResource;
import model.entity.Resource;

import java.util.ArrayList;
import java.util.List;

public class CraftingCatalog {

    private long id;

    private List<model.entity.Resource> resourcesToCraft = new ArrayList<>();

    private model.entity.CraftedResource finalResource;

    public long getId() {
        return id;
    }

    public CraftingCatalog() {
    }

    public ArrayList<model.entity.Resource> getResourcesToCraft() {
        return (ArrayList<model.entity.Resource>) resourcesToCraft;
    }

    public void setResourcesToCraft(ArrayList<Resource> resourcesToCraft) {
        this.resourcesToCraft = resourcesToCraft;
    }

    public model.entity.CraftedResource getFinalResource() {
        return finalResource;
    }

    public void setFinalResource(CraftedResource finalResource) {
        this.finalResource = finalResource;
    }
}
