package org.example;

import controller.DBController;
import controller.ResourceController;
import controller.StartController;
import model.domain.GameDomain;
import model.domain.InventoryDomain;
import model.domain.PlayerDomain;


import java.io.IOException;

public class ShowInventory {
    public static void main(String[] args) throws IOException {

        StartController sc = new StartController();
        DBController dbController=new DBController();
        GameDomain g = sc.start();

        PlayerDomain pd=g.getPlayer();
        ResourceController resourceController= new ResourceController();
        InventoryDomain inventory=dbController.showInventory(pd);
        //add resource to inventory
        //resourceController.addResourseToShowInventory(inventory);

        System.out.println(inventory.getResources().toString());


    }
}
