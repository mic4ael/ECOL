package pl.indecoders.archetype.dto.product;

import java.util.ArrayList;
import java.util.List;

import pl.indecoders.archetype.domain.product.ProductGroup;
import pl.indecoders.archetype.domain.product.Type;
import pl.indecoders.archetype.domain.product.Unit;

/**
 * The Class NewProductAttributesDto.
 * @author Mateusz
 */
public class NewProductAttributesDto {

	private List<String> productTypes;
	private List<String> groups;
	private List<String> units;
	
	public NewProductAttributesDto(final List<ProductGroup> productGroups) {
		this.productTypes = prepareProductTypes();
		this.units = prepareProductUnits();
		this.groups = prepareGroups(productGroups);
	}

	public List<String> getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(List<String> productTypes) {
		this.productTypes = productTypes;
	}

	public List<String> getGroups() {
		return groups;
	}

	public void setGroups(List<String> groups) {
		this.groups = groups;
	}

	public List<String> getUnits() {
		return units;
	}

	public void setUnits(List<String> units) {
		this.units = units;
	}

	private List<String> prepareGroups(List<ProductGroup> productGroups) {
		List<String> groups = new ArrayList<>();
		for(ProductGroup group : productGroups) {
			groups.add(group.getName());
		}
		return groups;
	}

	private List<String> prepareProductUnits() {
		List<String> units = new ArrayList<>();
		for(Unit unit : Unit.values()) {
			units.add(unit.getUnit());
		}
		return units;
	}

	private List<String> prepareProductTypes() {
		List<String> types = new ArrayList<>();
		for(Type type : Type.values()) {
			types.add(type.getType());
		}
		return types;
	}
}
