package persistence;

import model.Inventory;
import model.Resource;

import java.util.ArrayList;

public interface InventoryDao {
    public Inventory getInventory(long idInventory);
    public String getNameResource();
    public boolean removeResourcesFromInventory(ArrayList<Resource> selections);
}

