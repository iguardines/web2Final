<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!-- Header -->
<div id="header" class="shell">
	<div id="logo"><h1><a href="#"><spring:message code="app.name"/></a></h1><span><a href='<c:url value="/app/search" />'><spring:message code="app.slogan"/></a></span></div>
	<!-- Navigation -->
	<div id="navigation">
		<ul>
			<li><a href='<c:url value="/app/search" />'><spring:message code="menu.search"/></a></li>
			<li><a href='<c:url value="/app/books/new" />'><spring:message code="menu.add.book"/></a></li>
			<li><a href='<c:url value="/app/movies/new" />'><spring:message code="menu.add.movie"/></a></li>
			<li><a href='<c:url value="/app/loan/" />'><spring:message code="menu.my.loans"/></a></li>
			<li><a href='<c:url value="/app/loan/notifications" />'><spring:message code="menu.my.revisions"/></a></li>
			<c:if test="${empty user}">
				<li><a href='<c:url value="/app/login" />'><spring:message code="login"/></a></li>
			</c:if>
		</ul>
	</div>
	<!-- End Navigation -->
	<div class="cl">&nbsp;</div>
	<!-- Login-details -->
	<c:if test="${not empty user}">
		<div id="login-details">
			<p><spring:message code="welcome"/><spring:message code="comma"/>&nbsp;<span id="user">${user.name}</span>.&nbsp;</p>
			<a href='<c:url value="/app/logout/" />'><spring:message code="logout"/></a>
		</div>
	</c:if>
	<!-- End Login-details -->
</div>
<!-- End Header -->