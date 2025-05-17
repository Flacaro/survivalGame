package model.entity;

import controller.DBController;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MAP")
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.DETACH)
    private List<Area> areas = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public List<Area> setTotalMapArea(Mode modeDomain) {

        int totalArea = (int) modeDomain.getTotalArea();
        DBController dbController = new DBController();
        List<Area> areaDb = dbController.getAreas();
        return areaDb.subList(0, totalArea - 1);
    }
}