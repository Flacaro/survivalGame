package controller;

import model.domain.Game;
import model.domain.Map;
import model.domain.Mode;
import model.domain.Player;
import services.MapServices;

public class StartController {

    private final DBController dbController = new DBController();

    public Game start() {
        Mode mode = new Mode(1L);
        Player player = new Player("crivall", 5, 1);


        Map map = new Map();
        MapServices mapServices=new MapServices();
        map.setAreas(mapServices.setTotalMapArea(mode));

        Game game = new Game(
                1,
                mode,
                player,
                map
        );

        dbController.insertGame(game);

        return game;
    }
}
