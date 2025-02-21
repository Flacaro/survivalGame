package persistence;

import jakarta.persistence.EntityManager;
import model.entity.Game;

public interface GameDao {

    void saveGame(Game game, EntityManager em);

}

