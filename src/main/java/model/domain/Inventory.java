package model.domain;


import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private long id;

    private int capacity;

    private List<Resource> resources;

    private List<Resource> resourcesSelected;

    private Player player;

    public model.entity.Resource combine(model.entity.Resource[] selections) {
        // TODO - implement Inventory.combine
        throw new UnsupportedOperationException();
    }

    public boolean add(model.entity.Resource res) {
        // TODO - implement Inventory.add
        throw new UnsupportedOperationException();
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

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Resource> getResourcesSelected() {
        return resourcesSelected;
    }

    public void setResourcesSelected(ArrayList<Resource> resourcesSelected) {
        this.resourcesSelected = resourcesSelected;
    }
}
