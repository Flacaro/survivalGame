package controller;

import model.GameFactorySingleton;
import model.domain.*;
import persistence.ResourceDaoImpl;
import services.InventoryService;
import services.MapServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StartController {

    private final DBController dbController = new DBController();

    private GameFactorySingleton gms = GameFactorySingleton.getInstance();

    //start game with easy mode and single player
    public GameDomain start() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("scegli una modalita'\n");
        System.out.println("Scegli 1 per la modalita' facile, 2 per quella media, 3 per quella difficile'\n");
        int choice = Integer.parseInt(bf.readLine());

        ModeDomain modeDomain = new ModeDomain(choice);

        PlayerDomain playerDomain = new PlayerDomain();
        System.out.println("scegli un nickname\n");
        String nickname = bf.readLine();
        playerDomain.setNickname(nickname);
        playerDomain.setHealth(5);
        playerDomain.setLevel(1);

        InventoryDomain id = new InventoryDomain();
        playerDomain.setInventory(id);
        MapDomain mapDomain = new MapDomain();
        MapServices mapServices = new MapServices();
        mapDomain.setAreas(mapServices.setTotalMapArea(modeDomain));

        GameDomain gameDomain = new GameDomain(
                1,
                modeDomain,
                playerDomain,
                mapDomain
        );
        dbController.insertGame(gameDomain);

        ArrayList<AreaDomain> areaDomains = dbController.getAreas();
        gms.createEvent(areaDomains, modeDomain);
        dbController.updateArea(areaDomains);
        mapDomain.setAreas(areaDomains);
        GameDomain gameDB = dbController.getGame();
        dbController.updateGame(gameDB);
        //add resource necessarie per il craftig nell'inventario
        InventoryDomain inventory = dbController.showInventory(playerDomain);
        InventoryService inventoryService = new InventoryService();
        List<ResourceDomain> res = new ArrayList<>();
        ResourceDaoImpl resourceDao = new ResourceDaoImpl();
        res = resourceDao.getResourceByName();
        inventory.setResources(res);
        for (ResourceDomain r : res) {
            inventoryService.updateInventory(r, inventory);
        }
        return dbController.getGame();
    }

//    public void readPlayerNickname(PlayerDomain playerDomain) throws IOException {
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("scegli un nickname\n");
//        String nickname = bf.readLine();
//        DBController dbController = new DBController();
//        playerDomain.setNickname(nickname);
//        playerDomain.setHealth(5);
//        playerDomain.setLevel(1);
//        dbController.insertPlayer(playerDomain);
//    }

}
