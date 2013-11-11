<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<div class="page_view">

	<form method="post">
		<div class="sort-controls">
			<div class="pull-left">
				<select class="inv-input-med" name="dir">
					<option value="ASC"><s:message code="customersList.asc" /></option>
					<option value="DESC"><s:message code="customersList.desc" /></option>
				</select>
			</div>
			<div class="pull-left">
				<select class="inv-input-med" name="property">
					<option value="invoiceNumber"><s:message code="invoiceDetails.number" /></option>
					<option value="invoiceCity"><s:message code="invoiceDetails.city" /></option>
					<option value="generalAmount"><s:message code="invoiceDetails.amount" /></option>
					<option value="taxAmount"><s:message code="invoiceDetails.taxAmount" /></option>
					<option value="soldDate"><s:message code="invoiceDetails.soldDate" /></option>
					<option value="paymentInformations.paymentDate"><s:message code="invoiceDetails.paymentDate" /></option>
					<option value="paymentInformations.type"><s:message code="invoiceDetails.paymentType" /></option>
					<option value="customer.name"><s:message code="invoiceDetails.customerName" /></option>
				</select>
			</div>
			<div class="pull-left">
				<button class="btn btn-default">
					<s:message code="customersList.sort" />
				</button>
			</div>
		</div>
	</form>

	<table class="table table-striped">
		<thead>
			<tr>
				<th><s:message code="invoiceDetails.number" /></th>
				<th><s:message code="invoiceDetails.city" /></th>
				<th><s:message code="invoiceDetails.amount" /></th>
				<th><s:message code="invoiceDetails.taxAmount" /></th>
				<th><s:message code="invoiceDetails.soldDate" /></th>
				<th><s:message code="invoiceDetails.paymentDate" /></th>
				<th><s:message code="invoiceDetails.paymentType" /></th>
				<th><s:message code="invoiceDetails.customerName" /></th>
				<th><s:message code="invoiceDetails.details" /></th>
				<th><s:message code="invoiceDetails.pdf" /></th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach items="${invoices}" var="invoice">
			
				<c:url value="/invoice-details/${invoice.id}" var="details" />
				<c:url value="/generate-pdf/${invoice.id}" var="pdf" />
			
				<tr>
					<td>${invoice.invoiceNumber}</td>
					<td>${invoice.invoiceCity}</td>
					<td>${invoice.generalAmount} PLN</td>
					<td>${invoice.taxAmount} PLN</td>
					<td><joda:format pattern="dd - MM - yyyy" value="${invoice.soldDate}" /></td>
					<td><joda:format pattern="dd - MM - yyyy" value="${invoice.paymentInformations.paymentDate}" /></td>
					<td>${invoice.paymentInformations.type}</td>
					<td>${invoice.customer.name}</td>
					<td><a href="${details}"><s:message code="invoiceDetails.details" /></a></td>
					<td><a href="${pdf}"><s:message code="invoiceDetails.pdf" /></a></td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>
	
	<c:if test="${invoicesPages > 1}">
        <div class="pagination">
                <ul>
                        <c:forEach begin="1" end="${invoicesPages}" var="page">
                                <li><a href='<s:url value="/invoices-list/${page}"></s:url>'>${page}</a></li>
                        </c:forEach>
                </ul>
        </div>
    </c:if>

</div>