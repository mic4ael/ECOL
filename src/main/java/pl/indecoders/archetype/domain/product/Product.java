package pl.indecoders.archetype.domain.product;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.joda.time.DateTime.now;
import static org.joda.time.DateTimeZone.UTC;
import static org.springframework.format.annotation.NumberFormat.Style.CURRENCY;
import static org.springframework.format.annotation.NumberFormat.Style.NUMBER;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.format.annotation.NumberFormat;

import pl.indecoders.archetype.domain.AbstractEntity;
import pl.indecoders.archetype.domain.account.Account;

/**
 * The Class Product.
 * @author Mateusz
 */
@Entity
@Table(name = "product")
public class Product extends AbstractEntity {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "Product_SEQUENCE", sequenceName = "product_seq")
	@GeneratedValue(generator = "Product_SEQUENCE")
	private Long id;
	
	@Column(name = "product_code")
	private String productCode;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_specification")
	private String productSpecification;
	
	@Column(name = "tax_amount")
	@NumberFormat(style = NUMBER)
	private BigDecimal taxAmount;
	
	@Column(name = "product_type")
	@Enumerated(STRING)
	private Type productType;

	@Column(name = "product_unit")
	@Enumerated(STRING)
	private Unit productUnit;
	
	@Column(name = "base_price")
	@NumberFormat(style = CURRENCY)
	private BigDecimal basePrice;
	
	@ManyToOne(cascade = ALL, fetch = EAGER)
	@JoinColumn(name = "product_owner_id")
	private Account owner;

	@ManyToOne(fetch = EAGER)
	@JoinColumn(name = "product_group_id")
	private ProductGroup group;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

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

	public Account getOwner() {
		return owner;
	}

	public void setOwner(Account owner) {
		this.owner = owner;
	}

	public ProductGroup getGroup() {
		return group;
	}

	public void setGroup(ProductGroup group) {
		this.group = group;
	}
	
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	
	public Product() {
		super(now(UTC));
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
	}

	@Override
	public int hashCode() {
		return reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (!(this instanceof Product)) {
			return false;
		} else {
			Product comparedProduct = (Product) obj;
			return new EqualsBuilder().append(this.id, comparedProduct.id).isEquals();
		}
	}
}
