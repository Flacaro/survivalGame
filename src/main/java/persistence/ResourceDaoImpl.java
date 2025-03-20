package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.domain.CraftedResourceDomain;
import model.domain.InventoryDomain;
import model.domain.ResourceDomain;
import model.domain.ResourceQuantityInvDomain;
import model.entity.*;
import services.CraftedResourceService;
import services.ResourceQuantityInvService;
import services.ResourceService;

import java.util.ArrayList;
import java.util.List;

public class ResourceDaoImpl implements ResourceDao {

    public void saveResource(ResourceDomain resource, EntityManager em) {
        try {
            ResourceService resourceService= new ResourceService();
            em.getTransaction().begin();
            em.persist(resourceService.resourceMapper(resource)); // Salva nel database
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

    }

    @Override
    public ResourceDomain getResourceById(long id) {
        ResourceService resourceService= new ResourceService();
        EntityManager em = EntityManagerSingleton.getEntityManager();
        TypedQuery<Resource> query = em.createQuery("SELECT r FROM Resource r where r.id =:id", Resource.class);
        query.setParameter("id", id);
        return resourceService.resourceDomainMapper(query.getSingleResult());
    }

    @Override
    public ArrayList<CraftedResourceDomain> getResourcesCrafted(EntityManager em) {
        CraftedResourceService resourceService= new CraftedResourceService();
        TypedQuery<CraftedResource> query = em.createQuery("SELECT r FROM CraftedResource r", CraftedResource.class);

        ArrayList<CraftedResourceDomain> resourceDomains= new ArrayList<>();
        for (CraftedResource r :query.getResultList()){
            resourceDomains.add(resourceService.craftedResourceDomainMapper(r));
        }
        return resourceDomains;
    }

    @Override
    public List<ResourceDomain> getResourceByName() {
        ResourceService resourceService= new ResourceService();
        List<ResourceDomain> list=new ArrayList<>();
        EntityManager em = EntityManagerSingleton.getEntityManager();
        TypedQuery<Resource> query = em.createQuery("SELECT r FROM Resource r where r.name =:name", Resource.class);
        query.setParameter("name", "LEGNO");
        list.add(resourceService.resourceDomainMapper(query.getSingleResult()));
        TypedQuery<Resource> query1 = em.createQuery("SELECT r FROM Resource r where r.name =:name", Resource.class);
        query1.setParameter("name", "METALLO");
        list.add(resourceService.resourceDomainMapper(query1.getSingleResult()));
        return list;
    }

    @Override
    public void removeResources(InventoryDomain inventoryDomain, EntityManager em) {
        try {
            ResourceService resourceService=new ResourceService();
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            Inventory inventory = em.find(Inventory.class, inventoryDomain.getId()); // Trova l'oggetto con ID 1
            if (inventory != null) {
                ArrayList<Resource> resources= new ArrayList<>();
                for (ResourceDomain r : inventoryDomain.getResources()) {
                    resources.add(resourceService.resourceMapper(r));
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
    public void removeQuantity(ResourceQuantityInvDomain resourceQuantityInvDomain,EntityManager em) {
        ResourceQuantityInvService resourceQuantityInvService=new ResourceQuantityInvService();
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            ResourceQuantityInv resourceQuantity = em.find(ResourceQuantityInv.class, resourceQuantityInvDomain.getId());
            if (resourceQuantity != null) {
                em.remove(resourceQuantity);
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
    public ArrayList<ResourceDomain> getResources() {
        ResourceService resourceService= new ResourceService();
        EntityManager em = EntityManagerSingleton.getEntityManager();
        TypedQuery<Resource> query = em.createQuery("SELECT r FROM Resource r", Resource.class);

        ArrayList<ResourceDomain> resourceDomains= new ArrayList<>();
        for (Resource r :query.getResultList()){
            resourceDomains.add(resourceService.resourceDomainMapper(r));
        }
        return resourceDomains;
    }

    @Override
    public void saveResource(ResourceDomain resource) {

    }

}
