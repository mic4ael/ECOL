package pl.indecoders.archetype.validation.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import pl.indecoders.archetype.validation.engine.RegularExpressionValidator;

/**
 * The Class RegularExpression.
 * 
 * @author Mateusz
 */

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = RegularExpressionValidator.class)
@Documented
public @interface RegularExpression {

	String expression();
	String message() default "{regex.value}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}