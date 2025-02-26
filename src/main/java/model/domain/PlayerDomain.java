package model.domain;

import jakarta.persistence.Column;
import model.entity.Enemy;
import model.entity.Inventory;
import model.entity.Resource;
import model.entity.Skill;

import java.util.ArrayList;
import java.util.List;

public class PlayerDomain {

    private long id;

    private String nickname;

    private float health;

    private int level = 1;

    private long idArea = 1;

    private InventoryDomain inventory;

    private List<Skill> skills= new ArrayList<>();

    private long x_axis=0;

    private long y_axis=0;


    public PlayerDomain(String nickname, float health, int level) {
        this.nickname = nickname;
        this.health = health;
        this.level = level;
        this.inventory=new InventoryDomain();
    }

    public PlayerDomain() {

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

    public long getIdArea() {
        return idArea;
    }

    public void setIdArea(long idArea) {
        this.idArea = idArea;
    }

    public InventoryDomain getInventory() {
        return inventory;
    }

    public void setInventory(InventoryDomain inventory) {
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

    public long getX_axis() {
        return x_axis;
    }

    public void setX_axis(long x_axis) {
        this.x_axis = x_axis;
    }

    public long getY_axis() {
        return y_axis;
    }

    public void setY_axis(long y_axis) {
        this.y_axis = y_axis;
    }
}
