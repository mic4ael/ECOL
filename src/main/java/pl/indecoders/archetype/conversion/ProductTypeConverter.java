package pl.indecoders.archetype.conversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pl.indecoders.archetype.domain.product.Type;

/**
 * The Class ProductTypeConversion.
 * @author Mateusz
 */
@Component
public class ProductTypeConverter implements Converter<String, Type> {
	@Override
	public Type convert(String source) {
		for(Type type : Type.values()) {
			if(type.getType().equalsIgnoreCase(source)) {
				return type;
			}
		}
		return null;
	}
}
