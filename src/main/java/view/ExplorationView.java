package view;

import java.io.IOException;

public class ExplorationView {
    public void displayFoundResource(String resourceName) {
        CommonViewUtils.displayMessage("Hai trovato " + resourceName + ", la vuoi prendere?");
        CommonViewUtils.displayMessage("1. Raccogli");
        CommonViewUtils.displayMessage("0. Ignora");
    }

    public int getPickupChoice() throws IOException {
        int choice=CommonViewUtils.readIntChoice("Inserisci 1 o 0:");
        while (choice!=0 || choice!=1){
            System.out.println("Errore: Inserisci un numero valido.");
            choice=CommonViewUtils.readIntChoice("Inserisci 1 o 0:");
        }
        return choice;
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
