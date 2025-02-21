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
//    public boolean checkCompatibility(ArrayList<Long> selections, ArrayList<Resource> resourcesToCraft) {
//        ArrayList<Long> resourcesIdsToCraft = new ArrayList<>();
//        for(Resource r : resourcesToCraft) {
//            resourcesIdsToCraft.add(r.getId());
//        }
//        return resourcesIdsToCraft.equals(selections);
//    }
}
