package services;

import jakarta.persistence.EntityManager;
import model.domain.AreaDomain;
import model.domain.GameDomain;
import model.entity.Area;
import persistence.AreaDaoImpl;

import java.util.ArrayList;
import java.util.List;


public class AreaService {

    private CheckpointService checkpointService;
    private ClimateService climateService;
    private AreaDaoImpl areaDaoImpl = new AreaDaoImpl();

    public Area areaMapper(AreaDomain areaDomain) {
        if (areaDomain == null) {
            return null;
        }
        Area area = new Area();
        area.setId(areaDomain.getId()); // Corretto
        area.setName(areaDomain.getName());
        area.setDescription(areaDomain.getDescription());
        area.setIdEvent(areaDomain.getIdEvent());
        area.setCategory(areaDomain.getCategory());

        if (areaDomain.getCheckpoint() != null) {
            area.setCheckpoint(checkpointService.checkpointMapper(areaDomain.getCheckpoint()));
        }

        // Conversione di ClimateDomain in Climate (se necessario)
        if (areaDomain.getClimate() != null) {
            area.setClimate(climateService.climateMapper(areaDomain.getClimate()));
        }

        return area;
    }

    public AreaDomain areaDomainMapper(Area area) {
        if (area == null) {
            return null;
        }
        AreaDomain areaDomain = new AreaDomain();
        areaDomain.setId(area.getId()); // Corretto
        areaDomain.setName(area.getName());
        areaDomain.setDescription(area.getDescription());
        areaDomain.setIdEvent(area.getIdEvent());
        areaDomain.setCategory(area.getCategory());

        if (area.getCheckpoint() != null) {
            areaDomain.setCheckpoint(checkpointService.checkpointDomainMapper(area.getCheckpoint()));
        }

        // Conversione di ClimateDomain in Climate (se necessario)
        if (area.getClimate() != null) {
            areaDomain.setClimate(climateService.climateDomainMapper(area.getClimate()));
        }

        return areaDomain;
    }


    public void updateArea(List<AreaDomain> areas, EntityManager em) {
        areaDaoImpl.updateArea(areas, em);
    }

    public ArrayList<AreaDomain> getAreas(EntityManager em) {
        return areaDaoImpl.getAreas(em);
    }

    /*public long setNewIdAreaxVariant(int x_axis, GameDomain g) {
        List<AreaDomain> areaxVariant= new ArrayList<>();
        List<AreaDomain> areas=g.getMap().getAreas();
        int range= (int) (g.getMode().getTotalArea()/2);
        areaxVariant=areas.subList(0,range-1);
        return areaxVariant.get(x_axis).getId();
    }

    public long setNewIdAreayVariant(int y_axis, GameDomain g) {
        List<AreaDomain> areayVariant= new ArrayList<>();
        List<AreaDomain> areas=g.getMap().getAreas();
        int range= (int) (g.getMode().getTotalArea()/2);
        areayVariant=areas.subList(range+1,areas.size()-1);
        return areayVariant.get(y_axis).getId();
    }


 }*/
    public long setNewIdAreaxVariant(int x_axis, GameDomain g) {
        List<AreaDomain> areaxVariant = new ArrayList<>();
        List<AreaDomain> areas = g.getMap().getAreas();
        int range = (int) (g.getMode().getTotalArea() / 2);
        areaxVariant = areas.subList(0, Math.max(0, range - 1)); //Avoid negative index in sublist.

        if (x_axis >= 0 && x_axis < areaxVariant.size() && areaxVariant.size() > 0) {
            return areaxVariant.get(x_axis).getId();
        } else {
            System.err.println("Errore: x_axis fuori dai limiti in setNewIdAreaxVariant.");
            return -1; // o lancia un'eccezione
        }
    }

    public long setNewIdAreayVariant(int y_axis, GameDomain g) {
        List<AreaDomain> areayVariant = new ArrayList<>();
        List<AreaDomain> areas = g.getMap().getAreas();
        int range = (int) (g.getMode().getTotalArea() / 2);
        int startIndex = Math.min(range + 1, areas.size() - 1); // Avoid index out of bound.
        areayVariant = areas.subList(startIndex, areas.size() - 1);

        if (y_axis >= 0 && y_axis < areayVariant.size() && areayVariant.size() > 0) {
            return areayVariant.get(y_axis).getId();
        } else {
            System.err.println("Errore: y_axis fuori dai limiti in setNewIdAreayVariant.");
            return -1; // o lancia un'eccezione
        }
    }
}