package org.example;

import controller.StartController;
import model.*;
import model.domain.GameDomain;
import model.domain.ResourceDomain;
import services.GameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {


    public static void main(String[] args) throws IOException {

        StartController sc = new StartController();
        GameDomain g = sc.start();
        GameService gameService = new GameService();
        //restituisce una risorsa dobbiamo fargliela vedere e chiedere cosa fare
        ResourceDomain resourceDomain=gameService.triggerEvent(g.getPlayer().getIdArea(), g);
        System.out.println("Hai trovato " + resourceDomain.getName()+ ", la vuoi prendere?\n"+ "Inserisci 1 per raccoglierla\n"+
                "inserisci 0 per ignorarla");
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        int choice= Integer.parseInt(bf.readLine());
        switch (choice){
            case 1:
                //controlla capienza inventario
                //aggiungi se c'è posto
                //rifiuta se non c'è postp
                System.out.println("Risorsa aggiunta all'inventario");
                break;
            case 2:
                System.out.println("Risorsa ignorata");
                break;
        }




    }


}