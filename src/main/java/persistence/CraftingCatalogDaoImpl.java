package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.CraftingCatalog;

import java.util.ArrayList;

public class CraftingCatalogDaoImpl implements CraftingCatalogDao {

    @Override
    public ArrayList<CraftingCatalog> getResourcesToCraft(){

        EntityManager em = EntityManagerSingleton.getEntityManager();

        try {
            // Query per ottenere tutte le righe della tabella crafting_catalog
            TypedQuery<CraftingCatalog> query = em.createQuery("SELECT c FROM CraftingCatalog c", CraftingCatalog.class);
            return (ArrayList<CraftingCatalog>) query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
