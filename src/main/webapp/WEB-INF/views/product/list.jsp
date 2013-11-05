<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>



<div class="page_view">
	<table class="table table-striped">
		<thead>
			<tr>
				<th><s:message code="newProduct.name"/></th>
				<th><s:message code="newProduct.type"/></th>
				<th><s:message code="newProduct.unit"/></th>
				<th><s:message code="newProduct.group"/></th>
				<th><s:message code="newProduct.price"/></th>
				<th><s:message code="newProduct.tax"/></th>
				<th><s:message code="groups.edit"/></th>
				<th><s:message code="groups.delete"/></th>
			</tr>
		</thead>
	
		<c:forEach  items="${productsList}" var="product">
			<c:url value="/products/${product.id}/edit" var="editUrl"/>
			<c:url value="/products/${product.id}/delete" var="deleteUrl"/>
			
			<tr>
				<td>${product.productName} </td>
				<td>${product.productType} </td>
				<td>${product.productUnit} </td>
				<td>${product.group.name} </td>
				<td>${product.basePrice} </td>
				<td>${product.taxAmount} </td>
				<td><a href="${editUrl}"><s:message code="groups.edit"/></a></td>
				<td><a href="${deleteUrl}"><s:message code="groups.delete"/></a></td>
			</tr>
		
		</c:forEach>
	</table>
</div>