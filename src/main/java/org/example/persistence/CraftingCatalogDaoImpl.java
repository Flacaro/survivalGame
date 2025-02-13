package org.example.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.model.CraftingCatalog;
import java.util.List;

public class CraftingCatalogDaoImpl implements CraftingCatalogDao {

    @Override
    public List<CraftingCatalog> getResourcesIdsToCraft(){

        EntityManager em = EntityManagerSingleton.getEntityManager();

        CraftingCatalog craftingCatalog;

        try {
            // Query per ottenere tutte le righe della tabella crafting_catalog
            TypedQuery<CraftingCatalog> query = em.createQuery("SELECT c FROM CraftingCatalog c", CraftingCatalog.class);
            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return null;
    }
}
