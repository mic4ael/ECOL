package pl.indecoders.archetype.domain.product;

import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.joda.time.DateTime.now;
import static org.joda.time.DateTimeZone.UTC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "referenced_product_id")
	private Product product;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "referenced_discount_id")
	private Discount discount;

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
}
