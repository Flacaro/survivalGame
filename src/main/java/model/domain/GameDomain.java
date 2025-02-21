package model.domain;


public class GameDomain {

    private long id;

    private int status;

    private ModeDomain modeDomain;

    private PlayerDomain playerDomain;

    private MapDomain mapDomain;

    public GameDomain() {
    }

    public GameDomain(int status, ModeDomain modeDomain, PlayerDomain playerDomain, MapDomain mapDomain) {
        this.status = status;
        this.modeDomain = modeDomain;
        this.playerDomain = playerDomain;
        this.mapDomain = mapDomain;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ModeDomain getModeDomain() {
        return modeDomain;
    }

    public void setModeDomain(ModeDomain modeDomain) {
        this.modeDomain = modeDomain;
    }

    public PlayerDomain getPlayerDomain() {
        return playerDomain;
    }

    public void setPlayerDomain(PlayerDomain playerDomain) {
        this.playerDomain = playerDomain;
    }

    public MapDomain getMapDomain() {
        return mapDomain;
    }

    public void setMapDomain(MapDomain mapDomain) {
        this.mapDomain = mapDomain;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ModeDomain getMode() {
        return modeDomain;
    }

    public void setMode(ModeDomain modeDomain) {
        this.modeDomain = modeDomain;
    }

    public PlayerDomain getPlayer() {
        return playerDomain;
    }

    public void setPlayer(PlayerDomain playerDomain) {
        this.playerDomain = playerDomain;
    }

    public MapDomain getMap() {
        return mapDomain;
    }

    public void setMap(MapDomain mapDomain) {
        this.mapDomain = mapDomain;
    }
}
