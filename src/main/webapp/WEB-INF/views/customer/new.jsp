<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<div class="page_view">
	<c:if test="${success != null}">
		<div id="message" style="text-align: center; postion: absolute; top: 100px; margin: 20px auto;" class="alert alert-success">
			${success}
		</div>
	</c:if>
	
	<p>
		<strong><s:message code="newCustomer.helloSlogan" arguments="${customersCount}" /></strong>
	</p>

	<form:form modelAttribute="newCustomer" method="post">

			<form:errors path="name" element="span" class="merit-error"></form:errors>
			<p id="customerInfo"></p>			

			<div class="input-row">
				<form:input path="name" type="text" id="customerNameInput"
					class="merit-input-med" />
				<span class="input-label"><s:message code="newCustomer.name" /></span>
			</div>
		
			<form:errors path="address.city" element="span" class="merit-error"></form:errors>
		
			<div class="input-row">
				<form:input path="address.city" type="text"
					class="merit-input-med" />
				<span class="input-label"><s:message code="newCustomer.city" /></span>
			</div>
			
			<form:errors path="address.postalCode" element="span" class="merit-error"></form:errors>
			
			<div class="input-row">
				<form:input path="address.postalCode" type="text"
					class="merit-input-mini" />
				<span class="input-label"><s:message code="newCustomer.postal" /></span>
			</div>

			<form:errors path="address.street" element="span" class="merit-error"></form:errors>

			<div class="input-row">
				<form:input path="address.street" type="text"
					class="merit-input-med" />
				<span class="input-label"><s:message code="newCustomer.street" /></span>
			</div>
			
			<form:errors path="address.homeNumber" element="span" class="merit-error"></form:errors>
			
			<div class="input-row">
				<form:input path="address.homeNumber" type="text"
					class="merit-input-mini" />
				<span class="input-label"><s:message code="newCustomer.home" /></span>
			</div>

			<form:errors path="nip" element="span" class="merit-error"></form:errors>

			<div class="input-row">
				<form:input path="nip" type="text"
					class="merit-input-sm" />
				<span class="input-label"><s:message code="newCustomer.nip" /></span>
			</div>
			
			<form:errors path="contactPhone" element="span" class="merit-error"></form:errors>
			
			<div class="input-row">
				<form:input path="contactPhone" type="text"
					class="merit-input-sm" />
				<span class="input-label"><s:message code="newCustomer.contactPhone" /></span>
			</div>

			<form:errors path="faxPhone" element="span" class="merit-error"></form:errors>

			<div class="input-row">
				<form:input path="faxPhone" type="text"
					class="merit-input-sm" />
				<span class="input-label"><s:message code="newCustomer.faxPhone" /></span>
			</div>

			<form:errors path="email" element="span" class="merit-error"></form:errors>

			<div class="input-row">
				<form:input path="email" type="text"
					class="merit-input-med" />
				<span class="input-label"><s:message code="newCustomer.email" /></span>
			</div>

			<button class="btn btn-default login-button">
				<s:message code="newCustomer.add" />
			</button>
		
	</form:form>

<script src="<c:url value="/resources/js/newCustomer.js" />"></script>
<script type="text/javascript">
	$('#message').fadeOut(4000);
</script>

</div>