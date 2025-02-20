package org.example.persistence;

import jakarta.persistence.EntityManager;
import org.example.model.Player;

public interface PlayerDao {

    void savePlayer(Player player, EntityManager em);
}
