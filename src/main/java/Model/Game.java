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

	@Column(name = "MODE", nullable = false)
	private Mode mode;

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