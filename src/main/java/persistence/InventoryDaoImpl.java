package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.entity.CraftedResource;
import model.entity.Inventory;
import model.entity.ResourceQuantityInv;
import model.entity.SimpleResource;
import persistence.dao.InventoryDao;

import java.util.ArrayList;
import java.util.List;

public class InventoryDaoImpl implements InventoryDao {


    @Override
    public Inventory getInventory(long idInventory,EntityManager em) {
        try {
            // Query per ottenere l'inventory selezionando l'id
            TypedQuery<Inventory> query = em.createQuery("SELECT c FROM Inventory c WHERE c.id= :idInventory", Inventory.class);
            query.setParameter("idInventory", idInventory);
            return query.getSingleResult();
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
    public boolean removeResourcesFromInventory(ArrayList<SimpleResource> selections, EntityManager em) {
        return false;
    }

    @Override
    public boolean updateInventory(SimpleResource res, Inventory id,EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }

            Inventory inventory = em.find(Inventory.class, id.getId());
            List<ResourceQuantityInv> resourcesQuantity = inventory.getResources_quantity();
            if (inventory != null) {
                inventory.setCapacity(id.getCapacity() - 1);

                boolean resourceFound = false;
                for (SimpleResource resource : inventory.getResources()) {
                    for (ResourceQuantityInv rqi :resourcesQuantity){
                    if (resource.getId() == res.getId()) {
                        if (resource.getId() == rqi.getResource().getId()) {
                           rqi.setQuantity(rqi.getQuantity()+1);
                        resourceFound = true;
                        break;
                    }
                    }
                    }}
                if (!resourceFound) {
                    SimpleResource newResource = em.find(SimpleResource.class, res.getId());
                    if (newResource == null) {
                        newResource = new SimpleResource(res.getId(), res.getCategory(), res.getAttacks(), res.getLevel(), res.getName(), res.getQuantity(), res.getType());
                        em.persist(newResource);
                    }
                    inventory.getResources().add(newResource);
                    ResourceQuantityInv newResourceq=new ResourceQuantityInv(inventory, newResource, 1);
                    resourcesQuantity.add(newResourceq);
                    em.persist(newResourceq);
                }
            }
            em.merge(inventory);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }


    @Override
    public Inventory updateInventoryCraft(Inventory id,EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            Inventory inventory = em.find(Inventory.class, id.getId());
            if (inventory != null) {
                ArrayList<SimpleResource> list=new ArrayList<>();
                ArrayList<ResourceQuantityInv> qnt=new ArrayList<>();
                for (SimpleResource r :id.getResources()){
                    list.add(r);
                }
                for (ResourceQuantityInv r :id.getResources_quantity()){
                    qnt.add(r);
                }
                inventory.getResources_quantity().clear();
                inventory.getResources().clear();
                inventory.getResources().addAll(list);
                inventory.getResources_quantity().addAll(qnt);
                ArrayList<CraftedResource> lists=new ArrayList<>();
                for (CraftedResource r :id.getCraftedResourceList()){
                    lists.add(r);
                }
                inventory.getCraftedResourceList().clear();
                inventory.getCraftedResourceList().addAll(lists);
            }
            em.merge(inventory);
            em.getTransaction().commit();
            return inventory;

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return null;

    }

    @Override
    public void deleteResourceFromInventory(SimpleResource res, Inventory id,EntityManager em) {
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
