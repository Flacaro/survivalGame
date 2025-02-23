package services;

import model.domain.CheckpointDomain;
import model.entity.Checkpoint;

public class CheckpointService {

    AreaService areaService=new AreaService();
    SkillServices skillServices= new SkillServices();

    public Checkpoint checkpointMapper(CheckpointDomain checkpointDomain) {
        Checkpoint checkpoint = new Checkpoint();
        checkpoint.setExp(checkpointDomain.getExp());
        checkpoint.setDescription(checkpointDomain.getDescription());
        checkpoint.setArea(areaService.areaMapper(checkpointDomain.getArea()));
        checkpoint.setSkill(skillServices.skillMapper(checkpointDomain.getSkill()));
        return checkpoint;
    }

    public CheckpointDomain checkpointDomainMapper(Checkpoint checkpoint) {
        CheckpointDomain checkpointDomain = new CheckpointDomain();
        checkpointDomain.setExp(checkpoint.getExp());
        checkpointDomain.setDescription(checkpoint.getDescription());
        checkpointDomain.setArea(areaService.areaDomainMapper(checkpoint.getArea()));
        checkpointDomain.setSkill(skillServices.skillDomainMapper(checkpoint.getSkill()));
        return checkpointDomain;
    }
}
