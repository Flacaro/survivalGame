package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.entity.Resource;

import java.util.ArrayList;

public class ResourceDaoImpl implements ResourceDao {

    public void saveResource(Resource resource, EntityManager em) {
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
    public ArrayList<Resource> getResource() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        TypedQuery<Resource> query = em.createQuery("SELECT r FROM Resource r", Resource.class);
        return (ArrayList<Resource>) query.getResultList();
    }
}
