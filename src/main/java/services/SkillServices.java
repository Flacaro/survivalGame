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

    public SkillDomain skillDomainMapper(Skill skill1){
        SkillDomain skill=new SkillDomain();
        skill.setId(skill1.getId());
        skill.setName(skill1.getName());
        skill.setDescription(skill1.getDescription());
        skill.setLevel(skill1.getLevel());
        skill.setCheckpoint(skill1.getCheckpoint());
        return skill;
    }
}
