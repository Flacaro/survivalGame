package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.domain.CraftingCatalogDomain;
import model.entity.CraftingCatalog;
import services.CraftingServices;

import java.util.ArrayList;

public class CraftingCatalogDaoImpl implements CraftingCatalogDao {

    @Override
    public ArrayList<CraftingCatalogDomain> getResourcesToCraft(){

        EntityManager em = EntityManagerSingleton.getEntityManager();
        CraftingServices craftingServices= new CraftingServices();

        try {
            // Query per ottenere tutte le righe della tabella crafting_catalog
            TypedQuery<CraftingCatalog> query = em.createQuery("SELECT c FROM CraftingCatalog c", CraftingCatalog.class);
            ArrayList<CraftingCatalogDomain> catalogDomainArrayList=new ArrayList<>();

            for(CraftingCatalog c : query.getResultList()){
                catalogDomainArrayList.add(craftingServices.craftingCatalogDomainMapper(c));
            }
            return catalogDomainArrayList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
