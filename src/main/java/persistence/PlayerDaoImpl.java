package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.entity.Inventory;
import model.entity.Player;
import persistence.dao.PlayerDao;

public class PlayerDaoImpl implements PlayerDao {


    @Override
    public void updatePlayer(Player player, EntityManager em) {
        try {

            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            Player player1 = em.find(Player.class, player.getId()); // Trova l'oggetto con ID 1
            if (player1 != null) {
                player1.setId_Area(player.getId_Area());
                player1.setX_axis(player.getX_axis());
                player1.setY_axis(player.getY_axis());
                player1.setExp(player.getExp());
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }


    }

    @Override
    public Inventory getInventoryToShow(Player pd, EntityManager em) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }

            TypedQuery<Inventory> queryInventory = em.createQuery("SELECT i FROM Inventory i", Inventory.class);
            if (queryInventory.getSingleResult() == null) {
                System.out.println("tab inventario è vuota");
                return null;
            }
            return queryInventory.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return null;

    }

}
