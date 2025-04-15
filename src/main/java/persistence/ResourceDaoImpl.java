package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.entity.*;
import persistence.dao.ResourceDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResourceDaoImpl implements ResourceDao {

    @Override
    public void saveResource(SimpleResource resource, EntityManager em) {
        try {
            em.getTransaction().begin();
            em.persist(resource); // Salva nel database
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

    }

    @Override
    public SimpleResource getResourceById(long id, EntityManager em) {
        TypedQuery<SimpleResource> query = em.createQuery("SELECT r FROM SimpleResource r where r.id =:id", SimpleResource.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public ArrayList<CraftedResource> getResourcesCrafted(EntityManager em) {
        TypedQuery<CraftedResource> query = em.createQuery("SELECT r FROM CraftedResource r LEFT JOIN FETCH r.components", CraftedResource.class);
        ArrayList<CraftedResource> resourceDomains = new ArrayList<>();
        for (CraftedResource r : query.getResultList()) {
            resourceDomains.add(r);
        }

        return resourceDomains;
    }

    @Override
    public List<SimpleResource> getResourceByName(EntityManager em) {
        List<SimpleResource> list = new ArrayList<>();
        TypedQuery<SimpleResource> query = em.createQuery("SELECT r FROM SimpleResource r where r.name =:name", SimpleResource.class);
        query.setParameter("name", "LEGNO");
        list.add(query.getSingleResult());
        TypedQuery<SimpleResource> query1 = em.createQuery("SELECT r FROM SimpleResource r where r.name =:name", SimpleResource.class);
        query1.setParameter("name", "METALLO");
        list.add(query1.getSingleResult());
        return list;
    }

    @Override
    public void removeResources(Inventory inventoryDomain, EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            Inventory inventory = em.find(Inventory.class, inventoryDomain.getId()); // Trova l'oggetto con ID 1
            if (inventory != null) {
                ArrayList<SimpleResource> resources = new ArrayList<>();
                for (SimpleResource r : inventoryDomain.getResources()) {
                    resources.add(r);
                }
                inventory.getResources().clear();
                inventory.getResources().addAll(resources);
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public void removeQuantity(ResourceQuantityInv resourceQuantityInvDomain, EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            ResourceQuantityInv resourceQuantity = em.find(ResourceQuantityInv.class, resourceQuantityInvDomain.getId());
            if (resourceQuantity != null) {
                Inventory inv = resourceQuantity.getInventory();
                inv.getResources_quantity().remove(resourceQuantity);
                em.merge(inv);
            } else {
                System.out.println("Nessuna entità trovata con ID");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public List<ResourceDomain> getResourcesforCrafting(String metallo, String legno) {
//        ResourceService resourceService= new ResourceService();
//        EntityManager em = EntityManagerSingleton.getEntityManager();
//        List<ResourceDomain>list= new ArrayList<>();
//        TypedQuery<Resource> query = em.createQuery("SELECT r FROM Resource r where r.name =:name", Resource.class);
//        query.setParameter("name", metallo);
//        list.add(resourceService.resourceDomainMapper(query.getSingleResult()));
//        TypedQuery<Resource> query2 = em.createQuery("SELECT r FROM Resource r where r.name =:name", Resource.class);
//        query2.setParameter("name", legno);
//        list.add(resourceService.resourceDomainMapper(query2.getSingleResult()));
//        return list;
//    }

    @Override
    public ArrayList<SimpleResource> getResources(EntityManager em) {
        TypedQuery<SimpleResource> query = em.createQuery("SELECT r FROM SimpleResource r", SimpleResource.class);

        ArrayList<SimpleResource> resourceDomains = new ArrayList<>();
        for (SimpleResource r : query.getResultList()) {
            resourceDomains.add(r);
        }
        return resourceDomains;
    }


}
