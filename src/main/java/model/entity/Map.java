package model.entity;

import controller.DBController;
import jakarta.persistence.*;
import persistence.MapDaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        List<Area> areadb = dbController.getAreas();
        List<Area> areaDomains = areadb.subList(0, totalArea - 1);
        return areaDomains;
    }
}