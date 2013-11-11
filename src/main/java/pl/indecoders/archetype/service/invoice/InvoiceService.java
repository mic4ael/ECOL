package pl.indecoders.archetype.service.invoice;

import static pl.indecoders.archetype.domain.payment.DiscountType.MONETISED;
import static pl.indecoders.archetype.domain.payment.DiscountType.PERCENTAGE;
import static pl.indecoders.archetype.specification.invoice.InvoiceSpecifications.hasOwnerAndInterval;
import static pl.indecoders.archetype.utils.SortTranslationUtils.translateDirection;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.account.Profile;
import pl.indecoders.archetype.domain.address.Address;
import pl.indecoders.archetype.domain.document.Invoice;
import pl.indecoders.archetype.domain.payment.BankInformations;
import pl.indecoders.archetype.domain.payment.Discount;
import pl.indecoders.archetype.domain.payment.PaymentInformations;
import pl.indecoders.archetype.domain.product.Product;
import pl.indecoders.archetype.domain.product.ProductRow;
import pl.indecoders.archetype.form.address.AddressForm;
import pl.indecoders.archetype.form.invoice.InvoiceForm;
import pl.indecoders.archetype.form.invoice.ProductRowForm;
import pl.indecoders.archetype.form.report.ReportForm;
import pl.indecoders.archetype.form.report.ReportModel;
import pl.indecoders.archetype.repository.invoice.InvoiceRepository;
import pl.indecoders.archetype.repository.product.ProductRepository;
import pl.indecoders.archetype.service.customer.CustomerService;

/**
 * The Class InvoiceService.
 * 
 * @author Mateusz
 */
@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductRepository productRepository;

	public Invoice prepareInvoice(final InvoiceForm form, final List<ProductRowForm> products, final Account owner) {
		Invoice invoice = new Invoice();
		invoice.setCreationDate(form.getCreationDate());
		invoice.setSoldDate(form.getSoldDate());
		invoice.setInvoiceNumber(form.getInvoiceNumber());
		invoice.setOwner(owner);
		invoice.setInvoiceCity(form.getInvoiceCity());
		invoice.setPaymentInformations(preparePaymentInformation(form));
		return setSeller(form, products, owner, invoice);
	}

	private PaymentInformations preparePaymentInformation(InvoiceForm form) {
		PaymentInformations information = new PaymentInformations();
		information.setPaymentDate(form.getPaymentDate());
		information.setType(form.getPaymentType());
		return information;
	}

	private Invoice setSeller(InvoiceForm form, List<ProductRowForm> products, final Account owner, final Invoice invoice) {
		Profile profile = new Profile();
		profile.setAddress(prepareAddress(form.getSeller().getAddress()));
		profile.setName(form.getSeller().getName());
		profile.setNip(form.getSeller().getNip());
		profile.setContactPhone(form.getSeller().getContactPhone() != null ? form.getSeller().getContactPhone() : null);
		profile.setFaxPhone(form.getSeller().getFaxPhone() != null ? form.getSeller().getFaxPhone() : null);
		profile.setEmail(form.getSeller().getEmail() != null ? form.getSeller().getEmail() : null);
		profile.setBankInformations(prepareBankInformation(form.getSeller().getBankName(), form.getSeller().getBankNumber()));
		invoice.setDifferentSeller(profile);
		return setCustomer(form, products, owner, invoice);
	}

	private BankInformations prepareBankInformation(String bankName, String bankNumber) {
		BankInformations information = new BankInformations();
		information.setBankAccountNumber(bankNumber);
		information.setBankName(bankName);
		return information;
	}

	private Address prepareAddress(AddressForm addressForm) {
		Address address = new Address();
		address.setCity(addressForm.getCity());
		address.setHomeNumber(addressForm.getHomeNumber());
		address.setPostalCode(addressForm.getPostalCode());
		address.setStreet(addressForm.getStreet());
		return address;
	}

	private Invoice setCustomer(InvoiceForm form, List<ProductRowForm> products, Account owner, final Invoice invoice) {
		invoice.setCustomer(customerService.findCustomerOrCreate(form.getCustomer(), owner));
		return setProducts(products, invoice);
	}

	private Invoice setProducts(List<ProductRowForm> products, Invoice invoice) {
		Set<ProductRow> productRows = new HashSet<ProductRow>();
		for(ProductRowForm row : products) {
			productRows.add(prepareRow(row));
		}
		invoice.setProducts(productRows);
		return persistInvoice(invoice);
	}

	private ProductRow prepareRow(ProductRowForm row) {
		ProductRow productRow = new ProductRow();
		Product product = productRepository.findOne(row.getId());
		productRow.setAmount(row.getAmount());
		productRow.setProduct(product);
		productRow.setDiscount(prepareDiscount(row.getDiscountAmount(), row.getDiscountType()));
		productRow.setGeneralPrice(prepareGeneralPrice(row, product));
		productRow.setTaxPrice(productRow.getGeneralPrice().multiply(product.getTaxAmount().divide(new BigDecimal(100))));
		return productRow;
	}

	private Discount prepareDiscount(BigDecimal discountAmount, String discountType) {
		Discount discount = new Discount();
		discount.setDiscountAmount(discountAmount);
		discount.setType(discountType.equalsIgnoreCase(MONETISED.toString()) ? MONETISED : PERCENTAGE);
		return discount; 
	}
	
	private BigDecimal prepareGeneralPrice(final ProductRowForm row, final Product product) {
		BigDecimal price = product.getBasePrice().multiply(new BigDecimal(row.getAmount()));
		if(row.getDiscountType().equalsIgnoreCase(MONETISED.toString())) {
			return price.subtract(row.getDiscountAmount());
		}
		return price.subtract(price.multiply(row.getDiscountAmount().divide(new BigDecimal(100))));
	}

	private Invoice persistInvoice(Invoice invoice) {
		prepareInvoiceAmounts(invoice);
		return invoiceRepository.saveAndFlush(invoice);
	}

	private void prepareInvoiceAmounts(Invoice invoice) {
		
		BigDecimal generalPrice = BigDecimal.ZERO;
		BigDecimal taxPrice = BigDecimal.ZERO;
		
		for(ProductRow row : invoice.getProducts()) {
			generalPrice = generalPrice.add(row.getGeneralPrice());
			taxPrice = taxPrice.add(row.getTaxPrice());
		}
		invoice.setGeneralAmount(generalPrice);
		invoice.setTaxAmount(taxPrice);
	}
	
	/* Paged records */
	
	public List<Invoice> getPagedInvoices(final Account owner, final Integer pageIndex, final Integer pageLimit) {
		final PageRequest request = new PageRequest(pageIndex, pageLimit);
		return invoiceRepository.findByOwner(owner, request);
	}
	
	public List<Invoice> getPagedInvoices(final Account owner, final Integer pageIndex, final Integer pageLimit, final String sortProperty, final String sortDirection) {
		final PageRequest request = new PageRequest(pageIndex, pageLimit, translateDirection(sortDirection), sortProperty);
		return invoiceRepository.findByOwner(owner, request);
	}
	
	/* Sold report */
	
	public ReportModel prepareReportModel(final ReportForm form, final Account owner) {
		List<Invoice> invoicesFromInterval = invoiceRepository.findAll(hasOwnerAndInterval(owner, form.getDateFrom(), form.getDateTo()));
		BigDecimal generalAmount = BigDecimal.ZERO;
		BigDecimal taxAmount = BigDecimal.ZERO;
		for(Invoice invoice : invoicesFromInterval) {
			generalAmount = generalAmount.add(invoice.getGeneralAmount());
			taxAmount = taxAmount.add(invoice.getTaxAmount());
		}
		return new ReportModel(generalAmount, taxAmount);
	}
}
