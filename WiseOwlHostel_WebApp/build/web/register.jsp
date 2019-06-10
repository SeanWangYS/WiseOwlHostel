<%-- 
    Document   : register
    Created on : 2019/4/5, 上午 11:00:54
    Author     : Sean
--%>

<%@page import="java.util.List"%>
<%@page import="uuu.woh.entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sign Up Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">  
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="style/woh.css">
        <script>
            function refreshCaptcha() {
                var image = document.getElementById("captchaImage");
                image.src = "images/register_captcha.jepg?refresh=" + new Date();
            }
        </script>
        <style>
            .container .h1{
                margin: 0px;
                padding: 0px 70px;
                font-size: 32px;
            }
            .container .container-form{
                padding: 0px 100px 50px 100px;
            }
            .container .container-form .h3{
                font-size: 19px;
                text-align: left;
                padding: 5px 0;
            }
            .container .container-form .input{
                font-size: 19px;
            }
        </style>
    </head>

    <body>
        <div class="container">
            <% 
                List<String> errors  = (List<String>)request.getAttribute("errors");
                if(errors!=null && errors.size()>0)out.print(errors);
            %>
            <h1 class="h1">Sign Up</h1>
            <form class="container-form" action='register.do' method="POST">
                <h3 class="h3">Email*</h3>
                <p>
                    <input class="input" type="text" name="email" 
                           pattern="^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$" required 
                           value="<%= request.getMethod().equals("POST") ? request.getParameter("email") : ""%>">
                </p>
                <h3 class="h3">Password*</h3>
                <p>
                    <input class="input" type="password"  name="pwd1" minlength="6" maxlength="20" required
                           value="<%= request.getMethod().equals("POST") ? request.getParameter("pwd1") : ""%>" >
                </p>
                <h3 class="h3">Enter Password Again*</h3>
                <p>
                    <input class="input" type="password"  name="pwd2" minlength="6" maxlength="20" required>
                </p>
                <h3 class="h3">Surname*</h3>
                <p>
                    <input class="input" type="text"  name="surname" required
                           value="<%= request.getMethod().equals("POST") ? request.getParameter("surname") : ""%>">
                </p>
                <h3 class="h3">Name*</h3>
                <p>
                    <input class="input" type="text"  name="name" required
                           value="<%= request.getMethod().equals("POST") ? request.getParameter("name") : ""%>">
                </p>
                <h3 class="h3">Phone*</h3>
                <p>
                    <input class="input" type="text"  name="phone" required
                           value="<%= request.getMethod().equals("POST") ? request.getParameter("phone") : ""%>">
                </p>
                <h3 class="h3">Gender*</h3>
                <p>
                    <input type="radio" name="gender" id="male" value="<%= Customer.MALE%>" required <%= String.valueOf(Customer.MALE).equals(request.getParameter("gender")) ? "checked" : ""%>
                    <lable>Male</lable>
                    <input type="radio" name="gender" id="female" value="<%= Customer.FEMALE%>" required <%= String.valueOf(Customer.FEMALE).equals(request.getParameter("gender")) ? "checked" : ""%>
                    <lable>Female</lable>
                </p>
                <h3 class="h3">Captcha*</h3>
                <p>
                    <input class="input" type="text"  name="captcha" required  autocomplete="off">
                    <a href="javascript:refreshCaptcha()">
                        <img id="captchaImage" src="images/register_captcha.jepg" style="vertical-align: middle">
                    </a>
                </p>
                <input class="input" type="submit" value="Sign up"/>
            </form>
        </div>
    </body>
</html>
