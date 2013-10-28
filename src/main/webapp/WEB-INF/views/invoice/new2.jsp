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

	<form>

		<div class="invoice-piece" style="height: 100px;">

			<div class="container-small pull-left">
				<span><s:message code="newInvoice.number" /></span> <input
					type="text" value="${actualYear} / ${invoiceNumber}" class="inv-input-sm" />
			</div>

			<div class="container-small pull-left">
				<span><s:message code="newInvoice.city" /></span> <input
					type="text" value="${newInvoice.city}" class="inv-input-sm" />
			</div>

			<div class="container-small pull-left">
				<span><s:message code="newInvoice.creationDate" /></span> <input
					id="creationDatePicker" value="${actualDate}" type="text" class="inv-input-sm" />
			</div>

			<div class="container-small pull-left">
				<span><s:message code="newInvoice.soldDate" /></span> <input
					id="soldDatePicker" value="${actualDate}" type="text" class="inv-input-sm" />
			</div>

		</div>

		<!-- Information about customer and seller -->

		<div style="height: 390px;" class="invoice-piece">

			<div class="invoice-piece-left">

				<p>
					<s:message code="newInvoice.seller" />
				</p>

				<div class="container-med-flat">
					<input class="inv-input-med" value="${newInvoice.name}" type="text" /> <span><s:message
							code="home.company" /></span>
				</div>

				<div class="container-med-flat">
					<input class="inv-input-med" value="${newInvoice.city}" type="text" /> <span><s:message
							code="home.city" /></span>
				</div>

				<div class="container-med-flat">
					<input class="inv-input-med" value="${newInvoice.street}" type="text" /> <span><s:message
							code="home.street" /></span>
				</div>

				<div class="container-med-flat">
					<input class="inv-input-med" value="${newInvoice.homeNumber}" type="text" /> <span><s:message
							code="home.home" /></span>
				</div>

				<div class="container-med-flat">
					<input class="inv-input-med" value="${newInvoice.postal}" type="text" /> <span><s:message
							code="home.postal" /></span>
				</div>

				<div class="container-med-flat">
					<input class="inv-input-med" value="${newInvoice.nip}" type="text" /> <span><s:message
							code="home.nip" /></span>
				</div>

				<div class="container-med-flat">
					<input class="inv-input-med" value="${newInvoice.contactPhone}" type="text" /> <span><s:message
							code="home.contactPhone" /></span>
				</div>

				<div class="container-med-flat">
					<input class="inv-input-med" value="${newInvoice.faxPhone}" type="text" /> <span><s:message
							code="home.faxPhone" /></span>
				</div>

			</div>

			<div class="invoice-piece-right">

				<p>
					<s:message code="newInvoice.customer" />
				</p>

				<div class="container-med-flat">
					<input class="inv-input-med" type="text" /> <span><s:message
							code="newInvoice.company" /></span>
				</div>

				<div class="container-med-flat">
					<input class="inv-input-med" type="text" /> <span><s:message
							code="newInvoice.city" /></span>
				</div>

				<div class="container-med-flat">
					<input class="inv-input-med" type="text" /> <span><s:message
							code="newInvoice.street" /></span>
				</div>

				<div class="container-med-flat">
					<input class="inv-input-med" type="text" /> <span><s:message
							code="newInvoice.home" /></span>
				</div>

				<div class="container-med-flat">
					<input class="inv-input-med" type="text" /> <span><s:message
							code="newInvoice.postal" /></span>
				</div>

				<div class="container-med-flat">
					<input class="inv-input-med" type="text" /> <span><s:message
							code="newInvoice.nip" /></span>
				</div>

				<div class="container-med-flat">
					<input class="inv-input-med" type="text" /> <span><s:message
							code="newInvoice.contactPhone" /></span>
				</div>

				<div class="container-med-flat">
					<input class="inv-input-med" type="text" /> <span><s:message
							code="newInvoice.faxPhone" /></span>
				</div>

				<a id="searchCustomer" style="cursor: pointer;"><s:message
						code="newInvoice.checkCustomer" /></a>

			</div>
		</div>
		
		<button class="btn btn-default login-button">
				<s:message code="newInvoice.next" />
		</button>

	</form>
</div>

<script src="<c:url value="/resources/js/newInvoice.js" />"></script>

