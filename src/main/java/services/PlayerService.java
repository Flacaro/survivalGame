package services;

import model.domain.PlayerDomain;
import model.entity.Player;

public class PlayerService {

    private InventoryService is;

    public Player playerMapper(PlayerDomain p) {
        Player player = new Player();
        player.setHealth(p.getHealth());
        player.setLevel(p.getLevel());
        player.setInventory();
    }
}
