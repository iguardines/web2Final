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
	
		<div id="main" class="shell">
					
			<div id="filter">
				<span><spring:message code="label.filter.by"/></span>
				<form:form action="/booksmov/app/search" modelAttribute="filterDto" method="POST">
					<form:label path="userName">
						<spring:message code="label.filter.user"/>
					</form:label>
					<form:input path="userName" /><br />
					<form:label path="rating">
						<spring:message code="label.filter.rating"/>
					</form:label>
					<form:radiobutton path="rating" value="1"/>1 
					<form:radiobutton path="rating" value="2"/>2 
					<form:radiobutton path="rating" value="3"/>3 
					<form:radiobutton path="rating" value="4"/>4 
					<form:radiobutton path="rating" value="5"/>5<br />
					<form:label path="title">
						<spring:message code="label.filter.title"/>
					</form:label>
					<form:input path="title" /> <br />
					<form:label path="borrowable">
						<spring:message code="label.filter.borrowable"/>
					</form:label>
					<form:checkbox path="borrowable"/>
					<br />
					<form:radiobutton path="type" value="all"/> 
					<form:label path="type">
						<spring:message code="label.filter.all"/>
					</form:label>
					<form:radiobutton path="type" value="books"/> 
					<form:label path="type">
						<spring:message code="label.filter.booksOnly"/>
					</form:label>
					<form:radiobutton path="type" value="movies"/> 
					<form:label path="type">
						<spring:message code="label.filter.moviesOnly"/>
					</form:label><br />
					<form:button class="submit-btn"><spring:message code="label.filter.submit"/></form:button>
				</form:form>
			</div>
			<div class="relative">
				<c:if test="${not empty message}">
					<div id="messages" class="message-box">
						<h3>${message}</h3>
					</div>
				</c:if>
			</div>
			<div id="content">
				<!-- Products -->
				<div class="products">
					<h3><spring:message code="books.and.movies" /></h3>
					<c:choose>
						<c:when test="${not empty products}">
							<div id="ul-header">
								<span class="icon"><spring:message code="sort.by.title" />:
									<a href='<c:url value="/app/search/${filter}?page=${pagination.currentPage}&order=asc&userName=${filterDto.userName}&stars=${filterDto.rating}&title=${filterDto.title}&type=${filterDto.type}&borrowable=${filterDto.borrowable}" />'>
				 						<img src='<c:url value="/resources/img/asc.png" />' style="width:16px; height:16px;"/>
				 					</a>
				 					<a href='<c:url value="/app/search/${filter}?page=${pagination.currentPage}&order=desc&userName=${filterDto.userName}&stars=${filterDto.rating}&title=${filterDto.title}&type=${filterDto.type}&borrowable=${filterDto.borrowable}" />'>
				 						<img src='<c:url value="/resources/img/desc.png" />' style="width:16px; height:16px;"/>
				 					</a>
					 			</span> 
								<span class="icon" ><spring:message code="sort.by.rating" />
									<a href='<c:url value="/app/search/${filter}?page=${pagination.currentPage}&order=asc&rating=true&userName=${filterDto.userName}&stars=${filterDto.rating}&title=${filterDto.title}&type=${filterDto.type}&borrowable=${filterDto.borrowable}" />'>
				 						<img src='<c:url value="/resources/img/asc.png" />' style="width:16px; height:16px;"/>
				 					</a>
				 					<a href='<c:url value="/app/search/${filter}?page=${pagination.currentPage}&order=desc&rating=true&userName=${filterDto.userName}&stars=${filterDto.rating}&title=${filterDto.title}&type=${filterDto.type}&borrowable=${filterDto.borrowable}" />'>
				 						<img src='<c:url value="/resources/img/desc.png" />' style="width:16px; height:16px;"/>
				 					</a>
								</span>
								<span id="results">
									<span>
										<c:choose>
											<c:when test="${pagination.totalResults > 1}">
												<spring:message code="pagination.total.results" arguments="${pagination.totalResults}"/>
											</c:when>
											<c:otherwise>
												<spring:message code="pagination.total.result" arguments="${pagination.totalResults}"/>
											</c:otherwise>
										</c:choose>
									</span>
								</span>
							</div>
							<ul>
								<c:forEach var="product" items="${products}">
									<c:choose>
										<c:when test="${product.type eq 'movie'}">
											<c:set var="type" value="movie" />
										</c:when>
										<c:otherwise>
											<c:set var="type" value="book" />
										</c:otherwise>
									</c:choose>
									<li class="glowing-border">	
										<div class="product">
											<div class="info">
												<span class="holder">
													<img height="130" width="100" src='<c:url value="/app/image/${type}/${product.id}" />' /> 
													<span class="book-name">${product.title}</span>
													<span class="icon">
														<c:forEach begin="1" end="${product.rating}">
															<img  height="16" width="16" src='<c:url value="/resources/img/star.png" />' />
														</c:forEach>
													</span>
													<span class="author">
														<c:choose>
															<c:when test="${type eq 'book'}">
																<c:set var="hasAuthors" value="false" />
																<c:if test="${fn:length(product.authorsList) > 0}">
																	<c:set var="hasAuthors" value="true" />
																	<strong><spring:message code="by"/></strong>
																</c:if>
																<c:forEach var="author" items="${product.authorsList}" varStatus="status">
																	${author.fullName}<c:if test="${hasAuthors and status.index < fn:length(product.authorsList) - 1}"><spring:message code="comma"/>
																	</c:if>
																</c:forEach>
															</c:when>
															<c:otherwise>
																<c:set var="hasActors" value="false" />
																<c:if test="${fn:length(product.actorList) > 0}">
																	<c:set var="hasActors" value="true" />
																	<strong><spring:message code="by"/></strong>
																</c:if>
																<span>
																<c:forEach var="actor" items="${product.actorList}" varStatus="status">
																	${actor.fullName}<c:if test="${hasActors and status.index < (fn:length(product.actorList) -1)}"><spring:message code="comma"/>
																	</c:if>
																</c:forEach>
																</span><br />
																<strong><spring:message code="format" />:</strong> ${product.selectedFormat}<br />
																<c:if test="${not empty product.director.fullName.trim()}">
																	<strong><spring:message code="director" />:</strong> ${product.director.fullName}
																</c:if>
															</c:otherwise>
														</c:choose>
												  </span>
												  <span class="description icon">
												  		<spring:message code="label.products.borrowable"/>: 
												  		<c:choose>
												  			<c:when test="${product.borrowable}">
												  				<img height="16" width="16" src='<c:url value="/resources/img/check.png" />' />
												  			</c:when>
												  			<c:otherwise>
												  				<img height="16" width="16" src='<c:url value="/resources/img/cross.png" />' />
												  			</c:otherwise>
												  		</c:choose>
												  		<c:set var="used" value=""/>
												  		<spring:message var="seen" code="seen" />
												  		<spring:message var="notSeen" code="not.seen" />
												  		<spring:message var="read" code="read" />
												  		<spring:message var="notRead" code="not.read" />
												  		<c:choose>
													  		<c:when test="${type eq 'movie' and product.alreadyUsed}">
																<c:set var="used" value="${seen}"/>		
															</c:when>
															<c:when test="${type eq 'movie' and !product.alreadyUsed}">
																<c:set var="used" value="${notSeen}"/>		
															</c:when>
															<c:when test="${type eq 'book' and product.alreadyUsed}">
																<c:set var="used" value="${read}"/>		
															</c:when>
															<c:when test="${type eq 'book' and !product.alreadyUsed}">
																<c:set var="used" value="${notRead}"/>		
															</c:when>
														</c:choose>
												  		<c:choose>
															<c:when test="${product.alreadyUsed}">
																<img title="${used}" src='<c:url value="/resources/img/eye_open.png" />' />
															</c:when>
															<c:otherwise>
																<img title="${used}" src='<c:url value="/resources/img/eye_closed.png" />' />
															</c:otherwise>
														</c:choose>
												  </span>
												
												  <span class="icon">
														<c:if test="${product.userId eq sessionScope.user.id}">
															<a href='<c:url value="/app/${type}s/edit/${product.id}" />'>
																<img title='<spring:message code="edit" />' src='<c:url value="/resources/img/editar.gif" />' />
															</a>
														</c:if>
														<c:if test="${product.userId ne sessionScope.user.id and product.borrowable eq true and product.requestableForLoan eq true}">
															<a href='<c:url value="/app/loan/request/${product.id}?owner=${product.userId}"  />'>
																<img title='<spring:message code="request" />' src='<c:url value="/resources/img/solicitud_prestamo.png"  />' />
															</a>
														</c:if>
												  </span>
												</span>
											</div>
										</div>
									</li>
									
								</c:forEach>
							</ul>
						</c:when>
						<c:otherwise>
							<spring:message code="no.results.found" />
						</c:otherwise>
					</c:choose>
				<!-- End Products -->
				</div>
				<div class="cl">&nbsp;</div>
			</div>
		<!-- End Content -->
			<div class="cl">&nbsp;</div>
				<div id="pagination">
					<span class="float-lf">
						<c:if test="${pagination.currentPage gt 0}">
							<a href='<c:url value="/app/search/${filter}?page=${pagination.currentPage - 1}&order=${search.order}&rating=${search.rating}&userName=${filterDto.userName}&stars=${filterDto.rating}&title=${filterDto.title}&type=${filterDto.type}&borrowable=${filterDto.borrowable}"/>'><spring:message code="previous" /></a>
						</c:if>
						
						<c:forEach var="i" begin="${pagination.begin}" end="${pagination.end}">
							<c:choose>
								<c:when test="${i == pagination.currentPage + 1}">
									${i}
								</c:when>
								<c:otherwise>
									<a href='<c:url value="/app/search/${filter}?page=${i - 1}&order=${search.order}&rating=${search.rating}&userName=${filterDto.userName}&stars=${filterDto.rating}&title=${filterDto.title}&type=${filterDto.type}&borrowable=${filterDto.borrowable}"/>'>${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						<c:if test="${pagination.currentPage lt (pagination.maxPage - 1)}">
							<a href='<c:url value="/app/search/${filter}?page=${pagination.currentPage + 1}&order=${search.order}&rating=${search.rating}&userName=${filterDto.userName}&stars=${filterDto.rating}&title=${filterDto.title}&type=${filterDto.type}&borrowable=${filterDto.borrowable}"/>'><spring:message code="next" /></a>
						</c:if>
					</span>
				</div>
				<div>
					<c:if test="${pagination.maxPage > 0}">
						<strong class="ml-page"><spring:message code="page" /> ${pagination.currentPage + 1}/${pagination.maxPage} </strong>
					</c:if>
				</div>
		</div>
		
		<!-- End Main -->
	<!-- Footer -->
	<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	<!-- End Footer -->
</body>
</html>