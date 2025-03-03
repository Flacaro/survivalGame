package persistence;

import model.domain.MapDomain;
import model.domain.ResourceDomain;

public interface MapDao {

    void updateMap(MapDomain mapDomain, ResourceDomain res);

    void saveMap(MapDomain map);
}
