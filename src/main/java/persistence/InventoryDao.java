package persistence;
import jakarta.persistence.EntityManager;
import model.entity.Inventory;
import model.entity.Resource;
import java.util.ArrayList;

public interface InventoryDao {

    Inventory getInventory(long idInventory, EntityManager em);

    String getNameResource();

    boolean removeResourcesFromInventory(ArrayList<Resource> selections,EntityManager em);

    boolean updateInventory(Resource res, Inventory id,EntityManager em);

    Inventory updateInventoryCraft(Inventory id,EntityManager em);

    void deleteResourceFromInventory(Resource res, Inventory id,EntityManager em);
}

