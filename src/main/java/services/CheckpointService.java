package services;

import model.domain.CheckpointDomain;
import model.entity.Checkpoint;

public class CheckpointService {

    public Checkpoint checkpointMapper(CheckpointDomain checkpointDomain) {
        Checkpoint checkpoint = new Checkpoint();
        checkpoint.setExp(checkpointDomain.getExp());
        checkpoint.setDescription(checkpointDomain.getDescription());
        checkpoint.setArea(checkpointDomain.getArea());
        checkpoint.setSkill(checkpointDomain.getSkill());
    }
}
