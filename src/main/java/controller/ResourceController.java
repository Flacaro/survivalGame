package controller;
import model.entity.CraftedResource;
import model.entity.Inventory;
import model.entity.Resource;
import model.entity.SimpleResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResourceController {


    public ArrayList<String> getCombinations() {
        DBController dbController = new DBController();
        ArrayList<CraftedResource> craft = dbController.getCraftedResources();
        ArrayList<String> descr = new ArrayList<>();
        for (CraftedResource s : craft) {
            for (String h : s.getDescription().split(",")) {
                descr.add(h.toLowerCase());
            }
        }
        return descr;
    }

    public ArrayList<String> inputIndexTranslatorToResources(String[] selections, HashMap<Integer, SimpleResource> corrisp) {
        ArrayList<String> list = new ArrayList<>();
        //codifica dell'input
        for (String s : selections) {
            SimpleResource rd = corrisp.get(Integer.parseInt(s));
            if (rd != null) {
                list.add(corrisp.get(Integer.parseInt(s)).getName());
            } else {
                return null;
            }
        }
        return list;
    }

    public CraftedResource pairsSelectedResourcesToCraftResource(ArrayList<String> list, ArrayList<String> descr) {
        DBController dbController = new DBController();
        List<CraftedResource> craft = dbController.getCraftedResources();

        boolean correspond = false;
        int count = 0;
        for (CraftedResource s : craft) {

            //check se gli elementi selezionati corrispondono ad una risorsa craftabile
            for (String l : list) {
                if (descr.contains(l.toLowerCase())) {
                    descr.remove(l.toLowerCase());
                    correspond = true;
                    count = count + 1;
                } else {
                    correspond = false;
                }
            }
            //se tutti gli elementi delle selezioni corrispondono alle risorse necessarie c'è un match
            if (correspond && count == s.getDescription().split(",").length) {
                return s;
            } else {
                descr.clear();
                count = 0;
                correspond = false;
            }
        }
        return null;
    }

    public CraftedResource compatible(String[] selections, HashMap<Integer, SimpleResource> corrisp) {
        ArrayList<String> list = inputIndexTranslatorToResources(selections, corrisp);
        if (list == null) {
            return null;
        }

        ArrayList<String> descr = getCombinations();
        return pairsSelectedResourcesToCraftResource(list, descr);
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
