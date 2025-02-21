package model;

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
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
	private List<Area> totalMapArea = new ArrayList<>();


	//test composizione
	//@OneToMany(mappedBy = "map",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
	@Column(name = "adjacentArea")
	private List<Area> adjacentArea= new ArrayList<>();


	//uno ad uno con il player
	@OneToOne(mappedBy = "map")
	private Player player;

	//uno ad uno con il gioco
	@OneToOne(mappedBy = "map")
	private Game game;

	public Map(ArrayList<Area> adjacentArea, Player player, Game game) {
		this.adjacentArea = adjacentArea;
		this.player = player;
		this.game = game;
	}

	public void setTotalMapArea(Mode mode, Map map) {
		GameFactorySingleton gms= GameFactorySingleton.getInstance();
		int totalArea= (int) mode.getTotalArea();
		for (int c = 0; c < totalArea; c++) {
			Area a = new Area("area","deserto", map);
			this.totalMapArea.add(a);
		}
		//vanno salvate tutte le aree nel db altrimenti non hanno id e non matcha con le risorse
		gms.createEvent(totalMapArea,mode);
		setAdjacentArea(totalMapArea);
	}

	public List<Area> getTotalMapArea() {
		return totalMapArea;
	}

	public void setAdjacentArea(List<Area> adjacentArea) {
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