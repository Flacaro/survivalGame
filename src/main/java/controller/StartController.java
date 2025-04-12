package controller;

import model.GameFactorySingleton;
import model.entity.*;
import persistence.ResourceDaoImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartController {

    private final DBController dbController = new DBController();

    private final GameFactorySingleton gms = GameFactorySingleton.getInstance();

    //start game with easy mode and single player
    public Game start(int choice, String nickname) throws IOException {

        Mode mode = dbController.getModeById(choice);

        Player player = new Player();
        player.setNickname(nickname);
        player.setHealth(5);
        player.setLevel(1);

        Inventory id = new Inventory();
        player.setInventory(id);
        Map map = new Map();
        map.setAreas(map.setTotalMapArea(mode));

        Game game = new Game(
                1,
                mode,
                player,
                map
        );
        dbController.insertGame(game);

        ArrayList<Area> areas = dbController.getAreas();
        gms.createEvent(areas, mode);
        dbController.updateArea(areas);
        map.setAreas(areas);
        Game gameDB = dbController.getGame();
        dbController.updateGame(gameDB);
        //add resource necessarie per il craftig nell'inventario
        Inventory inventory = dbController.showInventory(player);
        List<SimpleResource> res = new ArrayList<>();
        ResourceDaoImpl resourceDao = new ResourceDaoImpl();

        res = dbController.getResourcesByName();
        inventory.setResources(res);
        for (SimpleResource r : res) {
            dbController.updateInventory(r, inventory);
        }
        return dbController.getGame();
    }


}
