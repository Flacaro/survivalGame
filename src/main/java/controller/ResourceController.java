package controller;
import model.entity.CraftedResource;
import model.entity.Inventory;
import model.entity.Resource;
import model.entity.SimpleResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResourceController {

    public ArrayList<SimpleResource> inputIndexTranslatorToResources(String[] selections, HashMap<Integer, SimpleResource> corrisp) {
        ArrayList<SimpleResource> list = new ArrayList<>();
        //codifica dell'input
        for (String s : selections) {
            SimpleResource rd = corrisp.get(Integer.parseInt(s));
            if (rd != null) {
                list.add(rd);
            } else {
                return null;
            }
        }
        return list;
    }

    public CraftedResource pairsSelectedResourcesToCraftResource(ArrayList<SimpleResource> list, ArrayList<CraftedResource> craftedResources) {
        boolean correspond = false;
        int count = 0;
        for (SimpleResource l : list) {
            //check se gli elementi selezionati corrispondono ad una risorsa craftabile
           for (CraftedResource s : craftedResources) {
               for (SimpleResource sr : s.getComponents()) {
                   if (l.getId()==sr.getId()){
                       list.remove(l);
                       correspond = true;
                       count = count + 1;
                   } else{
                       correspond = false;
                   }
               }if (correspond && count == s.getComponents().size()) {
                   return s;
               } else {
                   list.clear();
                   count = 0;
                   correspond = false;
               }
           }
            //se tutti gli elementi delle selezioni corrispondono alle risorse necessarie c'è un match

        }
        return null;
    }

    public CraftedResource compatible(String[] selections, HashMap<Integer, SimpleResource> corrisp, ArrayList<CraftedResource> craftedResources) {
        ArrayList<SimpleResource> list = inputIndexTranslatorToResources(selections, corrisp);
        if (list == null) {
            return null;
        }
        return pairsSelectedResourcesToCraftResource(list,craftedResources);
    }


    public Inventory combine(String[] selections, HashMap<Integer, SimpleResource> corrisp, Inventory inventoryDomain, CraftedResource s) {
        ArrayList<SimpleResource> listResSel = new ArrayList<>();
        //aggiunge la risorsa selezionata attraverso l'input inserito
        for (String k : selections) {
            listResSel.add(corrisp.get(Integer.parseInt(k)));
        }
        //rimozone delle risorse dall'inventario
        Inventory id = inventoryDomain.remove(listResSel, inventoryDomain);
        s.setQuantity(s.getQuantity() + 1);
        List<CraftedResource> add = new ArrayList<>(inventoryDomain.getCraftedResourceList());
        add.add(s);
        id.setCraftedResourceList(add);
        id.setCapacity(inventoryDomain.getCapacity() - 1);
        DBController dbController=new DBController();
        return dbController.updateInventoryCraft(id);
    }


}
