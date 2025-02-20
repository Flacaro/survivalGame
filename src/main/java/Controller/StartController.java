package Controller;

import jakarta.persistence.EntityManager;
import org.example.model.*;
import org.example.persistence.EntityManagerSingleton;

import java.util.ArrayList;

public class StartController {

    private final Game game=new Game();
    private DBController dbController= new DBController();

    public Game start(Mode mode){
        game.setMode(mode);
        Player player=new Player("crivall",5,1,game);
        game.setPlayer(player);
        Map map=new Map(mode,game,player);
        game.setMap(map);

        dbController.insertPlayer(player);
        dbController.insertGame(game);
        dbController.insertMap(map);

        return game;
    }
}
