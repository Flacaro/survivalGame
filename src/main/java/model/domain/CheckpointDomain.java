package model.domain;

public class CheckpointDomain {

    private long id;

    private String description;

    private int exp;

    private AreaDomain area;

    private SkillDomain skill;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public AreaDomain getArea() {
        return area;
    }

    public void setArea(AreaDomain area) {
        this.area = area;
    }

    public SkillDomain getSkill() {
        return skill;
    }

    public void setSkill(SkillDomain skill) {
        this.skill = skill;
    }
}
