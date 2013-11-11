package pl.indecoders.archetype.form.report;

import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * The Class ReportForm.
 * @author Mateusz
 */
public class ReportForm {

	@DateTimeFormat(pattern = "dd - MM - yyyy")
	private DateTime dateFrom;
	
	@DateTimeFormat(pattern = "dd - MM - yyyy")
	private DateTime dateTo;

	public DateTime getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(DateTime dateFrom) {
		this.dateFrom = dateFrom;
	}

	public DateTime getDateTo() {
		return dateTo;
	}

	public void setDateTo(DateTime dateTo) {
		this.dateTo = dateTo;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
	}
}
