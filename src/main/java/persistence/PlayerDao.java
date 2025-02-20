package persistence;

import jakarta.persistence.EntityManager;
import model.Player;

public interface PlayerDao {

    void savePlayer(Player player, EntityManager em);
}
