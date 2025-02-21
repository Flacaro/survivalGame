package persistence;

import model.entity.Inventory;
import model.entity.Resource;

import java.util.ArrayList;

public interface InventoryDao {
    public Inventory getInventory(long idInventory);
    public String getNameResource();
    public boolean removeResourcesFromInventory(ArrayList<Resource> selections);
}

