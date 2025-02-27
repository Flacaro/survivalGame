package model.domain;


import java.util.ArrayList;
import java.util.List;

public class InventoryDomain {

    private long id;

    private int capacity=10;

    private List<ResourceDomain> resourceDomains;

    private List<ResourceDomain> resourcesSelected;

    private PlayerDomain playerDomain;

    public model.entity.Resource combine(model.entity.Resource[] selections) {
        // TODO - implement Inventory.combine
        throw new UnsupportedOperationException();
    }

    public boolean add(ResourceDomain res) {
        return resourceDomains.add(res);
    }


    public boolean remove(model.entity.Resource[] selections, int[] qnt) {
        // TODO - implement Inventory.remove
        throw new UnsupportedOperationException();
    }

    public boolean checkCapacity() {
        // TODO - implement Inventory.checkCapacity
        throw new UnsupportedOperationException();
    }


    public void addSelections(model.entity.Resource res) {
        // TODO - implement Inventory.addSelections
        throw new UnsupportedOperationException();
    }


    public void useResource(model.entity.Resource resource) {
        // TODO - implement Inventory.useResource
        throw new UnsupportedOperationException();
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

    public List<ResourceDomain> getResourcesSelected() {
        return resourcesSelected;
    }

    public void setResourcesSelected(List<ResourceDomain> resourcesSelected) {
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
}
