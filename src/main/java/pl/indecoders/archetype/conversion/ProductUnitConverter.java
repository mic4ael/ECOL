package pl.indecoders.archetype.conversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pl.indecoders.archetype.domain.product.Unit;

/**
 * The Class ProductUnitConversion.
 * @author Mateusz
 */
@Component
public class ProductUnitConverter implements Converter<String, Unit> {
	@Override
	public Unit convert(String source) {
		for(Unit unit : Unit.values()) {
			if(unit.getUnit().equalsIgnoreCase(source)) {
				return unit;
			}
		}
		return null;
	}
}
