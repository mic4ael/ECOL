package pl.indecoders.archetype.domain.account;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.joda.time.DateTime.now;
import static org.joda.time.DateTimeZone.UTC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import pl.indecoders.archetype.domain.AbstractEntity;
import pl.indecoders.archetype.domain.address.Address;
import pl.indecoders.archetype.domain.payment.BankInformations;

/**
 * The Class Profile.
 * @author Mateusz
 */
@Entity
@Table(name = "profile")
public class Profile extends AbstractEntity {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "Profile_SEQUENCE", sequenceName = "account_seq")
	@GeneratedValue(generator = "Profile_SEQUENCE")
	private Long id;

	@Column(name = "name")
	private String name;
	
	@OneToOne(fetch = EAGER, cascade = ALL)
	@JoinColumn(name = "address_id")
	private Address address;
	
	@Column(name = "nip")
	private String nip;
	
	@Column(name = "contact_phone")
	private String contactPhone;
	
	@Column(name = "fax_phone")
	private String faxPhone;
	
	@Column(name = "email")
	private String email;
	
	@OneToOne(cascade = ALL)
	@JoinColumn(name = "bank_informations_id")
	private BankInformations bankInformations;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
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

	public BankInformations getBankInformations() {
		return bankInformations;
	}

	public void setBankInformations(BankInformations bankInformations) {
		this.bankInformations = bankInformations;
	}

	public Profile() {
		super(now(UTC));
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
