package pl.indecoders.archetype.domain.customer;

import static javax.persistence.FetchType.EAGER;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.joda.time.DateTime.now;
import static org.joda.time.DateTimeZone.UTC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import pl.indecoders.archetype.domain.AbstractEntity;
import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.address.Address;

/**
 * The Class Customer.
 * @author Mateusz
 */
@Table(name = "customer")
@Entity
public class Customer extends AbstractEntity {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "Customer_SEQUENCE", sequenceName = "customer_seq")
	@GeneratedValue(generator = "Customer_SEQUENCE")
	private Long id;

	@Column(name = "customer_code")
	private String customerCode;
	
	@Column(name = "name")
	private String name;
	
	@OneToOne(fetch = EAGER)
	@JoinColumn(name = "address_id")
	private Address address;
	
	@Column(name = "nip")
	private String nip;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "contact_phone")
	private String contactPhone;
	
	@Column(name = "fax_phone")
	private String faxPhone;

	@ManyToOne(fetch = EAGER)
	@JoinColumn(name = "customer_owner_id")
	private Account owner;
	
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

	public Account getOwner() {
		return owner;
	}

	public void setOwner(Account owner) {
		this.owner = owner;
	}
	
	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
	public Customer() {
		super(now(UTC));
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
	}
}
