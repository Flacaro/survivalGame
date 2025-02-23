package persistence;

import jakarta.persistence.EntityManager;
import model.domain.GameDomain;
import model.entity.Game;

public interface GameDao {

    void saveGame(GameDomain game, EntityManager em);

}

