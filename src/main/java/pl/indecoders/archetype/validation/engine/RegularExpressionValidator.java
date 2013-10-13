package pl.indecoders.archetype.validation.engine;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import pl.indecoders.archetype.validation.annotation.RegularExpression;

/**
 * The Class RegularExpressionValidator.
 * @author Mateusz
 */
@Component
public class RegularExpressionValidator implements ConstraintValidator<RegularExpression, String> {

	private String expression;
	
	@Override
	public void initialize(RegularExpression constraintAnnotation) {
		this.expression = constraintAnnotation.expression();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches(expression);
	}
}
