package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.entity.Area;
import model.entity.Game;
import model.entity.Map;
import persistence.dao.GameDao;

import java.util.ArrayList;
import java.util.List;

public class GameDaoImpl implements GameDao {

    @Override
    public void saveGame(Game game, EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.merge(game);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public void updateGame(Game gameDomain, EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            Game game = em.find(Game.class, gameDomain.getId());
            List<Area> areas = new ArrayList<>();
            for (Area a : gameDomain.getMap().getAreas()) {
                areas.add(a);
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
    public Game getGame(EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            TypedQuery<Game> query = em.createQuery("SELECT g FROM Game g", Game.class);
            return query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return null;
    }

}
