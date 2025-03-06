package controller;

import model.domain.CraftedResourceDomain;
import model.domain.InventoryDomain;
import model.domain.PlayerDomain;
import model.domain.ResourceDomain;
import services.InventoryService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ResourceController {

    //implementare qui il pick-up

    public ArrayList<CraftedResourceDomain> getCraftedResource() {
        DBController dbController= new DBController();
        ArrayList<CraftedResourceDomain> craftedResourceDomains=dbController.getCraftedResources();
        return craftedResourceDomains;
    }

    public CraftedResourceDomain checkSelections(String[] selections, HashMap<Integer, ResourceDomain> corrisp){
        DBController dbController=new DBController();
        List<CraftedResourceDomain> craft=dbController.getCraftedResources();
        ArrayList<String> list=new ArrayList<>();
        ArrayList<ResourceDomain> listResSel=new ArrayList<>();
        for(String s :selections){
            list.add(corrisp.get(Integer.parseInt(s)).getName());
            listResSel.add(corrisp.get(Integer.parseInt(s)));
        }
        //prendere la descrizione della crafted resource
        ArrayList<String> descr= new ArrayList<>();
        boolean correspond=false;

        for(CraftedResourceDomain s :craft){
            for(String h :s.getDescription().split(",")){
                descr.add(h.toLowerCase());
            }
            for (String l :list){
                if (descr.contains(l.toLowerCase())){
                    correspond=true;
                }else {
                    correspond=false;
                }
            }
            if (correspond){
                return s;
            }
        }
        return null;
    }

    public void combine (String[] selections, HashMap<Integer, ResourceDomain> corrisp, PlayerDomain pDomain){
        ArrayList<ResourceDomain> listResSel=new ArrayList<>();
        InventoryDomain inventoryDomain;
        inventoryDomain=pDomain.getInventory();

        for(String s :selections){
            listResSel.add(corrisp.get(Integer.parseInt(s)));
        }
        inventoryDomain.remove(listResSel);
        DBController dbController=new DBController();
        dbController.removeResources(listResSel,inventoryDomain);

    }

    public void addResourseToShowInventory(InventoryDomain inventory) {
        InventoryService inventoryService=new InventoryService();

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
