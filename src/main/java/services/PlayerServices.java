package services;

import model.domain.PlayerDomain;
import model.entity.Enemy;
import model.entity.Inventory;
import model.entity.Player;
import model.entity.Resource;

public class PlayerServices {

    public Player playerMapper(PlayerDomain playerDomain){
        Player player=new Player();
        player.setId(playerDomain.getId());
        player.setNickname(playerDomain.getNickname());
        player.setHealth(playerDomain.getHealth());
        player.setLevel(playerDomain.getLevel());
        player.setIdArea(playerDomain.getIdArea());
        player.setInventory(playerDomain.getInventory());
        player.setSkills(playerDomain.getSkills());
        return player;

    }

    public PlayerDomain playerDomainMapper(Player player1){
        PlayerDomain player=new PlayerDomain();
        player.setId(player1.getId());
        player.setNickname(player1.getNickname());
        player.setHealth(player1.getHealth());
        player.setLevel(player1.getLevel());
        player.setIdArea(player1.getIdArea());
        player.setInventory(player1.getInventory());
        player.setSkills(player1.getSkills());
        return player;

    }

    public boolean pickUp(Resource res) {
        // TODO - implement Player.pickUp
        throw new UnsupportedOperationException();
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
