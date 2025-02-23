package persistence;

import jakarta.persistence.EntityManager;
import model.domain.GameDomain;
import services.GameService;

public class GameDaoImpl implements GameDao {

    public void saveGame(GameDomain game, EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            GameService gameService= new GameService();
            em.persist(gameService.gameMapper(game));
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

}
