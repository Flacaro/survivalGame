/*package view;

import controller.DBController;
import model.entity.Game;
import model.entity.Inventory;
import model.entity.ResourceQuantityInv;
import model.entity.SimpleResource;
import model.entity.CraftedResource;  // <-- Add this import

import java.util.List;
import java.util.Objects;

public class InventoryView {
    public void showInventory(Game g) {
        DBController dbController = new DBController();
        Inventory inventory = dbController.showInventory(g.getPlayer());
        List<ResourceQuantityInv> resourcesQuantity = inventory.getResources_quantity();

        if (!inventory.getResources().isEmpty()) {
            System.out.println("Contenuto dell'inventario:");
            for (SimpleResource r : inventory.getResources()) {
                for (ResourceQuantityInv rqid : resourcesQuantity) {
                    if (Objects.equals(r.getName(), rqid.getResource().getName())) {
                        System.out.println("-" + r.getName() + "  quantità:" + rqid.getQuantity());
                    }
                }
            }
            if (!inventory.getCraftedResourceList().isEmpty()) {
                for (CraftedResource r : inventory.getCraftedResourceList()) {  // <-- Use CraftedResource
                    System.out.println("-" + r.getName() + "  quantità:" + r.getQuantity());
                }
            }
        } else {
            if (inventory.getResources().isEmpty()) {
                if (!inventory.getCraftedResourceList().isEmpty()) {
                    System.out.println("Contenuto dell'inventario:");
                    for (CraftedResource r : inventory.getCraftedResourceList()) {  // <-- Use CraftedResource
                        System.out.println("-" + r.getName() + " quantità: " + r.getQuantity());
                    }
                    return;
                }
                System.out.println("L'inventario è vuoto, esplora le aree per trovare delle risorse");
            }
        }
    }
}*/
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
        List<CraftedResource> craftedResources = inventory.getCraftedResourceList(); // Assumendo esista questo metodo
        List<ResourceQuantityInv> quantities = inventory.getResources_quantity(); // Assumendo esista

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
                    int quantity = quantityMap.getOrDefault(r.getId(), 0); // Default a 0 se non trovata (improbabile)
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
                    // Assumendo che CraftedResource abbia un campo quantity o un modo per ottenerlo
                    CommonViewUtils.displayMessage("- " + cr.getName() + "  quantità: " + cr.getQuantity()); // Modificare se il campo quantità ha nome diverso
                }
            }
        }
        CommonViewUtils.displaySeparator();
    }
}
