package pl.indecoders.archetype.form.invoice;

import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import pl.indecoders.archetype.domain.payment.PaymentType;
import pl.indecoders.archetype.form.customer.NewCustomerForm;

/**
 * The Class InvoiceForm.
 * @author Mateusz
 */
public class InvoiceForm {

	@NotEmpty
	private String invoiceNumber;
	
	@NotEmpty
	private String invoiceCity;
	
	@DateTimeFormat(pattern = "dd - MM - yyyy")
	private DateTime creationDate;
	
	@DateTimeFormat(pattern = "dd - MM - yyyy")
	private DateTime soldDate;
	
	@Valid
	private SellerForm seller;
	
	@Valid
	private NewCustomerForm customer;
	
	private PaymentType paymentType;
	
	@DateTimeFormat(pattern = "dd - MM - yyyy")
	private DateTime paymentDate;

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoiceCity() {
		return invoiceCity;
	}

	public void setInvoiceCity(String invoiceCity) {
		this.invoiceCity = invoiceCity;
	}

	public DateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}

	public DateTime getSoldDate() {
		return soldDate;
	}

	public void setSoldDate(DateTime soldDate) {
		this.soldDate = soldDate;
	}

	public SellerForm getSeller() {
		return seller;
	}

	public void setSeller(SellerForm seller) {
		this.seller = seller;
	}

	public NewCustomerForm getCustomer() {
		return customer;
	}

	public void setCustomer(NewCustomerForm customer) {
		this.customer = customer;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public DateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(DateTime paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
	}
}
