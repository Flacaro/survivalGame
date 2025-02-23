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

    public ModeDomain modeDomainMapper(Mode mode1) {
        ModeDomain mode = new ModeDomain();
        mode.setDescription(mode1.getDescription());
        mode.setNumEnemy(mode1.getNumEnemy());
        mode.setNumResources(mode1.getNumResources());
        mode.setTotalArea(mode1.getTotalArea());
        return mode;
    }
}
