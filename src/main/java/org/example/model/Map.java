package org.example.model;

import jakarta.persistence.*;
import java.util.ArrayList;

//@Entity
@Table(name = "MAP")
public class Map {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "TOTAL_MAP_AREA", nullable = false)
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "AREA",
			joinColumns = @JoinColumn(name = "ID"))
	private ArrayList<Long> totalMapArea;

	@Column(name = "ADJACENT_AREA", nullable = false)
	private ArrayList<Long> adjacentArea;

	@Column(name = "PLAYER_POSITION", nullable = false)
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinTable(name = "PLAYER",
			joinColumns = @JoinColumn(name = "POSITION"))
	private long playerPosition;


	public void setAdjacentArea(long position) {
		if(position == totalMapArea.get(totalMapArea.size()- 1)) {
			adjacentArea.add(position - 1);
		} else {
            adjacentArea.add(position - 1);
			adjacentArea.add(position + 1);
            }
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