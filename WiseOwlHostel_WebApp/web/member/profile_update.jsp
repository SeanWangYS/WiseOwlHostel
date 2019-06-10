<%-- 
    Document   : profile_update
    Created on : 2019/4/7, 下午 09:50:56
    Author     : Sean
--%>

<%@page import="uuu.woh.entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">  
        <meta charset="UTF-8">
        <%@ include file="/WEB-INF/subviews/global.jsp" %>
        <script>
            function refreshCaptcha() {
                var image = document.getElementById("captchaImage");
                image.src = "<%= request.getContextPath()%>/images/register_captcha.jepg?refresh=" + new Date();
            }

            function changePwdHandler(theCheckBox) {
                if ($(theCheckBox).prop("checked")) {
                    $("#changePwdFieldSet").removeClass("disabled");
                    $("#changePwdFieldSet>input").prop("disabled", false);
                } else {
                    $("#changePwdFieldSet").addClass("disabled");
                    $("#changePwdFieldSet>input").prop("disabled", true);
                    $("#changePwdFieldSet>input").val("");
                }
            }
            $(function () {
                changePwdHandler(${"changePwd"});
            });
        </script>
        <style>

            /*=====================content=========================*/
            body{margin: 0px;}
            .content-wrapper{
                margin: auto;
                
                width:100%;
                min-width: 1000px;
                margin: auto;
                /*                font-size: 8em;
                                text-align: center;*/
            }
            .content-wrapper .container-form{
                width:340px;
                margin: auto;
                padding-bottom: 50px;
            }

        </style>
    </head>

    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp"/>
        <!--page-title-wrapper-->
        <div class="page-title-wrapper" style="background-image: url(../images/Barcelona_Head_Ok.jpg); ">
            <div class="page-title-wrapper-container">
                <h2 class="page-title">PROFILE UPDATE</h2>
            </div>
        </div>
        <!-- content wrapper -->
        <div class="content-wrapper">
            <%
                Customer member = (Customer) session.getAttribute("member");
            %>

            <%
                if (member != null) {
            %>
            <form class="container-form" action='update.do' method="POST">  <!-- UpdateServlet: /member/update.do (因為action內輸入的不是網址，只是Servlet名稱，但是request內含的網址有/member/，所以去改servlet的路徑來配合) -->  
                <h3>Email</h3>
                <p>
                    <input type="text" name="email" readonly value="${sessionScope.member.email}">
                </p>
                <h3>Password</h3>
                <p>
                    <input type="password"  name="pwd" minlength="6" maxlength="20" 
                           placeholder="If you want to change your password，please type in your old password for confirming">
                </p>
                <fieldset style='margin-left: -1ex;width:28ex' class='disabled' id="changePwdFieldSet">
                    <legend><input type="checkbox" name="changePwd" id="changePwd" onchange="changePwdHandler(this)" ${empty param.changePwd?"":"checked"}>
                        <label for='changePwd'>Change My Password</label></legend>
                    <label>new password</label>
                    <input type='password' name='pwd1'  minlength="6" maxlength="20" style='width:27ex'><br>
                    <label>Enter Password Again</label>
                    <input type='password' name='pwd2'  minlength="6" maxlength="20" style='width:27ex' disabled>
                </fieldset>
                <h3>Surname</h3>
                <p>
                    <input type="text"  name="surname" required
                           value="${empty param.surname? sessionScope.member.surname : param.surname}">
                </p>
                <h3>Name</h3>
                <p>
                    <input type="text"  name="name" required 
                           value="${empty param.name? sessionScope.member.name : param.name}">
                </p>
                <h3>Phone</h3>
                <p>
                    <input type="text"  name="phone" required
                           value="${empty param.phone? sessionScope.member.phone : param.phone}">
                </p>
                <h3>Gender</h3>
                <p>
                    <input type="radio" name="gender" id="male" value="<%= Customer.MALE%>" required 
                           <%--= request.getParameter("gender") == null && Customer.MALE.equals(member.getGender()) || request.getParameter("gender")!= null && Customer.MALE.equals(request.getParameter("gender").charAt(0)) ? "checked" : ""--%> 
                        ${empty param.gender && member.gender==Customer.MALE || not empty param.gender && param.gender.charAt(0)==Customer.MALE?"checked":""}/>
                <lable>Male</lable>
                <input type="radio" name="gender" id="female" value="<%= Customer.FEMALE%>" required 
                       ${empty param.gender && member.gender==Customer.FEMALE|| not empty param.gender && param.gender.charAt(0)==Customer.FEMALE?"checked":""} />
                <lable>Female</lable>
                </p>
                <h3>Captcha</h3>
                <p>
                    <input type="text"  name="captcha" required  autocomplete="off">
                    <a href="javascript:refreshCaptcha()">
                        <img id="captchaImage" src="<%= request.getContextPath()%>/images/register_captcha.jepg" style="vertical-align: middle">
                    </a>
                </p>
                <input type="submit" value="Submit"/>
            </form>
            <%} else {%>
            <p class="container-form">Please sign up for updating personal profile</p>
            <%}%>
        </div>
        <%@ include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
