package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.domain.ResourceDomain;
import model.entity.Resource;
import services.ResourceService;

import java.util.ArrayList;

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


}
