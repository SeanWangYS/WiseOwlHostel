<%-- 
    Document   : member_function
    Created on : 2019/4/4, 下午 10:18:46
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Member Function Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">  
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="style/woh.css">

        <style>
            
            .container h4 a{text-decoration: none;}
            .container h4 a:link{color:navy;}
            .container h4 a:visited{color: navy;}
            .container h4 a:hover{
                color: blue;
                text-decoration: none;
            }
        </style>
    </head>

    <body>
        
        <div class="container">
            <h4><a href="<%=request.getContextPath()%>/member/orders_history.jsp">My Bookings</a></h4>
            <h4><a href="<%=request.getContextPath()%>/member/update.jsp">Profile update</a></h4>
            <h4><a href='<%=request.getContextPath()%>/logout.do'>SIGN OUT</a></h4>  
        </div>
    </body>
</html>
