<%-- 
    Document   : check_out
    Created on : 2019/4/4, 下午 02:22:51
    Author     : Sean
--%>

<%@page import="uuu.woh.entity.ShoppingCart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">  
        <meta charset="UTF-8">
        <title>check out</title>
        <%@include file="/WEB-INF/subviews/global.jsp" %>
        <style>
            .content-wrapper .content-container{
                width: 1000px;
                margin: auto;
                /*background: lightblue;*/
            }

            input,select{
                padding: 5px 0px;
                color: #555;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
                font-size: 14px;
                text-align: left;
                vertical-align: middle;
            }

            .content-wrapper .content-container .booking-info{
                display: flex;
                flex-direction:column;
                align-items: flex-start;
                border: 1px solid #CCC;
                margin-top: 80px;
            }

            .content-wrapper .content-container .booking-info p{
                margin: 20px 0px 10px;
                padding: 0px 15px;

            }

            .content-wrapper .content-container .booking-info section{
                font-size:14px;
                height: 140px;
                /*background: lightyellow;*/
                width: 100%;
            }

            .content-wrapper .content-container .booking-info section .room-image,.sub-wrapper-1,.sub-wrapper-2{
                float: left;
                padding:15px;
            }
            .content-wrapper .content-container .booking-info section .sub-wrapper-2{
                float: right;
                display:flex;
                flex-direction: column;
                justify-content: space-between;
                height:110px;
            }
            .content-wrapper .content-container .booking-info table{
                border: 1px solid #CCC;
                border-spacing: 10px;
                border-collapse: collapse;
                margin: 15px;
            }
            .content-wrapper .content-container .booking-info table td{
                font-size:14px;
                padding: 10px 15px;
                border: 1px solid #CCC;
                text-align: center;
                vertical-align: middle;
            }
            .content-wrapper .content-container .booking-info table .td-odd{
                background-color: #EEEEEE;
            }
            /*====================================================================*/
            .content-wrapper .content-container .member-info p{
                margin: 0px;
                margin-top: 20px;
            }
            .content-wrapper .content-container .member-info .member-info-wrapper{
                display: flex;
                font-size: 14px;

            }
            .content-wrapper .content-container .member-info .member-info-wrapper .member-info-sub-wrapper{
                width: 100%;
                margin: 0px 15px;
            }

            .content-wrapper .content-container .member-info .member-info-wrapper .member-info-sub-wrapper div{
                padding: 0 15px;
                margin: 10px 0px 5px;
            }
            .content-wrapper .content-container .member-info .member-info-wrapper .member-info-sub-wrapper div input,select{
                width: 100%;
            }



        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp"/>
        <!--page-title-wrapper-->
        <div class="page-title-wrapper" style="background-image: url(../images/Barcelona_Head_Ok.jpg); ">
            <div class="page-title-wrapper-container">
                <h2 class="page-title">BOOKING</h2>
            </div>
        </div>
        <!-- content wrapper -->

        <div class="content-wrapper">
            <div class="content-container">
                <div class="booking-info">
                    <p>Please complete the room occupancy:</p>
                    <section>
                        <div class="room-image">
                            <img src="../images/rooms/Dormitorio-4-Camas.jpg"  style="width:150px;height:110px"  alt=""/>
                        </div>
                        <div class="sub-wrapper-1">
                            <div class="room-name">Single Room</div>
                            <div class="room-number">Room:1</div>
                        </div>
                        <div class="sub-wrapper-2">
                            <div class="max-peolpe">Max.People in this room: 1</div>
                            <p><input class="price" type="text" value="155" style="background-color: #EEEEEE; color:black"><span>EUR</span></p>
                        </div>
                    </section>
                    <hr noshade size="1">
                    <table>
                        <tr>
                            <td class="td-odd">
                                <small>From: </small>
                                <div>2019-07-09 </div>
                                <small>To: </small>
                                <div>2019-07-10</div>
                            </td>
                            <td>
                                <div>Number of Nights: </div>
                                <div>2</div>
                            </td>
                            <td class="td-odd">
                                <div>Total Price:</div>
                                <strong>EUR 347<sup style="font-size:10px;text-decoration: underline;">00</sup></strong>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="member-info">
                    <p>Please complete your personal data:</p>
                    <div class="member-info-wrapper">
                        <div class="member-info-sub-wrapper">
                            <div>
                                <label>Name</label>
                                <input type="text" name="name" value="從後端抓" placeholder="Name">
                            </div>
                            <div>
                                <label>Telephone</label>
                                <input type="text" name="telephone" value="從後端抓" placeholder="Telephone">
                            </div>
                            <div>
                                <label>Country</label>
                                <input type="text" name="country"  placeholder="Country">
                            </div>
                            <div>
                                <label>Estimated time of arrival</label>
                                <select name="bookingEstimatedArrival" >
                                    <option value="-">I don't know</option>
                                    <option value="00:00">00:00 - 01:00</option>
                                    <option value="01:00">01:00 - 02:00</option>
                                    <option value="02:00">02:00 - 03:00</option>
                                    <option value="03:00">03:00 - 04:00</option>
                                    <option value="04:00">04:00 - 05:00</option>
                                    <option value="05:00">05:00 - 06:00</option>
                                    <option value="06:00">06:00 - 07:00</option>
                                    <option value="07:00">07:00 - 08:00</option>
                                    <option value="08:00">08:00 - 09:00</option>
                                    <option value="09:00">09:00 - 10:00</option>
                                    <option value="10:00">10:00 - 11:00</option>
                                    <option value="11:00">11:00 - 12:00</option>
                                    <option value="12:00">12:00 - 13:00</option>
                                    <option value="13:00">13:00 - 14:00</option>
                                    <option value="14:00">14:00 - 15:00</option>
                                    <option value="15:00">15:00 - 16:00</option>
                                    <option value="16:00">16:00 - 17:00</option>
                                    <option value="17:00">17:00 - 18:00</option>
                                    <option value="18:00">18:00 - 19:00</option>
                                    <option value="19:00">19:00 - 20:00</option>
                                    <option value="20:00">20:00 - 21:00</option>
                                    <option value="21:00">21:00 - 22:00</option>
                                    <option value="22:00">22:00 - 23:00</option>
                                    <option value="23:00">23:00 - 00:00</option>
                                </select>
                            </div>
                        </div>
                        <div class="member-info-sub-wrapper">
                            <div>
                                <label>Surame</label>
                                <input type="text" name="surame" value="從後端抓" placeholder="Surame">
                            </div>
                            <div>
                                <label>Email</label>
                                <input type="text" name="eamil" value="從後端抓" placeholder="Email">
                            </div>
                            <div>
                                <label>City</label>
                                <input type="text" name="city" value="從後端抓" placeholder="City">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="credit-cart">
                    <p>Credit Card</p>
                </div>
            </div>
    </body>
</html>
