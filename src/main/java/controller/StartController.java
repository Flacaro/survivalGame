package controller;

import model.GameFactorySingleton;
import model.entity.*;

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

        Checkpoint ck = new Checkpoint(
                "primo checkpoint",
                2,
                null,
                null

        );
        dbController.checkpointUpdate(ck);

        ArrayList<Area> areas = dbController.getAreas();

        List<Checkpoint> cks = dbController.getCheckpoints();
        Checkpoint c = new Checkpoint();
        if (!cks.isEmpty()) {
            c = cks.get(0);
        }
        c.setArea(areas.get(3));
        dbController.checkpointUpdate(c);

        areas.get(3).setCheckpoint(c);
        gms.createEvent(areas, mode);
        dbController.updateArea(areas);
        map.setAreas(areas);
        player.setId_Area(map.getAreas().get(0));
        Game gameDB = dbController.getGame();
        gameDB.setPlayer(player);
        gameDB.getMap().getAreas().get(3).setCheckpoint(ck);
        dbController.updateGame(gameDB);
        //add resource necessarie per il craftig nell'inventario
        Inventory inventory = dbController.showInventory(player);
        List<SimpleResource> res = dbController.getResourcesByName();
        inventory.setResources(res);
        return dbController.getGame();
    }


}
