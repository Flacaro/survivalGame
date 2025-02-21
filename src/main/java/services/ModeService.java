package services;

import model.domain.ModeDomain;
import model.entity.Mode;

public class ModeService {

    public Mode modeMapper(ModeDomain modeDomain) {
        Mode mode = new Mode();
        mode.setDescription(modeDomain.getDescription());
        mode.setNumEnemy(modeDomain.getNumEnemy());
        mode.setNumResources(modeDomain.getNumResources());
        mode.setTotalArea(modeDomain.getTotalArea());
        return mode;
    }
}
