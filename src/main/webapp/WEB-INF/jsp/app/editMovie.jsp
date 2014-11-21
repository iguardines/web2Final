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
		<h1 class="main-title"><spring:message code="edit.movie"/></h1><br /> <br />
		
		<form:form method="POST" modelAttribute="movieDto" action="/booksmov/app/movies/edit" enctype="multipart/form-data" >
			<img height="130" width="100" src='<c:url value="/app/image/movie/${movieDto.id}" />' /> <br /> <br />
			<form:label path="title">
				<spring:message code="label.movies.title" /> 
			</form:label><br/>
			<form:input path="title" maxlength="200"/> <form:errors path="title" cssStyle="color: red" /><br/><br/>
			<form:label path="director.fullName">
				<spring:message code="label.movies.director"/>
			</form:label><br/>
			<form:input path="director.fullName" maxlength="100"/><br/><br/> 
			<form:label path="actors">
				<spring:message code="label.movies.actors"/>
				<spring:message code="label.movies.actors.example"/>
			</form:label><br/>
			<form:input path="actors" maxlength="100"/> <form:errors path="actors" cssStyle="color: red" /><br/> <br />
			<form:label path="rating">
				<spring:message code="label.movies.rating" /> 
			</form:label><img  height="16" width="16" src='<c:url value="/resources/img/star.png" />' /><br/>
			<form:radiobutton path="rating" value="1"/>1 
			<form:radiobutton path="rating" value="2"/>2 
			<form:radiobutton path="rating" value="3"/>3 
			<form:radiobutton path="rating" value="4"/>4 
			<form:radiobutton path="rating" value="5"/>5
			<form:errors path="rating" cssStyle="color: red" /><br/><br/>
			
			<form:label path="selectedFormat">
				<spring:message code="label.movies.formats" /> 
			</form:label><br/>
			<form:select path="selectedFormat">
			     <form:options items="${formats}"  />
			</form:select> <form:errors path="selectedFormat" cssStyle="color: red" /><br/><br/>
			
			<form:label path="borrowable">
				<spring:message code="label.movies.borrowable" /> 
			</form:label>
			<form:checkbox path="borrowable" /><br /><br/>
			
			<form:label path="alreadyUsed">
				<spring:message code="label.movies.alreadyUsed" /> 
			</form:label>
			<form:checkbox path="alreadyUsed" /><br /><br/>
			
			<form:label path="image">
				<spring:message code="label.movies.image" /> 
			</form:label>
			<form:input type="file" path="image" />
			<form:errors path="image" cssStyle="color: red" /><br/>
			<form:hidden path="id" /><br/>
			<form:button class="submit-btn"><spring:message code="label.products.modify"/></form:button><br/><br/>
		</form:form>
		
		<c:if test="${movieDto.success}">
			<div class="success-message">
				<h3><spring:message code="movie.successfully.edited"/></h3><br /><br />
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