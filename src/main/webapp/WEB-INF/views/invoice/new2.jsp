<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<s:message code="newInvoice2.searchProductPlaceholder" var="filter" />

	<!-- New customer modal -->

<div class="modal fade" id="searchProductModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<strong><s:message code="newInvoice2.find" /></strong>
			</div>

			<div class="modal-body">
			
				<div class="modal-row">
					<input class="inv-input-med" id="searchProductInput" placeholder="${filter}" />
				</div>
			
				<div class="modal-record-container">
			
					<table class="table">
						<thead>
							<tr>
								<th style="display: none;">id</th>
								<th><s:message code="newProduct.name" /></th>
								<th><s:message code="newProduct.price" /></th>
								<th><s:message code="newProduct.tax" /></th>
							</tr>
						</thead>
						<tbody id="productsJsonList">
							
						</tbody>
					</table>
				
				</div>
			
			</div>

			<div class="modal-footer">
				<button id="productsModalCloser" type="button" class="btn btn-default">Close</button>
			</div>
		</div>
	</div>
</div>


<div class="page_view">

	<!-- Main piece of invoice - general information -->

	<p>
		<strong><s:message code="newInvoice2.title" /></strong>
	</p>

	<form:form modelAttribute="productRow" method="post">
	
		<div class="container-flat">
			<form:input id="prod-name" class="inv-input-med" disabled="true" path="name" /><span class="eco-info"><a id="product-model-trigger" style="cursor: pointer;"><s:message code="newInvoice2.find" /></a></span>
		</div>	

		<div class="container-flat">
			<form:errors path="amount" element="span" class="merit-error"></form:errors>
			<form:input class="inv-input-med" path="amount" /><span class="eco-info"><s:message code="newInvoice2.amount" /></span>
		</div>	

		<div class="container-flat">
			<form:select class="inv-input-med" path="discountType"><span class="eco-info"><s:message code="newInvoice2.discountType" /></span>
			
				<form:option value="MONETISED"><s:message code="newInvoice2.discountType.m" /></form:option>
				<form:option value="PERCENTAGE"><s:message code="newInvoice2.discountType.p" /></form:option>
			
			</form:select>
		</div>	

		<div class="container-flat">
			<form:errors path="discountAmount" element="span" class="merit-error"></form:errors>
			<form:input class="inv-input-med" path="discountAmount" /><span class="eco-info"><s:message code="newInvoice2.discountAmount" /></span>
		</div>	


		<button class="btn btn-default login-button">
				<s:message code="newInvoice2.next" />
		</button>
	
		<button class="btn btn-default login-button">
				<s:message code="newInvoice2.finalize" />
		</button>
	
	</form:form>

</div>

<script src="<c:url value="/resources/js/newInvoiceProduct.js" />"></script>

