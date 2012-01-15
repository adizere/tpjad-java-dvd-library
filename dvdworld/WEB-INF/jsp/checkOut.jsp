<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!--
DVDWorld Check Out Page
-->


<html>
<head>
	<title>DVD World | Check Out</title>
	<%@ include file="/WEB-INF/jsp/common/headIncludes.jsp" %>
</head>

<body>

<div class="BigContainerPane">
	<%@ include file="/WEB-INF/jsp/common/pageHeader.jsp" %>
	
	<div class="ContentPane" id="_MainContent">
	<div class="RealContentPane" id="_RealContentPane">
	
	<h3>Check Out Shopping Cart</h3>
	
	<c:choose>
	<c:when test="${not empty param.success}">
		<table class="LayoutTableStyle1">
			<tr>
				<td><img src="public/img/dvd-ok-big.png"/></td>
				<td>
					<h3>Yey, Check Out was Successful!</h3>
					<div class="InfoText">
						Your DVD list request to rent the selected items has been successfully committed.
						You are now kindly requested to proceed to the check out counter in order to obtain your requested items and perform payment.
						<br /><br />
						Thank you for choosing our DVD rental services.
						<br />
						See you 'till next time!
					</div>
				</td>
			</tr>
		</table>
	</c:when>
	<c:otherwise>
		<table class="LayoutTableStyle1">
			<tr>
				<td><img src="public/img/white-shopping-bag-medium.png"/></td>
				<td>
					<h3>Aaooo... Check Out Failed!</h3>
					<div class="InfoText">
						An internal problem led to the interruption of the check out process.
						<br />
						We greatly apologize for this major inconvenience.
						<br /><br />
						Please contact your local administration specialist in order to solve this issue.
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
