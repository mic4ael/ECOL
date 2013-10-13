package pl.indecoders.archetype.domain.payment;

import static javax.persistence.EnumType.STRING;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.joda.time.DateTime.now;
import static org.joda.time.DateTimeZone.UTC;
import static org.springframework.format.annotation.NumberFormat.Style.NUMBER;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.format.annotation.NumberFormat;

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
	
	@Column(name = "payment_time")
	@NumberFormat(style = NUMBER)
	private Integer paymentTime;

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

	public Integer getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Integer paymentTime) {
		this.paymentTime = paymentTime;
	}

	public PaymentInformations() {
		super(now(UTC));
	}	
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
	}
}
