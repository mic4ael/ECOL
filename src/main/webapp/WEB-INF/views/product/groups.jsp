<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<s:message code="groups.groupName" var="groupName" />
<s:message code="groups.specification" var="groupSpecif" />



<div class="page_view">
	<c:if test="${message}">
		<div id="message" style="text-align: center; postion: absolute; top: 100px; margin: 10px auto;" class="alert alert-success">
			<s:message code="operation.success"/>
		</div>
	</c:if>
	
	<p>
		<strong><s:message code="groups.helloSlogan" arguments="${groupsCount}" /></strong>
	</p>
	
	<div class="page_view_left">
		
		<p><s:message code="groups.addNew" /></p>
		
		<c:if test="${editedGroup == null}">
		
			<form:form modelAttribute="groupForm" method="post" action="${pageContext.request.contextPath }/product-groups">
				
				<form:errors path="name" element="span" class="merit-error"/>				
				
				<div class="input-row">
					<form:input path="name" type="text"
						class="merit-input-med" placeholder="${groupName}" />
				</div>
	
				<div class="input-row">
				<form:errors path="specification" element="span" class="merit-error"/>
					<form:textarea path="specification" type="text"
						class="merit" cssStyle="max-width: 500px;" placeholder="${groupSpecif}" />
				</div>
				
				<button class="btn btn-default login-button">
					<s:message code="groups.button" />
				</button>
			
			</form:form>
		
		</c:if>

		<c:if test="${editedGroup != null}">
		
			<form:form modelAttribute="editedGroup" method="post" action="${pageContext.request.contextPath }/product-groups/${id}/edit">
				
				<span class="merit-error">${errorname}</span>
				<div class="input-row">
					<form:input path="name" value="${editedGroup.name}" type="text"
						class="merit-input-med" placeholder="${groupName}" />
				</div>
	
				<div class="input-row">
				<span class="merit-error">${specerror}</span>
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
		
		<form class="inline" method="POST">
			<div class="col-xs-4" style="padding: 5px;">
				<select class="form-control" name="sortField">
					<option value="name"><s:message code="groups.name"/></option>
					<option value="specification"><s:message code="groups.specif"/></option>
				</select>
			</div>
			<div class="col-xs-4 " style="padding: 5px;">
				<select class="form-control" name="sortDir">
					<option value="ASC"><s:message code="customersList.asc"/></option>
					<option value="DESC"><s:message code="customersList.desc"/></option>
				</select>
			</div>
			<div class="pull-left" style="padding: 5px;">
				<c:choose>
					<c:when test="${groupsCount > 0 }">
						<button class="btn btn-default">
							<s:message code="customersList.sort"/>				
						</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-default" disabled>
							<s:message code="customersList.sort"/>				
						</button>
					</c:otherwise>
				</c:choose>
			</div>
		</form>
		
		<table class="table table-striped" style="table-layout: fixed;">
			<thead>
				<tr>
					<th><s:message code="groups.name" /></th>
					<th><s:message code="groups.specif" /></th>
					<th><s:message code="groups.edit" /></th>
					<th><s:message code="groups.delete" /></th>
				</tr>
			</thead>
			<tbody>
			<c:choose>
				<c:when test="${empty groupList}">
					<tr><td colspan="4" style="text-align: center;"><s:message code="groups.norows"/></td></tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${groupList}" var="row">
					
					<c:url value="/product-groups/${row.id}/edit" var="editUrl" />
					<c:url value="/product-groups/${row.id}/delete" var="deleteUrl" />
					
						<tr>
							<td style="word-wrap: break-word;">${row.name}</td>
							<td style="word-wrap: break-word;">${row.specification}</td>
							<td><a href="${editUrl}"><s:message code="groups.edit" /></a></td>
							<td><a href="${deleteUrl}"><s:message code="groups.delete" /></a></td>
						</tr>
						
					</c:forEach>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
		<c:if test="${groupPagesCount > 1}">
	        <div class="pagination" style="float: right;">
				<ul>
			        <c:forEach begin="1" end="${groupPagesCount}" var="page">
		                <li><a href='<s:url value="/product-groups/${page}"></s:url>'>${page}</a></li>
			        </c:forEach>
				</ul>
	        </div>
	    </c:if>
	</div>
</div>

<script type="text/javascript">
	$('#message').fadeOut(4000);
</script>