package persistence;

import jakarta.persistence.EntityManager;
import model.entity.Inventory;
import model.entity.Player;


public interface PlayerDao {

    void updatePlayer(Player player, EntityManager em);

    Inventory getInventoryToShow(Player pd, EntityManager em);
}
