package pl.indecoders.archetype.form.address;

import static pl.indecoders.archetype.navigation.Navigator.POSTAL_CODE_REGEX;
import static pl.indecoders.archetype.navigation.Navigator.PHONE_DOMESTIC_REGEX;

import org.hibernate.validator.constraints.NotEmpty;

import pl.indecoders.archetype.validation.annotation.RegularExpression;

/**
 * The Class AddressForm.
 * @author Mateusz
 */
public class AddressForm {

	@NotEmpty
	private String city;
	
	@NotEmpty
	private String street;
	
	@NotEmpty
	@RegularExpression(expression = "", expressions = {PHONE_DOMESTIC_REGEX})
	private String homeNumber;
	
	@NotEmpty
	@RegularExpression(expression = POSTAL_CODE_REGEX, expressions = {})
	private String postalCode;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}
