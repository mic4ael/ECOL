<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>

	<title><s:message code="main.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" media="screen" />
	<link href="<c:url value="/resources/css/core.css" />" rel="stylesheet" media="screen" />
	<link href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" rel="stylesheet" media="screen" />
	
	<script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	
	
	<tilesx:useAttribute id="styles" name="styles" classname="java.util.List" ignore="true" />
	<c:forEach var="cssName" items="${styles}">
		<link type="text/css" href="<c:url value="/resources/css/${cssName}"/>" rel="stylesheet" media="screen" />
	</c:forEach>
	
	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

</head>
<body>
	
	<tiles:insertAttribute name="header"  defaultValue="" />
	
	<!-- Page content -->
	<div class="container">

		<tiles:insertAttribute name="body" defaultValue="" />
	
	</div>
	
	<!-- End of page content -->
	<tiles:insertAttribute name="footer"  defaultValue="" />
	
	
</body>
</html>