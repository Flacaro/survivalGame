package model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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

	@OneToMany(cascade = CascadeType.ALL)
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

	public void remove(ArrayList<SimpleResource> selections) {
		List<ResourceQuantityInv> quantity=this.getResources_quantity();
		ArrayList<ResourceQuantityInv> rqiToRemove = new ArrayList<>();
		ArrayList<SimpleResource> toRemove = new ArrayList<>();
		for (ResourceQuantityInv q : quantity){
			for (SimpleResource r :selections){
				if (q.getResource().getId()== r.getId()){
					q.setQuantity(q.getQuantity()-1);
					r.setQuantity(r.getQuantity()-1);
					this.setCapacity(this.getCapacity()+1);
					if (q.getQuantity()==0){
						rqiToRemove.add(q);
						toRemove.add(r);
					}
					break;
				}
			}
		}
		this.getResources().removeAll(toRemove);
		quantity.removeAll(rqiToRemove);
		this.setResources_quantity(quantity);
	}

	public void removeCraftedResource(ArrayList<CraftedResource> selections){
		List<CraftedResource> craftedResources= this.getCraftedResourceList();
		for (CraftedResource cr : craftedResources){
			for (CraftedResource resource: selections){
				if (resource.getId()==cr.getId()){
					cr.setQuantity(cr.getQuantity()-1);
					this.setCapacity(this.getCapacity()+1);
				}
				if (resource.getQuantity()==0){
					craftedResources.remove(cr);
				}
				break;
			}
		}
		this.setCraftedResourceList(craftedResources);
	}

	public boolean checkCapacity(Inventory inventoryDomain) {
		return inventoryDomain.getCapacity() > 0;
	}

	public void updateInventoryCraft() {
				ArrayList<SimpleResource> list = new ArrayList<>();
				ArrayList<ResourceQuantityInv> qnt = new ArrayList<>();
				for (SimpleResource r : this.getResources()) {
					list.add(r);
				}
				for (ResourceQuantityInv r : this.getResources_quantity()) {
					qnt.add(r);
				}
				this.getResources_quantity().clear();
				this.getResources().clear();
				this.getResources().addAll(list);
				this.getResources_quantity().addAll(qnt);
				ArrayList<CraftedResource> lists = new ArrayList<>();
				for (CraftedResource r : this.getCraftedResourceList()) {
					lists.add(r);
				this.getCraftedResourceList().clear();
				this.getCraftedResourceList().addAll(lists);
			}
	}
	public void updateInventory(ArrayList<SimpleResource> r) {
		boolean resourceFound = false;
		for (SimpleResource res :r ){
			List<ResourceQuantityInv> resourcesQuantity = this.getResources_quantity();
			this.setCapacity(this.getCapacity() - 1);
			for (SimpleResource resource : this.getResources()) {
				for (ResourceQuantityInv rqi : resourcesQuantity) {
					if (Objects.equals(resource.getId(), res.getId())) {
						if (Objects.equals(resource.getId(), rqi.getResource().getId())) {
							rqi.setQuantity(rqi.getQuantity() + 1);
							resourceFound = true;
								break;
							}
						}
					}
			}if (!resourceFound) {
					this.getResources().add(res);
					ResourceQuantityInv newResourceq = new ResourceQuantityInv(this, res, 1);
					resourcesQuantity.add(newResourceq);
				}
		}
	}
}