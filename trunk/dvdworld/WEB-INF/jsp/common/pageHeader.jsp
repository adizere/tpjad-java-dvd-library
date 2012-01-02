<div class="TopSpacing"></div>
<div class="HeaderPane"><a href="index.jsp"><img src="public/img/dvdworldlogo-small.jpg" /></a></div>
<div id="_MenuContent">
<%

// If the user is logged in, display the menu and the account information.

if (request.getUserPrincipal() != null)
{
%>
<%@ include file="/WEB-INF/jsp/common/userMenu.jsp" %>
<%
}
 %>

</div>