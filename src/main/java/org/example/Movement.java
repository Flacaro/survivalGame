package org.example;

import controller.MoveController;
import controller.StartController;
import model.domain.GameDomain;
import model.domain.PlayerDomain;
import services.GameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Movement {

    public static void main(String[] args) throws IOException {

        StartController sc = new StartController();
        GameDomain g = sc.start();

        PlayerDomain pd=g.getPlayer();
        System.out.println("Dove vuoi spostarti?\n"+"Inserisci 0 per andare a nord\n"+"Inserisci 1 per andare a est\n"
                +"Inserisci 2 per andare a sud\n"+"Inserisci 3 per andare a ovest\n");
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        int move= Integer.parseInt(bf.readLine());
        MoveController moveController= new MoveController();
        System.out.println(moveController.move(move,g));


    }
}
