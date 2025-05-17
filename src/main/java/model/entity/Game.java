package model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GAME")
public class Game {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "STATUS", nullable = false)
    private int status;


    //uno ad uno con modalità
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_mode", referencedColumnName = "id")
    private Mode mode;


    //uno ad uno con player, il player possiede la relazione
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_player", referencedColumnName = "id")
    private Player player;


    //uno ad uno con la mappa
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_map", referencedColumnName = "id")
    private Map map;

    public Game() {
    }

    public Game(int i, Mode modeDomain, Player playerDomain, Map mapDomain) {
        status = i;
        mode = modeDomain;
        player = playerDomain;
        map = mapDomain;
    }


    public long getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setId(long id) {
        this.id = id;
    }


    public boolean move(int position) {
        Player p = this.getPlayer();
        int x_axis = p.getX_axis();
        int y_axis = p.getY_axis();
        Mode m = this.getMode();
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
                    p.setId_Area(p.newPosition(this));
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
                    p.setId_Area(p.newPosition(this));
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
                    p.setId_Area(p.newPosition(this));
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
                    p.setId_Area(p.newPosition(this));
                    return true;
                }
                return false;
        }
        return false;
    }

    public boolean pickUp(SimpleResource r, Player player) {
        Inventory id = player.getInventory();
        ArrayList<SimpleResource> res= new ArrayList<>();
        res.add(r);
        if (id.checkCapacity(id)) {
            id.updateInventory(res);
        }
        return id.checkCapacity(id);
    }
}