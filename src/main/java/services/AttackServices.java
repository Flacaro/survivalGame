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
}
