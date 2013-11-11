package pl.indecoders.archetype.domain.document;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.joda.time.DateTime.now;
import static org.joda.time.DateTimeZone.UTC;
import static org.springframework.format.annotation.NumberFormat.Style.NUMBER;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.NumberFormat;

import pl.indecoders.archetype.domain.AbstractEntity;
import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.account.Profile;
import pl.indecoders.archetype.domain.customer.Customer;
import pl.indecoders.archetype.domain.payment.PaymentInformations;
import pl.indecoders.archetype.domain.product.ProductRow;

/**
 * The Class Invoice.
 * @author Mateusz
 */
@Table(name = "invoice")
@Entity
public class Invoice extends AbstractEntity {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "Invoice_SEQUENCE", sequenceName = "invoice_seq")
	@GeneratedValue(generator = "Invoice_SEQUENCE")
	private Long id;

	@Column(name = "invoice_number")
	@NumberFormat(style = NUMBER)
	private String invoiceNumber;
	
	@Column(name = "invoice_city")
	private String invoiceCity;
	
	@Column(name = "sold_date")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime soldDate;
	
	@ManyToOne(fetch = EAGER)
	@JoinColumn(name = "invoice_owner_id")
	private Account owner;
	
	@ManyToOne(fetch = EAGER)
	@JoinColumn(name = "invoice_customer_id")
	private Customer customer;
	
	@ManyToMany(fetch = EAGER, cascade = ALL)
	private Set<ProductRow> products;
	
	@ManyToOne(fetch = EAGER, cascade = ALL)
	@JoinColumn(name = "payment_informations_id")
	private PaymentInformations paymentInformations;
	
	@ManyToOne(fetch = EAGER, cascade = ALL)
	private Profile differentSeller;
	
	@Column(name = "general_amount")
	private BigDecimal generalAmount;
	
	@Column(name = "tax_amount")
	private BigDecimal taxAmount;
	
	public BigDecimal getGeneralAmount() {
		return generalAmount;
	}

	public void setGeneralAmount(BigDecimal generalAmount) {
		this.generalAmount = generalAmount;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public DateTime getSoldDate() {
		return soldDate;
	}

	public void setSoldDate(DateTime soldDate) {
		this.soldDate = soldDate;
	}

	public Account getOwner() {
		return owner;
	}

	public void setOwner(Account owner) {
		this.owner = owner;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<ProductRow> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductRow> products) {
		this.products = products;
	}

	public PaymentInformations getPaymentInformations() {
		return paymentInformations;
	}

	public void setPaymentInformations(PaymentInformations paymentInformations) {
		this.paymentInformations = paymentInformations;
	}

	public Invoice() {
		super(now(UTC));
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
	}

	public Profile getDifferentSeller() {
		return differentSeller;
	}

	public void setDifferentSeller(Profile differentSeller) {
		this.differentSeller = differentSeller;
	}

	public String getInvoiceCity() {
		return invoiceCity;
	}

	public void setInvoiceCity(String invoiceCity) {
		this.invoiceCity = invoiceCity;
	}
}
