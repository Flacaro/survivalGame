package org.example;

import controller.StartController;
import model.*;
import model.domain.GameDomain;
import services.GameService;


public class Main {


    public static void main(String[] args) {

        StartController sc = new StartController();
        GameDomain g = sc.start();
        GameService gameService = new GameService();
        gameService.triggerEvent(g.getPlayer().getIdArea(), g);

    }


}