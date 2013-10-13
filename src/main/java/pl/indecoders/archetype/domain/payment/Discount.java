package pl.indecoders.archetype.domain.payment;

import static javax.persistence.EnumType.STRING;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.joda.time.DateTimeZone.UTC;
import static org.springframework.format.annotation.NumberFormat.Style.NUMBER;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.joda.time.DateTime;
import org.springframework.format.annotation.NumberFormat;

import pl.indecoders.archetype.domain.AbstractEntity;

/**
 * The Class Discount.
 * @author Mateusz
 */
@Table(name = "product_discount")
@Entity
public class Discount extends AbstractEntity {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "Discount_SEQUENCE", sequenceName = "discount_seq")
	@GeneratedValue(generator = "Discount_SEQUENCE")
	private Long id;
	
	@Column(name = "discount_type")
	@Enumerated(STRING)
	private DiscountType type;
	
	@Column(name = "amount")
	@NumberFormat(style = NUMBER)
	private BigDecimal discountAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DiscountType getType() {
		return type;
	}

	public void setType(DiscountType type) {
		this.type = type;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Discount() {
		super(DateTime.now(UTC));
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
	}
}
