<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<s:message code="loginView.email" var="email" />
<s:message code="loginView.pass" var="password" />
<s:message code="registrationView.repeatPass" var="repeatedPassword" />

<div class="container">
	<div class="form-container">

		<p id="main-paragraph"><s:message code="loginView.authorization" /></p>

		<form:form modelAttribute="registerAccount">
			<p>
				<form:input path="email" class="standard-input" type="text"
					placeholder="${email}" />
			</p>
			<p>
				<form:input path="password" class="standard-input" type="password"
					placeholder="${password}" />
			</p>
			<p>
				<form:input path="repeatedPassword" class="standard-input" type="password"
					placeholder="${repeatedPassword}" />
			</p>

			<button name="finalize" class="btn btn-default login-button"><s:message code="registrationView.finalize" /></button>
			<button class="btn btn-default login-button"><s:message code="registrationView.nextStep" /></button>

		</form:form>

	</div>
</div>
