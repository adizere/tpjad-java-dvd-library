<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="org.springframework.security.web.authentication.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.web.authentication.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>


<!--
DVDWorld User Shopping Cart Page.
Here, users can preview what they actually chose to rent.
-->


<html>
<head>
	<title>DVD World | My Shopping Cart</title>
	<%@ include file="/WEB-INF/jsp/common/headIncludes.jsp" %>
</head>

<body>

<div class="BigContainerPane">
	<%@ include file="/WEB-INF/jsp/common/pageHeader.jsp" %>
	
	<div class="ContentPane" id="_MainContent">
	<div class="RealContentPane" id="_RealContentPane">
	
	<h3>My Shopping Cart</h3>
	
	<c:choose>
	<c:when test="${not empty cartRentals}">
	<table class="ViewDvdTableStyle">
	<tr>
		<th>ID</th>
		<th>Title</th>
		<th>Details</th>
	</tr>
	
	<!-- Value used to compute total amount of cash the user has to pay a day to rent all these DVDs. -->
	<c:set var="costPerDay" value="0" scope="page"/>
	
	<!-- Do the loop. -->
	<c:forEach var="rental" items="${cartRentals}">
		<c:set var="myDvd" value="${rental.dvd}" />
		<tr>
			<!-- DVD ID -->
			<td>#<c:out value="${myDvd.id}" /></td>
			
			<!-- DVD Title -->
			<td><c:out value="${myDvd.title}" /></td>
			
			<!-- DVD Description -->
			<td>
				<b>Description</b>: <c:out value="${myDvd.description}" />
				<br />
				<b>Price</b>: <c:out value="${myDvd.price}" /> <div class="PriceTag">euro/day</div>
				<br /><br />
				<a href="processCart.html?id=<c:out value="${myDvd.id}"/>&operation=REMOVEFROMCART"><img src="public/img/cancel-circle-small.png"/> Remove</a>
			</td>
		</tr>
		<c:set var="costPerDay" value="${costPerDay + myDvd.price}" scope="page"/>
	</c:forEach>
	
	</table>
	<div class="OperationResult">
	Displaying a total of <c:out value="${fn:length(cartRentals)}" /> item(s).
	<br />
	The cost to rent <i>all</i> these selected DVDs from your shopping cart is <b><span id="_CostPerDay"><c:out value="${costPerDay}"/></span></b> euro/day.
	</div>
	
	<br />
	<br />
	<table>
		<tr>
			<td><img src="public/img/red-shopping-bag-full-big.png" /></td>
			<td>
				<div class="FormStyle">
				<form action="processCart.html?operation=CHECKOUT" onsubmit="return ValidateCheckOutForm(this, '_ComputeTotalAmountResult', '_DaysToRent')" method="post">
					<h3>Final Check Out</h3>
					Input <i>total amount of days</i> you wish to rent these DVDs.
					<br />
					Days:
					<input type="text" id="_DaysToRent" name="daysToRent" class="FormInputStyle" style="width:50px;" onfocus="ActivateFormInputBgColor(this,'FormStyeObjRollover')" onblur="DeactivateFormInputBgColor(this, 'FormStyeObjRollover')" />
					<input type="button" value="Compute Total Amount" onClick="ComputeTotalAmount('_ComputeTotalAmountResult', '_DaysToRent')" />
					<div id="_ComputeTotalAmountResult" class="InfoText"></div>
					<br /><br />
					<input type="submit" value="Check Out!"/>
				</form>
				</div>
			</td>
		</tr>
	</table>

	
	</c:when>
	<c:otherwise>
		<div class="InfoText">
		<img src="public/img/red-shopping-bag-big.png"/><br />
		There are no items currently in your shopping cart.<br /><br />
		Please check our DVD library and add DVDs to your shopping cart and then proceed to the check out section to complete renting operation.</div>
	</c:otherwise>
	</c:choose>
	
	</div>
	</div>
	
	<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>
	
</div>

</body>
</html>
