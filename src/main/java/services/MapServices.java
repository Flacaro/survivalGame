package services;

import java.util.ArrayList;
import java.util.List;

import model.domain.AreaDomain;
import model.domain.MapDomain;
import model.domain.ModeDomain;
import model.domain.PlayerDomain;
import model.entity.Area;
import model.entity.Map;

public class MapServices {

    public List<AreaDomain> setTotalMapArea(ModeDomain modeDomain) {

        int totalArea = (int) modeDomain.getTotalArea();
        List<AreaDomain> areaDomains = new ArrayList<>();
        for (int c = 0; c < totalArea; c++) {
            AreaDomain a = new AreaDomain("area", "deserto");
            areaDomains.add(a);
        }
        return areaDomains;
    }

    public List<AreaDomain> setAdjacentArea(List<AreaDomain> totalMapArea, PlayerDomain p) {
        long currentArea=p.getIdArea();
        List<AreaDomain> adjacentAreaDomains = new ArrayList<>();
        for (AreaDomain a :totalMapArea) {
            AreaDomain a = new AreaDomain("area", "deserto");
            adjacentAreaDomains.add(a);
        }
        return adjacentAreaDomains;
    }

    public Map mapMapper(MapDomain mapDomain) {
        Map map = new Map();
        map.setId(mapDomain.getId());

        AreaService areaService=new AreaService();
        List<Area> list= new ArrayList<>();
        List<AreaDomain> domainList= mapDomain.getAreas();
        for (AreaDomain a :domainList){
            list.add(areaService.areaMapper(a));
        }
        map.setAreas(list);
        return map;
    }


    public MapDomain mapDomainMapper(Map map) {
        MapDomain mapDomain1 = new MapDomain();
        mapDomain1.setId(map.getId());

        AreaService areaService=new AreaService();
        List<AreaDomain> listDomain = new ArrayList<>();
        List<Area> list = map.getAreas();
        for (Area a : list){
            listDomain.add(areaService.areaDomainMapper(a));
        }
        mapDomain1.setAreas(listDomain);
        return mapDomain1;
    }
}
