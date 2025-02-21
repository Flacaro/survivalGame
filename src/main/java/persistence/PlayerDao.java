package persistence;

import jakarta.persistence.EntityManager;
import model.entity.Player;

public interface PlayerDao {

    void savePlayer(Player player, EntityManager em);
}
