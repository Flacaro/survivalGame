package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.domain.AreaDomain;
import model.domain.InventoryDomain;
import model.domain.ResourceDomain;
import model.entity.Area;
import model.entity.Inventory;
import model.entity.Resource;
import services.AttackServices;
import services.InventoryService;
import services.ResourceService;

import java.util.ArrayList;
import java.util.List;

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

    public void updateInventory(ResourceDomain res, InventoryDomain id, EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }

            ResourceService rs = new ResourceService();
            Inventory inventory = em.find(Inventory.class, id.getId());
            if (inventory != null) {
                inventory.setCapacity(id.getCapacity() - 1);

                boolean resourceFound = false;
                for (Resource resource : inventory.getResources()) {
                    if (resource.getId() == res.getId()) {
                        resource.setQuantity(resource.getQuantity() - 1);
                        resourceFound = true;
                        break;
                    }
                }

                if (!resourceFound) {
                    Resource newResource = new Resource(res.getCategory(), rs.resourceMapper(res).getAttacks(), res.getLevel(), res.getName(), res.getQuantity(), res.getType());
                    List<Resource> inventoryResources = inventory.getResources();
                    inventoryResources.add(newResource);
                }

                em.merge(inventory);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
}
