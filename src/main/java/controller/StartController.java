package controller;

import model.Game;
import model.Map;
import model.Mode;
import model.Player;

import java.util.ArrayList;

public class StartController {

    private final Game game=new Game();
    private DBController dbController= new DBController();

    public Game start(Mode mode){
        game.setMode(mode);
        Player player=new Player("crivall",5,1,game);
        game.setPlayer(player);
        Map map = new Map(new ArrayList<>(),player, game);
        game.setMap(map);

        dbController.insertPlayer(player);
        map.setTotalMapArea(game.getMode(), map);
        dbController.insertArea(map.getTotalMapArea());
       // dbController.insertArea(map.getTotalMapArea());

        return game;
    }
}
