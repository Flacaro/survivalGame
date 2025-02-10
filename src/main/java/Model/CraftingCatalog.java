package Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "CRAFTING_CATALOG")
public class CraftingCatalog {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "ID_RESOURCES_TO_CRAFT", nullable = false)
	private ArrayList<Long> resourcesIdsToCraft;

	@Column(name = "FINAL_RESOURCE", nullable = false)
	private Long finalResource;

	private HashMap<ArrayList<Long>, Long> combinationOfCrafting;


	public boolean checkCompatibility(ArrayList<Long> selections) {
		return combinationOfCrafting.containsKey(selections);
	}


//in un controller!
//	public Resource createResource(List<Long> selections) {
//		return null;
//	}

}