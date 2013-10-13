package pl.indecoders.archetype.validation.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import pl.indecoders.archetype.validation.engine.UniqueGroupNameValidator;

/**
 * The annotation UniqueEmail.
 * 
 * @author Mateusz
 */
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueGroupNameValidator.class)
@Documented
public @interface UniqueGroupName {

	String message() default "{unique.group}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}