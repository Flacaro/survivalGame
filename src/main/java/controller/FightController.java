package controller;

import model.entity.fight.Fight;
import view.FightView;

import java.io.IOException;

public class FightController {
    private Fight fight;
    private FightView fightView;

    public void startFight(Fight fight) throws IOException {
        this.fight=fight;
        fightView.displayFoundEnemy(fight.getEnemy().getName());
        int choice=fightView.getFightChoice();
        fight.playerChoses(choice);
    }

    public void updateRunaway(boolean runaway){
        if (runaway){
            System.out.println("sei riuscito a scappare");
        }
        else {
            System.out.println("non sei riuscito a scappare");
        }

    }
    //chiede gli input alla view
    //passa gli input al fight
    //tramite l'observer aggiorna la view


}
