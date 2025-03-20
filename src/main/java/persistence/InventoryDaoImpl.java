package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.domain.CraftedResourceDomain;
import model.domain.InventoryDomain;
import model.domain.ResourceDomain;
import model.entity.CraftedResource;
import model.entity.Inventory;
import model.entity.Resource;
import model.entity.ResourceQuantityInv;
import services.CraftedResourceService;
import services.InventoryService;
import services.ResourceService;

import java.util.ArrayList;
import java.util.List;

public class InventoryDaoImpl implements InventoryDao {


    @Override
    public InventoryDomain getInventory(long idInventory) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
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

    @Override
    public boolean updateInventory(ResourceDomain res, InventoryDomain id) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }

            ResourceService rs = new ResourceService();
            Inventory inventory = em.find(Inventory.class, id.getId());
            List<ResourceQuantityInv> resourcesQuantity = inventory.getResources_quantity();
            if (inventory != null) {
                inventory.setCapacity(id.getCapacity() - 1);

                boolean resourceFound = false;
                for (Resource resource : inventory.getResources()) {
                    for (ResourceQuantityInv rqi :resourcesQuantity){
                    if (resource.getId() == res.getId()) {
                        if (resource.getId() == rqi.getResource().getId()) {
                           rqi.setQuantity(rqi.getQuantity()+1);
                        resourceFound = true;
                        break;
////                        } else {
//                        resourcesQuantity.add((int)resource.getId(),resourcesQuantity.get((int)resource.getId())+1);
//                            //resource.setQuantity(resource.getQuantity() + 1);
////                        }
//                        resourceFound = true;
//                        break;
                    }
                    }
                }}
                if (!resourceFound) {
                    Resource newResource = new Resource(res.getId(), res.getCategory(), rs.resourceMapper(res).getAttacks(), res.getLevel(), res.getName(), res.getQuantity(), res.getType());
                    List<Resource> inventoryResources = inventory.getResources();
                    inventoryResources.add(newResource);
                    resourcesQuantity.add(new ResourceQuantityInv(inventory,newResource,1));
                }
                em.merge(inventory);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }


    @Override
    public void updateInventoryCraft(InventoryDomain id) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            Inventory inventory = em.find(Inventory.class, id.getId()); // Trova l'oggetto con ID 1
            if (inventory != null) {
                ResourceService rs=new ResourceService();
                ArrayList<Resource> list=new ArrayList<>();
                for (ResourceDomain r :id.getResources()){
                    list.add(rs.resourceMapper(r));
                }
                inventory.getResources().clear();
                inventory.getResources().addAll(list);

                CraftedResourceService crs= new CraftedResourceService();
                ArrayList<CraftedResource> lists=new ArrayList<>();
                for (CraftedResourceDomain r :id.getCraftedResourceDomainList()){
                    lists.add(crs.craftedResourceMapper(r));
                }
                inventory.getCraftedResourceList().clear();
                inventory.getCraftedResourceList().addAll(lists);
            }
            em.merge(inventory);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }


    }

    @Override
    public void deleteResourceFromInventory(Resource res, InventoryDomain id) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            TypedQuery<Inventory> query = em.createQuery("DELETE c FROM Inventory c WHERE c.resource_id= :resourceId", Inventory.class);
            query.setParameter("resourceId", res.getId());

        } catch (
                Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
}
