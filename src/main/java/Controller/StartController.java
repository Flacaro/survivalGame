package Controller;

import org.example.model.*;

import java.util.ArrayList;

public class StartController {

    private final Game game=new Game();
    private DBController dbController= new DBController();

//    public void start(Mode mode) {
//        this.mode=mode;
//        //va salvato nel db
//        this.player=new Player();
//        this.map=new Map(mode,this,player);
//    }
    public Game start(Mode mode){
        game.setMode(mode);
        Player player=new Player();
        game.setPlayer(player);
        Map map=new Map(mode,game,player);
        game.setMap(map);
//        dbController.insertPlayer(player);
//        dbController.insertGame(game);
//        dbController.insertMap(map);
        dbController.insertPlayerGameMap(player, game, map);

        return game;
    }
}
