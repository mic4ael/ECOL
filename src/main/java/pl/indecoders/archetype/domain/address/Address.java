package pl.indecoders.archetype.domain.address;

import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.joda.time.DateTime.now;
import static org.joda.time.DateTimeZone.UTC;
import static pl.indecoders.archetype.navigation.Navigator.POSTAL_CODE_REGEX;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import pl.indecoders.archetype.domain.AbstractEntity;
import pl.indecoders.archetype.validation.annotation.RegularExpression;

/**
 * The Class Address.
 * @author Mateusz
 */
@Table(name = "address")
@Entity
public class Address extends AbstractEntity {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "Address_SEQUENCE", sequenceName = "address_seq")
	@GeneratedValue(generator = "Address_SEQUENCE")
	private Long id;
		
	@Column(name = "city")
	private String city;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "home_number")
	private String homeNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Address() {
		super(now(UTC));
	}

	@Override
	public int hashCode() {
		return reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (!(this instanceof Address)) {
			return false;
		} else {
			Address comparedAddress = (Address) obj;
			return EqualsBuilder.reflectionEquals(this, comparedAddress);
		}
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
	}
}
