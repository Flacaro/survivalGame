package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import model.domain.AreaDomain;
import model.domain.GameDomain;
import model.domain.PlayerDomain;
import model.entity.*;
import services.*;

import java.util.ArrayList;
import java.util.List;

public class PlayerDaoImpl implements PlayerDao {

    public void savePlayer(PlayerDomain player, EntityManager em) {
        try {
            PlayerService playerService = new PlayerService();
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.persist(playerService.playerMapper(player));
            // Salva nel database
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

    }

    public PlayerDomain getPlayer(EntityManager em) {
        try {
            PlayerService playerService = new PlayerService();
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            TypedQuery<Player> query = em.createQuery("SELECT p FROM Player p", Player.class);

            return playerService.playerDomainMapper(query.getSingleResult());

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return null;
    }


//    public void updatePlayerInventory(PlayerDomain playerDomain) {
//        EntityManager em = EntityManagerSingleton.getEntityManager();
//        InventoryService inventoryService = new InventoryService();
//        try {
//            if (!em.getTransaction().isActive()) {
//                em.getTransaction().begin();
//            }
//            Player player = em.find(Player.class, playerDomain.getId());
//            if (player != null) {
//                Inventory managedInventory = inventoryService.inventoryMapper(playerDomain.getInventory());
//
//                Inventory oldInventory = player.getInventory();
//                oldInventory.setResources(managedInventory.getResources());
////                //List<Resource> managedResources = new ArrayList<>();
////                for (Resource res : managedInventory.getResources()) {
////                    managedResources.add(res);
////
////                }
//                //managedInventory.setResources(managedResources);
//
//                player.setInventory(oldInventory);
//            }
//
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            em.getTransaction().rollback();
//        }
//    }



}
