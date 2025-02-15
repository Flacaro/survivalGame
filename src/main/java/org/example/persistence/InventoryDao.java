package org.example.persistence;

import org.example.model.Inventory;
import org.example.model.Resource;

import java.util.ArrayList;

public interface InventoryDao {
    public Inventory getInventory(long idInventory);
    public String getNameResource();
    public boolean removeResourcesFromInventory(ArrayList<Resource> selections);
}

