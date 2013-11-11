package pl.indecoders.archetype.domain.document;

import java.math.BigDecimal;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.joda.time.DateTime;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.account.Profile;
import pl.indecoders.archetype.domain.customer.Customer;
import pl.indecoders.archetype.domain.payment.PaymentInformations;
import pl.indecoders.archetype.domain.product.ProductRow;

/**
 * The Class Invoice_.
 * @author Mateusz
 */
@StaticMetamodel(Invoice.class)
public class Invoice_ {

	public static SingularAttribute<Invoice, Long> id;
	public static SingularAttribute<Invoice, String> invoiceNumber;
	public static SingularAttribute<Invoice, String> invoiceCity;
	public static SingularAttribute<Invoice, DateTime> soldDate;
	public static SingularAttribute<Invoice, Account> owner;
	public static SingularAttribute<Invoice, Customer> customer;
	public static SetAttribute<Invoice, ProductRow> products;
	public static SingularAttribute<Invoice, PaymentInformations> paymentInformations;
	public static SingularAttribute<Invoice, Profile> differentSeller;
	public static SingularAttribute<Invoice, BigDecimal> generalAmount;
	public static SingularAttribute<Invoice, BigDecimal> taxAmount;
}
