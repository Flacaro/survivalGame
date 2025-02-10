package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GAME")
public class Game {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "STATUS", nullable = false)
	private int status;


	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Mode mode;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Player player;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Map map;

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