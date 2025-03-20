package model.domain;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import model.entity.CraftedResource;
import model.entity.Resource;
import model.entity.ResourceQuantityInv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InventoryDomain {

    private long id;

    private int capacity=10;

    private List<ResourceDomain> resourceDomains;

    private List<CraftedResourceDomain> resourcesSelected;

    private List<ResourceQuantityInvDomain> resources_quantity= new ArrayList<>();;

    private PlayerDomain playerDomain;

    public List<ResourceQuantityInvDomain> getResources_quantity() {
        return resources_quantity;
    }

    public void setResources_quantity(List<ResourceQuantityInvDomain> resources_quantity) {
        this.resources_quantity = resources_quantity;
    }

    public boolean add(ResourceDomain res) {
        return resourceDomains.add(res);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<ResourceDomain> getResources() {
        return resourceDomains;
    }

    public void setResources(List<ResourceDomain> resourceDomains) {
        this.resourceDomains = resourceDomains;
    }

    public PlayerDomain getPlayer() {
        return playerDomain;
    }

    public void setPlayer(PlayerDomain playerDomain) {
        this.playerDomain = playerDomain;
    }

    public List<CraftedResourceDomain> getResourcesSelected() {
        return resourcesSelected;
    }

    public void setResourcesSelected(List<CraftedResourceDomain> resourcesSelected) {
        this.resourcesSelected = resourcesSelected;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ResourceDomain> getResourceDomains() {
        return resourceDomains;
    }

    public void setResourceDomains(List<ResourceDomain> resourceDomains) {
        this.resourceDomains = resourceDomains;
    }

    public PlayerDomain getPlayerDomain() {
        return playerDomain;
    }

    public void setPlayerDomain(PlayerDomain playerDomain) {
        this.playerDomain = playerDomain;
    }

    @Override
    public String toString() {
        return "InventoryDomain{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", resourceDomains=" + resourceDomains +
                ", resourcesSelected=" + resourcesSelected +
                ", playerDomain=" + playerDomain +
                '}';
    }
}
