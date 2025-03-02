package org.example;

import controller.StartController;
import model.domain.GameDomain;
import model.domain.ResourceDomain;
import services.GameService;
import services.MapServices;
import services.PlayerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        StartController sc = new StartController();
        GameDomain g = sc.start();

        GameService gameService = new GameService();
        PlayerService playerService = new PlayerService();
        MapServices ms = new MapServices();

        ResourceDomain resourceDomain = gameService.triggerEvent(g.getPlayer().getIdArea(), g);
        System.out.println("Hai trovato " + resourceDomain.getName() + ", la vuoi prendere?\n" + "Inserisci 1 per raccoglierla\n" +
                "inserisci 0 per ignorarla");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int choice = Integer.parseInt(bf.readLine());
        switch (choice) {
            case 1:
                //pickup
                if (playerService.pickUp(resourceDomain, g.getPlayer())) {
                    ms.updateMap(g.getMap(), resourceDomain);
                    System.out.println("Risorsa aggiunta all'inventario");
                } else {
                    System.out.println("L'inventario e' pieno, non puoi aggiungere la risorsa");
                }

                break;
            case 2:
                System.out.println("Risorsa ignorata");
                break;
        }


    }

//    View view = new View();
//        view.avviaGioco(gameDomain);

}