package services;

import model.GameFactorySingleton;
import model.domain.AreaDomain;
import model.domain.MapDomain;
import model.domain.ModeDomain;
import model.domain.ResourceDomain;
import model.entity.Area;
import model.entity.Map;
import model.entity.Resource;

import java.util.ArrayList;
import java.util.List;

public class MapServices {

    public List<AreaDomain> setTotalMapArea(ModeDomain modeDomain) {

        GameFactorySingleton gms = GameFactorySingleton.getInstance();
        int totalArea = (int) modeDomain.getTotalArea();
        List<AreaDomain> areaDomains = new ArrayList<>();
        for (int c = 0; c < totalArea; c++) {
            AreaDomain a = new AreaDomain("area", "deserto");
            areaDomains.add(a);
        }
        //vanno salvate tutte le aree nel db altrimenti non hanno id e non matcha con le risorse
        gms.createEvent(areaDomains, modeDomain);
        return areaDomains;
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
}
