package pl.indecoders.archetype.validation.engine;

import static org.apache.commons.beanutils.BeanUtils.getProperty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pl.indecoders.archetype.validation.annotation.FieldMatch;

/**
 * The Class FieldMatchValidator.
 * 
 * @author Mateusz
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, String> {

	private String fieldFirst;
	private String fieldSecond;

	@Override
	public void initialize(FieldMatch constraintAnnotation) {
		this.fieldFirst = constraintAnnotation.firstField();
		this.fieldSecond = constraintAnnotation.secondField();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			final Object firstField = getProperty(value, "fieldFirst");
			final Object secondField = getProperty(value, "fieldSecond");

			if ((String.valueOf(firstField).equals(String.valueOf(secondField)) && firstField != null && secondField != null)) {
				return true;
			}
		} catch (final Exception ignore) {
		}
		return false;
	}
}
