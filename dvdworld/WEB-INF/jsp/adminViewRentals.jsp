<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="org.springframework.security.core.AuthenticationException" %>


<!--
DVDWorld Administrator Page - View Rentals
-->


<html>
<head>
	<title>DVD World | View Rentals (Administrator)</title>
	<%@ include file="/WEB-INF/jsp/common/headIncludes.jsp" %>
</head>

<body>

<div class="BigContainerPane">
	<%@ include file="/WEB-INF/jsp/common/pageHeader.jsp" %>
	
	<div class="ContentPane" id="_MainContent">
	<div class="RealContentPane" id="_RealContentPane">
	
	<h3>View <i>DVD</i>World Rentals By Users</h3>
	
	<c:choose>
	<c:when test="${not empty users}">
		<table class="ViewDvdTableStyle">
		<tr>
			<th>ID</th>
			<th>Details</th>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<!-- User ID -->
				<td>#<c:out value="${user.id}" /></td>
				
				<!-- User Info and Rentals -->
				<td>
					<!-- User Name -->
					<c:out value="${user.name}" /> (<c:out value="${user.username}" />)
					<br /><br />
					<a href="adminViewUser.html?userid=<c:out value="${user.id}"/>"><img src="public/img/view-rentals.png" alt="View Rentals"/></a>
				</td>
			</tr>
		</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<table class="LayoutTableStyle1">
			<tr>
				<td><img src="public/img/empty-folder-camera-big.png"/></td>
				<td>
					<h3>No Rental Requests</h3>
					<div class="InfoText">
						There are no rental requests at the moment.
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
