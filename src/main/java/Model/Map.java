package Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "MAP")
public class Map {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "TOTAL_MAP_AREA", nullable = false)
	private List<Long> totalMapArea;

	@Column(name = "ADJACENT_AREA", nullable = false)
	private List<Long> adjacentArea;

	@Column(name = "PLAYER_POSITION", nullable = false)
	private long playerPosition;


	public int setadjacentArea(int position) {
		// TODO - implement Map.setadjacentArea
		throw new UnsupportedOperationException();
	}

	public Event getEvent() {
		// TODO - implement Map.getEvent
		throw new UnsupportedOperationException();
	}


	public void setarea(Mode mode) {
		// TODO - implement Map.setarea
		throw new UnsupportedOperationException();
	}


	public int setplayerPosition(int position) {
		// TODO - implement Map.setplayerPosition
		throw new UnsupportedOperationException();
	}

}