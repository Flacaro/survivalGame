package persistence;
import jakarta.persistence.EntityManager;
import model.entity.Inventory;
import model.entity.Resource;
import model.entity.SimpleResource;

import java.util.ArrayList;

public interface InventoryDao {

    Inventory getInventory(long idInventory, EntityManager em);

    String getNameResource();

    boolean removeResourcesFromInventory(ArrayList<SimpleResource> selections, EntityManager em);

    boolean updateInventory(SimpleResource res, Inventory id,EntityManager em);

    Inventory updateInventoryCraft(Inventory id,EntityManager em);

    void deleteResourceFromInventory(SimpleResource res, Inventory id,EntityManager em);
}

