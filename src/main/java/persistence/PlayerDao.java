package persistence;

import jakarta.persistence.EntityManager;
import model.domain.PlayerDomain;
import model.entity.Player;

public interface PlayerDao {

    void savePlayer(PlayerDomain player, EntityManager em);
    void updatePlayer(PlayerDomain player, EntityManager em);
}
