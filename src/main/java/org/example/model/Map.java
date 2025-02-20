package org.example.model;

import Controller.DBController;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MAP")
public class Map {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;


	//test composizione
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Area> totalMapArea =new ArrayList<>();


	//test composizione
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Area> adjacentArea= new ArrayList<>();


	//uno ad uno con il player
	@OneToOne(mappedBy = "map")
	private Player player;

	//uno ad uno con il gioco
	@OneToOne(mappedBy = "map")
	private Game game;

	public Map(Mode mode,Game game,Player player) {
		setTotalMapArea(mode);
		this.game=game;
		this.player=player;
		this.player.setPosition(totalMapArea.get(0));
		this.player.setMap(this);
	}

	public void setTotalMapArea(Mode mode) {
		GameFactorySingleton gms= GameFactorySingleton.getInstance();
		int totalArea= (int) mode.getTotalArea();
		DBController controller=new DBController();
		for (int c=0; c<totalArea; c++){
			Area a=new Area("area",String.valueOf(c), this);
			this.totalMapArea.add(a);
			controller.insertArea(a);
		}
		//vanno salvate tutte le aree nel db altrimenti non hanno id e non matcha con le risorse
		gms.createEvent((ArrayList<Area>) totalMapArea,mode);
		setAdjacentArea((ArrayList<Area>) totalMapArea);
	}

	public ArrayList<Area> getTotalMapArea() {
		return (ArrayList<Area>) totalMapArea;
	}

	public void setAdjacentArea(ArrayList<Area> adjacentArea) {
		this.adjacentArea = adjacentArea;
	}

	public Event getEvent () {
			// TODO - implement Map.getEvent
			throw new UnsupportedOperationException();
		}



		//IN UN CONTROLLER
//	public void setTotalArea(Long modeId) {
//		List<Long> totalArea = new ArrayList<>();
//
//		throw new UnsupportedOperationException();
//	}


		public int setplayerPosition ( int position){
			// TODO - implement Map.setplayerPosition
			throw new UnsupportedOperationException();
		}

}