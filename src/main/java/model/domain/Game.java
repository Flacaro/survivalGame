package model.domain;

import model.domain.Event;
import model.domain.Mode;
import model.domain.Player;

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

    public void triggerEvent(Event event) {
        // TODO - implement Game.triggerEvent
        throw new UnsupportedOperationException();
    }


    public boolean verifyAnswer(Event event, boolean answer) {
        // TODO - implement Game.verifyAnswer
        throw new UnsupportedOperationException();
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
