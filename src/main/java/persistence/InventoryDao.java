package persistence;

import model.domain.CraftedResourceDomain;
import model.domain.InventoryDomain;
import model.domain.ResourceDomain;
import model.entity.Inventory;
import model.entity.Resource;

import java.util.ArrayList;
import java.util.List;

public interface InventoryDao {

    InventoryDomain getInventory(long idInventory);

    String getNameResource();

    boolean removeResourcesFromInventory(ArrayList<ResourceDomain> selections);

    boolean updateInventory(ResourceDomain res, InventoryDomain id);

    InventoryDomain updateInventoryCraft(InventoryDomain id);

    void deleteResourceFromInventory(Resource res, InventoryDomain id);
}

