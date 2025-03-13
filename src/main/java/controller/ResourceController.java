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

    public ArrayList<String> inputTranslator(String[] selections, HashMap<Integer, ResourceDomain> corrisp) {
        ArrayList<String> list=new ArrayList<>();
        //codifica dell'input
        for(String s :selections){
            ResourceDomain rd=corrisp.get(Integer.parseInt(s));
            if(rd!=null){
                list.add(corrisp.get(Integer.parseInt(s)).getName());
            }else {
                return null;
            }
        }
        return list;
    }

    public boolean compatible(String[] selections, HashMap<Integer, ResourceDomain> corrisp){
        DBController dbController=new DBController();
        List<CraftedResourceDomain> craft=dbController.getCraftedResources();

        ArrayList<String> list=inputTranslator(selections,corrisp);
        if (list==null){
            return false;
        }

        //prendere la descrizione della crafted resource
        ArrayList<String> descr= new ArrayList<>();
        boolean correspond=false;
        int count=0;
        for(CraftedResourceDomain s :craft){
            for(String h :s.getDescription().split(",")){
                descr.add(h.toLowerCase());
            }
            //se il numero di risorse selezionate non corrisponde alle risorse necessarie
            //passa alla risorsa successiva
            if (selections.length < descr.size()|| selections.length < descr.size()){
                break;
            }
            //check se gli elementi selezionati corrispondono ad una risorsa craftabile
            for (String l :list){
                if (descr.contains(l.toLowerCase())){
                    descr.remove(l.toLowerCase());
                    correspond=true;
                    count=count+1;
                }
                else {
                    correspond=false;
                }
            }
            //se tutti gli elementi delle selezioni corrispondono alle risorse necessarie c'è un match
            if (correspond && count==s.getDescription().split(",").length){
                return correspond;
            }else{
                descr.clear();
                count=0;
                correspond=false;
            }
        }
    return correspond;
    }

    public CraftedResourceDomain checkSelections(String[] selections, HashMap<Integer, ResourceDomain> corrisp){
        DBController dbController=new DBController();
        List<CraftedResourceDomain> craft=dbController.getCraftedResources();

        ArrayList<String> list=inputTranslator(selections,corrisp);

        //prendere la descrizione della crafted resource
        ArrayList<String> descr= new ArrayList<>();
        CraftedResourceDomain finalR=new CraftedResourceDomain();
        int counter=0;
        for(CraftedResourceDomain s :craft){
            for(String h :s.getDescription().split(",")){
                descr.add(h.toLowerCase());
            }
            for (String l :list){
                if (descr.contains(l.toLowerCase())){
                    counter=counter+1;
                    if (counter==selections.length){
                        finalR=s;}
                }else{
                    finalR=null;
                }
            }
        }
        return finalR;
    }

    public void combine (String[] selections, HashMap<Integer, ResourceDomain> corrisp, PlayerDomain pDomain,CraftedResourceDomain s){
        ArrayList<ResourceDomain> listResSel=new ArrayList<>();
        InventoryDomain inventoryDomain;
        InventoryService inventoryService=new InventoryService();
        inventoryDomain=pDomain.getInventory();

        for(String k :selections){
            listResSel.add(corrisp.get(Integer.parseInt(k)));
        }
        inventoryDomain=inventoryService.remove(listResSel,inventoryDomain);
        inventoryService.updateInventoryCraft(s,inventoryDomain,listResSel);
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
