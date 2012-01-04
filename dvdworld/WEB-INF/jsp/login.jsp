<%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="org.springframework.security.web.authentication.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.web.authentication.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>


<!--
Custom DVDWorld Login Page.
This page overrides the default and boring spring security login page.
Keep in mind that you must use the j_username and j_password form names
for the username and password, since we are not using our own controller
for validating the form and authentication.

The logout handler is the default spring handler: j_spring_security_logout.
If you want to change this, be sure to change all the links in the website as well.
-->


<html>
<head>
	<title>DVD World | Login</title>
	<%@ include file="/WEB-INF/jsp/common/headIncludes.jsp" %>
</head>

<body>

<div class="BigContainerPane">
	<%@ include file="/WEB-INF/jsp/common/pageHeader.jsp" %>
	
	<div class="ContentPane" id="_MainContent">
	<div class="RealContentPane" id="_RealContentPane">
	
	<br/>

	<div class="FormStyle">
	<form action="<c:url value="/loginProcess" />" method="post">
        <p>
        	<h3>Login to <i>DVD</i><b>World</b>!</h3>
        </p>
        
        <p>
			<label for="_Username">Username:</label>
			<spring:bind path="credentials.username">
			<input type="text" <c:if test="${not empty param.login_error}">value="<%= session.getAttribute(AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY) %>"</c:if> name="j_username" id="_Username" class="FormInputStyle" onfocus="ActivateFormInputBgColor(this,'FormStyeObjRollover')" onblur="DeactivateFormInputBgColor(this, 'FormStyeObjRollover')" />
			</spring:bind>
		</p>
		<p>
		<spring:hasBindErrors name="credentials">
		<div class="Legend">
			<core:out value="${status.errorMessage}"/>
		</div>
		</spring:hasBindErrors>
        </p>
		<p>
			<label for="_Password">Password:</label>
			<spring:bind path="credentials.password">
			<input type="password" name="j_password" id="_Password" class="FormInputStyle" onfocus="ActivateFormInputBgColor(this,'FormStyeObjRollover')" onblur="DeactivateFormInputBgColor(this, 'FormStyeObjRollover')" />
			</spring:bind>
		</p>
		<p>
		<spring:hasBindErrors name="credentials">
		<div class="Legend">
			<core:out value="${status.errorMessage}"/>
		</div>
		</spring:hasBindErrors>
        </p>
		<p>
			<label for="_Submit">&nbsp;</label>
			<input type="submit" name="submit" value="Log in" id="_Submit" />
		</p>
		<div class="Legend">
			<c:if test="${not empty param.login_error}">
				<div class="errors">
					<fmt:message key="login.msg.failure"/><br />
					<fmt:message key="login.failure.reason"/>: <%= ((AuthenticationException) session.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY)).getMessage() %>
				</div>
			</c:if>
		</div>
	</form>
	</div>
	
	</div>
	</div>
	
	<%@ include file="/WEB-INF/jsp/common/pageFooter.jsp" %>
	
</div>

</body>
</html>
