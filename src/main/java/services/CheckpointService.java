package services;

import model.domain.CheckpointDomain;
import model.domain.MapDomain;
import model.entity.Checkpoint;
import model.entity.Map;

public class CheckpointService {

    public Checkpoint mapMapper(CheckpointDomain checkpointDomain) {
        Checkpoint checkpoint = new Checkpoint();
        checkpoint.setExp(checkpointDomain.getExp());
        checkpoint.setDescription(checkpointDomain.getDescription());
        checkpoint.setArea(checkpointDomain.getArea());
        checkpoint.setSkill(checkpointDomain.getSkill());
    }
}
