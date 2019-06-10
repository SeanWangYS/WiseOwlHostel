<%-- 
    Document   : rooms
    Created on : 2019/3/20, 下午 02:18:12
    Author     : Sean
--%>

<%@page import="uuu.woh.entity.Room"%>
<%@page import="java.util.List"%>
<%@page import="uuu.woh.model.RoomService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Rooms Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include file="/WEB-INF/subviews/global.jsp" %>
        <style>
            /*=====================content=========================*/
            .content-wrapper{
                width:80%;
                min-width: 1000px;
                margin: auto;

            }
            .content-wrapper .content-section{
                width:40%;
                /*background: orange;*/ 
                padding: 20px 50px;
                float: left;
            }
            .content-wrapper .content-section .title-item{
                padding: 10px 10px 10px 0px;
            }
            .content-wrapper .content-section .content-item .room-info{
                margin: 10px 0px;
                text-align: center; 
                font-size: 0.8em
            }
            .content-wrapper .content-section .content-item .booking-button{
                padding: 0px 10px 10px 0px;
                background: hsl(208, 68%, 21%);
                width: 100px;
                height: 20px;
                margin: auto;
                text-align: center;  
                font-size: 0.8em
            }
            .content-wrapper .content-section .content-item a{
                text-decoration: none;
                color: white;
                position:relative;
                top:6px;
            }
            .content-wrapper .content-section .content-item a:link{color: white;}
            .content-wrapper .content-section .content-item a:visited{color: white;}
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp"/>
        <!--page-title-wrapper-->
        <div class="page-title-wrapper" style="background-image: url(images/Barcelona_Head_Ok.jpg); ">
            <div class="page-title-wrapper-container">
                <h2 class="page-title">ROOMS</h2>
            </div>
        </div>
        <!-- content wrapper -->
        <%
            RoomService service = new RoomService();
            List<Room> list = service.getAllRooms();
        %>
        <div class="content-wrapper">
            <% if(list!= null && list.size()>0){ %>
            <% for (int i = 0; i < list.size(); i++){ 
                Room r = list.get(i);
            %>
            <section class="content-section">
                <div class="title-item">
                    <h3 style="margin:0px;"><%= r.getNameTitle() %></h3>
                </div>
                <div class="image-frame">
                    <img src="<%= r.getPhotoUrl() %>"  style="width:100%;height:100%"/>
                </div>
                <div class="content-item">
                    <div class="room-info">
                        <span class="head"><em>Bathroom:</em></span>
                        <strong><span class="tail"><%= r.getRoomType() %> | </span></strong>
                        <span class="head"><em>Max:</em> <strong><%= r.getMax() %> | </strong></span>
                        <span class="head"><em>Balcony:</em> </span>
                        <strong><span class="tail"><%= r.getBalcony() %></span></strong>
                    </div>
                    <div style="text-align: center; font-size: 0.8em">
                        <p><strong>PRICE: <%= r.getUnitPrice() %> €</strong></p>
                    </div>
                    <div class="booking-button">
                        <a href="booking.jsp" ><strong>BOOK</strong></a>
                    </div>
                </div>
            </section>
            <%}%>
            <%}%>


            <div style="clear:both; height:70px"></div>
        </div>        
        <%@ include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
