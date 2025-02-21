package model;

import persistence.ResourceDaoImpl;

import java.util.*;

public class GameFactorySingleton {
	private static GameFactorySingleton instance;

	public static GameFactorySingleton getInstance() {
		if (instance==null){
			instance=new GameFactorySingleton();
		}
		return instance;
	}

	public void createEvent(List<Area> totalMapArea, Mode mode) {
		//int numEn=mode.getNumEnemy();
		int numRes= mode.getNumResources();

		//devo recuperare le risorse nel db, posizionarle nelle caselle ed aggiornare la quantità
		ResourceDaoImpl resourceDao = new ResourceDaoImpl();
		ArrayList<Resource> resources;
		resources = resourceDao.getResource();
		if(resources == null) {
			resources = new ArrayList<>();
		}

		Collections.shuffle(totalMapArea);
		Collections.shuffle(resources);
		//id delle caselle contenenti le risorse
		List<Area> subListArea = totalMapArea.subList(0, numRes);
		List<Resource> subListResources= resources.subList(0,numRes);

		for (int i = 0; i < numRes; i++){
			subListArea.get(i).setEvent(subListResources.get(i).getId());
			System.out.println(subListArea.get(i).getEvent());
		}
	}


	public void createClimate(Mode mode) {
		// TODO - implement GameFactorySingleton.createClimate
		throw new UnsupportedOperationException();
	}

	public void setInventory() {
		Inventory inventory = new Inventory();
		throw new UnsupportedOperationException();
	}

}