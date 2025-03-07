package persistence;

import jakarta.persistence.EntityManager;
import model.domain.CraftedResourceDomain;
import model.domain.InventoryDomain;
import model.domain.ResourceDomain;
import model.entity.Inventory;
import model.entity.Resource;

import java.util.ArrayList;

public interface InventoryDao {
     InventoryDomain getInventory(long idInventory);
     String getNameResource();
     boolean removeResourcesFromInventory(ArrayList<ResourceDomain> selections);
     boolean updateInventory(ResourceDomain res, InventoryDomain id);

    boolean updateInventoryCraft(CraftedResourceDomain res, InventoryDomain id);
}

