package org.example;

import controller.MoveController;
import controller.ResourceController;
import controller.StartController;
import model.domain.GameDomain;
import model.domain.PlayerDomain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShowInventory {
    public static void main(String[] args) throws IOException {

        StartController sc = new StartController();
        GameDomain g = sc.start();

        PlayerDomain pd=g.getPlayer();
        ResourceController resourceController= new ResourceController();
        System.out.println(ResourceController.showInventory(pd).toString());


    }
}
