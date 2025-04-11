package model.entity;

import controller.DBController;
import jakarta.persistence.*;
import persistence.MapDaoImpl;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MAP")
public class Map {

    @Id
    @GeneratedValue
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

//        List<AreaDomain> areaDomains=new ArrayList<>();
        int totalArea = (int) modeDomain.getTotalArea();
        DBController dbController=new DBController();
        List<Area> areadb=dbController.getAreas();
        List<Area> areaDomains =areadb.subList(0,totalArea-1);
//        for (int c = 0; c < totalArea; c++) {
//            AreaDomain a = new AreaDomain("area", "deserto");
//            areaDomains.add(a);
//        }
        return areaDomains;
    }
}