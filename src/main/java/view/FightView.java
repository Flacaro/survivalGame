package view;

import model.entity.Attack;
import model.entity.SimpleResource;
import model.entity.fight.Fight;

import java.io.IOException;
import java.util.HashMap;

public class FightView {

    public void displayFoundEnemy(String enemyName) {
        CommonViewUtils.displayMessage("Hai incontrato " + enemyName + ", vuoi combattere?");
        CommonViewUtils.displayMessage("1. Combatti");
        CommonViewUtils.displayMessage("0. Scappa");
    }

    public int getFightChoice() throws IOException {
        int choice = CommonViewUtils.readIntChoice("Inserisci 1 o 0:");
        while (choice < 0 || choice > 1) {
            CommonViewUtils.displayMessage("Errore: Inserisci un numero valido.");
            choice = CommonViewUtils.readIntChoice("Inserisci 1 o 0:");
        }
        return choice;
    }

    public Attack displayWeapons(Fight fight) throws IOException {
        CommonViewUtils.displayMessage("Con cosa vuoi affrontare il nemico?");
        HashMap<Integer, SimpleResource> correspondence = new HashMap<>();
        int counter = 1;
        CommonViewUtils.displayMessage("Le armi a tua disposizione sono: ");
        for (SimpleResource sr : fight.getGame().getPlayer().getInventory().getResources()) {
            if (!sr.getAttacks().isEmpty()) {
                CommonViewUtils.displayMessage("Indice arma: " + counter + ", nome arma: " + sr.getName() + " , attacco: " + sr.getAttacks() + " , livello:" + sr.getLevel());
                correspondence.put(counter, sr);
                counter++;
            }
        }
        if (counter == 1) {
            CommonViewUtils.displayMessage("Non hai armi a tua disposizione!");
            return null;
        }
        int choice = CommonViewUtils.readIntChoice("Scegli la tua arma e combatti per la tua vita! \n Scegli l'indice per selezionare l'arma.");
        while (choice==0 || choice>=counter){
            CommonViewUtils.displayMessage("Input non valido");
            choice = CommonViewUtils.readIntChoice("Scegli la tua arma e combatti per la tua vita! \n Scegli l'indice per selezionare l'arma.");
        }
        SimpleResource s = correspondence.get(choice);


        CommonViewUtils.displayMessage("Hai scelto " + s.getName() + " con attacco " + s.getAttacks().get(0).getName()+ " e danno"+ s.getAttacks().get(0).getDamage());
        return s.getAttacks().get(0);
    }

}
