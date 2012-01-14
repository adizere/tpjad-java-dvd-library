<%@ page contentType="text/html" language="java" %>
<%@ page import="javax.servlet.ServletContext" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.dvdworld.model.User" %>
<%@ page import="com.dvdworld.services.DvdWorldDao" %>

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
        <li><a href="index.jsp"
            onmouseover="MenuOpen('Menu_Home')" 
            onmouseout="MenuCloseTime()"><img src="public/img/metro-back-small.png"/> Home</a>
            <div id="Menu_Home"
                onmouseover="MenuCancelCloseTime()" 
                onmouseout="MenuCloseTime()">
            </div>
        </li>
        <li><a href="viewDvds.html"
            onmouseover="MenuOpen('Menu_ViewDVDs')" 
            onmouseout="MenuCloseTime()"><img src="public/img/blue-dvd-case-small.png"/> View DVDs</a>
            <div id="Menu_ViewDVDs"
                onmouseover="MenuCancelCloseTime()" 
                onmouseout="MenuCloseTime()">
            </div>
        </li>
        <li><a href="myShoppingCart.html"
            onmouseover="MenuOpen('Menu_MyShoppingCart')" 
            onmouseout="MenuCloseTime()"><img src="public/img/red-shopping-bag-small.png"/> My Shopping Cart</a>
            <div id="Menu_MyShoppingCart"
                onmouseover="MenuCancelCloseTime()" 
                onmouseout="MenuCloseTime()">
            </div>
        </li>
        <li><a href="about.html"
            onmouseover="MenuOpen('Menu_About')"
            onmouseout="MenuCloseTime()"><img src="public/img/info-small.png"/> About</a>
            <div id="Menu_About"
                onmouseover="MenuCancelCloseTime()"
                onmouseout="MenuCloseTime()">
            </div>
        </li>
    </ul>
    
    <div id="_WelcomeUser">
		Welcome,
		<%
			ServletContext servletContext = this.getServletContext();
			WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
			DvdWorldDao dvdWorldDao = (DvdWorldDao)wac.getBean("dvdWorldDao");
			User user = dvdWorldDao.getUser(request.getUserPrincipal().getName());
			if (user != null) {
				out.println(user.getName());
			} else {
				out.println(request.getUserPrincipal().getName());
			}
		%>
		(<%= request.getUserPrincipal().getName() %>) !
		<br />
		<a href="j_spring_security_logout">Logout</a>
    </div>
</div>