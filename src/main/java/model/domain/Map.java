package model.domain;


import model.GameFactorySingleton;
import model.entity.Event;
import model.entity.Mode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Map {

    private long id;

    private List<Area> areas = new ArrayList<>();

    public Map() {
    }

    public void setTotalMapArea(Mode mode) {
        GameFactorySingleton gms = GameFactorySingleton.getInstance();
        int totalArea = (int) mode.getTotalArea();
        for (int c = 0; c < totalArea; c++) {
            Area a = new Area("area", "deserto");
            this.areas.add(a);
        }
        //vanno salvate tutte le aree nel db altrimenti non hanno id e non matcha con le risorse
        gms.createEvent(areas, mode);
        setAreas(areas);
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public Event getEvent() {
        // TODO - implement Map.getEvent
        throw new UnsupportedOperationException();
    }


    public int setplayerPosition(int position) {
        // TODO - implement Map.setplayerPosition
        throw new UnsupportedOperationException();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Map map = (Map) o;
        return id == map.id && Objects.equals(areas, map.areas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, areas);
    }
}
