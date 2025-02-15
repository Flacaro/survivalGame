package Controller;

import org.example.model.*;

import java.util.ArrayList;

public class StartController {

    private final Game game=new Game();

    public Game start(Mode mode){
        game.start(mode);
        return game;

    }
}
