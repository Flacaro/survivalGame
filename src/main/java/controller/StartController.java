package controller;

import model.entity.Game;
import model.domain.Map;
import model.domain.Mode;
import model.entity.Player;

public class StartController {

    private final DBController dbController = new DBController();

    public Game start() {
        Mode mode = new Mode(1L);
        Player player = new Player("crivall", 5, 1);


        Map map = new Map();
        map.setTotalMapArea(mode);

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
