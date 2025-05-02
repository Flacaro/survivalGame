package view;

import java.io.IOException;

public class FightView {
    public void displayFoundEnemy(String enemyName) {
        CommonViewUtils.displayMessage("Hai trovato " + enemyName + ", la vuoi prendere?");
        CommonViewUtils.displayMessage("1. Combatti");
        CommonViewUtils.displayMessage("0. Scappa");
    }
    public int getFightChoice() throws IOException {
        int choice=CommonViewUtils.readIntChoice("Inserisci 1 o 0:");
        while (choice!=0 || choice!=1){
            System.out.println("Errore: Inserisci un numero valido.");
            choice=CommonViewUtils.readIntChoice("Inserisci 1 o 0:");
        }
        return choice;
    }
}
