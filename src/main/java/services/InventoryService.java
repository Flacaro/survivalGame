package services;

import model.domain.InventoryDomain;
import model.entity.Inventory;

public class InventoryService {

    private ResourceService rs;

    public Inventory inventoryMapper(InventoryDomain id) {
        Inventory i = new Inventory();
        i.setCapacity(id.getCapacity());
        i.setResources(rs.resourceMapper(id.getResources()));
        return i;
    }
}
