package persistence;

import jakarta.persistence.EntityManager;
import model.domain.PlayerDomain;
import services.PlayerServices;

public class PlayerDaoImpl implements PlayerDao {

    public void savePlayer(PlayerDomain player, EntityManager em) {
        try {
            PlayerServices playerServices=new PlayerServices();
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

}
