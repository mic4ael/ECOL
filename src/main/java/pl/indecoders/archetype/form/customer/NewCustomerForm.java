package pl.indecoders.archetype.form.customer;

import static org.apache.commons.lang.builder.ToStringStyle.DEFAULT_STYLE;
import static pl.indecoders.archetype.navigation.Navigator.NIP_REGEX;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import pl.indecoders.archetype.form.address.AddressForm;
import pl.indecoders.archetype.validation.annotation.RegularExpression;

/**
 * The Class NewCustomerForm.
 * @author Mateusz
 */
public class NewCustomerForm {
	
	@NotEmpty
	private String name;
	
	@Valid
	private AddressForm address;
	
	@RegularExpression(expression = NIP_REGEX)
	@NotEmpty
	private String nip;
	
	private String email;
	
	private String contactPhone;
	private String faxPhone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AddressForm getAddress() {
		return address;
	}

	public void setAddress(AddressForm address) {
		this.address = address;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getFaxPhone() {
		return faxPhone;
	}

	public void setFaxPhone(String faxPhone) {
		this.faxPhone = faxPhone;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, DEFAULT_STYLE);
	}
}
