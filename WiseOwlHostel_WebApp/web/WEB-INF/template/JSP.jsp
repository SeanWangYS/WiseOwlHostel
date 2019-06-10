<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>

<%@page contentType="text/html" pageEncoding="${encoding}"%>
${doctype}
<html>
    <head>
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">  
        <meta charset="${encoding}">
        <%@ include file="/WEB-INF/subviews/global.jsp" %>
    </head>
    
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp"/>
        <section class="container1">
            this is content
        </section>
        
        <%@ include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
