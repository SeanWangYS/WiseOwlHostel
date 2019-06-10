<%-- 
    Document   : login
    Created on : 2019/4/4, 下午 07:19:13
    Author     : Sean
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sign In Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">  
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="style/woh.css">
        <script>
            function refreshCaptcha() {
                var image = document.getElementById("captchaImage");
                image.src = "images/captcha.jepg?refresh=" + new Date();
            }
        </script>
        <style>
            .container{
                text-align: left;
            }
            .container .h1{
                margin: 0px;
                padding: 20px 70px;
                font-size: 32px;
            }
            .container .container-form{
                padding: 0px 100px 20px 100px;
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
            <h1 class="h1">Sign In</h1>
            <%
            Cookie[] cookies = request.getCookies();
            String email = "";
            String auto = "";
            if(cookies!=null && cookies.length>0){
                for(int i=0; i<cookies.length; i++){
                    Cookie cookie = cookies[i];
                    if(cookie.getName().equals("email")){
                        email = cookie.getValue();
                    }
                    if(cookie.getName().equals("auto")){
                            auto = cookie.getValue();
                    }
                }
            }
            %>
            
            <%
                List<String> errors = (List<String>)request.getAttribute("errors");
                   if(errors!=null && errors.size()>0)out.println(errors);
            %>
            <form class="container-form" method="POST" action="login.do">
                <h3 class="h3">Email</h3>
                <p>
                    <input class="input" type="text" name="email" 
                           pattern="^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$"
                           required value="<%= request.getMethod().equals("GET")? email: request.getParameter("email") %>">
                    <input type="checkbox" name="auto" value="ON" <%=auto%> > remember my account
                </p>
                <h3 class="h3">Password</h3>
                <p>
                    <input class="input" type="password"  name="pwd" minlength="6" maxlength="20" required>
                </p>
                <h3 class="h3">Captcha</h3>
                <p>
                    <input class="input" type="text"  name="captcha" required 
                           value='<%= request.getMethod().equals("GET") ? "" : request.getParameter("captcha")%>' autocomplete="off">
                    <a href="javascript:refreshCaptcha()">
                        <img id="captchaImage" src="images/captcha.jepg" style="vertical-align: middle">
                    </a>
                </p>
                <input type="hidden" name="referedURI" value="<%= request.getParameter("referedURI") %>">
                <input  class="input" type="submit" value="Sign in"/>
            </form>
        </div>
    </body>
</html>
