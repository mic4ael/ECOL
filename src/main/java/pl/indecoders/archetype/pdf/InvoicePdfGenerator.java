package pl.indecoders.archetype.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import pl.indecoders.archetype.domain.document.Invoice;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

@Component(value="pdfView")
public class InvoicePdfGenerator extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter pdf, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Invoice invoice = (Invoice) map.get("invoiceDetails");
		Table table = new Table(2);
		table.addCell(invoice.getInvoiceCity());
		table.addCell(invoice.getSoldDate().toString());
		
		document.addTitle("Faktura nr: " + invoice.getInvoiceNumber());
		document.addCreationDate();
		document.add(table);
	}
}
