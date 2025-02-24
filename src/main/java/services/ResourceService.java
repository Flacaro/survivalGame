package services;

import model.domain.AttackDomain;
import model.domain.ResourceDomain;
import model.entity.Attack;
import model.entity.Resource;
import persistence.ResourceDaoImpl;

import java.util.ArrayList;
import java.util.List;

public class ResourceService {

    private ResourceDaoImpl resourceDaoImpl = new ResourceDaoImpl();


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

    public ResourceDomain resourceDomainMapper(Resource r) {
        AttackServices attackServices=new AttackServices();
        ResourceDomain resourceDomain = new ResourceDomain();
        resourceDomain.setId(r.getId());
        resourceDomain.setCategory(r.getCategory());
        List<Attack> attackList=r.getAttacks();
        List<AttackDomain> domainList=  new ArrayList<>();

        for (Attack a :attackList){
            domainList.add(attackServices.attackDomainMapper(a));
        }
        resourceDomain.setAttacks(domainList);
        resourceDomain.setLevel(r.getLevel());
        resourceDomain.setName(r.getName());
        resourceDomain.setQuantity(r.getQuantity());
        resourceDomain.setType(r.getType());
        return resourceDomain;
    }

    public ArrayList<ResourceDomain> getResources() {
        return resourceDaoImpl.getResources();
    }


}
