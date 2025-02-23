package services;

import model.domain.CraftingCatalogDomain;
import model.entity.CraftingCatalog;

public class CraftingServices {

    public CraftingCatalog craftingCatalogMapper(CraftingCatalogDomain craftingCatalog){
        CraftingCatalog catalog = new CraftingCatalog();
        catalog.setId(craftingCatalog.getId());
        catalog.setResourcesToCraft(craftingCatalog.getResourcesToCraft());
        catalog.setFinalResource(craftingCatalog.getFinalResource());
        return catalog;
    }

    public CraftingCatalogDomain craftingCatalogDomainMapper(CraftingCatalog craftingCatalog){
        CraftingCatalogDomain catalogDomain = new CraftingCatalogDomain();
        catalogDomain.setId(craftingCatalog.getId());
        catalogDomain.setResourcesToCraft(craftingCatalog.getResourcesToCraft());
        catalogDomain.setFinalResource(craftingCatalog.getFinalResource());
        return catalogDomain;
    }
//    public boolean checkCompatibility(ArrayList<Long> selections, ArrayList<Resource> resourcesToCraft) {
//        ArrayList<Long> resourcesIdsToCraft = new ArrayList<>();
//        for(Resource r : resourcesToCraft) {
//            resourcesIdsToCraft.add(r.getId());
//        }
//        return resourcesIdsToCraft.equals(selections);
//    }
}
