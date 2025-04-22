/*package view;

import controller.DBController;
import model.entity.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExplorationView {

    private final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public void explore(Game game) throws IOException {
        long currentIdArea = game.getPlayer().getIdArea();
        SimpleResource resource = game.triggerEvent(currentIdArea, game);
        DBController dbController = new DBController();
        Area currentArea = dbController.getAreasById(currentIdArea);

        if (resource != null && currentArea.getIdEvent() == resource.getId()) {
            System.out.println("Hai trovato " + resource.getName() + ", vuoi raccoglierla? (1 = sì, 0 = no)");
            int choice = Integer.parseInt(bf.readLine());
            if (choice == 1) {
                if (game.pickUp(resource, game.getPlayer())) {
                    dbController.updateMap(game.getMap(), resource);
                    System.out.println("Risorsa aggiunta all'inventario.");
                } else {
                    System.out.println("Inventario pieno.");
                }
            } else {
                System.out.println("Hai ignorato la risorsa.");
            }
        } else {
            System.out.println("Non hai trovato nulla questa volta.");
        }
    }
}*/
package view;

import java.io.IOException;

public class ExplorationView {
    public void displayFoundResource(String resourceName) {
        CommonViewUtils.displayMessage("Hai trovato " + resourceName + ", la vuoi prendere?");
        CommonViewUtils.displayMessage("1. Raccogli");
        CommonViewUtils.displayMessage("0. Ignora");
    }

    public int getPickupChoice() throws IOException {
        // Aggiungere validazione per 0 o 1
        return CommonViewUtils.readIntChoice("Inserisci 1 o 0:");
    }

    public void displayResourcePickedUp() {
        CommonViewUtils.displayMessage("Risorsa aggiunta all'inventario.");
    }

    public void displayResourceIgnored() {
        CommonViewUtils.displayMessage("Risorsa ignorata.");
    }

    public void displayInventoryFull() {
        CommonViewUtils.displayMessage("L'inventario e' pieno, non puoi aggiungere la risorsa.");
    }

    public void displayNothingFound() {
        CommonViewUtils.displayMessage("Non hai trovato nulla questa volta.");
    }
}
