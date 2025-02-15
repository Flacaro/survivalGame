package org.example.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.model.CraftingCatalog;
import org.example.model.Resource;

import java.util.ArrayList;

public class ResourceDao {
    public ArrayList<Resource> getResource(){
        EntityManager em = EntityManagerSingleton.getEntityManager();

        try {
            // Query per ottenere tutte le righe della tabella resource
            TypedQuery<Resource> query = em.createQuery("SELECT c FROM Resource c", Resource.class);
            return (ArrayList<Resource>) query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return null;
    }
}
