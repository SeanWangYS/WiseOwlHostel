<%-- 
    Document   : booking.jsp
    Created on : 2019/4/1, 上午 11:34:21
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/woh/style/woh.css">
        <style>

            /*=====================content=========================*/

            .content-wrapper{
                height: 2000px;
                width: 100%;
                min-width: 1000px;
                /*                font-size: 8em;
                                text-align: center;*/
            }
        </style>
    </head>
    <body>
        <!--header-wrapper-->
        <header class="header-wrapper">
            <!--  logo -->
            <div class="logo-wrapper">
                <div class="logo">
                    <img src="images/owl.png" style="padding:10px 15px;">
                    <p style="margin:0px">Wise Owl Hostel</p>
                </div>
                <div class="logo-content">
                    <ul>
                        <li>09:00AM — 05:00PM</li>
                        <li>WiseOwlHouse@gmail.com</li>
                    </ul>
                </div>
            </div>
        </header>
        <!--  navigation -->
        <nav class="navigator">
            <ul class="function">
                <li class="nav-item"><a href="index.jsp">HOME</a></li>
                <li class="nav-item"><a href="rooms.jsp">ROOMS</a></li>
                <li class="nav-item"><a href="blog.jsp">BLOG</a></li>
                <li class="nav-item"><a href="contact.jsp">CONTACT</a></li>
                <li class="nav-item"><a href="booking.jsp">BOOKING</a></li>
            </ul>

            <ul class="member">
                <li class="nav-item"><a href="index.jsp">Login</a></li>
                <li class="nav-item"><a href="rooms.jsp">New Friend</a></li>
            </ul>
        </nav>

        <!--page-title-wrapper-->
        <div class="page-title-wrapper" style="background-image: url(images/Barcelona_Head_Ok.jpg); ">
            <div class="page-title-wrapper-container">
                <h2 class="page-title">BOOKING</h2>
            </div>
        </div>

        <!-- content wrapper -->
        <div class="content-wrapper">
            <div class="main-content">
                <div class="rearch-rooms">
                    <form method="GET">
                        <p>
                            <label>From:</label>
                            <input type='date' name='date-from' required> 
                        </p>
                        <p>
                            <label>To:</label>
                            <input type='date' name='date-to' required>
                        </p>
                        <!--<p>
                             <label>Number of People:</label>
                             <input type='text' name='npeople' required>
                         </p>-->
                        <input type='submit' value="search">
                    </form>
                </div>
                <hr>
            </div>
                <div class="sub-content">
                    <form method>
                        <div class="showroom">
                            <section class="room">
                                <p class="room-name">Double Room with Private Bathroom</p>
                                <div class="image-frame" >
                                    <img src="images/rooms/MG_4928-3-1024x697.jpg"  style="width:100px;height:100px"/>
                                </div>
                                <div class="room-content-table" style="background-color:skyblue">
                                    <table>
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <span>Max.People : 2</span>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <h4>70</h4><sup>00</sup><small>EUR pre night</small> <!--之後加入天數計算後的結果-->
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                        <lable>Number of Rooms</lable>
                                                        <select>
                                                            <option>1</option>
                                                        </select>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="room-description" style="background-color:lightgreen">
                                    <p>arble floor, Wardrobe/Closet, Private Bathroom and Toilet , Hair dryer, Shower gel, Free WiFi is available in all rooms.</p>
                                </div>
                            </section>
                            <input type="submit" value="Booking"/> <!--TODO 增加沒有選擇房間則無法送出的檢查-->
                        </div>
                        
                    </form>
                </div>
            
        </div>


        <!--footer-wrapper-->
        <footer class="footer-wrapper">
            <div class="footer-content">
                <p>© 2019 - Wise Owl Hostel</p>
            </div>
        </footer>
    </body>
</html>
