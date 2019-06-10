<%-- 
    Document   : booking.jsp
    Created on : 2019/4/1, 上午 11:34:21
    Author     : Admin
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="uuu.woh.entity.Customer"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="uuu.woh.entity.Room"%>
<%@page import="java.util.List"%>
<%@page import="uuu.woh.model.RoomService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Booking Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include file="/WEB-INF/subviews/global.jsp" %>
        <%
            Customer member = (Customer) session.getAttribute("member");
        %>
        <script>
            $(document).ready(init);
            function init() {
                $(".select").change(checkHandler);
            }
            function checkHandler() {
                var selected = $(this).children('option:selected').val();
                //alert(selected);
                if (selected != '') {
                    $("#disabled").removeClass("disabled");
                    $("#disabled").prop("disabled", false);
                } else {
                    $("#disabled").addClass("disabled");
                    $("#disabled").prop("disabled", true);
                }
            }

            function checkLogin() {
                if (<%= member == null%>) {
                    alert("Sign in, please!")
                    return false;
                }
            }

//            function submitForm(){
//                alert("test!")
////               document.searchForm.submit();
//            }
        </script>
        <style>


            /*=====================content=========================*/

            .content-wrapper{
                width: 100%;
                min-width: 1000px;
                padding-bottom: 50px;
            }

            .content-wrapper .main-content .search-rooms form p label{
                padding:0px 10px;
            }
            .content-wrapper .main-content .search-rooms form p .btn{
                cursor: pointer;
                margin: 0px 10px;
                padding: 6px 12px;
                font-size: 14px;
                color: #fff;
                background-color: #337ab7;
                border: 1px solid;
                border-color: #2e6da4;
                border-radius: 5px;
                font-weight: 400;
                text-align: center;
                vertical-align: middle;
            }

            .content-wrapper .main-content .search-rooms form p .input-date,.input-type {
                padding: 5px 0px;
                color: #555;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
                font-size: 14px;
                text-align: center;
                vertical-align: middle;
            }

            .content-wrapper .main-content .search-rooms form p .input-type {
                padding: 7px 5px;
                width: 148px;
            }

            .content-wrapper .main-content, .sub-content{
                width:1050px;;
                min-width: 1000px;
                margin: auto;
            }

            .content-wrapper .sub-content .content-form{
                display: flex;
            }
            .content-wrapper .sub-content .content-form .order-info .btn{
                cursor: pointer;
                width: 100% ;
                padding: 15px 16px;
                font-size: 18px;
                color:#ffffff;
                background-color: #5cb85c;
                border: 1px solid;
                border-color: #4cae4c;
                /*box-shadow: none;*/
                /*display: inline-block;*/
                border-radius: 6px;
                font-weight: 400;
                text-align: center;
                vertical-align: middle;
            }
            .content-wrapper .sub-content .content-form .order-info .alert{
                width:200px ;
                color: #8a6d3b;
                background-color: #fcf8e3;
                border-color: #faebcc;
                text-align: center;
                padding: 15px;
                margin: 20px 0px;
                border: 1px solid transparent;
                border-radius: 4px;
            }

            .content-wrapper .sub-content .content-form .order-info{
                padding: 0px 30px;
            }
            .content-wrapper .sub-content .content-form .showroom section .room-info-wrapper{
                display: flex;

            }
            .content-wrapper .sub-content .content-form .showroom section .room-name{
                color: #344e86;
                font-size: 16px;
                font-weight:bold;
                margin: 20px 0px 10px;
                padding: 5px;
            }

            .content-wrapper .sub-content .content-form .showroom section .room-info-wrapper .room-image,.room-content,.room-description{
                padding:0px 15px;
                font-size:14px;
                width: 25%;
            }
            .content-wrapper .sub-content .content-form .showroom section .room-info-wrapper .room-description{
                width: 50%;
            }
            .content-wrapper .sub-content .content-form .showroom section .room-info-wrapper .room-content{
                text-align: center;
                margin:auto;
            }
            .content-wrapper .sub-content .content-form .showroom section .room-info-wrapper .room-content select{
                padding: 6px 12px;
                color: #555;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
                /*                transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;*/
            }
            .content-wrapper .sub-content .content-form .order-info .disabled{
                color:gray;
                opacity: 0.57;
            } 
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp"/>
        <!--page-title-wrapper-->
        <div class="page-title-wrapper" style="background-image: url(images/Barcelona_Head_Ok.jpg); ">
            <div class="page-title-wrapper-container">
                <h2 class="page-title">BOOKING</h2>
            </div>
        </div>
        <!-- content wrapper -->
        <%
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String roomType = request.getParameter("roomType");
        %>
        <div class="content-wrapper">
            <div class="main-content">
                <h2 style="color:red">${sessionScope.alert}</h2>
                <% session.removeAttribute("alert");%>
                <div class="search-rooms">
                    <form id="searchForm" method="GET">
                        <p style="padding:10px 0px">
                            <label >From:</label>
                            <input class="input-date" type='date' name='startDate' required value="<%= startDate != null ? startDate : null%>" > 

                            <label>To:</label>
                            <input class="input-date" type='date' name='endDate' required value="<%= endDate != null ? endDate : null%>">

                            <label>Room Type:</label>
                            <select class="input-type" name="roomType" value="<%= roomType != null ? roomType : null%>">
                                <option ></option>
                                <option value="Individual">Individual</option>
                                <option value="Double">Double</option>
                                <option value="Triple">Triple</option>
                                <option value="Quadruple">Quadruple</option>
                                <option value="Dormitory">Dormitory</option>
                            </select>

                            <input class="btn" type='submit' value="search">
                        </p>
                    </form>
                </div>
                <hr>
            </div>
            <div class="sub-content">
                <%
                    RoomService service = new RoomService();
                    if (startDate != null && endDate != null && startDate.length() > 0 && endDate.length() > 0) {  //判斷是否執行商業邏輯(查空房)
                        //這邊計算住宿天數(前提是有查詢日期才能計算，所以放在這個if內)
                        LocalDate dateFrom_D = LocalDate.parse(startDate);
                        LocalDate dateTo_D = LocalDate.parse(endDate);
                        int bookingPeriod = (int) dateFrom_D.until(dateTo_D, ChronoUnit.DAYS);

                        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String endDate_1 = dateTo_D.plusDays(-1).format(fmt); //扣除輸入的搜尋日期的最後一天，再把這個日子拿去跑searchRoomsByDate

                        service.autoInserStockOfRoomsByDate(startDate, endDate_1);
                        List<Room> list = service.searchRoomsByDateAndRoomType(startDate, endDate_1, roomType);//就算roomType是空字串，SQL查詢也可以完成
                %>
                <%
                    if (list != null && list.size() > 0) {
                %>
                <form  class="content-form" method="POST" action="add_cart.do" onsubmit="return checkLogin()">
                    <input type='hidden' name='startDate' value="<%= startDate%>">  <!--需要把日期資訊放在form表單範圍內，才能一併送出-->
                    <input type='hidden' name='endDate' value="<%= endDate%>">
                    <div class="showroom">
                        <%
                            for (Room r : list) {
                        %>
                        <section>
                            <input type="hidden" name="roomId_<%= r.getId()%>" value="<%=r.getId()%>" >  <!--為了傳到add_cart.do-->
                            <div class="room-name"><%= r.getName()%></div>
                            <div class="room-info-wrapper">
                                <div class="room-image">
                                    <img src="<%= r.getPhotoUrl()%>"  style="width:210px;height:154px"/>
                                </div>

                                <div class="room-content">
                                    <table style="padding-left:25px">
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <% if (r.getId() < 7) {%>
                                                    <span style="font-size:14px; margin:5px 0px 15px;color: #344e86">Max.People : <%= r.getMax()%></span>
                                                    <% } else {%>
                                                    <span style="font-size:14px; margin:5px 0px 15px;color: #344e86">Number of Beds : <%= r.getMax()%></span>
                                                    <%}%>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <div style="color:red; font-size:18px; margin: 0px 0px 10px;"><%= (int) Math.round(r.getUnitPrice() * bookingPeriod)%><sup style="font-size:10px;text-decoration: underline;">00</sup><small style="color:#777777">EUR</small></div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <% if (r.getId() < 7) { %>
                                        <lable style="font-size:14px;margin: 5px 0px015px;color: #344e86">Number of Rooms</lable>
                                            <%} else {%>
                                        <lable style="font-size:14px;margin: 5px 0px015px;color: #344e86">Number of Beds</lable>
                                            <%}%>
                                        <br>
                                        <select class="select" name="roomId_<%= r.getId()%>_number" style="color:#55555;font-size:14px;padding:6px 12px;width: 120px">  <!--為了傳到add_cart.do-->
                                            <option></option>
                                            <% for (int i = 1; i <= r.getStock(); i++) {%>
                                            <option value="<%=i%>"><%= i%> (EUR <%= i * (int) Math.round(r.getUnitPrice() * bookingPeriod)%>)</option>
                                            <% }%>
                                        </select> 
                                        </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="room-description">
                                    <p style="font-size:14px;margin:5px 0px 10px"><%= r.getDescription()%></p>
                                </div>
                            </div>
                        </section>
                        <% if (r.getId() != 9) { %> <!--為了把每個房型做出間隔兒加上hr，  笨寫法，問老師有沒有更好的-->
                        <hr noshade size="1">
                        <%}%>
                        <% }%>
                    </div>
                    <div class="order-info">
                        <div class="alert">Number of Night : <%= bookingPeriod%></div>
                        <input id="disabled" class="btn disabled" type="submit" value="Booking ❯" disabled /> 
                    </div>
                </form>

                <%} else {%>
                <p style="font-size:14px">No rooms were found</p>
                <div style="height:500px"></div><!--設高度，為了讓footer跑到下面-->
                <%}%>

                <%} else {%>
                <p style="font-size:14px" >please chose the date you will stay</p> 
                <div style="height:500px"></div><!--設高度，為了讓footer跑到下面-->
                <%}%>
            </div>
        </div>

        <%@ include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
