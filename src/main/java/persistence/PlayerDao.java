package persistence;

import jakarta.persistence.EntityManager;
import model.domain.InventoryDomain;
import model.domain.PlayerDomain;
import model.entity.Player;

public interface PlayerDao {

    void savePlayer(PlayerDomain player, EntityManager em);
    void updatePlayer(PlayerDomain player, EntityManager em);

    InventoryDomain getInventorytoShow(PlayerDomain pd, EntityManager em);
}
