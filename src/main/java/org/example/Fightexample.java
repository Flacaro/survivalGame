package org.example;

import controller.DBController;
import controller.StartController;
import model.entity.Attack;
import model.entity.Enemy;
import model.entity.Game;
import model.entity.fight.Fight;
import view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Fightexample {
    public static void main(String[] args) throws IOException {
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        StartController sc = new StartController();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("scegli una modalita'\n");
        System.out.println("Scegli 1 per la modalita' facile, 2 per quella media, 3 per quella difficile'\n");
        int mode = Integer.parseInt(bf.readLine());
        System.out.println("scegli un nickname\n");
        String nickname = bf.readLine();
        sc.start(mode, nickname);
        DBController dbController= new DBController();
        Game g = dbController.getGame();

        ArrayList<Attack> attacks=new ArrayList<>();
        attacks.add(new Attack(1L, "graffio",1.5, "attacco diretto"));
        Enemy  enemy= new Enemy(attacks, 1, "orso");

        Fight fight=new Fight();

    }
}
