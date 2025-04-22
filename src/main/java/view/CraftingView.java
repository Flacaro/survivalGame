/*package view;

import controller.DBController;
import controller.ResourceController;
import model.entity.Game;
import model.entity.CraftedResource;
import model.entity.Inventory;
import model.entity.Player;
import model.entity.SimpleResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CraftingView {
    private final BufferedReader bf;

    public CraftingView(BufferedReader bf) {
        this.bf = bf;
    }

    public boolean crafting(Game g) {
        DBController dbController = new DBController();
        ResourceController resourceController = new ResourceController();
        Player player = g.getPlayer();
        Inventory inventory = dbController.showInventory(player);
        ArrayList<CraftedResource> craft = dbController.getCraftedResources();
        System.out.println("Combinazioni possibili per il crafting degli oggetti");
        System.out.println("-----------------------------------------------------");
        for (CraftedResource r : craft) {
            String recipe="Per creare " + r.getName() + " seleziona ";
            for (SimpleResource s : r.getComponents()){
                recipe+=s.getName()+ " ";
            }
            System.out.println(recipe);
            System.out.println("-----------------------------------------------------");
        }

        if (inventory.getResources().isEmpty()) {
            System.out.println("L'inventario è vuoto, esplora le aree per trovare delle risorse");
            return false;
        } else {
            try {
                System.out.println("Inserisci l'indice delle risorse da combinare separate tramite virgola (es. 1,2)");
                int counter = 0;
                HashMap<Integer, SimpleResource> corrisp = new HashMap<>();
                for (SimpleResource r : inventory.getResources()) {
                    counter = counter + 1;
                    System.out.println("Inserire l'indice " + counter + " per selezionare la risorsa: " + r.getName());
                    corrisp.put(counter, r);
                }
                String input = bf.readLine();
                String[] selections = input.split(",");
                for (String s : selections) {
                    if (Integer.parseInt(s) < 1 || selections.length > corrisp.size() || Integer.parseInt(s) > corrisp.size()) {
                        System.out.println("Input non valido.");
                        return false;
                    }
                }
                CraftedResource pair = resourceController.compatible(selections, corrisp, craft);
                if (pair != null) {
                    player.setInventory(resourceController.combine(selections, corrisp, inventory, pair));
                    System.out.println("Hai creato: " + pair.getName());
                    dbController.updatePlayer(player);
                    g.setPlayer(player);
                    dbController.updateGame(g);
                    return true;
                } else {
                    System.out.println("Input non valido.");
                    return false;
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Input non valido.");
                return false;
            }
        }
    }
}
*/
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
            CommonViewUtils.displayMessage("Non hai risorse semplici nell'inventario per il crafting.");
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
            CommonViewUtils.displayMessage("Combinazione non valida o fallita.");
        }
    }

    public void displayInvalidInput() {
        CommonViewUtils.displayMessage("Input non valido. Assicurati di inserire numeri corrispondenti alle risorse, separati da virgole.");
    }

    public void displayInventoryEmpty() {
        CommonViewUtils.displayMessage("L'inventario è vuoto o non contiene risorse semplici, esplora le aree per trovarne.");
    }
}