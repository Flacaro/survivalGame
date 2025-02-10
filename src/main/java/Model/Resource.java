package Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RESOURCES")
public class Resource extends Event {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "CATEGORY", nullable = false)
	private String category;

	@Column(name = "ATTACK_IDS", nullable = false)
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ATTACK",
			joinColumns = @JoinColumn(name = "ID"))
	private ArrayList<Long> attackIds;

	@Column(name = "LEVEL", nullable = false)
	private int level = 1;

	@Override
	public void updateQuantity(String name, int qnt) {

	}

	@Override
	public void setUp(String type, Mode mode) {

	}
}