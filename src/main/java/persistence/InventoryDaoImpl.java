package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.entity.Inventory;
import model.entity.Resource;

import java.util.ArrayList;

public class InventoryDaoImpl implements InventoryDao{
    @Override
    public Inventory getInventory(long idInventory) {
        EntityManager em = EntityManagerSingleton.getEntityManager();

        Inventory inventory;
        try {
            // Query per ottenere l'inventory selezionando l'id
            TypedQuery<Inventory> query = em.createQuery("SELECT c FROM Inventory c WHERE c.id= :idInventory", Inventory.class);
            query.setParameter("idInventory", idInventory);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return null;
    }

    @Override
    public String getNameResource() {
        return "";
    }

    @Override
    public boolean removeResourcesFromInventory(ArrayList<Resource> selections) {

        return false;
    }
}
