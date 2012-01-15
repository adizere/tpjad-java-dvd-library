<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="org.springframework.security.web.authentication.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.web.authentication.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>


<!--
DVDWorld DVD Browsing Page.
Here, users can browse the collection of DVDs.
-->


<html>
<head>
	<title>DVD World | Browse DVDs</title>
	<%@ include file="/WEB-INF/jsp/common/headIncludes.jsp" %>
</head>

<body>

<div class="BigContainerPane">
	<%@ include file="/WEB-INF/jsp/common/pageHeader.jsp" %>
	
	<div class="ContentPane" id="_MainContent">
	<div class="RealContentPane" id="_RealContentPane">
	
	<h3>Browse Our DVD Collection!</h3>
	<p>
		Our DVD Collection is at your disposal.
		Choose from a great variety of DVDs, all ranging from the classical favorites to the modern day blockbusters, and rent at the smallest prices possible.
		It's as easy as 1, 2, 3... 
	</p>
	
	
	<!-- This is the table were we post all the DVDs. -->
	<c:choose>
	<c:when test="${not empty dvds}">
	<table class="ViewDvdTableStyle">
	<tr>
		<th>ID</th>
		<th>Title</th>
		<th>Details</th>
	</tr>
	
	<c:forEach var="dvd" items="${dvds}">
		<tr>
			<!-- DVD ID -->
			<td>#<c:out value="${dvd.id}" /></td>
			
			<!-- DVD Title -->
			<td><c:out value="${dvd.title}" /></td>
			
			<!-- DVD Description -->
			<td>
				<b>Description</b>: <c:out value="${dvd.description}" />
				<br />
				<c:if test="${dvd.quantity <= 2}">
					<img src="public/img/quantity_low.png" />
				</c:if>
				<c:if test="${dvd.quantity > 2 && dvd.quantity <= 10}">
					<img src="public/img/quantity_medium.png" />
				</c:if>
				<c:if test="${dvd.quantity > 10}">
					<img src="public/img/quantity_high.png" />
				</c:if>
				<b>Quantity available</b>:
				<c:choose>
					<c:when test="${dvd.quantity > 0}">
						<c:out value="${dvd.quantity}" />
					</c:when>
					<c:otherwise>
						<span class="InfoText">Out of supply.</span>
					</c:otherwise>
				</c:choose>
				<br />
				<b>Price</b>: <c:out value="${dvd.price}" /> <div class="PriceTag">euro/day</div>
				<br /><br />
				<c:if test="${dvd.quantity > 0}">
					<a href="processCart.html?id=<c:out value="${dvd.id}"/>&operation=ADDTOCART"><img src="public/img/add-to-cart.png" alt="Add to cart!" /></a>
				</c:if>
			</td>
		</tr>
	</c:forEach>
	
	</table>	
	
	<div class="OperationResult">Displaying a total of <c:out value="${fn:length(dvds)}" /> item(s).</div>
	</c:when>
	<c:otherwise>
	<div class="InfoText">There are currently no items in the library at the moment.</div>
	</c:otherwise>
	</c:choose>
	
	</div>
	</div>
	
	<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>
	
</div>

</body>
</html>
