<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src='<c:url value="/resources/js/lib/jquery-1.8.2.js" />'></script>
	<script src='<c:url value="/resources/js/lib/json2.js" />'></script>
	<link rel="shortcut icon" href='<c:url value="/resources/css/images/favicon.ico" />' />
	<link rel="stylesheet" href='<c:url value="/resources/css/style.css" />' type="text/css" media="all" />
	<title><spring:message code="app.name" /></title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<div id="main" class="shell">	
		<h1 class="main-title"><spring:message code="edit.book"/></h1> <br /> <br />
		
		<img height="130" width="100" src='<c:url value="/app/image/book/${bookDto.id}" />' /> <br /> <br />
		
		<form:form method="POST" modelAttribute="bookDto" action="/booksmov/app/books/edit" enctype="multipart/form-data" >
			<form:label path="title">
				<spring:message code="label.books.title" /> 
			</form:label> <br />
			<form:input path="title" maxlength="200"/> <form:errors path="title" cssStyle="color: red" /><br/><br/>
			<form:label path="authors">
				<spring:message code="label.books.authors"/>
				<spring:message code="label.books.authors.example"/>
			</form:label> <br /> 
			<form:input path="authors" maxlength="100"/> <form:errors path="authors" cssStyle="color: red" /><br/> <br />
			<form:label path="rating">
				<spring:message code="label.books.rating" /> 
			</form:label><img  height="16" width="16" src='<c:url value="/resources/img/star.png" />' /><br/>
			<form:radiobutton path="rating" value="1"/>1 
			<form:radiobutton path="rating" value="2"/>2 
			<form:radiobutton path="rating" value="3"/>3 
			<form:radiobutton path="rating" value="4"/>4 
			<form:radiobutton path="rating" value="5"/>5
			<form:errors path="rating" cssStyle="color: red" /><br/> <br />
			
			<form:label path="description">
				<spring:message code="label.books.description" /> 
			</form:label> <br />
			<form:textarea path="description" rows="5" cols="30" /><br/> <br />
			
			<form:label path="borrowable">
				<spring:message code="label.books.borrowable" /> 
			</form:label> 
			<form:checkbox path="borrowable" /><br /> <br />
			
			<form:label path="alreadyUsed">
				<spring:message code="label.books.alreadyRead" /> 
			</form:label> 
			<form:checkbox path="alreadyUsed" /><br /> <br />
			
			<form:label path="image">
				<spring:message code="label.books.image" /> 
			</form:label>
			<form:input type="file" path="image" />
			<form:errors path="image" cssStyle="color: red" /><br/> <br />
			<form:hidden path="id" />
			<form:button class="submit-btn"><spring:message code="label.products.modify"/></form:button>
		</form:form>
		
		<c:if test="${bookDto.success}">
			<div class="success-message">
				<h3><spring:message code="book.successfully.edited"/></h3><br /><br />
				<a class="back" href='<c:url value="/app/search" />'><spring:message code="go.back"/></a>
			</div>
		</c:if>
		
	</div>
	<!-- End Main -->
	<!-- Footer -->
	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	<!-- End Footer -->
</body>
</html>