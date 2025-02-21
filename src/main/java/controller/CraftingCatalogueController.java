package controller;

import model.entity.CraftingCatalog;
import persistence.CraftingCatalogDaoImpl;


public class CraftingCatalogueController {

    private final CraftingCatalog craftingCatalog = new CraftingCatalog();
    private final CraftingCatalogDaoImpl craftingCatalogDaoImpl = new CraftingCatalogDaoImpl();

//    public CraftedResource craftingResources(ArrayList<Long> selections) {
//        ArrayList<CraftingCatalog> resourcesToCraft = craftingCatalogDaoImpl.getResourcesToCraft();
//
//        for (CraftingCatalog resource : resourcesToCraft) {
//            if (craftingCatalog.checkCompatibility(selections, resource.getResourcesToCraft())) {
//                return resource.getFinalResource();
//            }
//        }
//        return null;
//        }

}
