package Model;

import javax.persistence.*;

@Entity
@Table(name = "GAME")
public class Game {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "STATUS", nullable = false)
	private int status;

	@Column(name = "MODE_ID", nullable = false)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "MODE",
			joinColumns = @JoinColumn(name = "ID"))
	private long modeId;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinTable(name = "PLAYER",
			joinColumns = @JoinColumn(name = "ID"))
	private long playerId;

	@Column(name = "MAP_ID", nullable = false)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "MAP",
			joinColumns = @JoinColumn(name = "ID"))
	private long mapId;

	public void start(Mode mode) {
		// TODO - implement Game.start
		throw new UnsupportedOperationException();
	}

	public void triggerEvent(Event event) {
		// TODO - implement Game.triggerEvent
		throw new UnsupportedOperationException();
	}


	public boolean verifyAnswer(Event event, boolean answer) {
		// TODO - implement Game.verifyAnswer
		throw new UnsupportedOperationException();
	}

}