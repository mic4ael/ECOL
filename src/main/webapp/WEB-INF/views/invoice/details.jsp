<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<div class="page_view">

	<span><s:message code="invoiceDetails.num" arguments="${invoiceDetails.invoiceNumber}" /></span>
	
	<table class="pull-right">
		<tr>
			<td><p><strong><s:message code="invoiceDetails.city" />:</strong> ${invoiceDetails.invoiceCity}</p></td>
		</tr>
		<tr>
			<td><p><strong><s:message code="invoiceDetails.soldDate" />:</strong> <joda:format pattern="dd - MM - yyyy" value="${invoiceDetails.soldDate}" /></p>
			</td>
		</tr>
		<tr>
			<td><p><strong><s:message code="invoiceDetails.creationDate" />:</strong> <joda:format pattern="dd - MM - yyyy" value="${invoiceDetails.creationDate}" /></p>
			</td>
		</tr>
		<tr>
			<td><p><strong><s:message code="invoiceDetails.paymentDate" />:</strong> <joda:format pattern="dd - MM - yyyy" value="${invoiceDetails.paymentInformations.paymentDate}" /></p>
			</td>
		</tr>
	</table>
	
	<div style="margin-top: 120px;">
	
		<table class="pull-left table" style="margin-top: 10px; width: 45%;">
			<tr>
				<td style="border-bottom: 1px solid #373737;"><strong>Seller</strong></td>
			</tr>
			<tr>
				<td><strong>${invoiceDetails.differentSeller.name}</strong></td>
			</tr>
			<tr>
				<td>${invoiceDetails.differentSeller.address.street} ${invoiceDetails.differentSeller.address.homeNumber}</td>
			</tr>
			<tr>
				<td>${invoiceDetails.differentSeller.address.city}, ${invoiceDetails.differentSeller.address.postalCode}</td>
			</tr>
			<tr>
				<td>NIP: ${invoiceDetails.differentSeller.nip}</td>
			</tr>
			<c:if test="${invoiceDetails.differentSeller.contactPhone != null}">
				<tr>
					<td><s:message code="invoiceDetails.phone" />: ${invoiceDetails.differentSeller.contactPhone}</td>
				</tr>
			</c:if>
			<c:if test="${invoiceDetails.differentSeller.faxPhone != null}">
				<tr>
					<td><s:message code="invoiceDetails.fax" />: ${invoiceDetails.differentSeller.faxPhone}</td>
				</tr>
			</c:if>
		</table>
		
		<table class="pull-right table" style="margin-top: 10px; width: 45%;">
			<tr>
				<td style="border-bottom: 1px solid #373737;"><strong>Customer</strong></td>
			</tr>
			<tr>
				<td><strong>${invoiceDetails.customer.name}</strong></td>
			</tr>
			<tr>
				<td>${invoiceDetails.customer.address.street} ${invoiceDetails.customer.address.homeNumber}</td>
			</tr>
			<tr>
				<td>${invoiceDetails.customer.address.city}, ${invoiceDetails.customer.address.postalCode}</td>
			</tr>
			<tr>
				<td>NIP: ${invoiceDetails.customer.nip}</td>
			</tr>
			<c:if test="${invoiceDetails.customer.contactPhone != null}">
				<tr>
					<td><s:message code="invoiceDetails.phone" />: ${invoiceDetails.customer.contactPhone}</td>
				</tr>
			</c:if>
			<c:if test="${invoiceDetails.customer.faxPhone != null}">
				<tr>
					<td><s:message code="invoiceDetails.fax" />: ${invoiceDetails.customer.faxPhone}</td>
				</tr>
			</c:if>
		</table>
	
	</div>
	
	<div style="margin-top: 400px;">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th><s:message code="invoiceDetails.productName" /></th>
					<th><s:message code="invoiceDetails.productUnit" /></th>
					<th><s:message code="invoiceDetails.productNumber" /></th>
					<th><s:message code="invoiceDetails.productPrice" /></th>
					<th><s:message code="invoiceDetails.productTaxValue" /></th>
					<th><s:message code="invoiceDetails.productAmount" /></th>
					<th><s:message code="invoiceDetails.productTax" /></th>
				</tr>
			</thead>
			<tbody>
			
			<c:set var="count" value="1" scope="page" />

				<c:forEach items="${invoiceDetails.products}" var="row">
				<tr>		
					<td>${count}</td>
					<td>${row.product.productName}</td>
					<td>${row.product.productUnit}</td>
					<td>${row.amount}</td>
					<td>${row.product.basePrice} PLN</td>
					<td>${row.product.taxAmount} %</td>
					<td>${row.generalPrice} PLN</td>
					<td>${row.taxPrice} PLN</td>	
					
				<c:set var="count" value="${count + 1}" scope="page"/>
				
				</tr>
									
				</c:forEach>
				
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><strong><s:message code="invoiceDetails.general" /></strong></td>
					<td>${invoiceDetails.generalAmount} PLN</td>
					<td>${invoiceDetails.taxAmount} PLN</td>
				</tr>
				
			</tbody>
		</table>
		
		<p><strong><s:message code="invoiceDetails.bankName" />: </strong>${invoiceDetails.differentSeller.bankInformations.bankName}</p>
		<p><strong><s:message code="invoiceDetails.bankNumber" />: </strong>${invoiceDetails.differentSeller.bankInformations.bankAccountNumber}</p>
		<p><strong><s:message code="invoiceDetails.paymentType" />: </strong>${invoiceDetails.paymentInformations.type}</p>
		
	</div>

</div>