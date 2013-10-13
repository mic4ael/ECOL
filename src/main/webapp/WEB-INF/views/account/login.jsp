<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<s:message code="loginView.email" var="email" />
<s:message code="loginView.pass" var="password" />

<div class="container">
	<div class="form-container">

		<p id="main-paragraph"><s:message code="loginView.authorization" /></p>

		<c:if test="${badCredentials != null}">
			<div class="validation-error"><s:message code="badCredentials" /></div>		
		</c:if>

		<form action='<s:url value="/j_spring_security_check"/>' method="post">
			<p>
				<input class="standard-input" type="text" name="j_username"
					placeholder="${email}" />
			</p>
			<p>
				<input class="standard-input" type="password" name="j_password"
					placeholder="${password}" />
			</p>

			<button class="btn btn-default login-button"><s:message code="loginView.login" /></button>

		</form>

		<p>
			<a href='<s:url value="/forgotten-password"/>'><s:message code="loginView.forgottenPassword" /></a>
		</p>
		<p>
			<a href='<s:url value="/registration"/>'><s:message code="loginView.register" /></a>
		</p>

	</div>
</div>
