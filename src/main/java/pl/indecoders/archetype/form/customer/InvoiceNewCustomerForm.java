package pl.indecoders.archetype.form.customer;

import static org.apache.commons.lang.builder.ToStringStyle.DEFAULT_STYLE;
import static pl.indecoders.archetype.navigation.Navigator.EMAIL_REGEX;
import static pl.indecoders.archetype.navigation.Navigator.NIP_REGEX;
import static pl.indecoders.archetype.navigation.Navigator.PHONE_DOMESTIC_REGEX;
import static pl.indecoders.archetype.navigation.Navigator.PHONE_MOBILE_INTERN_REGEX;
import static pl.indecoders.archetype.navigation.Navigator.PHONE_MOBILE_REGEX;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import pl.indecoders.archetype.form.address.AddressForm;
import pl.indecoders.archetype.validation.annotation.RegularExpression;

/**
 * The Class NewCustomerForm.
 * @author Mateusz
 */
public class InvoiceNewCustomerForm {
	
	@NotEmpty
	private String name;
	
	@Valid
	private AddressForm address;
	
	@RegularExpression(expression = NIP_REGEX, expressions = {})
	@NotEmpty
	private String nip;
	
	@RegularExpression(expression = EMAIL_REGEX, expressions = {})
	private String email;
	
	@RegularExpression(expression = "", expressions = {PHONE_MOBILE_REGEX, PHONE_DOMESTIC_REGEX, PHONE_MOBILE_INTERN_REGEX})
	private String contactPhone;

	@RegularExpression(expression = "", expressions = {PHONE_MOBILE_REGEX, PHONE_DOMESTIC_REGEX, PHONE_MOBILE_INTERN_REGEX})
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
