<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<div id="main-navbar" class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand main-brand" href="#">ECOL - HELPER</a>
		</div>
		<div class="navbar-collapse collapse">

			<ul class="nav navbar-nav">
				<li class="active"><a href='<s:url value="/"/>'><s:message code="navbar.main" /></a></li>
			</ul>

			<ul class="nav navbar-nav white-links pull-right">
				<li class="dropdown"><a href="#" id="account-link" class="dropdown-toggle"
					data-toggle="dropdown"><strong><s:message code="navbar.account" /></strong></a>
					<ul class="dropdown-menu custom-font" id="sub-links">
					
					<security:authorize access="isAuthenticated()">
					
						<li><a href="#"><s:message code="navbar.account.edit" /></a></li>
						<li><a href='<s:url value="/logout"/>'><s:message code="navbar.account.logout" /></a></li>
					
					</security:authorize>
					
					<security:authorize access="!isAuthenticated()">
					
						<li><a href='<s:url value="/registration"/>'><s:message code="navbar.account.registration" /></a></li>
					
					</security:authorize>
					
					</ul></li>
			</ul>

		</div>
	</div>
</div>

<div class="header">
	<div class="container">
		<security:authorize access="isAuthenticated()">

			<ul class="header-ul">

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><strong><s:message code="headerMenu.customers" /></strong> <b
						class="caret"></b></a>
					<ul class="dropdown-menu custom-font account-menu">
						<li><a href='<s:url value="/new-customer"/>'><s:message code="headerMenu.customers.new" /></a></li>
						<li><a href='<s:url value="/customers-list/1"/>'><s:message code="headerMenu.customers.list" /></a></li>
					</ul></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><strong><s:message code="headerMenu.products" /></strong> <b
						class="caret"></b></a>
					<ul class="dropdown-menu custom-font account-menu">
						<li><a href='<s:url value="/new-product"/>'><s:message code="headerMenu.products.new" /></a></li>
						<li><a href='<s:url value="/products-list"/>'><s:message code="headerMenu.products.list" /></a></li>
						<li><a href='<s:url value="/product-groups/1"/>'><s:message code="headerMenu.products.group" /></a></li>
					</ul></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><strong><s:message code="headerMenu.invoices" /></strong> <b
						class="caret"></b></a>
					<ul class="dropdown-menu custom-font account-menu">
						<li><a href='<s:url value="/new-invoice"/>'><s:message code="headerMenu.invoices.new" /></a></li>
						<li><a href='<s:url value="/invoices-list/1"/>'><s:message code="headerMenu.invoices.list" /></a></li>
					</ul></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><strong><s:message code="headerMenu.reports" /></strong> <b
						class="caret"></b></a>
					<ul class="dropdown-menu custom-font account-menu">
						<li><a href="#"><s:message code="headerMenu.reports.sold" /></a></li>
					</ul></li>
				
			</ul>

		</security:authorize>
	</div>
</div>