package pl.indecoders.archetype.domain.product;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.FetchType.EAGER;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.joda.time.DateTime.now;
import static org.joda.time.DateTimeZone.UTC;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import pl.indecoders.archetype.domain.AbstractEntity;
import pl.indecoders.archetype.domain.payment.Discount;

/**
 * The Class ProductRow.
 * @author Mateusz
 */
@Table(name = "product_row")
@Entity
public class ProductRow extends AbstractEntity {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "ProductRow_SEQUENCE", sequenceName = "product_row_seq")
	@GeneratedValue(generator = "ProductRow_SEQUENCE")
	private Long id;

	@ManyToOne(fetch = EAGER, cascade = MERGE)
	@JoinColumn(name = "referenced_product_id")
	private Product product;
	
	@ManyToOne(fetch = EAGER, cascade = ALL)
	@JoinColumn(name = "referenced_discount_id")
	private Discount discount;
	
	@Column(name = "referenced_product_amount")
	private Integer amount;
	
	@Column(name = "referenced_product_general_price")
	private BigDecimal generalPrice;
	
	@Column(name = "referenced_product_tax_price")
	private BigDecimal taxPrice;
	
	public BigDecimal getGeneralPrice() {
		return generalPrice;
	}

	public void setGeneralPrice(BigDecimal generalPrice) {
		this.generalPrice = generalPrice;
	}

	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public ProductRow() {
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
		if(this == obj) {
			return true;
		} else if (!(this instanceof ProductRow)) {
			return false;
		} else {
			EqualsBuilder builder = new EqualsBuilder();
			Set<ProductRow> comparedSet = (Set<ProductRow>) obj;
			
			for(ProductRow comparedRow : comparedSet) {
				builder.append(this.id, comparedRow.getId());
				builder.append(this.amount, comparedRow.getAmount());
				builder.append(this.discount, comparedRow.getDiscount());
				builder.append(this.product, comparedRow.getProduct());
			}
			
			return builder.isEquals();
		}
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
