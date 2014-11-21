<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!-- Footer -->
<div id="footer" class="shell">
		<div class="top">
			<div class="cnt">
				<div class="col about">
					<h4><spring:message code="about" /></h4>
					<p><spring:message code="student" />: Andrés Biasoli </p>
					<p><spring:message code="identifier" />: 54065 </p>
					<p><spring:message code="subject" />: <spring:message code="programming" /> </p>
					<p><spring:message code="year" />: 2014 </p>
					<p><spring:message code="teacher" />: Diego Mornacco </p>
				</div>
				<div class="col store">
					<h4><spring:message code="what.to.do" /></h4>
					<ul>
						<li><a href='<c:url value="/app/search" />'><spring:message code="menu.search"/></a></li>
						<li><a href='<c:url value="/app/books/new" />'><spring:message code="menu.add.book"/></a></li>
						<li><a href='<c:url value="/app/movies/new" />'><spring:message code="menu.add.movie"/></a></li>
						<li><a href='<c:url value="/app/loan/" />'><spring:message code="menu.my.loans"/></a></li>
						<li><a href='<c:url value="/app/loan/notifications" />'><spring:message code="menu.my.revisions"/></a></li>
					</ul>
				</div>
				<div class="col" id="newsletter">
					<h4><spring:message code="subscription" /></h4>
					<p><spring:message code="send.your.email" /> </p>
					<form action='<c:url value="/app/search" />' method='post'>
						<input type="text" class="field" value="<spring:message code="your.name" />" title="<spring:message code="your.name" />" />
						<input type="text" class="field" value="Email" title="Email" />
						<div class="form-buttons"><input type="submit" value="<spring:message code="submit" />" class="submit-btn" /></div>
					</form>
				</div>
				<div class="cl">&nbsp;</div>
				<div class="copy">
					<p>&copy; <a href="#">BestSeller.com</a>. Design by <a href="http://css-free-templates.com/">CSS-FREE-TEMPLATES.COM</a></p>
				</div>
			</div>
		</div>
	</div>
	<!-- End Footer -->