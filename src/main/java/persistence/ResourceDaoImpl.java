package persistence;

import jakarta.persistence.EntityManager;
import model.Resource;

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
        return null;
    }
}
