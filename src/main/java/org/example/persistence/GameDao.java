package org.example.persistence;

import jakarta.persistence.EntityManager;
import org.example.model.Game;

public interface GameDao {

    void saveGame(Game game, EntityManager em);

}

