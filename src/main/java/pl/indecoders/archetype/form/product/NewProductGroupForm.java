package pl.indecoders.archetype.form.product;

import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import pl.indecoders.archetype.validation.annotation.UniqueGroupName;

/**
 * The Class ProductGroupForm.
 * @author Mateusz
 */
public class NewProductGroupForm {

	@NotEmpty
	@Size(max=255, message="{group.maxsize}")
	@UniqueGroupName
	private String name;
	
	@Size(max=255, message="{group.maxsize}")
	private String specification;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSpecification() {
		return specification;
	}
	
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
	}
}
