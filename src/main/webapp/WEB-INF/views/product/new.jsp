<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<div class="page_view">

	<p>
		<strong><s:message code="newProduct.helloSlogan" arguments="${productsCount}" /></strong>
	</p>

	<form:form modelAttribute="newProduct" method="post">
		
			<div class="input-row">
				<form:input path="productCode" type="text"
					class="merit-input-med" />
				<span class="input-label"><s:message code="newProduct.code" /></span>
			</div>

			<div class="input-row">
				<form:input path="productName" type="text"
					class="merit-input-med" />
				<span class="input-label"><s:message code="newProduct.name" /></span>
			</div>
		
			<div class="input-row">
				<form:input path="productSpecification" type="text"
					class="merit-input-med" />
				<span class="input-label"><s:message code="newProduct.specif" /></span>
			</div>
			
			<div class="input-row">
				<form:select path="productType" class="merit-input-sm">
					<form:options items="${productAttributes.productTypes}"/>
				</form:select>
				<span class="input-label"><s:message code="newProduct.type" /></span>
				
			</div>
			
			<div class="input-row">
				<form:select path="productUnit" class="merit-input-sm">
					<form:options items="${productAttributes.units}"/>
				</form:select>
				<span class="input-label"><s:message code="newProduct.unit" /></span>
			</div>
			
			<div class="input-row">
				<form:select path="group" class="merit-input-sm">
					<form:options items="${productAttributes.groups}"/>
				</form:select>
				<span class="input-label"><s:message code="newProduct.group" /></span>
			</div>
			
			<div class="input-row">
				<form:input path="basePrice" type="text"
					class="merit-input-mini" />
				<span class="input-label"><s:message code="newProduct.price" /></span>				
			</div>

			<div class="input-row">
				<form:select path="taxAmount" class="merit-input-mini">
					<form:option value="0">0%</form:option>
					<form:option value="7">7%</form:option>
					<form:option value="23">23%</form:option>
					<form:option value="28">28%</form:option>
				</form:select>
				<span class="input-label"><s:message code="newProduct.tax" /></span>
			</div>
			
			<button class="btn btn-default login-button">
				<s:message code="newProduct.add" />
			</button>
		
	</form:form>

</div>