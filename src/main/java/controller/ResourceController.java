package controller;

import model.entity.CraftedResource;
import model.entity.Inventory;
import model.entity.SimpleResource;

import java.util.ArrayList;
import java.util.Collections;
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

    public CraftedResource pairsSelectedResourcesToCraftResource(ArrayList<SimpleResource> selected, ArrayList<CraftedResource> craftedResources) {
        ArrayList<Long> selectedIds = new ArrayList<>();
        for (SimpleResource s : selected) {
            selectedIds.add(s.getId());
        }
        //li ordino
        Collections.sort(selectedIds);

        for (CraftedResource crafted : craftedResources) {
            ArrayList<Long> componentIds = new ArrayList<>();
            for (SimpleResource c : crafted.getComponents()) {
                componentIds.add(c.getId());
            }
            Collections.sort(componentIds);

            //confronto le due liste
            if (selectedIds.equals(componentIds)) {
                return crafted;
            }
        }
        return null;
    }


    public CraftedResource compatible(String[] selections, HashMap<Integer, SimpleResource> corrisp, ArrayList<CraftedResource> craftedResources) {
        ArrayList<SimpleResource> list = inputIndexTranslatorToResources(selections, corrisp);
        if (list == null) {
            return null;
        }
        return pairsSelectedResourcesToCraftResource(list, craftedResources);
    }


    public Inventory combine(String[] selections, HashMap<Integer, SimpleResource> corrisp, Inventory inventoryDomain, CraftedResource s) {
        ArrayList<SimpleResource> listResSel = new ArrayList<>();
        //aggiunge la risorsa selezionata attraverso l'input inserito
        for (String k : selections) {
            listResSel.add(corrisp.get(Integer.parseInt(k)));
        }
        //rimozone delle risorse dall'inventario
        inventoryDomain.remove(listResSel);
        s.setQuantity(s.getQuantity() + 1);
        List<CraftedResource> add = new ArrayList<>(inventoryDomain.getCraftedResourceList());
        add.add(s);
        inventoryDomain.setCraftedResourceList(add);
        inventoryDomain.setCapacity(inventoryDomain.getCapacity() - 1);
        inventoryDomain.updateInventoryCraft();
        return inventoryDomain;
    }


}
