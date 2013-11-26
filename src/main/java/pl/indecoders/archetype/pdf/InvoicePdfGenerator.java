package pl.indecoders.archetype.pdf;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import pl.indecoders.archetype.domain.document.Invoice;
import pl.indecoders.archetype.domain.product.ProductRow;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component(value="pdfView")
public class InvoicePdfGenerator extends AbstractPdfView {
	
	private final float MINIMUM_BLANK_CELL_HEIGHT = 20;
	private final Font boldFont = new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD);
	
	@Override
	protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter pdf, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Invoice invoice = (Invoice) map.get("invoiceDetails");
		
		document.add(createTitle(document, invoice));
		document.add(createGenericInfo(document, invoice));
		PdfPTable t = new PdfPTable(2);
		
		t.setWidthPercentage(100);
		t.addCell(createSellerData(document, invoice));
		t.addCell(createCustomerData(document, invoice));
		
		document.add(t);
		PdfPTable newT = new PdfPTable(1);
		newT.setWidthPercentage(100);
		newT.addCell(blankCell());
		
		document.add(newT);
		document.add(productsList(document, invoice));
		document.add(createBankData(document, invoice));
	}
	
	private PdfPTable createBankData(final Document doc, final Invoice inv) {
		PdfPTable tab = new PdfPTable(1);
		tab.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		
		tab.addCell(blankCell());
		tab.addCell(prepareNoBorderCell("Bank Name: ", inv.getDifferentSeller().getBankInformations().getBankName(), PdfPTable.ALIGN_LEFT));
		tab.addCell(prepareNoBorderCell("Bank Account Number: ", inv.getDifferentSeller().getBankInformations().getBankAccountNumber(), PdfPTable.ALIGN_LEFT));
		tab.addCell(prepareNoBorderCell("Payment Type: ", inv.getPaymentInformations().getType().toString(), PdfPTable.ALIGN_LEFT));
		
		tab.addCell(blankCell());
		
		return tab;
	}
	
	private PdfPTable productsList(final Document doc, final Invoice inv) throws DocumentException {
		PdfPTable tab = new PdfPTable(8);
		
		tab.setWidthPercentage(100);
		tab.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
		tab.setWidths(new float[] {30, 100, 30, 100, 50, 70, 50, 50});
		tab.addCell(new PdfPCell(new Phrase("#", boldFont)));
		tab.addCell(new PdfPCell(new Phrase("Name of product", boldFont)));
		tab.addCell(new PdfPCell(new Phrase("Unit", boldFont)));
		tab.addCell(new PdfPCell(new Phrase("Number of products", boldFont)));
		tab.addCell(new PdfPCell(new Phrase("Price", boldFont)));
		tab.addCell(new PdfPCell(new Phrase("Tax value", boldFont)));
		tab.addCell(new PdfPCell(new Phrase("Amount", boldFont)));
		tab.addCell(new PdfPCell(new Phrase("Tax", boldFont)));
		
		int i = 1;
		for(ProductRow product : inv.getProducts()) {
			tab.addCell(prepareNoBorderCell(null, new Integer(i++).toString(), PdfPCell.ALIGN_CENTER));
			tab.addCell(prepareNoBorderCell(null, product.getProduct().getProductName(), PdfPCell.ALIGN_CENTER));
			tab.addCell(prepareNoBorderCell(null, product.getProduct().getProductUnit().toString(), PdfPCell.ALIGN_CENTER));
			tab.addCell(prepareNoBorderCell(null, product.getAmount().toString(), PdfPCell.ALIGN_CENTER));
			tab.addCell(prepareNoBorderCell(null, product.getProduct().getBasePrice().toPlainString(), PdfPCell.ALIGN_CENTER));
			tab.addCell(prepareNoBorderCell(null, product.getProduct().getTaxAmount().toPlainString(), PdfPCell.ALIGN_CENTER));
			tab.addCell(prepareNoBorderCell(null, product.getGeneralPrice().toPlainString(), PdfPCell.ALIGN_CENTER));
			tab.addCell(prepareNoBorderCell(null, product.getTaxPrice().toPlainString(), PdfPCell.ALIGN_CENTER));
		}
		
		makeSummary(tab, inv);
		
		return tab;
	}
	
	private void makeSummary(final PdfPTable tab, final Invoice inv) {
		
		for (int i = 0; i < 5; ++i)
			tab.addCell(blankCell());
		
		tab.addCell(prepareNoBorderCell("Summary: ", null, PdfPCell.ALIGN_CENTER));
		tab.addCell(prepareNoBorderCell(null, inv.getGeneralAmount().toPlainString(), PdfPCell.ALIGN_CENTER));
		tab.addCell(prepareNoBorderCell(null, inv.getTaxAmount().toPlainString(), PdfPCell.ALIGN_CENTER));
	}
	
	private PdfPTable createSellerData(final Document doc, final Invoice inv) {
		PdfPTable tab = new PdfPTable(1);
		
		tab.setWidthPercentage(40);
		tab.setHorizontalAlignment(PdfPTable.ALIGN_RIGHT);
		tab.addCell(createTableHeader("Seller"));
		tab.addCell(prepareNoBorderCell(null, inv.getDifferentSeller().getName(), PdfPCell.ALIGN_LEFT));
		tab.addCell(prepareNoBorderCell(null, inv.getDifferentSeller().getAddress().getStreet() + ", " + 
				inv.getDifferentSeller().getAddress().getHomeNumber(), PdfPCell.ALIGN_LEFT));
		tab.addCell(prepareNoBorderCell(null, inv.getDifferentSeller().getAddress().getCity() + ", " + 
					inv.getDifferentSeller().getAddress().getPostalCode(), PdfPCell.ALIGN_LEFT));
		tab.addCell(prepareNoBorderCell("NIP: ", inv.getDifferentSeller().getNip(), PdfPCell.ALIGN_LEFT));
		tab.addCell(prepareNoBorderCell("Phone: ", inv.getDifferentSeller().getContactPhone(), PdfPCell.ALIGN_LEFT));
		tab.addCell(prepareNoBorderCell("Fax Phone: ", inv.getDifferentSeller().getFaxPhone(), PdfPCell.ALIGN_LEFT));
		
		return tab;
	}
	
	private PdfPCell createTableHeader(String headerValue) {
		PdfPCell cell = blankCell();
		
		cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
		cell.setPhrase(new Phrase(headerValue, boldFont));
		cell.setBorderWidthBottom(2);
		
		return cell;
	}
	
	private PdfPTable createCustomerData(final Document doc, final Invoice inv) {
		PdfPTable tab = new PdfPTable(1);
		
		tab.setWidthPercentage(40);
		tab.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
		tab.addCell(createTableHeader("Customer"));
		tab.addCell(prepareNoBorderCell(null, inv.getCustomer().getName(), PdfPCell.ALIGN_LEFT));
		tab.addCell(prepareNoBorderCell(null, inv.getCustomer().getAddress().getStreet() + ", " + 
				inv.getCustomer().getAddress().getHomeNumber(), PdfPCell.ALIGN_LEFT));
		tab.addCell(prepareNoBorderCell(null, inv.getCustomer().getAddress().getCity() + ", " + 
					inv.getCustomer().getAddress().getPostalCode(), PdfPCell.ALIGN_LEFT));
		tab.addCell(prepareNoBorderCell("NIP: ", inv.getCustomer().getNip(), PdfPCell.ALIGN_LEFT));
		tab.addCell(prepareNoBorderCell("Phone: ", inv.getCustomer().getContactPhone(), PdfPCell.ALIGN_LEFT));
		tab.addCell(prepareNoBorderCell("Fax Phone: ", inv.getCustomer().getFaxPhone(), PdfPCell.ALIGN_LEFT));
		
		return tab;
	}
	
	private PdfPTable createGenericInfo(final Document doc, final Invoice inv) {
		PdfPTable tab = new PdfPTable(1);
		tab.setHorizontalAlignment(PdfPTable.ALIGN_RIGHT);
		
		tab.addCell(prepareNoBorderCell("City: " , inv.getInvoiceCity(), PdfPCell.ALIGN_RIGHT));
		tab.addCell(prepareNoBorderCell("Sold Date: ", new SimpleDateFormat("dd-MM-YYYY").format(inv.getSoldDate().toDate()), PdfPCell.ALIGN_RIGHT));
		tab.addCell(prepareNoBorderCell("Creation Date: ", new SimpleDateFormat("dd-MM-YYYY").format(inv.getCreationDate().toDate()), PdfPCell.ALIGN_RIGHT));
		tab.addCell(prepareNoBorderCell("Payment Date: ", new SimpleDateFormat("dd-MM-YYYY").format(inv.getPaymentInformations().getPaymentDate().toDate()),
				PdfPCell.ALIGN_RIGHT));
		tab.addCell(blankCell());
		
		return tab;
	}
	
	private PdfPCell blankCell() {
		PdfPCell cell = new PdfPCell();
		
		cell.setMinimumHeight(MINIMUM_BLANK_CELL_HEIGHT);
		cell.setVerticalAlignment(PdfPTable.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		
		return cell;
	}
	
	private PdfPCell prepareNoBorderCell(String name, String value, int hAlign) {
		PdfPCell cell = new PdfPCell();
		
		Phrase ph = new Phrase(name, boldFont);
		ph.setFont(new Font());
		ph.add(new Phrase(value));
		
		cell.setPhrase(ph);
		cell.setHorizontalAlignment(hAlign);
		cell.setVerticalAlignment(PdfPTable.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.NO_BORDER);
		
		return cell;
	}
	
	private PdfPTable createTitle(final Document doc, final Invoice inv) throws BadElementException {
		PdfPTable tab = new PdfPTable(1);

		tab.addCell(prepareNoBorderCell("Invoice Number: ", inv.getInvoiceNumber(), PdfPCell.ALIGN_CENTER));
		tab.addCell(blankCell());
		
		return tab;
	}
}
