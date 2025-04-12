package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controller.DBController;
import model.entity.*;
import persistence.AreaDaoImpl;

public class GameFactorySingleton {
	private static GameFactorySingleton instance;

	public static GameFactorySingleton getInstance() {
		if (instance==null){
			instance=new GameFactorySingleton();
		}
		return instance;
	}

	public void createEvent(List<Area> totalMapAreaDomain, Mode modeDomain) {
		int numRes= modeDomain.getNumResources();
		DBController dbController=new DBController();
		//devo recuperare le risorse nel db, posizionarle nelle caselle ed aggiornare la quantità
		ArrayList<SimpleResource> resources= new ArrayList<>();
		resources = dbController.getResources();
		AreaDaoImpl areaDao= new AreaDaoImpl();

		//Collections.shuffle(totalMapAreaDomain);
		Collections.shuffle(resources);
		//id delle caselle contenenti le risorse
		// 0 index per 4 aree
		List<Area> subListAreaDomain = totalMapAreaDomain.subList(0, numRes);
		List<SimpleResource> subListResources= resources.subList(0,numRes);

		for (int i = 0; i < numRes; i++){
			subListAreaDomain.get(i).setIdEvent(subListResources.get(i).getId());
			subListAreaDomain.get(i).setCategory(subListResources.get(i).getCategory());
		}
		
	}


	public void createClimate(Mode modeDomain) {
		// TODO - implement GameFactorySingleton.createClimate
		throw new UnsupportedOperationException();
	}

	public void setInventory() {
		Inventory inventory = new Inventory();
		throw new UnsupportedOperationException();
	}

}