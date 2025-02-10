package Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "INVENTORY")
public class Inventory {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "CAPACITY", nullable = false)
	private int capacity;

	@Column(name = "RESOURCES_IDS", nullable = false)
	private List<Long> resourcesIds;

	@Column(name = "RESOURCES_SELECTED_IDS", nullable = false)
	private List<Long> resourcesSelectedIds;

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

}