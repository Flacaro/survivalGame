package model;

import jakarta.persistence.*;

@Entity
@Table(name = "GAME")
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "STATUS", nullable = false)
	private int status;


	//uno ad uno con modalità
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_mode",referencedColumnName = "id")
	private Mode mode;


	//uno ad uno con player, il player possiede la relazione
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_player", referencedColumnName = "id")
	private Player player;


	//uno ad uno con la mappa
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_map", referencedColumnName = "id")
	private Map map;




	public void triggerEvent(Event event) {
		// TODO - implement Game.triggerEvent
		throw new UnsupportedOperationException();
	}


	public boolean verifyAnswer(Event event, boolean answer) {
		// TODO - implement Game.verifyAnswer
		throw new UnsupportedOperationException();
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}
}