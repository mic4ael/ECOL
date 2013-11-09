<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<div class="page_view">
	<c:if test="${message != null}">
		<div id="message" style="text-align: center; postion: absolute; top: 100px; margin: 20px auto;" class="alert alert-success">
			<s:message code="operation.success"/>
		</div>
	</c:if>
	
	<div class="sort-controls">
		<form method="POST">
			<div class="pull-left">
				<select class="inv-input-med" name="sortField">
					<option value="productName"><s:message code="newProduct.name"/></option>
					<option value="productType"><s:message code="newProduct.type"/></option>
					<option value="productUnit"><s:message code="newProduct.unit"/></option>
					<option value="group.name"><s:message code="newProduct.group"/></option>
					<option value="basePrice"><s:message code="newProduct.price"/></option>
					<option value="taxAmount"><s:message code="newProduct.tax"/></option>
				</select>
			</div>
			<div class="pull-left">
				<select class="inv-input-med" name="sortDir">
					<option value="ASC"><s:message code="customersList.asc"/></option>
					<option value="DESC"><s:message code="customersList.desc"/></option>
				</select>
			</div>
			<div class="pull-left">
				<c:choose>
					<c:when test="${productsCount > 0 }">
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
	</div>
	
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
	
	<c:if test="${productPagesCount > 1}">
        <div class="pagination" style="float: right;">
			<ul style="padding-left: auto; padding-right: auto;">
		        <c:forEach begin="1" end="${productPagesCount}" var="page">
	                <li><a href='<s:url value="/products-list/${page}"></s:url>'>${page}</a></li>
		        </c:forEach>
			</ul>
        </div>
    </c:if>
</div>

<script type="text/javascript">
	$('#message').fadeOut(4000);
</script>

		