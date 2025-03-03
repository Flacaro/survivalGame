package persistence;

import jakarta.persistence.EntityManager;
import model.domain.InventoryDomain;
import model.domain.PlayerDomain;
import model.entity.Player;

public interface PlayerDao {

    void savePlayer(PlayerDomain player);

    void updatePlayer(PlayerDomain player);

    InventoryDomain getInventorytoShow(PlayerDomain pd);
}
