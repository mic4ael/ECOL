<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<div class="page_view">

	<p>
		<strong><s:message code="editCustomer.editedCustomer" arguments="${editedCustomer.id}, ${editedCustomer.name}" /></strong>
	</p>

	<c:if test="${success != null}">
		<p>
			<strong style="color: #37A10D;">${success}</strong>
		</p>
	</c:if>

	<form:form modelAttribute="newCustomer" method="post">

			<form:errors path="name" element="span" class="merit-error"></form:errors>
			<p id="customerInfo"></p>			

			<div class="input-row">
				<form:input path="name" value="${editedCustomer.name}" type="text" id="customerNameInput"
					class="merit-input-med" />
				<span class="input-label"><s:message code="newCustomer.name" /></span>
			</div>
		
			<form:errors path="address.city" element="span" class="merit-error"></form:errors>
		
			<div class="input-row">
				<form:input path="address.city" value="${editedCustomer.address.city}" type="text"
					class="merit-input-med" />
				<span class="input-label"><s:message code="newCustomer.city" /></span>
			</div>
			
			<form:errors path="address.postalCode" element="span" class="merit-error"></form:errors>
			
			<div class="input-row">
				<form:input path="address.postalCode" value="${editedCustomer.address.postalCode}" type="text"
					class="merit-input-mini" />
				<span class="input-label"><s:message code="newCustomer.postal" /></span>
			</div>

			<form:errors path="address.street" element="span" class="merit-error"></form:errors>

			<div class="input-row">
				<form:input path="address.street" value="${editedCustomer.address.street}" type="text"
					class="merit-input-med" />
				<span class="input-label"><s:message code="newCustomer.street" /></span>
			</div>
			
			<form:errors path="address.homeNumber" element="span" class="merit-error"></form:errors>
			
			<div class="input-row">
				<form:input path="address.homeNumber" value="${editedCustomer.address.homeNumber}" type="text"
					class="merit-input-mini" />
				<span class="input-label"><s:message code="newCustomer.home" /></span>
			</div>

			<form:errors path="nip" element="span" class="merit-error"></form:errors>

			<div class="input-row">
				<form:input path="nip" value="${editedCustomer.nip}" type="text"
					class="merit-input-sm" />
				<span class="input-label"><s:message code="newCustomer.nip" /></span>
			</div>
			
			<form:errors path="contactPhone" element="span" class="merit-error"></form:errors>
			
			<c:choose>
				<c:when test="${editedCustomer.contactPhone != null}">
					<div class="input-row">
						<form:input path="contactPhone" value="${editedCustomer.contactPhone}" type="text"
							class="merit-input-sm" />
						<span class="input-label"><s:message code="newCustomer.contactPhone" /></span>
					</div>
				</c:when>
				<c:when test="${editedCustomer.contactPhone == null}">
					<div class="input-row">
						<form:input path="contactPhone" type="text"
							class="merit-input-sm" />
						<span class="input-label"><s:message code="newCustomer.contactPhone" /></span>
					</div>
				</c:when>
			</c:choose>

			<form:errors path="faxPhone" element="span" class="merit-error"></form:errors>

			<c:choose>
				<c:when test="${editedCustomer.contactPhone != null}">
					<div class="input-row">
						<form:input path="faxPhone" value="${editedCustomer.faxPhone}" type="text"
							class="merit-input-sm" />
						<span class="input-label"><s:message code="newCustomer.faxPhone" /></span>
					</div>				</c:when>
				<c:when test="${editedCustomer.contactPhone == null}">
					<div class="input-row">
						<form:input path="faxPhone" type="text"
							class="merit-input-sm" />
						<span class="input-label"><s:message code="newCustomer.faxPhone" /></span>
					</div>				
				</c:when>
			</c:choose>

			<form:errors path="email" element="span" class="merit-error"></form:errors>

			<c:choose>
				<c:when test="${editedCustomer.contactPhone != null}">
					<div class="input-row">
						<form:input path="email" value="${editedCustomer.email}" type="text"
							class="merit-input-med" />
						<span class="input-label"><s:message code="newCustomer.email" /></span>
					</div>
				</c:when>
				<c:when test="${editedCustomer.contactPhone == null}">
					<div class="input-row">
						<form:input path="email" type="text"
							class="merit-input-med" />
						<span class="input-label"><s:message code="newCustomer.email" /></span>
					</div>
				</c:when>
			</c:choose>

			<button class="btn btn-default login-button">
				<s:message code="editCustomer.save" />
			</button>
		
	</form:form>

<script src="<c:url value="/resources/js/newCustomer.js" />"></script>

</div>