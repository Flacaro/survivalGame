package services;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import model.domain.AreaDomain;
import model.domain.GameDomain;
import model.entity.Area;
import persistence.AreaDaoImpl;
import persistence.EntityManagerSingleton;

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

    public long setNewIdAreaxVariant(int x_axis, GameDomain g) {
        List<AreaDomain> areaxVariant= new ArrayList<>();
        List<AreaDomain> areas=g.getMap().getAreas();
        int range= (int) (g.getMode().getTotalArea()/2);
        areaxVariant=areas.subList(0,range);
        return areaxVariant.get(x_axis-1).getId();
    }

    public long setNewIdAreayVariant(int y_axis, GameDomain g) {
        List<AreaDomain> areayVariant= new ArrayList<>();
        List<AreaDomain> areas=g.getMap().getAreas();
        int range= (int) (g.getMode().getTotalArea()/2);
        areayVariant=areas.subList(range+1,areas.size()-1);
        return areayVariant.get(y_axis-1).getId();
    }



    public AreaDomain getAreaById(EntityManager em, long idArea) {
            return areaDaoImpl.getAreaById(em,idArea);
    }
}