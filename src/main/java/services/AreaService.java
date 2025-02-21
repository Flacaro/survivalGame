package services;

import model.domain.AreaDomain;
import model.entity.Area;


public class AreaService {

    private CheckpointService checkpointService;
    private ClimateService climateService;

    public Area areaMapper(AreaDomain areaDomain) {
        if (areaDomain == null) {
            return null;
        }

        Area area = new Area();
        area.setId(areaDomain.getId()); // Corretto
        area.setName(areaDomain.getName());
        area.setDescription(areaDomain.getDescription());
        area.setIdEvent(areaDomain.getIdEvent());

        if (areaDomain.getCheckpoint() != null) {
            area.setCheckpoint(checkpointService.checkpointMapper(areaDomain.getCheckpoint()));
        }

        // Conversione di ClimateDomain in Climate (se necessario)
        if (areaDomain.getClimate() != null) {
            area.setClimate(climateService.climateMapper(areaDomain.getClimate()));
        }

        return area;
    }
}
