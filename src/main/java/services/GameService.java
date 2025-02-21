package services;

import model.domain.GameDomain;
import model.entity.Game;

public class GameService {

    public Game gameMapper(GameDomain gameDomain) {
        Game game = new Game();
        game.setMap(gameDomain.getMap());
    }
}
