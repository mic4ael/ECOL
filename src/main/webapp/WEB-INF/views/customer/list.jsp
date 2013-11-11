<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<div class="page_view">
	<c:if test="${message}">
		<div id="message" style="text-align: center; postion: absolute; top: 100px; margin: 20px auto;" class="alert alert-success">
			<s:message code="operation.success"/>
		</div>
	</c:if>
	<form method="post">
		<div class="sort-controls">
			<div class="pull-left">
				<select class="inv-input-med" name="dir">
					<option value="ASC"><s:message code="customersList.asc" /></option>
					<option value="DESC"><s:message code="customersList.desc" /></option>
				</select>
			</div>
			<div class="pull-left">
				<select class="inv-input-med" name="property">
					<option value="name"><s:message code="newCustomer.name" /></option>
					<option value="nip"><s:message code="newCustomer.nip" /></option>
					<option value="email"><s:message code="newCustomer.email" /></option>
					<option value="contactPhone"><s:message code="newCustomer.contactPhone" /></option>
					<option value="faxPhone"><s:message code="newCustomer.faxPhone" /></option>
					<option value="creationDate"><s:message code="newCustomer.creationDate" /></option>
				</select>
			</div>
			<div class="pull-left">
				<c:choose>
					<c:when test="${customersCount > 0 }">
						<button class="btn btn-default">
							<s:message code="customersList.sort" />
						</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-default" disabled>
							<s:message code="customersList.sort" />
						</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form>

	<table class="table table-striped">
		<thead>
			<tr>
				<th><s:message code="newCustomer.name" /></th>
				<th><s:message code="newCustomer.city" /></th>
				<th><s:message code="newCustomer.postal" /></th>
				<th><s:message code="newCustomer.street" /></th>
				<th><s:message code="newCustomer.home" /></th>
				<th><s:message code="newCustomer.nip" /></th>
				<th><s:message code="newCustomer.email" /></th>
				<th><s:message code="newCustomer.contactPhone" /></th>
				<th><s:message code="newCustomer.faxPhone" /></th>
				<th><s:message code="newCustomer.creationDate" /></th>
				<th><s:message code="groups.edit" /></th>
				<th><s:message code="groups.delete" /></th>			
			</tr>
		</thead>
		
		<c:forEach items="${customersList}" var="customer">
			
			<c:url value="/customers-list/${customer.id}/edit" var="editUrl" />
			<c:url value="/customers-list/${customer.id}/delete" var="deleteUrl" />
			
			<tr>
				<td>${customer.name}</td>
				<td>${customer.address.city}</td>
				<td>${customer.address.postalCode}</td>
				<td>${customer.address.street}</td>
				<td>${customer.address.homeNumber}</td>
				<td>${customer.nip}</td>
				<td>${customer.email}</td>
				<td>${customer.contactPhone}</td>
				<td>${customer.faxPhone}</td>
				<td><joda:format pattern="dd - MM - yyyy" value="${customer.creationDate}" /></td>
				<td><a href="${editUrl}"><s:message code="groups.edit" /></a></td>
				<td><a href="${deleteUrl}"><s:message code="groups.delete" /></a></td>
			</tr>
			
		</c:forEach>
		
	</table>
	
	
	<c:if test="${customersPages > 1}">
        <div class="pagination">
                <ul>
                        <c:forEach begin="1" end="${customersPages}" var="page">
                                <li><a href='<s:url value="/customers-list/${page}"></s:url>'>${page}</a></li>
                        </c:forEach>
                </ul>
        </div>
    </c:if>
</div>

<script type="text/javascript">
	$('#message').fadeOut(4000);
</script>