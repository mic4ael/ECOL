package pl.indecoders.archetype.domain.payment;

import static javax.persistence.EnumType.STRING;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.joda.time.DateTime.now;
import static org.joda.time.DateTimeZone.UTC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import pl.indecoders.archetype.domain.AbstractEntity;

/**
 * The Class PaymentInformations.
 * @author Mateusz
 */
@Table(name = "payment_informations")
@Entity
public class PaymentInformations extends AbstractEntity {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "PaymentInformations_SEQUENCE", sequenceName = "payment_informations_seq")
	@GeneratedValue(generator = "PaymentInformations_SEQUENCE")
	private Long id;

	@Column(name = "payment_type")
	@Enumerated(STRING)
	private PaymentType type;
	
	@Column(name = "payment_date")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	protected DateTime paymentDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public DateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(DateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PaymentInformations() {
		super(now(UTC));
	}	
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
	}
}
