package services;

import model.domain.AttackDomain;
import model.entity.Attack;

public class AttackServices {

    public Attack attackMapper(AttackDomain attackDomain){
        Attack attack= new Attack();
        attack.setId(attackDomain.getId());
        attack.setName(attackDomain.getName());
        attack.setType(attackDomain.getType());
        attack.setDamage(attackDomain.getDamage());
        return attack;
    }
    public AttackDomain attackDomainMapper(Attack attack){
        AttackDomain attackDomain= new AttackDomain();
        attackDomain.setId(attack.getId());
        attackDomain.setName(attack.getName());
        attackDomain.setType(attack.getType());
        attackDomain.setDamage(attack.getDamage());
        return attackDomain;
    }
}
