package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.domain.AreaDomain;
import model.domain.GameDomain;
import model.entity.Area;
import model.entity.Game;
import model.entity.Map;
import services.AreaService;
import services.GameService;
import services.MapServices;

import java.util.ArrayList;
import java.util.List;

public class GameDaoImpl implements GameDao {

    public void saveGame(GameDomain game, EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            GameService gameService = new GameService();
            em.persist(gameService.gameMapper(game));
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public void updateGame(GameDomain gameDomain, EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            AreaService areaService = new AreaService();
            Game game = em.find(Game.class, gameDomain.getId());
            List<Area> areas = new ArrayList<>();
            for (AreaDomain a : gameDomain.getMap().getAreas()) {
                areas.add(areaService.areaMapper(a));
            }
            Map map = game.getMap();
            map.setAreas(areas);
            game.setMap(map);
            em.merge(game);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public GameDomain getGame(EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            GameService gameService = new GameService();
            TypedQuery<Game> query = em.createQuery("SELECT g FROM Game g", Game.class);

            return gameService.gameDomainMapper(query.getSingleResult());

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return null;
    }

}
