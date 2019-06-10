<%-- 
    Document   : header
    Created on : 2019/4/4, 下午 05:46:39
    Author     : Sean
--%>

<%@page import="uuu.woh.entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--header.jsp-->
<!--header-wrapper-->
<%
    Customer member = (Customer) session.getAttribute("member");
%>
<header class="header-wrapper">
    <!--  logo -->
    <div class="logo-wrapper">
        <div class="logo">
            <img src="<%=request.getContextPath()%>/images/owl.png" style="padding:10px 15px;">
            <p style="margin:0px">Wise Owl Hostel</p>
        </div>
        <div class="logo-content">
            <ul>
                <li>09:00AM — 05:00PM</li>
                <li>WiseOwlHouse@gmail.com</li>
            </ul>
        </div>
    </div>
</header>
<!--  navigation -->
<nav class="navigator" >
    <ul class="function">
        <li class="nav-item"><a href="<%=request.getContextPath()%>/index.jsp">HOME</a></li>
        <li class="nav-item"><a href="<%=request.getContextPath()%>/rooms.jsp">ROOMS</a></li>
        <li class="nav-item"><a href="<%=request.getContextPath()%>/index.jsp#blog">BLOG</a></li>
        <li class="nav-item"><a href="<%=request.getContextPath()%>/index.jsp#contact">CONTACT</a></li>
        <li class="nav-item"><a href="<%=request.getContextPath()%>/booking.jsp">BOOKING</a></li>
    </ul>

    <ul class="member" style="position: relative">
        <% if (member == null) { %>
        <li class="nav-item"><a href="javascript:login()">SIGN IN</a></li>
        <li class="nav-item"><a href="javascript:register()">CREAT ACCOUNT</a></li>
            <%} else {%>
        <li class="nav-item"><a id="member-btn">${sessionScope.member.name} ${sessionScope.member.surname}</a></li>
         <!--<li class="nav-item"><a href="<%--=request.getContextPath()--%>/member/orders_history.jsp">My Bookings</a></li>
         <li class="nav-item"><a href="<%--=request.getContextPath()--%>/member/profile_update.jsp">Profile update</a></li>
         <li class="nav-item"><a href='<%--=request.getContextPath()--%>/logout.do'>SIGN OUT</a></li>-->
        <%}%>
        <!--for 會員管理-->
        <div id="membership">
            <ul class="member-function">
                <li class="nav-item" style="border-bottom: 1px gray solid"><a href="<%=request.getContextPath()%>/member/orders_history.jsp">My Bookings</a></li>
                <li class="nav-item" style="border-bottom: 1px gray solid"><a href="<%=request.getContextPath()%>/member/profile_update.jsp">Profile update</a></li>
                <li class="nav-item"><a href='<%=request.getContextPath()%>/logout.do' >SIGN OUT</a></li>
            </ul>
        </div>
    </ul>
</nav>

<div style="clear:both"></div>
<div id="loginBox"></div><!--for 燈箱效果-->
<div id="registerBox"></div><!--for 燈箱效果-->
<!--header.jsp end-->