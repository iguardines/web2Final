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
	<title><spring:message code="app.name" /></title>
	<link rel="shortcut icon" href='<c:url value="/resources/css/images/favicon.ico" />' />
	<link rel="stylesheet" href='<c:url value="/resources/css/style.css" />' type="text/css" media="all" />
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
		<div class="shell">
				<div class="image">
					<img src="<c:url value="/resources/css/images/books.png" />" alt="" />
				</div>
				<div class="details">
					<h3><spring:message code="welcome" />&nbsp;<spring:message code="to" />&nbsp;<spring:message code="app.name" />!</h3><br />
					<p class="title"><spring:message code="desc1" /></p>
					<p class="description"><spring:message code="desc2" /></p>
				</div>
			</div>
	<div id="main" class="shell">
		
		<div class="col login" id="newsletter">
			<h4><spring:message code="login" /></h4>
			<p><spring:message code="enter.credentials" /> </p>
			<c:url value="/j_spring_security_check" var="loginUrl" />
			<form:form  method="POST" modelAttribute="userDto" action="${loginUrl}">
				<c:if test="${param.error != null}">
					<label class="error"><spring:message code="errors.login.bad.credentials"/></label>
				</c:if>
				<form:label path="j_username">
					<spring:message code="label.name" />
				</form:label>
				<form:input path="j_username" cssClass="field"/>
				<form:label path="j_password">
					<spring:message code="label.password"/>
				</form:label>
				<form:password path="j_password" cssClass="field"/> 
				<form:button id="login-btn" class="submit-btn"><spring:message code="label.submit"/></form:button>
			</form:form>
		</div>
		
	</div>
	<!-- Footer -->
	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	<!-- End Footer -->
</body>
</html>