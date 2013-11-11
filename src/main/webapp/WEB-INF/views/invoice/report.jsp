<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<div class="page_view">

		<form:form modelAttribute="soldForm" method="post">
		<div class="sort-controls">
			<div class="pull-left">
			<s:message code="report.dfrom" /> 
				<form:input path="dateFrom" id="dateFrom" class="inv-input-med" name="dateFrom" />
			</div>
			<div class="pull-left">
			<s:message code="report.dto" />
				<form:input path="dateTo" id="dateTo" class="inv-input-med" name="dateTo" />
			</div>
			<div class="pull-left">
				<button class="btn btn-default">
					<s:message code="report.prepare" />
				</button>
			</div>
		</div>
	</form:form>
	
	<c:if test="${report != null}">
		<p><strong><s:message code="report.general" /></strong> ${report.generalPrice} PLN</p>
		<p><strong><s:message code="report.tax" /></strong> ${report.tax} PLN</p>
	</c:if>
</div>

<script src="<c:url value="/resources/js/soldReport.js" />"></script>
