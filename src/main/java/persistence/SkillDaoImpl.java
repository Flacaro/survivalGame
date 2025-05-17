package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.entity.Enemy;
import model.entity.Event;
import model.entity.Skill;
import persistence.dao.SkillDao;

import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl implements SkillDao {

    @Override
    public void saveSkill(Skill skill, EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.merge(skill);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public List<Skill> getSkills(EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            TypedQuery<Skill> query = em.createQuery("SELECT s FROM Skill s", Skill.class);

            ArrayList<Skill> skills = new ArrayList<>();
            for (Skill s : query.getResultList()) {
                skills.add(s);
            }
            return skills;

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return null;
    }
}
