package services;

import model.domain.AreaDomain;
import model.domain.MapDomain;
import model.entity.Area;
import model.entity.Map;

public class AreaService {

    public Area areaMapper(AreaDomain areaDomain) {
        Area area = new Area();
        area.setId(area.getId());
        area.setName(areaDomain.getName());
        area.setDescription(area.getDescription());
        area.setIdEvent(area.getIdEvent());
        area.setCheckpoint(areaDomain.getCheckpoint());

    }
}
