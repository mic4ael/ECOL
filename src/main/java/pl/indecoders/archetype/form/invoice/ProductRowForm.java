package pl.indecoders.archetype.form.invoice;

import java.math.BigDecimal;

/**
 * The Class ProductRowForm.
 * @author Mateusz
 */
public class ProductRowForm {

	private String name;
	private String discountType;
	private BigDecimal discountAmount;
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
}
