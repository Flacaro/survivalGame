package model.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "INVENTORY")
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "CAPACITY", nullable = false)
	private int capacity;


	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(name = "RESOURCES", nullable = false)
	private List<Resource> resources=new ArrayList<>();

	//@Column(name = "RESOURCES_SELECTED_IDS", nullable = false)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(name = "RESOURCES_SELECTED", nullable = false)
	private ArrayList<Resource> resourcesSelected= new ArrayList<>();


	@OneToOne(mappedBy = "inventory")
	private Player player;

}