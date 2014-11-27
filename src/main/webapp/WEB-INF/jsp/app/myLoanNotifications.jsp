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
	<script src='<c:url value="/resources/js/lib/jquery-1.8.2.js" />'></script>
	<script src='<c:url value="/resources/js/lib/jquery-ui-1.11.2.custom/jquery-ui.js" />'></script>
	<title><spring:message code="loan.my.loans"/></title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<div id="main" class="shell">
		<h1 class="main-title"><spring:message code="loan.notified.list"/></h1><br />
		<c:set var="backgroundColor" value="" />
		<c:set var="state" value="" />
		<c:set var="pending" value="false" />
		<c:set var="accepted" value="false" />
<%-- 		<c:choose> --%>
<%-- 			<c:when test="${empty loans}"> --%>
<!-- 				<div id="messages" class="success-message"> -->
<%-- 					<h3><spring:message code="loan.not.in.revision"/></h3><br /><br /> --%>
<%-- 					<a class="back" href='<c:url value="/app/search" />'><spring:message code="go.back"/></a> --%>
<!-- 				</div> -->
<%-- 			</c:when> --%>
<%-- 			<c:otherwise> --%>
<!-- 				<div class="draggable"> -->
<!-- 					<h1>arrastrame</h1> -->
<!-- 				</div> -->
<!-- 				<table> -->
<!-- 					<tr> -->
<%-- 						<th><spring:message code="loan.requested.cover"/></th> --%>
<%-- 						<th><spring:message code="loan.requested.title"/></th> --%>
<%-- 						<th><spring:message code="loan.requested.requester"/></th> --%>
<%-- 						<th><spring:message code="loan.requested.date"/></th> --%>
<%-- 						<th><spring:message code="loan.requested.state"/></th> --%>
<%-- 						<th><spring:message code="loan.requested.message"/></th>			 --%>
<%-- 						<th><spring:message code="loan.response.date"/></th> --%>
<%-- 						<th><spring:message code="loan.delivery.date"/></th> --%>
<!-- 					</tr> -->
<%-- 					<c:forEach var="loan" items="${loans}"> --%>
<%-- 						<c:choose>	 --%>
<%-- 							<c:when test="${loan.state eq 'ACCEPTED'}"> --%>
<%-- 								<c:set var="state"> --%>
<%-- 									<spring:message code='loan.state.accepted' /> --%>
<%-- 								</c:set> --%>
<%-- 								<c:set var="backgroundColor" value="springgreen" /> --%>
<%-- 								<c:set var="pending" value="false" /> --%>
<%-- 								<c:set var="accepted" value="true" /> --%>
<%-- 							</c:when> --%>
<%-- 							<c:when test="${loan.state eq 'REJECTED'}"> --%>
<%-- 								<c:set var="state"> --%>
<%-- 									<spring:message code="loan.state.rejected" /> --%>
<%-- 								</c:set> --%>
<%-- 								<c:set var="backgroundColor" value="tomato" /> --%>
<%-- 								<c:set var="accepted" value="false" /> --%>
<%-- 								<c:set var="delivered" value="false" /> --%>
<%-- 							</c:when> --%>
<%-- 							<c:when test="${loan.state eq 'DELIVERED'}"> --%>
<%-- 								<c:set var="state"> --%>
<%-- 									<spring:message code="loan.state.delivered" /> --%>
<%-- 								</c:set> --%>
<%-- 								<c:set var="backgroundColor" value="lightgray" /> --%>
<%-- 								<c:set var="pending" value="false" /> --%>
<%-- 								<c:set var="accepted" value="false" /> --%>
<%-- 							</c:when> --%>
<%-- 							<c:otherwise> --%>
<%-- 								<c:set var="state"> --%>
<%-- 									<spring:message code="loan.state.pending" /> --%>
<%-- 								</c:set> --%>
<%-- 								<c:set var="backgroundColor" value="rgb(255,255,125)" /> --%>
<%-- 								<c:set var="pending" value="true" /> --%>
<%-- 								<c:set var="accepted" value="false" /> --%>
<%-- 							</c:otherwise> --%>
<%-- 						</c:choose> --%>
<%-- 						<tr style="background-color:${backgroundColor}"> --%>
<%-- 							<td><img height="130" width="100" src='<c:url value="/app/image/${loan.product.id}" />' /></td> --%>
<%-- 							<td>${loan.product.title}</td> --%>
<%-- 							<td>${loan.requester.name}</td> --%>
<%-- 							<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${loan.requestDate}" /></td> --%>
<%-- 							<td>${state}</td> --%>
<%-- 							<td>${loan.requestDescription}</td> --%>
<!-- 							<td> -->
<%-- 								<c:choose> --%>
<%-- 									<c:when test="${not empty loan.responseDate}"> --%>
<%-- 										<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${loan.responseDate}" /> --%>
<%-- 									</c:when> --%>
<%-- 									<c:otherwise> --%>
<%-- 										<spring:message code="loan.response.date.missing"/> --%>
<%-- 									</c:otherwise> --%>
<%-- 								</c:choose> --%>
<!-- 							</td> -->
<!-- 							<td> -->
<%-- 								<c:choose> --%>
<%-- 									<c:when test="${loan.state eq 'DELIVERED'}"> --%>
<%-- 										<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${loan.deliveryDate}" /> --%>
<%-- 									</c:when> --%>
<%-- 									<c:otherwise> --%>
<%-- 										<spring:message code="loan.no.apply"/> --%>
<%-- 									</c:otherwise> --%>
<%-- 								</c:choose> --%>
<!-- 							</td> -->
<!-- 							<td> -->
<!-- 								<div> -->
<%-- 									<c:if test="${pending}"> --%>
<!-- 										<div class="margin-bottom"> -->
<%-- 											<a href='<c:url value="/app/loan/accept/${loan.id}"  />' > --%>
<%-- 											   <button class="submit-btn"><spring:message code="loan.response.accept"/></button> --%>
<!-- 											</a> -->
<!-- 										</div> -->
<!-- 										<div class="margin-top"> -->
<%-- 											<a href="<c:url value="/app/loan/reject/${loan.id}" />" > --%>
<%-- 											   <button class="submit-btn"><spring:message code="loan.response.reject"/></button> --%>
<!-- 											</a> -->
<!-- 										</div> -->
<%-- 									</c:if> --%>
<%-- 									<c:if test="${accepted}"> --%>
<%-- 										<a href='<c:url value="/app/loan/deliver/${loan.id}" />' > --%>
<%-- 										   <button class="submit-btn"><spring:message code="loan.response.deliver"/></button> --%>
<!-- 										</a> -->
<%-- 									</c:if> --%>
<!-- 								</div> -->
<!-- 							</td> -->
<!-- 						</tr> -->
<%-- 					</c:forEach> --%>
<!-- 				</table> -->
<%-- 			</c:otherwise> --%>
<%-- 		</c:choose> --%>
	<div class="loans">
		<ul>
			<c:forEach var="loan" items="${loans}">
				<c:choose>	
					<c:when test="${loan.state eq 'ACCEPTED'}">
						<c:set var="state">
							<spring:message code='loan.state.accepted' />
						</c:set>
						<c:set var="backgroundColor" value="springgreen" />
						<c:set var="pending" value="false" />
						<c:set var="accepted" value="true" />
					</c:when>
					<c:when test="${loan.state eq 'REJECTED'}">
						<c:set var="state">
							<spring:message code="loan.state.rejected" />
						</c:set>
						<c:set var="backgroundColor" value="tomato" />
						<c:set var="accepted" value="false" />
						<c:set var="delivered" value="false" />
					</c:when>
					<c:when test="${loan.state eq 'DELIVERED'}">
						<c:set var="state">
							<spring:message code="loan.state.delivered" />
						</c:set>
						<c:set var="backgroundColor" value="lightgray" />
						<c:set var="pending" value="false" />
						<c:set var="accepted" value="false" />
					</c:when>
					<c:otherwise>
						<c:set var="state">
							<spring:message code="loan.state.pending" />
						</c:set>
						<c:set var="backgroundColor" value="rgb(255,255,125)" />
						<c:set var="pending" value="true" />
						<c:set var="accepted" value="false" />
					</c:otherwise>
				</c:choose>
				<li class="glowing-border">	
					<div class="loan">
						<div class="info">
							<span class="holder">
								<img height="130" width="100" src='<c:url value="/app/image/${loan.product.id}" />' />
								<span class="book-name">
									${loan.product.title}
								</span><br /><br />
								<span class="float-lf">
									<strong><spring:message code="loan.requested.requester"/>:</strong><br />
									${loan.requester.name}<br />
								</span>
								<span class="float-rt">
									<strong><spring:message code="loan.requested.date"/>:</strong><br />
									<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${loan.requestDate}" /><br />
							    </span>
							    <span class="float-lf">
							    	<strong><spring:message code="loan.requested.state"/>:</strong><br />
							    	${state} <br />
							    </span>
								<span class="float-rt">
									<strong><spring:message code="loan.response.date"/>:</strong><br />
									<c:choose>
										<c:when test="${not empty loan.responseDate}">
											<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${loan.responseDate}" />  <br />
										</c:when>
										<c:otherwise>
											<spring:message code="loan.response.date.missing"/>  <br />
										</c:otherwise>
									</c:choose>
								</span>
								<span class="float-lf">
									<strong><spring:message code="loan.delivery.date"/>:</strong><br />
									<c:choose>
										<c:when test="${loan.state eq 'DELIVERED'}">
											<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${loan.deliveryDate}" /> <br />
										</c:when>
										<c:otherwise>
											<spring:message code="loan.no.apply"/> 
										</c:otherwise>
									</c:choose>
								</span>
								<span class="float-rt">
									<strong><spring:message code="loan.requested.message"/>:</strong><br />
									${loan.requestDescription} <br />
								</span>
							</span>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div class="cl">&nbsp;</div>
	</div>
	<script src=''></script>
	<!-- End Main -->
	<!-- Footer -->
	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	<!-- End Footer -->
</body>
</html>