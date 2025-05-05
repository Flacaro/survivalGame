package view;

import model.entity.Attack;
import model.entity.SimpleResource;
import model.entity.fight.Fight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FightView {

    public void displayFoundEnemy(String enemyName) {
        CommonViewUtils.displayMessage("Hai incontrato " + enemyName + ", vuoi combattere?");
        CommonViewUtils.displayMessage("1. Combatti");
        CommonViewUtils.displayMessage("0. Scappa");
    }
    public int getFightChoice() throws IOException {
        int choice=CommonViewUtils.readIntChoice("Inserisci 1 o 0:");
        while (choice<0 || choice>1){
            System.out.println("Errore: Inserisci un numero valido.");
            choice=CommonViewUtils.readIntChoice("Inserisci 1 o 0:");
        }
        return choice;
    }

    public Attack displayWeapons(Fight fight) {
        System.out.println("Con cosa vuoi affrontare il nemico?");
        Attack attack=new Attack(2L, "Distrugge tutto",1, "attacco diretto");
        SimpleResource res=fight.getGame().getPlayer().getInventory().getResources().get(0);
        List<Attack>attacks=new ArrayList<>();
        attacks.add(attack);
        res.setAttacks(attacks);
        System.out.println("hai scelto "+ res.getName()+" con "+ res.getAttacks().get(0));
        return res.getAttacks().get(0);
    }
}
