package org.example.model;

import Controller.DBController;
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "MAP")
public class Map {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;


	//test composizione
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private ArrayList<Area> totalMapArea =new ArrayList<>();


	//test composizione
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private ArrayList<Area> adjacentArea= new ArrayList<>();


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
			this.totalMapArea.add(new Area());

		}
		//vanno salvate tutte le aree nel db altrimenti non hanno id e non matcha con le risorse
		gms.createEvent(totalMapArea,mode);

	}

	public ArrayList<Area> getTotalMapArea() {
		return totalMapArea;
	}

	public void setAdjacentArea(long position) {
//		if(position == totalMapArea.get(totalMapArea.size()- 1)) {
//			adjacentArea.add(position - 1);
//		} else {
//            adjacentArea.add(position - 1);
//			adjacentArea.add(position + 1);
//            }
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