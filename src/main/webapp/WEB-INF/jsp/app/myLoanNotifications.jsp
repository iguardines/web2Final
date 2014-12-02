<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="shortcut icon" href='<c:url value="/resources/css/images/favicon.ico" />' />
	<link rel="stylesheet" href='<c:url value="/resources/css/style.css" />' type="text/css" media="all" />
	<link rel="stylesheet" href='<c:url value="/resources/css/pure.css" />'>
	<script src='<c:url value="/resources/js/lib/jquery-1.8.2.js" />'></script>
	<script src='<c:url value="/resources/js/lib/jquery-ui-1.11.2.custom/jquery-ui.js" />'></script>
	<title><spring:message code="loan.my.loans"/></title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<div id="main" class="shell">
		<h1 class="main-title"><spring:message code="loan.notified.list"/></h1><br />
		<c:set var="backgroundColor" value="" />
		
		<div class="pos-status">
			<div id="dropzone-accepted" data-action="accept" class="float-lf ml-loan loan-status springgreen droppable">
				<span class="span-pos"><spring:message code='loan.state.accepted' /></span>
			</div>
			<div id="dropzone-rejected" data-action="reject" class="float-lf ml-loan loan-status tomato droppable">
				<span class="span-pos"><spring:message code="loan.state.rejected" /></span>
			</div>
			<div id="dropzone-delivered" data-action="deliver" class="float-lf ml-loan loan-status lightgray droppable">
				<span class="span-pos"><spring:message code="loan.state.delivered" /></span>
			</div>
		</div>
		<div class="loans">
			<ul class="pure-g">
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
						<c:when test="${loan.state eq 'DELIVERED'}">
							<c:set var="state">
								<spring:message code="loan.state.delivered" />
							</c:set>
							<c:set var="backgroundColor" value="lightgray" />
						</c:when>
						<c:otherwise>
							<c:set var="state">
								<spring:message code="loan.state.pending" />
							</c:set>
							<c:set var="backgroundColor" value="rgb(255,255,125)" />
						</c:otherwise>
					</c:choose>
					<li id="loan-${loan.id}" class="glowing-border clear-bt pos-li pure-u-1-3" style="background-color:${backgroundColor}">	
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
										<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${loan.requestDate}" />
								    </span>
								    <span class="float-lf w">
								    	<strong><spring:message code="loan.requested.state"/>:</strong><br />
								    	<span id="state-${loan.id}">${state}</span> <br />
								    </span>
									<span class="float-rt">
										<strong><spring:message code="loan.response.date"/>:</strong><br />
										<c:choose>
											<c:when test="${not empty loan.responseDate}">
												<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${loan.responseDate}" /> 
											</c:when>
											<c:otherwise>
												<spring:message code="loan.response.date.missing"/> <br />
											</c:otherwise>
										</c:choose>
									</span>
									<span class="float-lf">
										<strong><spring:message code="loan.delivery.date"/>:</strong><br />
										<c:choose>
											<c:when test="${loan.state eq 'DELIVERED'}">
												<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${loan.deliveryDate}" /> 
											</c:when>
											<c:otherwise>
												<spring:message code="loan.no.apply"/> <br />
											</c:otherwise>
										</c:choose>
									</span>
									<span class="float-rt">
										<strong><spring:message code="loan.requested.message"/>:</strong><br />
										${loan.requestDescription} <br />
									</span>
									<span class="drag-me draggable move" data-state="${loan.state}" data-loanid="${loan.id}">
										<img src='<c:url value="/resources/img/drag.png" />' title='<spring:message code="move.me" />'/>
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
	<script src="<c:url value='/resources/js/dragAndDrop.js' />"></script>
	<!-- End Main -->
	<!-- Footer -->
	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	<!-- End Footer -->
</body>
</html>