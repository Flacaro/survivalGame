package model.domain;


public class Game {

    private long id;

    private int status;

    private Mode mode;

    private Player player;

    private Map map;

    public Game() {
    }

    public Game(int status, Mode mode, Player player, Map map) {
        this.status = status;
        this.mode = mode;
        this.player = player;
        this.map = map;
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
}
