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
	private int capacity=10;


	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(name = "RESOURCES", nullable = false)
	private List<Resource> resources=new ArrayList<>();

	//@Column(name = "RESOURCES_SELECTED_IDS", nullable = false)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	//@Column(name = "RESOURCES_SELECTED", nullable = false)
	@JoinColumn(name = "inventory_id")
	private List<Resource> resourcesSelected= new ArrayList<>();


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public List<Resource> getResourcesSelected() {
		return resourcesSelected;
	}

	public void setResourcesSelected(List<Resource> resourcesSelected) {
		this.resourcesSelected = resourcesSelected;
	}

}