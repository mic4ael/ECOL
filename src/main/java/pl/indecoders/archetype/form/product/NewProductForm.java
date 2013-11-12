package pl.indecoders.archetype.form.product;

import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.springframework.format.annotation.NumberFormat.Style.CURRENCY;
import static org.springframework.format.annotation.NumberFormat.Style.NUMBER;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import pl.indecoders.archetype.domain.product.ProductGroup;
import pl.indecoders.archetype.domain.product.Type;
import pl.indecoders.archetype.domain.product.Unit;
import pl.indecoders.archetype.validation.annotation.UniqueProductName;

/**
 * The Class NewProductForm.
 * @author Mateusz
 */
public class NewProductForm {
	
	@NotEmpty
	@Size(max=255)
	@UniqueProductName
	private String productName;
	
	@Size(max=255)
	private String productSpecification;
	
	@NumberFormat(style = NUMBER)
	private BigDecimal taxAmount;
	
	@Column(name = "product_type")
	private Type productType;

	@Column(name = "product_unit")
	private Unit productUnit;
	
	@NotNull(message="{product.initialBasePrice}")
	@DecimalMax(value="9999999999999999", message="{product.maxsize}")
	@NumberFormat(style = CURRENCY)
	private BigDecimal basePrice;
	
	private ProductGroup group;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductSpecification() {
		return productSpecification;
	}

	public void setProductSpecification(String productSpecification) {
		this.productSpecification = productSpecification;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Type getProductType() {
		return productType;
	}

	public void setProductType(Type productType) {
		this.productType = productType;
	}

	public Unit getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(Unit productUnit) {
		this.productUnit = productUnit;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public ProductGroup getGroup() {
		return group;
	}

	public void setGroup(ProductGroup group) {
		this.group = group;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
	}
}
