package persistence;

import jakarta.persistence.EntityManager;
import model.domain.InventoryDomain;
import model.domain.PlayerDomain;

public interface PlayerDao {

    void updatePlayer(PlayerDomain player,EntityManager em);

    InventoryDomain getInventoryToShow(PlayerDomain pd, EntityManager em);
}
