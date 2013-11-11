package pl.indecoders.archetype.form.account;

import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static pl.indecoders.archetype.navigation.Navigator.BANK_REGEX;
import static pl.indecoders.archetype.navigation.Navigator.EMAIL_REGEX;
import static pl.indecoders.archetype.navigation.Navigator.NIP_REGEX;
import static pl.indecoders.archetype.navigation.Navigator.PHONE_DOMESTIC_REGEX;
import static pl.indecoders.archetype.navigation.Navigator.PHONE_MOBILE_INTERN_REGEX;
import static pl.indecoders.archetype.navigation.Navigator.PHONE_MOBILE_REGEX;
import static pl.indecoders.archetype.navigation.Navigator.POSTAL_CODE_REGEX;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import pl.indecoders.archetype.validation.annotation.RegularExpression;

/**
 * The Class PersonalInformations.
 * @author Mateusz
 */
public class PersonalInformationForm {

	private String name;
	private String city;
	private String street;
	
	@RegularExpression(expression = POSTAL_CODE_REGEX, expressions = {})
	private String postal;
	private String homeNumber;
	
	@RegularExpression(expression = NIP_REGEX, expressions = {})
	private String nip;
	
	@RegularExpression(expression = "", expressions = {PHONE_MOBILE_REGEX, PHONE_DOMESTIC_REGEX, PHONE_MOBILE_INTERN_REGEX})
	private String contactPhone;

	@RegularExpression(expression = "", expressions = {PHONE_MOBILE_REGEX, PHONE_DOMESTIC_REGEX, PHONE_MOBILE_INTERN_REGEX})
	private String faxPhone;

	@RegularExpression(expression = EMAIL_REGEX, expressions = {})
	private String email;
	private String bankName;
	
	@RegularExpression(expression = BANK_REGEX, expressions = {})
	private String bankNumber;

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
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getPostal() {
		return postal;
	}
	
	public void setPostal(String postal) {
		this.postal = postal;
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
	
	public String getBankName() {
		return bankName;
	}
	
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public String getBankNumber() {
		return bankNumber;
	}
	
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
