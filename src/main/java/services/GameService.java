package services;

import jakarta.persistence.EntityManager;
import model.domain.GameDomain;
import model.entity.Event;
import model.entity.Game;
import persistence.GameDaoImpl;

public class GameService {

    private MapServices mapServices= new MapServices();
    private ModeService modeService= new ModeService();
    private PlayerServices playerService= new PlayerServices();
    private GameDaoImpl gameDaoImpl = new GameDaoImpl();

    public Game gameMapper(GameDomain gameDomain) {
        Game game = new Game();
        game.setMap(mapServices.mapMapper(gameDomain.getMap()));
        game.setMode(modeService.modeMapper(gameDomain.getMode()));
        game.setPlayer(playerService.playerMapper(gameDomain.getPlayer()));
        game.setStatus(gameDomain.getStatus());
        game.setId(gameDomain.getId());
        return game;
    }

    public GameDomain gameDomainMapper(Game game) {
        GameDomain gameDomain = new GameDomain();
        gameDomain.setMap(mapServices.mapDomainMapper(game.getMap()));
        gameDomain.setMode(modeService.modeDomainMapper(game.getMode()));
        gameDomain.setPlayer(playerService.playerDomainMapper(game.getPlayer()));
        gameDomain.setStatus(game.getStatus());
        gameDomain.setId(game.getId());
        return gameDomain;
    }

    public void saveGame(GameDomain g, EntityManager em)  {
        gameDaoImpl.saveGame(g, em);
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
