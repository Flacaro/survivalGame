package model;

import model.domain.AreaDomain;
import model.domain.ResourceDomain;
import model.entity.Inventory;
import model.domain.ModeDomain;
import model.entity.Resource;
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

	public void createEvent(List<AreaDomain> totalMapAreaDomain, ModeDomain modeDomain) {
		int numRes= modeDomain.getNumResources();

		//devo recuperare le risorse nel db, posizionarle nelle caselle ed aggiornare la quantità
		ResourceDaoImpl resourceDao = new ResourceDaoImpl();
		ArrayList<ResourceDomain> resources;
		resources = resourceDao.getResource();
		if(resources == null) {
			resources = new ArrayList<>();
		}

		Collections.shuffle(totalMapAreaDomain);
		Collections.shuffle(resources);
		//id delle caselle contenenti le risorse
		List<AreaDomain> subListAreaDomain = totalMapAreaDomain.subList(0, numRes-1);
		List<ResourceDomain> subListResources= resources.subList(0,numRes-1);

		for (int i = 0; i < numRes; i++){
			subListAreaDomain.get(i).setEvent(subListResources.get(i).getId());
			System.out.println(subListAreaDomain.get(i).getEvent());
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