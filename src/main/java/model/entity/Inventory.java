package model.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
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
	@Column(name = "RESOURCES")
	private List<Resource> resources=new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(name = "RESOURCES_SELECTED")
	private List<CraftedResource> resourcesSelected= new ArrayList<>();

	@OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ResourceQuantityInv> resources_quantity= new ArrayList<>();

	public List<ResourceQuantityInv> getResources_quantity() {
		return resources_quantity;
	}

	public void setResources_quantity(List<ResourceQuantityInv> resources_quantity) {
		this.resources_quantity = resources_quantity;
	}

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

	public List<CraftedResource> getResourcesSelected() {
		return resourcesSelected;
	}

	public void setResourcesSelected(List<CraftedResource> resourcesSelected) {
		this.resourcesSelected = resourcesSelected;
	}
}