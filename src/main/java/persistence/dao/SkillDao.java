package persistence.dao;

import jakarta.persistence.EntityManager;
import model.entity.Skill;

import java.util.List;

public interface SkillDao {

    void saveSkill(Skill skill, EntityManager em);

    List<Skill> getSkills(EntityManager em);
}
