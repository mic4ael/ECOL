package pl.indecoders.archetype.form.invoice;

import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.springframework.format.annotation.NumberFormat.Style.CURRENCY;
import static org.springframework.format.annotation.NumberFormat.Style.NUMBER;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.format.annotation.NumberFormat;

/**
 * The Class ProductRowForm.
 * @author Mateusz
 */
public class ProductRowForm {

	private Long id;
	private String name;
	private String discountType;
	
	@NumberFormat(style = CURRENCY)
	private BigDecimal discountAmount;
	
	@NumberFormat(style = NUMBER)
	private Integer amount;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDiscountType() {
		return discountType;
	}
	
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
	}
}
