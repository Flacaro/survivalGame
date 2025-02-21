package persistence;

import jakarta.persistence.EntityManager;
import model.Game;

public class GameDaoImpl implements GameDao {

    public void saveGame(Game game, EntityManager em) {
        try {
            //em.getTransaction().begin();
            em.persist(game); // Salva nel database
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

}
