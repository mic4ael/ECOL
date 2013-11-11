<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<% pageContext.setAttribute("now", new org.joda.time.DateTime()); %>
<joda:format value="${now}" var="actualDate" pattern="dd - MM - yyyy"/>

<joda:format value="${now}" var="actualYear" pattern="yyyy" />

<s:message code="newInvoice.searchCustomerPlaceholder" var="filter" />

	<!-- New customer modal -->

<div class="modal fade" id="searchCustomerModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<strong><s:message code="newInvoice.checkCustomer" /></strong>
			</div>

			<div class="modal-body">
			
				<div class="modal-row">
					<input class="inv-input-med" id="searchCustomerInput" placeholder="${filter}" />
				</div>
			
				<div class="modal-record-container">
			
					<table class="table">
						<thead>
							<tr>
								<th style="display: none;">id</th>
								<th><s:message code="newCustomer.name" /></th>
								<th><s:message code="newCustomer.city" /></th>
								<th><s:message code="newCustomer.nip" /></th>
							</tr>
						</thead>
						<tbody id="customersJsonList">
							
						</tbody>
					</table>
				
				</div>
			
			</div>

			<div class="modal-footer">
				<button id="customersModalCloser" type="button" class="btn btn-default">Close</button>
			</div>
		</div>
	</div>
</div>


<div class="page_view">

	<!-- Main piece of invoice - general information -->

	<p>
		<strong><s:message code="newInvoice.title" /></strong>
	</p>

	<form:form modelAttribute="newInvoiceForm" method="post">

		<div class="invoice-piece" style="height: 100px;">
	
			<div class="container-small pull-left">
				<form:errors path="invoiceNumber" element="span" class="merit-error"></form:errors>
				<span><s:message code="newInvoice.number" /></span> <form:input path="invoiceNumber"
					type="text" value="${actualYear} / ${invoiceNumber}" class="inv-input-sm" />
			</div>

			<div class="container-small pull-left">
			<form:errors path="invoiceCity" element="span" class="merit-error"></form:errors>
				<span><s:message code="newInvoice.city" /></span> <form:input path="invoiceCity"
					type="text" value="${newInvoice.city}" class="inv-input-sm" />
			</div>

			<div class="container-small pull-right">
				<span><s:message code="newInvoice.creationDate" /></span> <form:input path="creationDate"
					id="creationDatePicker" value="${actualDate}" type="text" class="inv-input-sm" />
			</div>

			<div class="container-small pull-right">
				<span><s:message code="newInvoice.soldDate" /></span> <form:input path="soldDate"
					id="soldDatePicker" value="${actualDate}" type="text" class="inv-input-sm" />
			</div>

		</div>

		<!-- Information about customer and seller -->

		<div style="min-height: 420px;" class="invoice-piece">

			<div class="invoice-piece-left">

				<p>
					<strong><s:message code="newInvoice.seller" /></strong>
				</p>

				<div class="container-med-flat">
				<form:errors path="seller.name" element="span" class="merit-error"></form:errors>
					<form:input path="seller.name" class="inv-input-med" value="${newInvoice.name}" type="text" /> <span><s:message
							code="home.company" /></span>
				</div>

				<div class="container-med-flat">
				<form:errors path="seller.address.city" element="span" class="merit-error"></form:errors>
					<form:input path="seller.address.city" class="inv-input-med" value="${newInvoice.city}" type="text" /> <span><s:message
							code="home.city" /></span>
				</div>

				<div class="container-med-flat">
				<form:errors path="seller.address.street" element="span" class="merit-error"></form:errors>
					<form:input path="seller.address.street" class="inv-input-med" value="${newInvoice.street}" type="text" /> <span><s:message
							code="home.street" /></span>
				</div>

				<div class="container-med-flat">
				<form:errors path="seller.address.homeNumber" element="span" class="merit-error"></form:errors>
					<form:input path="seller.address.homeNumber" class="inv-input-med" value="${newInvoice.homeNumber}" type="text" /> <span><s:message
							code="home.home" /></span>
				</div>

				<div class="container-med-flat">
				<form:errors path="seller.address.postalCode" element="span" class="merit-error"></form:errors>
					<form:input path="seller.address.postalCode" class="inv-input-med" value="${newInvoice.postal}" type="text" /> <span><s:message
							code="home.postal" /></span>
				</div>

				<div class="container-med-flat">
				<form:errors path="seller.nip" element="span" class="merit-error"></form:errors>
					<form:input path="seller.nip" class="inv-input-med" value="${newInvoice.nip}" type="text" /> <span><s:message
							code="home.nip" /></span>
				</div>

				<div class="container-med-flat">
				<form:errors path="seller.contactPhone" element="span" class="merit-error"></form:errors>
					<form:input path="seller.contactPhone" class="inv-input-med" value="${newInvoice.contactPhone}" type="text" /> <span><s:message
							code="home.contactPhone" /></span>
				</div>

				<div class="container-med-flat">
				<form:errors path="seller.faxPhone" element="span" class="merit-error"></form:errors>
					<form:input path="seller.faxPhone" class="inv-input-med" value="${newInvoice.faxPhone}" type="text" /> <span><s:message
							code="home.faxPhone" /></span>
				</div>

				<div class="container-med-flat">
				<form:errors path="seller.email" element="span" class="merit-error"></form:errors>
					<form:input path="seller.email" class="inv-input-med" value="${newInvoice.email}" type="text" /> <span><s:message
							code="home.email" /></span>
				</div>

			</div>

			<div class="invoice-piece-right">

				<p>
					<strong><s:message code="newInvoice.customer" /></strong>
				</p>

				<div class="container-med-flat">
				<form:errors path="customer.name" element="span" class="merit-error"></form:errors>
					<form:input path="customer.name" id="customer-name" class="inv-input-med" type="text" /> <span><s:message
							code="newInvoice.company" /></span>
				</div>

				<div class="container-med-flat">
				<form:errors path="customer.address.city" element="span" class="merit-error"></form:errors>
					<form:input path="customer.address.city" id="customer-city" class="inv-input-med" type="text" /> <span><s:message
							code="newInvoice.city" /></span>
				</div>

				<div class="container-med-flat">
				<form:errors path="customer.address.street" element="span" class="merit-error"></form:errors>
					<form:input path="customer.address.street" id="customer-street" class="inv-input-med" type="text" /> <span><s:message
							code="newInvoice.street" /></span>
				</div>

				<div class="container-med-flat">
				<form:errors path="customer.address.homeNumber" element="span" class="merit-error"></form:errors>
					<form:input path="customer.address.homeNumber" id="customer-home" class="inv-input-med" type="text" /> <span><s:message
							code="newInvoice.home" /></span>
				</div>

				<div class="container-med-flat">
				<form:errors path="customer.address.postalCode" element="span" class="merit-error"></form:errors>
					<form:input path="customer.address.postalCode" id="customer-postal" class="inv-input-med" type="text" /> <span><s:message
							code="newInvoice.postal" /></span>
				</div>

				<div class="container-med-flat">
				<form:errors path="customer.nip" element="span" class="merit-error"></form:errors>
					<form:input path="customer.nip" id="customer-nip" class="inv-input-med" type="text" /> <span><s:message
							code="newInvoice.nip" /></span>
				</div>

				<div class="container-med-flat">
				<form:errors path="customer.contactPhone" element="span" class="merit-error"></form:errors>
					<form:input path="customer.contactPhone" id="customer-contactPhone" class="inv-input-med" type="text" /> <span><s:message
							code="newInvoice.contactPhone" /></span>
				</div>

				<div class="container-med-flat">
				<form:errors path="customer.faxPhone" element="span" class="merit-error"></form:errors>
					<form:input path="customer.faxPhone" id="customer-faxPhone" class="inv-input-med" type="text" /> <span><s:message
							code="newInvoice.faxPhone" /></span>
				</div>

				<div class="container-med-flat">
				<form:errors path="customer.email" element="span" class="merit-error"></form:errors>
					<form:input path="customer.email" id="customer-email" class="inv-input-med" type="text" /> <span><s:message
							code="home.email" /></span>
				</div>

			</div>
			
			<a id="searchCustomer" class="btn btn-default" style="cursor: pointer; position: relative; margin-left: 65%;"><s:message
						code="newInvoice.checkCustomer" /></a>
			
			
		</div>
		
		<div class="invoice-piece" style="min-height: 60px;">
			
			<form:errors path="seller.bankName" element="span" class="merit-error"></form:errors>
			<span class="eco-info"><s:message code="newInvoice.bankName" /></span> <form:input
				type="text" path="seller.bankName" value="${newInvoice.bankName}" class="inv-input-med" />

			<form:errors path="seller.bankNumber" element="span" class="merit-error"></form:errors>
			<span class="eco-info"><s:message code="newInvoice.bankNumber" /></span> <form:input
				type="text" path="seller.bankNumber" value="${newInvoice.bankNumber}" class="inv-input-med" />

		</div>
		
		<div class="invoice-piece" style="min-height: 60px;">
	
			<span class="eco-info"><s:message code="newInvoice.paymentType" /></span> 
			<form:select class="inv-input-med" path="paymentType">
				<form:option value="CASH"><s:message code="newInvoice.paymentType.cash" /></form:option>
				<form:option value="TRANSFER"><s:message code="newInvoice.paymentType.transfer" /></form:option>
			</form:select>

			<span class="eco-info"><s:message code="newInvoice.paymentDate" /></span> <form:input
				path="paymentDate" type="text" id="paymentDate" value="${actualDate}" class="inv-input-sm" />
		
		</div>
		
		<button class="btn btn-default login-button">
				<s:message code="newInvoice.next" />
		</button>

	</form:form>
</div>

<script src="<c:url value="/resources/js/newInvoice.js" />"></script>

