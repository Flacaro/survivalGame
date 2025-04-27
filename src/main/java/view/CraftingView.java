package view;

import model.entity.CraftedResource;
import model.entity.Inventory;
import model.entity.SimpleResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CraftingView {

    public void displayCraftingRecipes(ArrayList<CraftedResource> craftableRecipes) {
        CommonViewUtils.displayMessage("\nCombinazioni possibili per il crafting degli oggetti");
        CommonViewUtils.displaySeparator();
        if (craftableRecipes == null || craftableRecipes.isEmpty()) {
            CommonViewUtils.displayMessage("Nessuna ricetta di crafting disponibile al momento.");
            CommonViewUtils.displaySeparator();
            return;
        }
        for (CraftedResource r : craftableRecipes) {
            if (r == null || r.getComponents() == null) continue;
            StringBuilder recipe = new StringBuilder("Per creare " + r.getName() + " seleziona: ");
            for (SimpleResource s : r.getComponents()) {
                if (s != null) recipe.append(s.getName()).append(" ");
            }
            CommonViewUtils.displayMessage(recipe.toString().trim());
            CommonViewUtils.displaySeparator();
        }
    }

    public HashMap<Integer, SimpleResource> displayInventoryForCrafting(Inventory inventory) {
        CommonViewUtils.displayMessage("\nSeleziona le risorse dal tuo inventario:");
        CommonViewUtils.displaySeparator();
        HashMap<Integer, SimpleResource> correspondence = new HashMap<>();
        int counter = 0;
        if (inventory != null && inventory.getResources() != null && !inventory.getResources().isEmpty()) {
            for (SimpleResource r : inventory.getResources()) {
                if (r != null) {
                    counter++;
                    CommonViewUtils.displayMessage(counter + ". " + r.getName());
                    correspondence.put(counter, r);
                }
            }
        } else {
            CommonViewUtils.displayMessage("Non hai risorse nell'inventario.");
        }
        CommonViewUtils.displaySeparator();
        return correspondence;
    }

    public String[] getCraftingInput(boolean hasResources) throws IOException {
        if (!hasResources) {
            return null; // Non chiedere input se non ci sono risorse
        }
        String input = CommonViewUtils.readString("Inserisci gli indici delle risorse da combinare separati da virgola (es. 1,2), oppure 'annulla':");
        if (input.equalsIgnoreCase("annulla")) {
            return null;
        }
        return input.split(",");
    }

    public void displayCraftingResult(boolean success, CraftedResource craftedItem) {
        if (success && craftedItem != null) {
            CommonViewUtils.displayMessage("Hai creato con successo: " + craftedItem.getName());
        } else {
            CommonViewUtils.displayMessage("Combinazione non valida.");
        }
    }

    public void displayInvalidInput() {
        CommonViewUtils.displayMessage("Input non valido. Assicurati di inserire numeri corrispondenti alle risorse, separati da virgole.");
    }

    public void displayInventoryEmpty() {
        CommonViewUtils.displayMessage("L'inventario è vuoto, esplora le aree per trovarne.");
    }
}