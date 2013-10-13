package pl.indecoders.archetype.domain.payment;

import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.joda.time.DateTime.now;
import static org.joda.time.DateTimeZone.UTC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import pl.indecoders.archetype.domain.AbstractEntity;

/**
 * The Class BankInformations.
 * @author Mateusz
 */
@Table(name = "bank_informations")
@Entity
public class BankInformations extends AbstractEntity {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "BankInformations_SEQUENCE", sequenceName = "bank_informations_seq")
	@GeneratedValue(generator = "BankInformations_SEQUENCE")
	private Long id;

	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name = "bank_account_number")
	private String bankAccountNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public BankInformations() {
		super(now(UTC));
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
	}
}
