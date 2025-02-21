package model.domain;

import model.entity.Enemy;
import model.entity.Inventory;
import model.entity.Resource;
import model.entity.Skill;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private long id;

    private String nickname;

    private float health;

    private int level = 1;

    private long position;

    private Inventory inventory;

    private List<Skill> skills= new ArrayList<>();

    public Player(String nickname, float health, int level) {
        this.nickname = nickname;
        this.health = health;
        this.level = level;
    }

    public boolean pickUp(Resource res) {
        // TODO - implement Player.pickUp
        throw new UnsupportedOperationException();
    }


    public boolean attack(Enemy enemy, Resource res) {
        // TODO - implement Player.attack
        throw new UnsupportedOperationException();
    }


    public void move(int position) {
        // TODO - implement Player.move
        throw new UnsupportedOperationException();
    }


    public void openInventory(Inventory inv) {
        // TODO - implement Player.openInventory
        throw new UnsupportedOperationException();
    }

    public boolean combineResources() {
        // TODO - implement Player.combineResources
        throw new UnsupportedOperationException();
    }


    public void useSkill(String skill) {
        // TODO - implement Player.useSkill
        throw new UnsupportedOperationException();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
