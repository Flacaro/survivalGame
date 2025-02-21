package persistence;

import jakarta.persistence.EntityManager;
import model.Game;

public class GameDaoImpl implements GameDao {

    public void saveGame(Game game, EntityManager em) {
        try {

            if(!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.persist(game);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

}
