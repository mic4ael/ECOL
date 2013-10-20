<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<s:message code="groups.groupName" var="groupName" />
<s:message code="groups.specification" var="groupSpecif" />

<div class="page_view">

	<p>
		<strong><s:message code="groups.helloSlogan" arguments="${groupsCount}" /></strong>
	</p>
	
	<div class="page_view_left">
	
		<p><s:message code="groups.addNew" /></p>
		
		<c:if test="${editedGroup == null}">
		
			<form:form modelAttribute="groupForm" method="post">
				
				<form:errors path="name" element="span" class="merit-error"></form:errors>
				
				<div class="input-row">
					<form:input path="name" type="text"
						class="merit-input-med" placeholder="${groupName}" />
				</div>
	
				<div class="input-row">
					<form:textarea path="specification" type="text"
						class="merit" placeholder="${groupSpecif}" />
				</div>
				
				<button class="btn btn-default login-button">
					<s:message code="groups.button" />
				</button>
			
			</form:form>
		
		</c:if>

		<c:if test="${editedGroup != null}">
		
			<form:form modelAttribute="editedGroup" method="post">
				
				<form:errors path="name" element="span" class="merit-error"></form:errors>
				
				<div class="input-row">
					<form:input path="name" value="${editedGroup.name}" type="text"
						class="merit-input-med" placeholder="${groupName}" />
				</div>
	
				<div class="input-row">
					<form:textarea path="specification" type="text"
						class="merit" placeholder="${groupSpecif}"></form:textarea>
				</div>
				
				<button class="btn btn-default login-button">
					<s:message code="groups.editButton" />
				</button>
			
			</form:form>
		
		</c:if>

	
	</div>
	<div class="page_view_right">
	
		<p><s:message code="groups.seeList" /></p>
		
		<table class="table table-striped">
			<thead>
				<tr>
					<th><s:message code="groups.name" /></th>
					<th><s:message code="groups.specif" /></th>
					<th><s:message code="groups.edit" /></th>
					<th><s:message code="groups.delete" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${groupList}" var="row">
				
				<c:url value="/product-groups/${row.id}/edit" var="editUrl" />
				<c:url value="/product-groups/${row.id}/delete" var="deleteUrl" />
				
					<tr>
						<td>${row.name}</td>
						<td>${row.specification}</td>
						<td><a href="${editUrl}"><s:message code="groups.edit" /></a></td>
						<td><a href="${deleteUrl}"><s:message code="groups.delete" /></a></td>
					</tr>
					
				</c:forEach>
			</tbody>
		</table>
	
	</div>

</div>