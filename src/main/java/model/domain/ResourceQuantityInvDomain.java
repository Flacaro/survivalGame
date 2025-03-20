package model.domain;

import model.entity.Inventory;
import model.entity.Resource;

import java.util.Objects;

public class ResourceQuantityInvDomain {

    private Long id;

    private Inventory inventory;

    private Resource resource;

    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ResourceQuantityInvDomain that = (ResourceQuantityInvDomain) o;
        return quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(inventory, that.inventory) && Objects.equals(resource, that.resource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, inventory, resource, quantity);
    }
}
