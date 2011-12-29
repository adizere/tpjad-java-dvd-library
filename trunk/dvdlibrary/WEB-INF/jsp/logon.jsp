<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
<head>
	<title>Welcome to the DVD Library!</title>
	<link rel="stylesheet" href="style.css" type="text/css" />
	<script language="javascript" src="animation.js"></script>
</head>

<body>
<div class="BigContainerPane">
    <div class="TopSpacing">
    </div>
	<div class="HeaderPane">DVD Library</div>
    <div id="_MenuContent"></div>
	<div class="ContentPane" id="_MainContent">
	<br/>

	<div class="FormStyle">
	<form method="post">
        <p><h3>Welcome!</h3></p><br />
        <p>
			<label for="_Username">Username:</label>
			<spring:bind path="credentials.username">
			<input type="text" value="<core:out value="${status.value}"/>" name="username" id="_Username" class="FormInputStyle" onfocus="ActivateFormInputBgColor(this,'FormStyeObjRollover')" onblur="DeactivateFormInputBgColor(this, 'FormStyeObjRollover')" />
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
			<input type="password" name="password" id="_Password" class="FormInputStyle" onfocus="ActivateFormInputBgColor(this,'FormStyeObjRollover')" onblur="DeactivateFormInputBgColor(this, 'FormStyeObjRollover')" />
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
			<input type="submit" value="Logon" id="_Submit" />
		</p>
	</form>
	</div>
	</div>
</div>

</body>
</html>