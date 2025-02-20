package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Area;
import model.CraftingCatalog;
import model.Map;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AreaDaoImpl implements AreaDao {

    @Override
    public void saveTotalMapArea(ArrayList<Area> area, EntityManager em) {
        try {
            em.getTransaction().begin();
            for (int i = 0; i < area.size(); i++) {
                em.persist(area);
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    public ArrayList<Area> getTotalMapArea(Long mapId, EntityManager em) {

        ArrayList<Area> aree = new ArrayList<>();

        try {
            TypedQuery<Map> query = em.createQuery("SELECT m FROM Map m where id = :id", Map.class);
            query.setParameter("id", mapId);

            List<Map> maps = query.getResultList();
            if(!maps.isEmpty()) {
                Map map = maps.get(0);
                aree.addAll(map.getTotalMapArea());
            }
            return aree;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return null;
    }

}
