package model.entity;

import controller.DBController;
import jakarta.persistence.*;

@Entity
@Table(name = "RESOURCE_QUANTITY_INV")
public class ResourceQuantityInv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private SimpleResource resource;

    @Column(name = "quantity")
    private int quantity;

    public ResourceQuantityInv() {
    }

    public ResourceQuantityInv(Inventory inventory, SimpleResource resource, int quantity) {
        this.inventory = inventory;
        this.resource = resource;
        this.quantity = quantity;
    }

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

    public SimpleResource getResource() {
        return resource;
    }

    public void setResource(SimpleResource resource) {
        this.resource = resource;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
