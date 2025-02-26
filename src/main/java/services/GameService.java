package services;

import jakarta.persistence.EntityManager;
import model.domain.AreaDomain;
import model.domain.GameDomain;
import model.domain.ResourceDomain;
import model.entity.Event;
import model.entity.Game;
import persistence.GameDaoImpl;

import java.util.List;
import java.util.Objects;

public class GameService {

    private MapServices mapServices= new MapServices();
    private ModeService modeService= new ModeService();
    private PlayerServices playerService= new PlayerServices();
    private final GameDaoImpl gameDaoImpl = new GameDaoImpl();
    private ResourceService resourceService = new ResourceService();
    private EnemyService enemyService =  new EnemyService();

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

    //viene chiamato ogni volta che il giocatore si muove
    public ResourceDomain triggerEvent(long idArea, GameDomain gd) {
        List<AreaDomain> mapAreas = gd.getMap().getAreas();
        for (AreaDomain areas : mapAreas) {
            if(areas.getId() == idArea) {
                long idEvent = areas.getIdEvent();
                String category = areas.getCategory();
                if(Objects.equals(category, "RISORSA")) {
                    System.out.println("C'E' UNA RISORSA");
                   return resourceService.getResourceById(idEvent);
                } else {
                    enemyService.getEnemyById(idEvent);
                }
            }
        }
        System.out.println("Nessuna risorsa è presente nell'area in cui ti trovi");
        return null;
    }


    public boolean verifyAnswer(Event event, boolean answer) {
        // TODO - implement Game.verifyAnswer
        throw new UnsupportedOperationException();
    }

    public void updateGame(GameDomain gameDomain, EntityManager em) {

        gameDaoImpl.updateGame(gameDomain,em);
    }

    public  GameDomain getGame(EntityManager em){
        return gameDaoImpl.getGame(em);
    }
}
