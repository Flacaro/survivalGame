package persistence;

import jakarta.persistence.EntityManager;
import model.domain.AreaDomain;
import model.domain.PlayerDomain;
import model.entity.Area;
import model.entity.Player;
import services.PlayerService;

public class PlayerDaoImpl implements PlayerDao {

    public void savePlayer(PlayerDomain player, EntityManager em) {
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
    public void updatePlayer(PlayerDomain player, EntityManager em) {
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

}
