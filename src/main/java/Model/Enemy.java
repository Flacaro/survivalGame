package Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ENEMY")
public class Enemy extends Event {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "ATTACKS", nullable = false)
	private List<Attack> attacks;

	@Column(name = "TYPE", nullable = false)
	private String type;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "LEVEL", nullable = false)
	private int level = 1;

	@Override
	public void updateQuantity(String name, int qnt) {

	}

	@Override
	public void setUp(String type, Mode mode) {

	}
}