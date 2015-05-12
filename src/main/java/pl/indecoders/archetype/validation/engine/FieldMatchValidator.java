package pl.indecoders.archetype.validation.engine;

import static org.apache.commons.beanutils.BeanUtils.getProperty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pl.indecoders.archetype.form.account.RegisterAccountForm;
import pl.indecoders.archetype.validation.annotation.FieldMatch;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, RegisterAccountForm> {

	private String fieldFirst;
	private String fieldSecond;

	@Override
	public void initialize(FieldMatch constraintAnnotation) {
		this.fieldFirst = constraintAnnotation.firstField();
		this.fieldSecond = constraintAnnotation.secondField();
	}

	@Override
	public boolean isValid(RegisterAccountForm value, ConstraintValidatorContext context) {
		try {
			final String firstField = getProperty(value, this.fieldFirst);
			final String secondField = getProperty(value, this.fieldSecond);

			if (firstField != null && secondField != null && firstField.equals(secondField)) {
				return true;
			}
			
		} catch (final Exception ignore) {}
		
		return false;
	}
}
