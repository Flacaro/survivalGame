package controller;

import model.entity.*;
import singleton.GameFactorySingleton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartController {

    private final DBController dbController = new DBController();

    private final GameFactorySingleton gms = GameFactorySingleton.getInstance();

    //start game with easy mode and single player
    public Game start(int choice, String nickname) throws IOException {

        Mode mode = dbController.getModeById(choice);
        ArrayList<Area> areas = dbController.getAreas();

        Player player = new Player();
        player.setNickname(nickname);
        player.setHealth(5);
        player.setLevel(1);
        player.setId_Area(areas.get(0));

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
        Game gameDB = dbController.getGame();

        List<Checkpoint> checkpoints = dbController.getCheckpoints();
        Checkpoint c = new Checkpoint();
        if (!checkpoints.isEmpty()) {
            c = checkpoints.get(0);
        }

        areas.get(3).setCheckpoint(c);
        gms.createEvent(areas, mode);
        dbController.updateArea(areas);
        map.setAreas(areas);
        gameDB.setMap(map);
        gameDB.getMap().getAreas().get(3).setCheckpoint(c);
        gameDB.getPlayer().setId_Area(gameDB.getMap().getAreas().get(0));
        ArrayList<SimpleResource> res = (ArrayList<SimpleResource>) dbController.getResourcesByName();

        gameDB.getPlayer().getInventory().updateInventory(res);

        return gameDB;
    }


}
