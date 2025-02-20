package model;

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

	public Resource combine(Resource[] selections) {
		// TODO - implement Inventory.combine
		throw new UnsupportedOperationException();
	}

	public boolean add(Resource res) {
		// TODO - implement Inventory.add
		throw new UnsupportedOperationException();
	}


	public boolean remove(Resource[] selections, int[] qnt) {
		// TODO - implement Inventory.remove
		throw new UnsupportedOperationException();
	}

	public boolean checkCapacity() {
		// TODO - implement Inventory.checkCapacity
		throw new UnsupportedOperationException();
	}


	public void addSelections(Resource res) {
		// TODO - implement Inventory.addSelections
		throw new UnsupportedOperationException();
	}


	public void useResource(Resource resource) {
		// TODO - implement Inventory.useResource
		throw new UnsupportedOperationException();
	}

	public long getId() {
		return id;
	}

	public int getCapacity() {
		return capacity;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public ArrayList<Resource> getResourcesSelected() {
		return resourcesSelected;
	}

	public Player getPlayer() {
		return player;
	}
}