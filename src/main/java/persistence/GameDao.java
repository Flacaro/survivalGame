package persistence;

import jakarta.persistence.EntityManager;
import model.entity.Game;

public interface GameDao {

    void saveGame(Game game, EntityManager em);

    void updateGame(Game gameDomain, EntityManager em);

    Game getGame(EntityManager em);
}

