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
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src='<c:url value="/resources/js/lib/jquery-1.8.2.js" />'></script>
	<script src='<c:url value="/resources/js/lib/json2.js" />'></script>
	<link rel="shortcut icon" href='<c:url value="/resources/css/images/favicon.ico" />' />
	<link rel="stylesheet" href='<c:url value="/resources/css/style.css" />' type="text/css" media="all" />
	<title><spring:message code="loan.my.loans"/></title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<div id="main" class="shell">
		<h1 class="main-title"><spring:message code="loan.requested.list"/></h1> <br />
		
		<c:set var="backgroundColor" value="" />
		<c:set var="state" value="" />
		<c:choose>
			<c:when test="${empty loans}">
				<div id="messages" class="success-message">
					<h3><spring:message code="loan.not.in.course"/></h3> <br /> <br />
					<a href='<c:url value="/app/search" />'><spring:message code="go.back"/></a>
				</div>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<th><spring:message code="loan.requested.cover"/></th>
						<th><spring:message code="loan.requested.title"/></th>
						<th><spring:message code="loan.requested.owner"/></th>
						<th><spring:message code="loan.requested.date"/></th>
						<th><spring:message code="loan.response.date"/></th>
						<th><spring:message code="loan.requested.state"/></th>			
					</tr>
					<c:forEach var="loan" items="${loans}">
						<c:choose>	
							<c:when test="${loan.state eq 'ACCEPTED'}">
								<c:set var="state">
									<spring:message code='loan.state.accepted' />
								</c:set>
								<c:set var="backgroundColor" value="springgreen" />
							</c:when>
							<c:when test="${loan.state eq 'REJECTED'}">
								<c:set var="state">
									<spring:message code="loan.state.rejected" />
								</c:set>
								<c:set var="backgroundColor" value="tomato" />
							</c:when>
							<c:otherwise>
								<c:set var="state">
									<spring:message code="loan.state.pending" />
								</c:set>
								<c:set var="backgroundColor" value="rgb(255,255,125)" />
							</c:otherwise>
						</c:choose>
						<tr style="background-color:${backgroundColor}">
							<td><img height="130" width="100" src='<c:url value="/app/image/${loan.product.type}/${loan.product.id}" />' /></td>
							<td>${loan.product.title}</td>
							<td>${loan.consignee.name}</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${loan.requestDate}" /></td>
							<td>
								<c:choose>
									<c:when test="${not empty loan.responseDate}">
										<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${loan.responseDate}" />
									</c:when>
									<c:otherwise>
										<spring:message code="loan.response.date.missing"/>
									</c:otherwise>
								</c:choose>
							</td>
							<td>${state}</td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
	<!-- End Main -->
	<!-- Footer -->
	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	<!-- End Footer -->
</body>
</html>