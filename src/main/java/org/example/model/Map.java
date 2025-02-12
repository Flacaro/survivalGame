package org.example.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MAP")
public class Map {

	@Id
	@GeneratedValue
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
	//private long playerPosition;

	//uno ad uno con il gioco
	@OneToOne(mappedBy = "map")
	private Game game;


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