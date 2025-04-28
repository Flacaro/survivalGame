package view;
import java.util.HashMap;
import model.entity.CraftedResource;
import model.entity.Inventory;
import model.entity.ResourceQuantityInv;
import model.entity.SimpleResource;

import java.util.List;

public class InventoryView {
    public void displayInventory(Inventory inventory) {
        if (inventory == null) {
            CommonViewUtils.displayMessage("Errore: Impossibile recuperare l'inventario.");
            return;
        }

        List<SimpleResource> simpleResources = inventory.getResources();
        List<CraftedResource> craftedResources = inventory.getCraftedResourceList();
        List<ResourceQuantityInv> quantities = inventory.getResources_quantity();

        boolean simpleIsEmpty = simpleResources == null || simpleResources.isEmpty();
        boolean craftedIsEmpty = craftedResources == null || craftedResources.isEmpty();


        if (simpleIsEmpty && craftedIsEmpty) {
            CommonViewUtils.displayMessage("L'inventario è vuoto, esplora le aree per trovare delle risorse.");
            return;
        }

        CommonViewUtils.displayMessage("\nContenuto dell'inventario:");
        CommonViewUtils.displaySeparator();

        // Mostra risorse semplici con quantità
        if (!simpleIsEmpty && quantities != null) {
            // Usiamo una mappa per efficienza se le liste sono grandi
            HashMap<Long, Integer> quantityMap = new HashMap<>();
            for (ResourceQuantityInv rqid : quantities) {
                if (rqid != null && rqid.getResource() != null) {
                    quantityMap.put(rqid.getResource().getId(), rqid.getQuantity());
                }
            }

            for (SimpleResource r : simpleResources) {
                if (r != null) {
                    int quantity = quantityMap.getOrDefault(r.getId(), 0);
                    CommonViewUtils.displayMessage("- " + r.getName() + "  quantità: " + quantity);
                }
            }
        } else if (!simpleIsEmpty) {
            // Fallback se non ci sono quantità (mostra solo i nomi)
            for (SimpleResource r : simpleResources) {
                if (r != null) CommonViewUtils.displayMessage("- " + r.getName());
            }
        }


        // Mostra risorse craftate con quantità
        if (!craftedIsEmpty) {
            for (CraftedResource cr : craftedResources) {
                if (cr != null) {
                    CommonViewUtils.displayMessage("- " + cr.getName() + "  quantità: " + cr.getQuantity()); // Modificare se il campo quantità ha nome diverso
                }
            }
        }
        CommonViewUtils.displaySeparator();
    }
}
