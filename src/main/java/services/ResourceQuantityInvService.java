package services;

import controller.DBController;
import model.domain.ResourceQuantityInvDomain;
import model.entity.ResourceQuantityInv;

public class ResourceQuantityInvService {

    public ResourceQuantityInv resourceQuantityInvMapper(ResourceQuantityInvDomain rqid) {
        ResourceQuantityInv quantity = new ResourceQuantityInv();
        quantity.setQuantity(rqid.getQuantity());
        quantity.setResource(rqid.getResource());
        quantity.setId(rqid.getId());
        quantity.setInventory(rqid.getInventory());
        return quantity;
    }

    public ResourceQuantityInvDomain resourceQuantityInvDomainMapper(ResourceQuantityInv resourceQuantityInv) {
        ResourceQuantityInvDomain quantityInvDomain = new ResourceQuantityInvDomain();
        quantityInvDomain.setId(resourceQuantityInv.getId());
        quantityInvDomain.setResource(resourceQuantityInv.getResource());
        quantityInvDomain.setQuantity(resourceQuantityInv.getQuantity());
        quantityInvDomain.setInventory(resourceQuantityInv.getInventory());
        return quantityInvDomain;
    }

    public void remove(ResourceQuantityInvDomain resourceQuantityInvDomain) {
        DBController dbController = new DBController();
        dbController.removeQuantity(resourceQuantityInvDomain);
    }
}
