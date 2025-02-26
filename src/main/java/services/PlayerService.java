package services;

import model.domain.InventoryDomain;
import model.domain.PlayerDomain;
import model.domain.ResourceDomain;
import model.entity.Enemy;
import model.entity.Inventory;
import model.entity.Player;
import model.entity.Resource;
import persistence.PlayerDaoImpl;

public class PlayerService {

    private InventoryService inventoryService = new InventoryService();

    public Player playerMapper(PlayerDomain playerDomain) {
        Player player = new Player();
        InventoryService service = new InventoryService();
        player.setId(playerDomain.getId());
        player.setNickname(playerDomain.getNickname());
        player.setHealth(playerDomain.getHealth());
        player.setLevel(playerDomain.getLevel());
        player.setIdArea(playerDomain.getIdArea());
        player.setInventory(service.inventoryMapper(playerDomain.getInventory()));
        player.setSkills(playerDomain.getSkills());
        player.setX_axis(playerDomain.getX_axis());
        player.setY_axis(playerDomain.getY_axis());
        return player;

    }

    public PlayerDomain playerDomainMapper(Player player1) {
        PlayerDomain player = new PlayerDomain();
        InventoryService service = new InventoryService();
        player.setId(player1.getId());
        player.setNickname(player1.getNickname());
        player.setHealth(player1.getHealth());
        player.setLevel(player1.getLevel());
        player.setIdArea(player1.getIdArea());
        player.setInventory(service.inventoryDomainMapper(player1.getInventory()));
        player.setSkills(player1.getSkills());
        player.setX_axis(player1.getX_axis());
        player.setY_axis(player1.getY_axis());
        return player;

    }

    //caso in cui il giocatore prende la risorsa
    //controllo la capienza dell'inventario
    //se c'e' spazio, lo aggiungo all'inventario altrimenti "non hai spazio!"
    public boolean pickUp(ResourceDomain res, PlayerDomain player) {
        InventoryDomain id = player.getInventory();
        if (inventoryService.checkCapacity(id)) {
            id.add(res);
            id.setCapacity(id.getCapacity() - 1);
            player.setInventory(id);
            updatePlayer(player);
            return true;
        }
        return false;
    }



    public void updatePlayer(PlayerDomain player) {
        PlayerDaoImpl playerDaoImpl = new PlayerDaoImpl();
        playerDaoImpl.updatePlayerInventory(player);
    }

    public boolean attack(Enemy enemy, Resource res) {
        // TODO - implement Player.attack
        throw new UnsupportedOperationException();
    }


    public void move(int position) {
        // TODO - implement Player.move
        throw new UnsupportedOperationException();
    }


    public void openInventory(Inventory inv) {
        // TODO - implement Player.openInventory
        throw new UnsupportedOperationException();
    }

    public boolean combineResources() {
        // TODO - implement Player.combineResources
        throw new UnsupportedOperationException();
    }


    public void useSkill(String skill) {
        // TODO - implement Player.useSkill
        throw new UnsupportedOperationException();
    }
}
