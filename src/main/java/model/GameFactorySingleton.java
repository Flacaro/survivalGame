package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.domain.AreaDomain;
import model.domain.ModeDomain;
import model.domain.ResourceDomain;
import model.entity.Inventory;
import persistence.AreaDaoImpl;
import persistence.ResourceDaoImpl;
import services.ResourceService;

public class GameFactorySingleton {
	private static GameFactorySingleton instance;
	private ResourceService rs = new ResourceService();

	public static GameFactorySingleton getInstance() {
		if (instance==null){
			instance=new GameFactorySingleton();
		}
		return instance;
	}

	public void createEvent(List<AreaDomain> totalMapAreaDomain, ModeDomain modeDomain) {
		int numRes= modeDomain.getNumResources();

		//devo recuperare le risorse nel db, posizionarle nelle caselle ed aggiornare la quantità
		ArrayList<ResourceDomain> resources= new ArrayList<>();
		resources = rs.getResources();
		AreaDaoImpl areaDao= new AreaDaoImpl();

		//Collections.shuffle(totalMapAreaDomain);
		Collections.shuffle(resources);
		//id delle caselle contenenti le risorse
		// 0 index per 4 aree
		List<AreaDomain> subListAreaDomain = totalMapAreaDomain.subList(0, numRes);
		List<ResourceDomain> subListResources= resources.subList(0,numRes);

		for (int i = 0; i < numRes; i++){
			subListAreaDomain.get(i).setIdEvent(subListResources.get(i).getId());
			subListAreaDomain.get(i).setCategory(subListResources.get(i).getCategory());
			System.out.println(subListAreaDomain.get(i).getIdEvent());
		}
		
	}


	public void createClimate(ModeDomain modeDomain) {
		// TODO - implement GameFactorySingleton.createClimate
		throw new UnsupportedOperationException();
	}

	public void setInventory() {
		Inventory inventory = new Inventory();
		throw new UnsupportedOperationException();
	}

}