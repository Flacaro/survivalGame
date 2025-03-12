package persistence;

import jakarta.persistence.EntityManager;
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

    boolean updateInventoryCraft(CraftedResourceDomain res, List<ResourceDomain> list, InventoryDomain id);
}

