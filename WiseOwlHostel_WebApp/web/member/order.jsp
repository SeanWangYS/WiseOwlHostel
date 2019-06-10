
<%-- 
    Document   : order
    Created on : 2019/4/13, 下午 01:36:54
    Author     : Sean
--%>

<%@page import="uuu.woh.entity.OrderItem"%>
<%@page import="uuu.woh.model.OrderService"%>
<%@page import="uuu.woh.entity.Order"%>
<%@page import="uuu.woh.entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Order</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">  
        <meta charset="UTF-8">
        <%@ include file="/WEB-INF/subviews/global.jsp" %>

        <style>
            .content-wrapper{
                padding: 5px 0px 300px;
            }
            .content-wrapper .content-container{
                width: 900px;
                margin: auto;
                font-size: 16px;
                text-align: left;

            }
            .reservation-info1{
                margin: 5px 0px;
                font-weight:bold;
            }
            .reservation-info2{
                padding: 5px 0px;
                font-weight:bold;
                padding-bottom: 15px;
            }
            .reservation-info3{
                padding: 5px 70px;
            }
            .reservation-info3 section{
                display:flex;
                flex-direction:row; 
                align-items:center;
                justify-content:space-between;
            }
            .reservation-info4{
                padding-top: 15px;
                font-weight:bold;
                width: 280px;
                float: right;
            }
        </style>
    </head>

    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp"/>
        <!--page-title-wrapper-->
        <div class="page-title-wrapper" style="background-image: url(../images/Barcelona_Head_Ok.jpg); ">
            <div class="page-title-wrapper-container">
                <h2 class="page-title">RESERVATION</h2>
            </div>
        </div>
        <!-- content wrapper -->
        <%
            Customer member = (Customer) session.getAttribute("member");
            Order order = (Order) request.getAttribute("order"); //CheckOutServlet 在把order存入SQL後  也把order存到request中在內部轉交到此page

            String orderId = request.getParameter("orderId"); //處理從歷史清單網頁連過來的時候
            if (order == null && orderId != null) {
                OrderService service = new OrderService();
                order = service.searchOredrByOrderId(orderId);
                if (order != null && member != null && !member.equals(order.getMember())) {
                    
                }else{order = null;}
            }
        %>
        <div class="content-wrapper">
            <%  if (order != null) {%>
            <div class="content-container">

                <div class="reservation-info1">
                    <div><span style="padding-right:30px">Reservation Number: <%= order.getFormatedId()%></span>

                        <span>Order Time: <%= order.getOrderDate()%> 
                            <%  
                                String orderTime = order.getOrderTime().toString();
                                String[] tokens = orderTime.split(":");
                                out.print(tokens[0]+":"+tokens[1]);
                            %></span></div>
                </div>
                <div class="reservation-info2">
                    <div><span>Booking Period: <%= order.getStartDate()%> ~ <%= order.getEndDate()%></span></div>
                </div>

                <div class="reservation-info3">
                    <% for (OrderItem item : order.getOrderItemSet()) {%>
                    <section>
                        <div class="room-image">
                            <img src="../<%= item.getRoom().getPhotoUrl()%>"  style="width:110px;height:85px"  alt=""/>
                        </div>
                        <div class="room-info-wrapper">
                            <div class="room-name"><%= item.getRoom().getName()%></div>
                            <div class="room-number"><% if (item.getRoom().getId() < 7) {
                                    out.print("Number of Rooms");
                                } else {
                                    out.print("Number of Beds");
                                }%>: <%= item.getNumber()%></div>
                            <div class="room-price">Unit Price: <%= item.getPrice()%> €</div>
                        </div>
                    </section>
                    <hr noshade="" size="1">
                    <%}%>
                </div >
                <div class="reservation-info4">
                    <div class="total-price">Total Price: <%= order.getTotalAmount()%>  €</div>
                    <div>Payment Type: <%= order.getPaymentType().getDescription()%></div>
                    <div>Estimated time of arrival: <%= order.getCustomerBookingEstimatedArrival()%></div>
                </div>
            </div>
            <%} else {%>
            <p>The order doesn't exist</p>
            <%}%>    
        </div>

        <%@ include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
