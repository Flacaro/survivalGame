package Model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "CRAFTING_CATALOG")
public class CraftingCatalog {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "RESOURCES_TO_CRAFT", nullable = false)
	private HashMap<List<Long>, Long> resourcesToCraft;


	public boolean checkCompatibility(Resource[] selections) {
		// TODO - implement CraftingCatalog.checkCompatibility
		throw new UnsupportedOperationException();
	}


	public Resource createResource(Resource[] selections) {
		// TODO - implement CraftingCatalog.createResource
		throw new UnsupportedOperationException();
	}

}