package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.domain.InventoryDomain;
import model.domain.PlayerDomain;
import model.entity.Inventory;
import model.entity.Player;
import services.InventoryService;
import services.PlayerService;


public class PlayerDaoImpl implements PlayerDao {

    @Override
    public void savePlayer(PlayerDomain player) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        try {
            PlayerService playerServices=new PlayerService();
            if(!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.persist(playerServices.playerMapper(player));
            // Salva nel database
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

    }

    @Override
    public void updatePlayer(PlayerDomain player) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
            try {

                if (!em.getTransaction().isActive()) {
                    em.getTransaction().begin();
                }
                    Player player1 = em.find(Player.class, player.getId()); // Trova l'oggetto con ID 1
                    if (player1 != null) {
                        player1.setIdArea(player.getIdArea());
                        player1.setX_axis(player.getX_axis());
                        player1.setY_axis(player.getY_axis());
                    }
                em.getTransaction().commit();

            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
            }


    }

    @Override
    public InventoryDomain getInventorytoShow(PlayerDomain pd) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            InventoryService inventoryService= new InventoryService();

            TypedQuery<Inventory> queryInventory = em.createQuery("SELECT i FROM Inventory i", Inventory.class);
            if(queryInventory.getSingleResult()==null){
                System.out.println("tab inventario è vuota");
                return null;
            }
            return inventoryService.inventoryDomainMapper(queryInventory.getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return  null;

    }

}
