package services;

import model.domain.GameDomain;
import model.entity.Game;

public class GameService {

    private MapServices mapServices;
    private ModeService modeService;
    private PlayerService playerService;

    public Game gameMapper(GameDomain gameDomain) {
        Game game = new Game();
        game.setMap(mapServices.mapMapper(gameDomain.getMap()));
        game.setMode(modeService.modeMapper(gameDomain.getMode()));
        game.setPlayer();
    }
}
