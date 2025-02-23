package persistence;

import model.domain.InventoryDomain;
import model.domain.ResourceDomain;
import model.entity.Inventory;
import model.entity.Resource;

import java.util.ArrayList;

public interface InventoryDao {
    public InventoryDomain getInventory(long idInventory);
    public String getNameResource();
    public boolean removeResourcesFromInventory(ArrayList<ResourceDomain> selections);
}

