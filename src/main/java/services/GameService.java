package services;

import model.domain.GameDomain;
import model.entity.Event;
import model.entity.Game;

public class GameService {

    private MapServices mapServices;
    private ModeService modeService;
    private PlayerService playerService;

    public Game gameMapper(GameDomain gameDomain) {
        Game game = new Game();
        game.setMap(mapServices.mapMapper(gameDomain.getMap()));
        game.setMode(modeService.modeMapper(gameDomain.getMode()));
        game.setPlayer(playerService.playerMapper(gameDomain.getPlayer()));
        game.setStatus(gameDomain.getStatus());
        game.setId(gameDomain.getId());
        return game;
    }


    public void triggerEvent(Event event) {
        // TODO - implement Game.triggerEvent
        throw new UnsupportedOperationException();
    }


    public boolean verifyAnswer(Event event, boolean answer) {
        // TODO - implement Game.verifyAnswer
        throw new UnsupportedOperationException();
    }
}
