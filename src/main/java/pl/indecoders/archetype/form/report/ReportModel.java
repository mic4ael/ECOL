package pl.indecoders.archetype.form.report;

import java.math.BigDecimal;

/**
 * The Class ReportModel.
 * @author Mateusz
 */
public class ReportModel {

	private BigDecimal generalPrice;
	private BigDecimal tax;
	
	public BigDecimal getGeneralPrice() {
		return generalPrice;
	}
	public void setGeneralPrice(BigDecimal generalPrice) {
		this.generalPrice = generalPrice;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public ReportModel(BigDecimal generalPrice, BigDecimal tax) {
		super();
		this.generalPrice = generalPrice;
		this.tax = tax;
	}
}
