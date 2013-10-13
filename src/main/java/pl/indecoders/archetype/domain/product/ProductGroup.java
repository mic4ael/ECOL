package pl.indecoders.archetype.domain.product;

import static javax.persistence.FetchType.EAGER;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.joda.time.DateTime.now;
import static org.joda.time.DateTimeZone.UTC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import pl.indecoders.archetype.domain.AbstractEntity;
import pl.indecoders.archetype.domain.account.Account;

/**
 * The Class Group.
 * @author Mateusz
 */
@Entity
@Table(name = "product_group")
public class ProductGroup extends AbstractEntity {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "ProductGroup_SEQUENCE", sequenceName = "product_group_seq")
	@GeneratedValue(generator = "ProductGroup_SEQUENCE")
	private Long id;

	@Column(name = "name")
	private String name;
	
	@Column(name ="specification")
	private String specification;

	@ManyToOne(fetch = EAGER)
	@JoinColumn(name = "group_owner_id")
	private Account owner;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Account getOwner() {
		return owner;
	}

	public void setOwner(Account owner) {
		this.owner = owner;
	}
	
	public ProductGroup() {
		super(now(UTC));
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
	}
}
