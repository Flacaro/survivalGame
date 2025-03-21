package services;

import controller.DBController;
import model.domain.*;
import model.entity.Player;

public class PlayerService {


    private final InventoryService inventoryService = new InventoryService();

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
            return inventoryService.updateInventory(res, id);
        }
        return false;
    }

    public boolean move(int position, GameDomain g) {
        DBController dbController = new DBController();
        AreaService a = new AreaService();
        PlayerDomain p = g.getPlayer();
        int x_axis = p.getX_axis();
        int y_axis = p.getY_axis();
        ModeDomain m = g.getMode();
        int range = (int) (m.getTotalArea() / 2) - 1;
        int rangey = 1;
        switch (position) {
            case 0:
                //nord x=x y=y-1
                if (y_axis - 1 >= 0 && y_axis - 1 <= rangey) {
                    y_axis = y_axis - 1;
                    //update player;
                    p.setX_axis(x_axis);
                    p.setY_axis(y_axis);
                    p.setIdArea(a.setNewIdAreayVariant(y_axis, g));
                    dbController.updatePlayer(p);
                    return true;
                }
                return false;
            case 1:
                //est x=x+1 y=y
                if (x_axis + 1 >= 0 && x_axis + 1 <= range) {
                    x_axis = x_axis + 1;
                    //update player;
                    p.setX_axis(x_axis);
                    p.setY_axis(y_axis);
                    p.setIdArea(a.setNewIdAreaxVariant(x_axis, g));
                    dbController.updatePlayer(p);
                    return true;
                }
                return false;
            case 2:
                //sud x=x y=y+1
                if (y_axis + 1 >= 0 && y_axis + 1 <= rangey) {
                    y_axis = y_axis + 1;
                    //update player;
                    p.setX_axis(x_axis);
                    p.setY_axis(y_axis);
                    p.setIdArea(a.setNewIdAreayVariant(y_axis, g));
                    dbController.updatePlayer(p);
                    return true;
                }
                return false;
            case 3:
                //ovest x=x-1 y=y
                if (x_axis - 1 >= 0 && x_axis - 1 <= range) {
                    x_axis = x_axis - 1;
                    //update player;
                    p.setX_axis(x_axis);
                    p.setY_axis(y_axis);
                    p.setIdArea(a.setNewIdAreaxVariant(x_axis, g));
                    dbController.updatePlayer(p);
                    return true;
                }
                return false;
        }
        return false;
    }

}
