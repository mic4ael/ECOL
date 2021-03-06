<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>


<div class="page_view">

	<p>
		<s:message code="home.hello" />, <strong>${signedUser.email}</strong>. <s:message code="home.registerDate" />
		<joda:format value="${signedUser.creationDate}" pattern="dd-MM-yyyy HH:mm:ss" />
		<strong>. <s:message code="home.currentInfo" /></strong>
	</p>

	<div class="form-group">

		<form:form modelAttribute="personalInfos" method="post">

			<form:errors path="name" element="span" class="merit-error"></form:errors>

			<div class="input-row">
				<form:input path="name" value="${personalInfos.name}" type="text"
					class="merit-input-med" />
				<span class="input-label"><s:message code="home.company" /></span>
			</div>

			<form:errors path="city" element="span" class="merit-error"></form:errors>

			<div class="input-row">
				<form:input path="city" value="${personalInfos.city}" type="text"
					class="merit-input-sm" />
				<span class="input-label"><s:message code="home.city" /></span>
			</div>

			<form:errors path="street" element="span" class="merit-error"></form:errors>

			<div class="input-row">
				<form:input path="street" value="${personalInfos.street}"
					type="text" class="merit-input-sm" />
				<span class="input-label"><s:message code="home.street" /></span>
			</div>

			<form:errors path="postal" element="span" class="merit-error"></form:errors>

			<div class="input-row">
				<form:input path="postal" value="${personalInfos.postal}"
					type="text" class="merit-input-mini" />
				<span class="input-label"><s:message code="home.postal" /></span>
			</div>

			<form:errors path="homeNumber" element="span" class="merit-error"></form:errors>

			<div class="input-row">
				<form:input path="homeNumber" value="${personalInfos.homeNumber}"
					type="text" class="merit-input-mini" />
				<span class="input-label"><s:message code="home.home" /></span>
			</div>

			<form:errors path="nip" element="span" class="merit-error"></form:errors>

			<div class="input-row">
				<form:input path="nip" value="${personalInfos.nip}" type="text"
					class="merit-input-sm" />
				<span class="input-label"><s:message code="home.nip" /></span>
			</div>

			<form:errors path="contactPhone" element="span" class="merit-error"></form:errors>

			<div class="input-row">
				<form:input path="contactPhone"
					value="${personalInfos.contactPhone}" type="text"
					class="merit-input-sm" />
				<span class="input-label"><s:message code="home.contactPhone" /></span>
			</div>

			<form:errors path="faxPhone" element="span" class="merit-error"></form:errors>
	
			<div class="input-row">
				<form:input path="faxPhone" value="${personalInfos.faxPhone}"
					type="text" class="merit-input-sm" />
				<span class="input-label"><s:message code="home.faxPhone" /></span>
			</div>

			<form:errors path="email" element="span" class="merit-error"></form:errors>

			<div class="input-row">
				<form:input path="email" value="${personalInfos.email}"
					type="text" class="merit-input-sm" />
				<span class="input-label"><s:message code="home.email" /></span>
			</div>

			<form:errors path="bankName" element="span" class="merit-error"></form:errors>

			<div class="input-row">
				<form:input path="bankName" value="${personalInfos.bankName}"
					type="text" class="merit-input-med" />
				<span class="input-label"><s:message code="home.bankName" /></span>
			</div>

			<form:errors path="bankNumber" element="span" class="merit-error"></form:errors>

			<div class="input-row">
				<form:input path="bankNumber" value="${personalInfos.bankNumber}"
					type="text" class="merit-input-med" />
				<span class="input-label"><s:message code="home.bankNumber" /></span>
			</div>

			<button class="btn btn-default login-button">
				<s:message code="home.editProfile" />
			</button>

		</form:form>

	</div>

</div>