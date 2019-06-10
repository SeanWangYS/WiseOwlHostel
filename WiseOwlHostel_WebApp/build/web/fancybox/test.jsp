<%-- 
    Document   : test
    Created on : 2018/8/30, 下午 04:10:03
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include file="/WEB-INF/subviews/global.jsp" %>
        <link href="<%=request.getContextPath()%>/fancybox/jquery.fancybox.css" rel="stylesheet" type="text/css"/>
        <script src="<%=request.getContextPath()%>/fancybox/jquery.fancybox.js"></script>
        <script>
            function openHandler(){
                $.fancybox.open({
                    src  : '#dialog'                    
                })
            }
            
        </script>
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp" />
        <div class="article">
            <h1>Hello FancyBox!</h1>
            <input type='button' onclick="openHandler()" value="open fancybox">
        </div>
        <div id="dialog">
            Hello
        </div>
        <%@include file="/WEB-INF/subviews/footer.jsp"%>
    </body>
</html>
