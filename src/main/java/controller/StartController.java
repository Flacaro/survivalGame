package controller;

import model.GameFactorySingleton;
import model.entity.*;
import persistence.ResourceDaoImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StartController {

    private final DBController dbController = new DBController();

    private GameFactorySingleton gms = GameFactorySingleton.getInstance();

    //start game with easy mode and single player
    public Game start() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("scegli una modalita'\n");
        System.out.println("Scegli 1 per la modalita' facile, 2 per quella media, 3 per quella difficile'\n");
        int choice = Integer.parseInt(bf.readLine());

        Mode modeDomain =dbController.getModeById(choice);

        Player playerDomain = new Player();
        System.out.println("scegli un nickname\n");
        String nickname = bf.readLine();
        playerDomain.setNickname(nickname);
        playerDomain.setHealth(5);
        playerDomain.setLevel(1);

        Inventory id = new Inventory();
        playerDomain.setInventory(id);
        Map mapDomain = new Map();
        mapDomain.setAreas(mapDomain.setTotalMapArea(modeDomain));

        Game gameDomain = new Game(
                1,
                modeDomain,
                playerDomain,
                mapDomain
        );
        dbController.insertGame(gameDomain);

        ArrayList<Area> areaDomains = dbController.getAreas();
        gms.createEvent(areaDomains, modeDomain);
        dbController.updateArea(areaDomains);
        mapDomain.setAreas(areaDomains);
        Game gameDB = dbController.getGame();
        dbController.updateGame(gameDB);
        //add resource necessarie per il craftig nell'inventario
        Inventory inventory = dbController.showInventory(playerDomain);
        List<Resource> res = new ArrayList<>();
        ResourceDaoImpl resourceDao = new ResourceDaoImpl();

        res = dbController.getResourcesByName();
        inventory.setResources(res);
        for (Resource r : res) {
            dbController.updateInventory(r,inventory);
        }
        return dbController.getGame();
    }


}
