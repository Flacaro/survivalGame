package org.example.model;

import org.example.persistence.ResourceDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GameFactorySingleton {
	private static GameFactorySingleton instance;

	public static GameFactorySingleton getInstance() {
		if (instance==null){
			instance=new GameFactorySingleton();
		}
		return instance;
	}

	public void createEvent(ArrayList<Area> totalMapArea, Mode mode) {
		//int numEn=mode.getNumEnemy();
		int numRes= mode.getNumResources();

		//devo recuperare le risorse nel db, posizionarle nelle caselle ed aggiornare la quantità
		ResourceDao resourceDao=new ResourceDao();
		ArrayList<Resource> resources;
		resources=resourceDao.getResource();
		System.out.println(resources);

		List<Integer> numbers = new ArrayList<>();

		// Riempie la lista con tutti i numeri dell'intervallo
		for (int i =1; i <= mode.getTotalArea(); i++) {
			numbers.add(i);
		}

		Collections.shuffle(numbers);
		Collections.shuffle(resources);
		//id delle caselle contenenti le risorse
		List<Integer> subListR=numbers.subList(0, numRes);

		HashMap<Integer, Resource> pairs= new HashMap<>();

		for (int i = 0; i < numRes; i++) {
			pairs.put(subListR.get(i),resources.get(i));
		}

			for (Area a: totalMapArea){
				long id1 = a.getId();
				for (long id : pairs.keySet()){
					if (id1 == id){
						a.setEvent(pairs.get(id));
					}
			}
		}
		//numero delle caselle conteneti i nemici
		//List<Integer> subListE=numbers.subList(mode.getNumResources()+1,mode.getNumEnemy());

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