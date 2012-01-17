<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="org.springframework.security.web.authentication.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.web.authentication.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>


<!--
DVDWorld User Rentals
-->


<html>
<head>
	<title>DVD World | User Rentals</title>
	<%@ include file="/WEB-INF/jsp/common/headIncludes.jsp" %>
</head>

<body>

<div class="BigContainerPane">
	<%@ include file="/WEB-INF/jsp/common/pageHeader.jsp" %>
	
	<div class="ContentPane" id="_MainContent">
	<div class="RealContentPane" id="_RealContentPane">
	
	<h2>User Rental Information
	<%
		try {
			String userIdString = request.getParameter ("userid");
			int userId = Integer.parseInt(userIdString);
		
			// Do some context sniffing.
			ServletContext servletContext = this.getServletContext();
			WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
			DvdWorldDao dvdWorldDao = (DvdWorldDao)wac.getBean("dvdWorldDao");
			User user = dvdWorldDao.getUser(userId);
			if (user != null) {
				out.println(" (" + user.getName() + ")");
			}
		} catch (Exception e) {
		}
	%>
	</h2>
	
	<!-- 
	==================================================
	Open Rentals
	==================================================
	 -->
	
	<h3>View Open User Rentals</h3>
	<c:choose>
	<c:when test="${not empty userOpenRentals}">
		<table class="ViewDvdTableStyle">
		<tr>
			<th>Rental ID</th>
			<th>Open Rental Details</th>
		</tr>
		<c:forEach var="rental" items="${userOpenRentals}">
			<tr>
				<!-- Rental ID -->
				<td>#<c:out value="${rental.id}" /></td>
				
				<!-- Rental Description -->
				<c:set var="rentedDvd" value="${rental.dvd}" />
				<c:set var="userBorrower" value="${rental.user}" />
				<td>
					<!-- DVD ID -->
					<b>DVD ID</b>: #<c:out value="${rentedDvd.id}" />
					<br />
					
					<!-- DVD Title -->
					<b>DVD Title</b>: <c:out value="${rentedDvd.title}" />
					<br />
					
					<!-- DVD Description -->
					<b>Description</b>: <c:out value="${rentedDvd.description}" />
					<br />
					
					<!-- DVD Price -->
					<b>Price</b>: <c:out value="${rentedDvd.price}"/> <div class="PriceTag">euro/day</div>
					<br />
					
					<!-- Rental Quantity -->
					<b>Borrowed Quantity</b>: <c:out value="${rental.quantity}"/>
					<br />
					
					<!-- Rental Start Date -->
					<b>Start Date</b>: <c:out value="${rental.startDate}"/>
					<br />
					
					<!-- Rental Due Date -->
					<b>Due Date</b>: <c:out value="${rental.dueDate}"/>
					<br />
					
					<br />
					<br />
					<a href="adminViewUser.html?userid=<c:out value="${userBorrower.id}"/>&rentalid=<c:out value="${rental.id}"/>&operation=REMOVEFROMCART"><img src="public/img/remove-from-cart.png" alt="Remove From Cart"/></a>
					
				</td>
			</tr>
		</c:forEach>
		</table>
		<br />
		
		<table class="LayoutTableStyle1">
			<tr>
				<td>
					<img src="public/img/credit-card-big.png"/>
				</td>
				<td>
					<div class="InfoText">
						This is the final balance, corresponding to the total amount of items the Borrower wishes to rent.
						Please allow for the Borrower to perform payment and then register the selected items for renting.
						<br /><br />
						<a href="adminViewUser.html?userid=<c:out value="${userBorrower.id}"/>&operation=PROCEEDRENT"><img src="public/img/proceed-rent.png" alt="Proceed Rent"/></a>
						A total of <b><c:out value="${totalSumToPay}"/></b> <div class="PriceTag">euro</div>
					</div>
				</td>
			</tr>
		</table>
	</c:when>
	<c:otherwise>
		<table class="LayoutTableStyle1">
			<tr>
				<td><img src="public/img/empty-folder-camera-big.png"/></td>
				<td>
					<h3>No Open Rental Requests For This User</h3>
					<div class="InfoText">
						There are no rental requests at the moment registered for this user.
						<br />
						Please <b>refresh</b> this page in order to get the latest rental results update.
					</div>
				</td>
			</tr>
		</table>
	</c:otherwise>
	</c:choose>
	<br /><br /><br />
	
	<!-- 
	==================================================
	Current Rentals
	==================================================
	-->
	 
	 
	<h3>View Current User Rentals</h3>
	
	<c:choose>
	<c:when test="${not empty userCurrentRentals}">
		<table class="ViewDvdTableStyle">
		<tr>
			<th>Rental ID</th>
			<th>Current Rental Details</th>
		</tr>
		<c:forEach var="rental" items="${userCurrentRentals}">
			<tr>
				<!-- Rental ID -->
				<td>#<c:out value="${rental.id}" /></td>
				
				<!-- Rental Description -->
				<c:set var="rentedDvd" value="${rental.dvd}" />
				<c:set var="userBorrower" value="${rental.user}" />
				<td>
					<!-- DVD ID -->
					<b>DVD ID</b>: #<c:out value="${rentedDvd.id}" />
					<br />
					
					<!-- DVD Title -->
					<b>DVD Title</b>: <c:out value="${rentedDvd.title}" />
					<br />
					
					<!-- DVD Description -->
					<b>Description</b>: <c:out value="${rentedDvd.description}" />
					<br />
					
					<!-- DVD Price -->
					<b>Price</b>: <c:out value="${rentedDvd.price}"/> <div class="PriceTag">euro/day</div>
					<br />
					
					<!-- Rental Quantity -->
					<b>Borrowed Quantity</b>: <c:out value="${rental.quantity}"/>
					<br />
					
					<!-- Rental Start Date -->
					<b>Start Date</b>: <c:out value="${rental.startDate}"/>
					<br />
					
					<!-- Rental Due Date -->
					<b>Due Date</b>: <c:out value="${rental.dueDate}"/>
					<br />
					
					<br />
					<br />
					<a href="adminViewUser.html?userid=<c:out value="${userBorrower.id}"/>&rentalid=<c:out value="${rental.id}"/>&operation=ENDRENT"><img src="public/img/end-rent.png" alt="End Rent"/></a>
				</td>
			</tr>
		</c:forEach>
		</table>
		<br />
	</c:when>
	<c:otherwise>
		<table class="LayoutTableStyle1">
			<tr>
				<td><img src="public/img/empty-folder-camera-big.png"/></td>
				<td>
					<h3>No Current Rental Requests For This User</h3>
					<div class="InfoText">
						There are no current rental requests at the moment registered for this user.
						<br />
						Please <b>refresh</b> this page in order to get the latest rental results update.
					</div>
				</td>
			</tr>
		</table>
	</c:otherwise>
	</c:choose>	 
	<br /><br /><br />
	
	<!-- 
	==================================================
	Closed Rentals
	==================================================
	-->
	 
	 
	<h3>View Closed User Rentals</h3>
	
	<c:choose>
	<c:when test="${not empty userClosedRentals}">
		<table class="ViewDvdTableStyle">
		<tr>
			<th>Rental ID</th>
			<th>Closed Rental Details</th>
		</tr>
		<c:forEach var="rental" items="${userClosedRentals}">
			<tr>
				<!-- Rental ID -->
				<td>#<c:out value="${rental.id}" /></td>
				
				<!-- Rental Description -->
				<c:set var="rentedDvd" value="${rental.dvd}" />
				<c:set var="userBorrower" value="${rental.user}" />
				<td>
					<!-- DVD ID -->
					<b>DVD ID</b>: #<c:out value="${rentedDvd.id}" />
					<br />
					
					<!-- DVD Title -->
					<b>DVD Title</b>: <c:out value="${rentedDvd.title}" />
					<br />
					
					<!-- DVD Description -->
					<b>Description</b>: <c:out value="${rentedDvd.description}" />
					<br />
					
					<!-- DVD Price -->
					<b>Price</b>: <c:out value="${rentedDvd.price}"/> <div class="PriceTag">euro/day</div>
					<br />
					
					<!-- Rental Quantity -->
					<b>Borrowed Quantity</b>: <c:out value="${rental.quantity}"/>
					<br />
					
					<!-- Rental Start Date -->
					<b>Start Date</b>: <c:out value="${rental.startDate}"/>
					<br />
					
					<!-- Rental Due Date -->
					<b>Due Date</b>: <c:out value="${rental.dueDate}"/>
					<br />
					
					<!-- Rental End Date -->
					<b>End Date</b>: <c:out value="${rental.endDate}"/>
					<br/>
					
				</td>
			</tr>
		</c:forEach>
		</table>
		<br />
	</c:when>
	<c:otherwise>
		<table class="LayoutTableStyle1">
			<tr>
				<td><img src="public/img/empty-folder-camera-big.png"/></td>
				<td>
					<h3>No Closed Rental Requests For This User</h3>
					<div class="InfoText">
						There are no closed rental requests at the moment registered for this user.
						<br />
						Please <b>refresh</b> this page in order to get the latest rental results update.
					</div>
				</td>
			</tr>
		</table>
	</c:otherwise>
	</c:choose>
	
	</div>
	</div>
	<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>
	
</div>

</body>
</html>
