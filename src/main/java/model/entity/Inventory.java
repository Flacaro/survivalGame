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


	@OneToMany(cascade = CascadeType.ALL)
	@Column(name = "RESOURCES")
	private List<SimpleResource> resources=new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@Column(name = "CRAFTEDRESOURCELIST")
	private List<CraftedResource> craftedResourceList = new ArrayList<>();

	@OneToMany
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

	public List<SimpleResource> getResources() {
		return resources;
	}

	public void setResources(List<SimpleResource> resources) {
		this.resources = resources;
	}

	public List<CraftedResource> getCraftedResourceList() {
		return craftedResourceList;
	}

	public void setCraftedResourceList(List<CraftedResource> craftedResourceList) {
		this.craftedResourceList = craftedResourceList;
	}

	public Inventory remove(ArrayList<SimpleResource> selections, Inventory inventoryDomain) {
		List<ResourceQuantityInv> quantity=inventoryDomain.getResources_quantity();
		ArrayList<ResourceQuantityInv> rqiToRemove = new ArrayList<>();
		ArrayList<SimpleResource> toRemove = new ArrayList<>();
		for (ResourceQuantityInv q : quantity){
			for (SimpleResource r :selections){
				if (q.getResource().getId()== r.getId()){
					q.setQuantity(q.getQuantity()-1);
					r.setQuantity(r.getQuantity()-1);
					inventoryDomain.setCapacity(inventoryDomain.getCapacity()+1);
					if (q.getQuantity()==0){
						inventoryDomain.setCapacity(inventoryDomain.getCapacity()+1);
						rqiToRemove.add(q);
						toRemove.add(r);
					}
					break;
				}
			}
		}
		inventoryDomain.getResources().removeAll(toRemove);
		quantity.removeAll(rqiToRemove);
		inventoryDomain.setResources_quantity(quantity);
		return inventoryDomain;
	}

	public boolean checkCapacity(Inventory inventoryDomain) {
		return inventoryDomain.getCapacity() > 0;
	}

}