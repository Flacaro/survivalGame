package model.domain;


import model.entity.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MapDomain {

    private long id;

    private List<AreaDomain> areaDomains = new ArrayList<>();

    public MapDomain() {
    }



    public List<AreaDomain> getAreas() {
        return areaDomains;
    }

    public void setAreas(List<AreaDomain> areaDomains) {
        this.areaDomains = areaDomains;
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
        MapDomain mapDomain = (MapDomain) o;
        return id == mapDomain.id && Objects.equals(areaDomains, mapDomain.areaDomains);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, areaDomains);
    }
}
