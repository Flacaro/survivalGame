package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.domain.AreaDomain;
import model.domain.InventoryDomain;
import model.domain.ResourceDomain;
import model.entity.Area;
import model.entity.Inventory;
import services.InventoryService;

import java.util.ArrayList;

public class InventoryDaoImpl implements InventoryDao {

    private final InventoryService inventoryService = new InventoryService();


    @Override
    public InventoryDomain getInventory(long idInventory) {
        EntityManager em = EntityManagerSingleton.getEntityManager();

        Inventory inventory;
        try {
            // Query per ottenere l'inventory selezionando l'id
            InventoryService inventoryService = new InventoryService();
            TypedQuery<Inventory> query = em.createQuery("SELECT c FROM Inventory c WHERE c.id= :idInventory", Inventory.class);
            query.setParameter("idInventory", idInventory);
            return inventoryService.inventoryDomainMapper(query.getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return null;
    }

    @Override
    public String getNameResource() {
        return "";
    }

    @Override
    public boolean removeResourcesFromInventory(ArrayList<ResourceDomain> selections) {

        return false;
    }

    public void updateInventory(InventoryDomain id, EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            Inventory inventory = em.find(Inventory.class, id.getId()); // Trova l'oggetto con ID 1
            if (inventory != null) {
                inventory.setResources(inventoryService.inventoryMapper(id).getResources());
            }

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
}
