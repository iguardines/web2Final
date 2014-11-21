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
	<link rel="shortcut icon" href='<c:url value="/resources/css/images/favicon.ico" />' />
	<link rel="stylesheet" href='<c:url value="/resources/css/style.css" />' type="text/css" media="all" />
	<title><spring:message code="app.name" /></title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<div id="main" class="shell">
		<h1 class="main-title"><spring:message code="loan.request" /></h1> <br />
		
		<form:form action="/booksmov/app/loan/request" method="POST" modelAttribute="loanDto">
			<form:label path="requestDescription">
				<spring:message code="loan.request.description" />
			</form:label> <br />
			<form:textarea path="requestDescription" maxlength="500" cols="50" rows="10" /><br/>
			<form:errors path="requestDescription" cssStyle="color: red" /><br/>
			<form:hidden path="consigneeId"/>
			<form:errors path="consigneeId" cssStyle="color: red" /><br/>
			<form:hidden path="productId"/>
			<form:errors path="productId" cssStyle="color: red" />
			<form:button class="submit-btn"><spring:message code="loan.submit.request"/></form:button>
		</form:form>
		
		<c:if test="${not empty message}">
			<div id="messages" class="success-message">
				<h3>${message}</h3><br /><br />
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