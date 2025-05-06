package view;

import model.entity.Attack;
import model.entity.Inventory;
import model.entity.Resource;
import model.entity.SimpleResource;
import model.entity.fight.Fight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FightView {

    public void displayFoundEnemy(String enemyName) {
        CommonViewUtils.displayMessage("Hai incontrato " + enemyName + ", vuoi combattere?");
        CommonViewUtils.displayMessage("1. Combatti");
        CommonViewUtils.displayMessage("0. Scappa");
    }

    public int getFightChoice() throws IOException {
        int choice = CommonViewUtils.readIntChoice("Inserisci 1 o 0:");
        while (choice < 0 || choice > 1) {
            System.out.println("Errore: Inserisci un numero valido.");
            choice = CommonViewUtils.readIntChoice("Inserisci 1 o 0:");
        }
        return choice;
    }

    public Attack displayWeapons(Fight fight) throws IOException {
        System.out.println("Con cosa vuoi affrontare il nemico?");
        HashMap<Integer, SimpleResource> correspondence = new HashMap<>();
        int counter = 0;
        System.out.println("Le armi a tua disposizione sono: ");
        for (SimpleResource sr : fight.getGame().getPlayer().getInventory().getResources()) {
            if (!sr.getAttacks().isEmpty()) {
                System.out.println("Indice arma: " + counter + ", nome arma: " + sr.getName() + " , attacco: " + sr.getAttacks() + " , livello:" + sr.getLevel());
                correspondence.put(counter, sr);
                counter++;
            }

        }
        if(counter == 0) {
            System.out.println("Non hai armi a tua disposizione!");
            return null;
        }
        int choice = CommonViewUtils.readIntChoice("Scegli la tua arma e combatti per la tua vita! \n Scegli l'indice per selezionare l'arma \n Per uscire dal gioco premere 5");
        SimpleResource s = correspondence.get(choice);

        System.out.println("Hai scelto " + s.getName() + " con " + s.getAttacks().get(0));
        return s.getAttacks().get(0);
    }
}
