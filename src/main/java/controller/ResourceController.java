package controller;

import model.domain.InventoryDomain;
import model.domain.ResourceDomain;
import persistence.InventoryDaoImpl;

public class ResourceController {

    public void showResource(ResourceDomain resourceDomain) {
        System.out.println(resourceDomain);
    }

    public void addSelections(long idInventory){
        //query che prende l'inventario
        InventoryDaoImpl inventoryDao = new InventoryDaoImpl();
        InventoryDomain inventoryDomain = inventoryDao.getInventory(idInventory);
        for(ResourceDomain r : inventoryDomain.getResources()){
            //gliele faccio vedere tutte e lui me le deve selezionare
            System.out.println("risorsa: ");
        }

    }
    //deve aggiornare l'inventario dopo che il giocatore ha craftato una risorsa

    //deve aggiornare l'inventario se il giocatore ha preso una risorsa
    //deve avere una funzione che mi seleziona le risorse da craftare
    public void combine (){

    }
    //deve tenere traccia delle quantita' delle risorse (esempio se le rimuove o le usa)

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
