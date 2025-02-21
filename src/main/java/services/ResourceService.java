package services;

import model.domain.AttackDomain;
import model.domain.ResourceDomain;
import model.entity.Attack;
import model.entity.Resource;

import java.util.ArrayList;
import java.util.List;

public class ResourceService {


    public Resource resourceMapper(ResourceDomain rd) {
        AttackServices attackServices=new AttackServices();
        Resource r = new Resource();
        r.setId(rd.getId());
        r.setCategory(rd.getCategory());
        List<Attack> attackList= new ArrayList<>();
        List<AttackDomain> domainList= rd.getAttacks();
        for (AttackDomain a :domainList){
            attackList.add(attackServices.attackMapper(a));
        }
        r.setAttacks(attackList);
        r.setLevel(rd.getLevel());
        r.setName(rd.getName());
        r.setQuantity(rd.getQuantity());
        r.setType(rd.getType());
        return r;
    }
}
