package pl.indecoders.archetype.form.customer;

import static org.apache.commons.lang.builder.ToStringStyle.DEFAULT_STYLE;
import static pl.indecoders.archetype.navigation.Navigator.NIP_REGEX;
import static pl.indecoders.archetype.navigation.Navigator.POSTAL_CODE_REGEX;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import pl.indecoders.archetype.validation.annotation.RegularExpression;
import pl.indecoders.archetype.validation.annotation.UniqueCustomerName;

/**
 * The Class NewCustomerForm.
 * @author Mateusz
 */
public class NewCustomerForm {
	
	@NotEmpty
	@UniqueCustomerName
	private String name;
	
	@NotEmpty
	private String city;
	
	@NotEmpty
	@RegularExpression(expression = POSTAL_CODE_REGEX)
	private String postalCode;
	
	@NotEmpty
	private String street;
	
	@NotEmpty
	private String homeNumber;
	
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
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
