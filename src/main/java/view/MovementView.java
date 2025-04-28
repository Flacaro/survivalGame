package view;

import model.entity.Area;

import java.io.IOException;

public class MovementView {
    public int getDirectionChoice() throws IOException {
        CommonViewUtils.displayMessage("\nDove vuoi spostarti?");
        CommonViewUtils.displayMessage("0. Nord");
        CommonViewUtils.displayMessage("1. Est");
        CommonViewUtils.displayMessage("2. Sud");
        CommonViewUtils.displayMessage("3. Ovest");
        int choice=CommonViewUtils.readIntChoice("Inserisci un numero (0-3):");
        while (choice<0 || choice>3){
            System.out.println("Errore: Inserisci un numero valido.");
            choice=CommonViewUtils.readIntChoice("Inserisci un numero (0-3):");
        }
        return choice;
    }

    public void displayMovementResult(boolean success, Area newArea) {
        if (success && newArea != null) {
            CommonViewUtils.displayMessage("Ti sei spostato in: " + newArea.getName());
            CommonViewUtils.displayMessage(newArea.getDescription());
        } else {
            CommonViewUtils.displayMessage("Non puoi muoverti in quella direzione.");
        }
    }
}
