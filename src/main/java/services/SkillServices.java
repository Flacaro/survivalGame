package services;

import model.domain.SkillDomain;
import model.entity.Skill;

public class SkillServices {

    public Skill skillMapper(SkillDomain skillDomain){
        Skill skill=new Skill();
        skill.setId(skillDomain.getId());
        skill.setName(skillDomain.getName());
        skill.setDescription(skillDomain.getDescription());
        skill.setLevel(skillDomain.getLevel());
        skill.setCheckpoint(skillDomain.getCheckpoint());
        return skill;
    }
}
