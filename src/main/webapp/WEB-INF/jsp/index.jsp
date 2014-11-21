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
		<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
		<link rel="shortcut icon" href='<c:url value="/resources/css/images/favicon.ico" />' />
		<link rel="stylesheet" href='<c:url value="/resources/css/style.css" />' type="text/css" media="all" />
		<script type="text/javascript" src='<c:url value="/resources/js/lib/jquery-1.8.2.js" />'></script>
		<script type="text/javascript" src='<c:url value="/resources/js/lib/jquery.jcarousel.min.js" />'></script>
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
		
	<!-- Main -->
	<div id="main" class="shell">
	
		<!-- Content -->
		<div id="content">
			<!-- Products -->
			<div class="products">
				<h3><spring:message code="outstanding.of.the.week" /></h3>
				<ul>
					<li>
						<div class="product">
							<a href="#" class="info">
								<span class="holder">
									<img src='<c:url value="/resources/css/images/image01.jpg" />' alt="" />
									<span class="book-name">Book Name</span>
									<span class="author">by John Smith</span>
									<span class="description">Maecenas vehicula ante eu enim pharetra<br />scelerisque dignissim <br />sollicitudin nisi</span>
								</span>
							</a>
						</div>
					</li>
					<li>
						<div class="product">
							<a href="#" class="info">
								<span class="holder">
									<img src='<c:url value="/resources/css/images/image02.jpg" />' alt="" />
									<span class="book-name">Book Name</span>
									<span class="author">by John Smith</span>
									<span class="description">Maecenas vehicula ante eu enim pharetra<br />scelerisque dignissim <br />sollicitudin nisi</span>
								</span>
							</a>
						</div>
					</li>
					<li>
						<div class="product">
							<a href="#" class="info">
								<span class="holder">
									<img src='<c:url value="/resources/css/images/image03.jpg" />' alt="" />
									<span class="book-name">Book Name</span>
									<span class="author">by John Smith</span>
									<span class="description">Maecenas vehicula ante eu enim pharetra<br />scelerisque dignissim <br />sollicitudin nisi</span>
								</span>
							</a>
						</div>
					</li>
					<li>
						<div class="product">
							<a href="#" class="info">
								<span class="holder">
									<img src='<c:url value="/resources/css/images/image03.jpg" />' alt="" />
									<span class="book-name">Book Name</span>
									<span class="author">by John Smith</span>
									<span class="description">Maecenas vehicula ante eu enim pharetra<br />scelerisque dignissim <br />sollicitudin nisi</span>
								</span>
							</a>
						</div>
					</li>
					<li>
						<div class="product">
							<a href="#" class="info">
								<span class="holder">
									<img src='<c:url value="/resources/css/images/image04.jpg" />' alt="" />
									<span class="book-name">Book Name</span>
									<span class="author">by John Smith</span>
									<span class="description">Maecenas vehicula ante eu enim pharetra<br />scelerisque dignissim <br />sollicitudin nisi</span>
								</span>
							</a>
						</div>
					</li>
					<li>
						<div class="product">
							<a href="#" class="info">
								<span class="holder">
									<img src='<c:url value="/resources/css/images/image06.jpg" />' alt="" />
									<span class="book-name">Book Name</span>
									<span class="author">by John Smith</span>
									<span class="description">Maecenas vehicula ante eu enim pharetra<br />scelerisque dignissim <br />sollicitudin nisi</span>
								</span>
							</a>
						</div>
					</li>
					<li>
						<div class="product">
							<a href="#" class="info">
								<span class="holder">
									<img src='<c:url value="/resources/css/images/image07.jpg" />' alt="" />
									<span class="book-name">Book Name</span>
									<span class="author">by John Smith</span>
									<span class="description">Maecenas vehicula ante eu enim pharetra<br />scelerisque dignissim <br />sollicitudin nisi</span>
								</span>
							</a>
						</div>
					</li>
					<li>
						<div class="product">
							<a href="#" class="info">
								<span class="holder">
									<img src='<c:url value="/resources/css/images/image08.jpg" />' alt="" />
									<span class="book-name">Book Name</span>
									<span class="author">by John Smith</span>
									<span class="description">Maecenas vehicula ante eu enim pharetra<br />scelerisque dignissim <br />sollicitudin nisi</span>
								</span>
							</a>
						</div>
					</li>
				</ul>
			<!-- End Products -->
			</div>
			<div class="cl">&nbsp;</div>
		</div>
		<!-- End Content -->
		<div class="cl">&nbsp;</div>
	</div>
	<!-- End Main -->
	<!-- Footer -->
	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	<!-- End Footer -->
		
	</body>
</html>