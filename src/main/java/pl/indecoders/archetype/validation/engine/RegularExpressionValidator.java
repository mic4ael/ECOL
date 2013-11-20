package pl.indecoders.archetype.validation.engine;

import java.util.ArrayList;
import java.util.List;

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

	private String[] expressions;
	private String expression;
	
	@Override
	public void initialize(RegularExpression constraintAnnotation) {
		this.expression = constraintAnnotation.expression();
		this.expressions = constraintAnnotation.expressions();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if(value.isEmpty()) {
			return true;
		}
		
		if(expressions == null || expressions.length == 0) {
			return value.matches(expression);
		} else {
			List<Boolean> statuses = new ArrayList<Boolean>();
			
			for(String exp : expressions) {
				statuses.add(value.matches(exp));
			}
			
			return statuses.contains(true);
		}
	}
}
