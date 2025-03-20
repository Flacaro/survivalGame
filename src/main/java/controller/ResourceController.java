package controller;

import model.domain.CraftedResourceDomain;
import model.domain.InventoryDomain;
import model.domain.PlayerDomain;
import model.domain.ResourceDomain;
import services.InventoryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResourceController {


    public ArrayList<String> getCombinations() {
        DBController dbController = new DBController();
        ArrayList<CraftedResourceDomain> craft = dbController.getCraftedResources();
        ArrayList<String> descr = new ArrayList<>();
        for (CraftedResourceDomain s : craft) {
            for (String h : s.getDescription().split(",")) {
                descr.add(h.toLowerCase());
            }
        }
        return descr;
    }

    public ArrayList<String> inputIndexTranslatorToResources(String[] selections, HashMap<Integer, ResourceDomain> corrisp) {
        ArrayList<String> list = new ArrayList<>();
        //codifica dell'input
        for (String s : selections) {
            ResourceDomain rd = corrisp.get(Integer.parseInt(s));
            if (rd != null) {
                list.add(corrisp.get(Integer.parseInt(s)).getName());
            } else {
                return null;
            }
        }
        return list;
    }

    public CraftedResourceDomain pairsSelectedResourcesToCraftResource(ArrayList<String> list, ArrayList<String> descr) {
        DBController dbController = new DBController();
        List<CraftedResourceDomain> craft = dbController.getCraftedResources();

        boolean correspond = false;
        int count = 0;
        for (CraftedResourceDomain s : craft) {

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

    public CraftedResourceDomain compatible(String[] selections, HashMap<Integer, ResourceDomain> corrisp) {
        ArrayList<String> list = inputIndexTranslatorToResources(selections, corrisp);
        if (list == null) {
            return null;
        }

        ArrayList<String> descr = getCombinations();
        return pairsSelectedResourcesToCraftResource(list, descr);
    }


    public void combine(String[] selections, HashMap<Integer, ResourceDomain> corrisp, PlayerDomain pDomain, CraftedResourceDomain s) {
        ArrayList<ResourceDomain> listResSel = new ArrayList<>();
        InventoryDomain inventoryDomain = pDomain.getInventory();
        InventoryService inventoryService = new InventoryService();
        for (String k : selections) {
            listResSel.add(corrisp.get(Integer.parseInt(k)));
        }
        inventoryDomain=inventoryService.remove(listResSel,inventoryDomain);
        s.setQuantity(s.getQuantity()+1);
        List<CraftedResourceDomain> add = new ArrayList<>(inventoryDomain.getCraftedResourceDomainList());
        add.add(s);
        inventoryDomain.setCraftedResourceDomainList(add);
        inventoryDomain.setCapacity(inventoryDomain.getCapacity()-1);
        inventoryService.updateInventoryCraft(inventoryDomain);
    }


}
