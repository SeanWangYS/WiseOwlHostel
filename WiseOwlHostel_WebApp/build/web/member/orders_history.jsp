<%-- 
    Document   : orders_history
    Created on : 2019/4/15, 上午 12:30:52
    Author     : Sean
--%>

<%@page import="uuu.woh.entity.Order"%>
<%@page import="uuu.woh.model.OrderService"%>
<%@page import="java.util.List"%>
<%@page import="uuu.woh.entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">  
        <meta charset="UTF-8">
        <%@ include file="/WEB-INF/subviews/global.jsp" %>

        <style>
            .content-wrapper{
                padding: 50px 0px 600px;
            }
            .content-wrapper .content-container{
                width: 900px;
                margin: auto;
                font-size: 16px;
                text-align: left;
            }
            table{
                
                margin: auto;
                border-collapse: separate;
                border-spacing: 8px;
            }
           
        </style>
    </head>

    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp"/>
        <!--page-title-wrapper-->
        <div class="page-title-wrapper" style="background-image: url(../images/Barcelona_Head_Ok.jpg); ">
            <div class="page-title-wrapper-container">
                <h2 class="page-title">MY BOOKING</h2>
            </div>
        </div>
        <!-- content wrapper -->
        <div class="content-wrapper">
            <div class="content-container">
                <%
                    Customer member = (Customer) session.getAttribute("member");
                    List<Order> list = null;

                    if (member != null) {
                        OrderService service = new OrderService();
                        list = service.searchOrderByCustomerEmail(member.getEmail());
                    }
                    if (list != null && list.size() > 0) {
                %>
                <table style="width:90%">
                    <thead>
                        <tr>
                            <th>Reservation Number</th><th>Order Time</th><th>Booking Time</th><th>Total Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Order order : list) {%>
                        <tr>
                            <td>
                                <a href="order.jsp?orderId=<%= order.getId()%>">
                                    <%= order.getFormatedId()%>
                                </a>
                            </td>
                            <td><%=order.getOrderDate()%></td>
                            <td><%= order.getStartDate()%> ~ <%= order.getEndDate()%></td>
                            <td><%=order.getTotalAmount()%>  €</td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <%} else {%>
                <p>No reservation record in your profile.</p>
                <%}%>
            </div>
        </div>

        <%@ include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
