<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--

This is the Welcome Page that the user will see
each time he visits the website.

-->

<html>
<head>
	<title>DVD World | Your Ultimate DVD Library</title>
	<%@ include file="/WEB-INF/jsp/common/headIncludes.jsp" %>
</head>

<body>

<div class="BigContainerPane">
	<%@ include file="/WEB-INF/jsp/common/pageHeader.jsp" %>
	
	<div class="ContentPane" id="_MainContent">
	<div class="RealContentPane" id="_RealContentPane">
	
	<br/>

	<h3>Welcome to <i>DVD</i><b>World</b>!</h3>

	Welcome to <i>DVD</i><b>World</b>, the place were you can find a large variety of DVD collections for all tastes and pockets.<br />
	Be sure to register with us and log into the browsing section to have a glimpse of our DVDs that you can rent for really low prices.
	<br />
	<c:if test="${pageContext.request.userPrincipal == null}">
		<br />
		<h3><a href="login.html">Log in now!</a></h3>
	</c:if>
	<br />

	<br /><br />
	
	</div>
	</div>
	
	<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>
</div>

	Debug Data:
	<br />
	Your principal object is....: <%= request.getUserPrincipal() %>
	<br />
	<sec:authorize url='/secure/index.jsp'>You can currently access "/secure" URLs.</sec:authorize>
	<br />
	<sec:authorize url='/secure/extreme/index.jsp'>You can currently access "/secure/extreme" URLs.</sec:authorize>
	<br />
	<a href="secure/index.jsp">Secure page</a>
	<br />
	<a href="secure/extreme/index.jsp">Extremely secure page</a>
	
</body>
</html>
