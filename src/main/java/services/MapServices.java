package services;

import model.GameFactorySingleton;
import model.domain.Area;
import model.domain.Mode;

import java.util.ArrayList;
import java.util.List;

public class MapServices {

    public List<Area> setTotalMapArea(Mode mode) {

        GameFactorySingleton gms = GameFactorySingleton.getInstance();
        int totalArea = (int) mode.getTotalArea();
        List<Area> areas= new ArrayList<>();
        for (int c = 0; c < totalArea; c++) {
            Area a = new Area("area", "deserto");
            areas.add(a);
        }
        //vanno salvate tutte le aree nel db altrimenti non hanno id e non matcha con le risorse
        gms.createEvent(areas, mode);
        return areas;
    }
}
