package pl.indecoders.archetype.form.invoice;

import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static pl.indecoders.archetype.navigation.Navigator.EMAIL_REGEX;
import static pl.indecoders.archetype.navigation.Navigator.NIP_REGEX;
import static pl.indecoders.archetype.navigation.Navigator.PHONE_DOMESTIC_REGEX;
import static pl.indecoders.archetype.navigation.Navigator.PHONE_MOBILE_INTERN_REGEX;
import static pl.indecoders.archetype.navigation.Navigator.PHONE_MOBILE_REGEX;
import static pl.indecoders.archetype.navigation.Navigator.BANK_REGEX;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import pl.indecoders.archetype.form.address.AddressForm;
import pl.indecoders.archetype.validation.annotation.RegularExpression;


/**
 * The Class SellerForm.
 * @author Mateusz
 */
public class SellerForm {

	@NotEmpty
	private String name;

	@Valid
	private AddressForm address;
	
	@NotEmpty
	@RegularExpression(expression = NIP_REGEX, expressions = {})
	private String nip;

	@NotEmpty
	@RegularExpression(expression = "", expressions = {PHONE_DOMESTIC_REGEX, PHONE_MOBILE_INTERN_REGEX, PHONE_MOBILE_REGEX})
	private String contactPhone;
	
	@RegularExpression(expression = "", expressions = {PHONE_DOMESTIC_REGEX, PHONE_MOBILE_INTERN_REGEX, PHONE_MOBILE_REGEX})
	private String faxPhone;

	@NotEmpty
	private String bankName;

	@NotEmpty
	@RegularExpression(expression = BANK_REGEX, expressions = {})
	private String bankNumber;
	
	@RegularExpression(expression = EMAIL_REGEX, expressions = {})
	private String email;
	
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

