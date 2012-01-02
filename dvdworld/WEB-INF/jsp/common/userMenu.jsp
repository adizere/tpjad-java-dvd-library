<%@ page contentType="text/html" language="java" %>

<%--
//
//	Abstract:
//	
//		Main application user menu.
//		Here we're displaying the main user menu and user
//		session information such as	the login information
//		string (e.g. "Welcome, john!").
//
--%>

<div class="TopMenuPane">
    <ul id="MainMenu">
        <li><a href="viewDvds.html"
            onmouseover="MenuOpen('ViewDVDs')" 
            onmouseout="MenuCloseTime()">View DVDs</a>
            <div id="ViewDVDs"
                onmouseover="MenuCancelCloseTime()" 
                onmouseout="MenuCloseTime()">
            </div>
        </li>
        <li><a href="about.html"
            onmouseover="MenuOpen('About')"
            onmouseout="MenuCloseTime()">About</a>
            <div id="About"
                onmouseover="MenuCancelCloseTime()"
                onmouseout="MenuCloseTime()">
            </div>
        </li>
    </ul>
    
    <div id="_WelcomeUser">
		Welcome, <%= request.getUserPrincipal().getName() %> (<%= request.getUserPrincipal().getName() %>) !
		<br \>
		<a href="j_spring_security_logout">Logout</a>
    </div>
</div>