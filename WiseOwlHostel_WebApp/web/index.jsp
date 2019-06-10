<%-- 
    Document   : index.jsp
    Created on : 2019/3/20, 下午 02:10:24
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Wise Owl Hostel</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include file="/WEB-INF/subviews/global.jsp" %>
        <style>
            /*=====================content=========================*/
            .owl-carousel{
                position: sticky;
                top:50px;
                z-index:-1;
            }
            .services {
                padding: 0 0;
                background-color: #fff;
                color: #333;
            }

            .services-wrapper {
                display: flex;
                flex-direction: row;
                justify-content: space-between;
                width: 70%;
                margin: 0 auto;
                padding: 80px 0;
                text-align: center;
            }
            .service-box {
                display: table-cell;
            }
            .service-icon {
                width: 50px;
                height: 50px;
                margin-bottom: 30px;
                border: 4px solid;
                border-radius: 40%;
                color: #11385A;
                font-size: 35px;
                line-height: 51px;

            }
            .service-title {
                margin: 0 20px 20px;
                font-size: 24px;
            }
            .service-text {
                margin: 0 20px;
                line-height: 2;
            }
            /*================================== contact =================================================*/
            .contact{
                background-color: #E5E5E5;
            }
            h3{
                text-align: center;
                font-size: 27px;
                margin: 0;
                padding: 60px 0 5px;
            }
            .divider{

                border-bottom:1px solid gray;

                margin: 0px auto;
                max-width: 100%;
                width: 10%;
            }

            .contact-wrapper{
                display: flex;
                flex-direction: row;

                width: 60%;
                margin: 0 auto;
                padding: 30px 0 70px;
                text-align: center;
            }
            .contact-wrapper h3{
                font-size: 22px;
            }
            i{
                margin: 0 10px;
            }
            .contact-wrapper iframe,div{
                margin:0 auto;
            }
            /*=================== blogs========================*/
            .blog{
                background: white;
            }
            .bolgs-wrapper{
                display: flex;
                flex-direction: row;
                justify-content: space-between;
                width: 70%;
                margin: 0 auto;
                padding: 40px 0;
                /*text-align: center;*/
            }
            .bolg-box{
                width: 210px;
                margin: 0 auto;
            }
            .bolg-image{
                max-width: 210px;
                max-height: 250px;
                display:table-cell; /*寫一個偽table來讓裡面元素垂直置中*/
                vertical-align:middle;
                text-align:center;
                margin: auto;
            }
            .bolg-description-inner{
                padding: 20px 0;
            }
            .bolg-text{
                text-align: justifyl;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/subviews/header.jsp"/>
        <div class="owl-carousel">
            <div class="item"><img src="images/HomePageImages/MG_01_a.jpg" alt=""/></div>
            <div class="item"><img src="images/HomePageImages/MG_02_a.jpg" alt=""/></div>
            <div class="item"><img src="images/HomePageImages/MG_03_a.jpg" alt=""/></div>
            <div class="item"><img src="images/HomePageImages/MG_04_a.jpg" alt=""/></div>
            <div class="item"><img src="images/HomePageImages/MG_05_a.jpg" alt=""/></div>
        </div>

        <section class="services">
            <div class="services-wrapper">
                <div class="service-box">
                    <i class="service-icon fa fa-wifi"></i>
                    <div class="service-title">WIFI</div>
                    <p class="service-text">
                        In the hostel we have free WIFI at your disposal <br>
                        in all the rooms.
                    </p>
                </div>
                <div class="service-box">
                    <i class="service-icon fa fa-cutlery"></i>
                    <div class="service-title">OPEN KITCHEN</div>
                    <p class="service-text">
                        The hostel’s kitchen is at your disposal to store<br> 
                        and prepare your meals.
                    </p>
                </div>
                <div class="service-box">
                    <i class="service-icon fa fa-location-arrow"></i>
                    <div class="service-title">CITY CENTER</div>
                    <p class="service-text">
                        The location of the Hostel is privileged, it allows <br>
                        you to move on foot without problems.
                    </p>
                </div>
            </div>
        </section>

        <a name="contact"> </a>
        <section class="contact">
            <h3>ACCESS</h3>
            <div  class="divider" style="width:7%" ></div>
            <div class="contact-wrapper">
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2992.89126042473!2d2.166437115670781!3d41.39816780328925!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x12a4a2ebc68425f9%3A0xad151116fb442ac2!2zQ2FycmVyIGRlIE1hbGxvcmNhLCAzMzAsIDA4MDM3IEJhcmNlbG9uYSwg6KW_54-t54mZ!5e0!3m2!1szh-TW!2stw!4v1555657654836!5m2!1szh-TW!2stw" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
                <div>
                    <h3>Wise Owl Hostel</h3>
                    <p><i class=" fa fa-map-marker"></i> Calle Mallorca 330, 08037 Barcelona</p>
                    <p><i class=" fa fa-envelope"></i> seanwangfamily@gmail.com</p>
                    <p><i class=" fa fa-phone"></i> +34 931 752 151</p>
                    <p><i class=" fa fa-clock-o"></i> 24h</p>
                </div>
            </div>
        </section>

        <a name="blog"> </a>
        <section class="blog">
            <h3>THEY'VE ALREADY STAYED...</h3>
            <div  class="divider" style="width:24%" ></div>
            <div class="bolgs-wrapper">
                <div class="bolg-box ">
                    <a href="<%= request.getScheme() + "://" + request.getServerName()%>:8084<%= request.getContextPath()%>/images/HomePageImages/IMG_5185.jpg">
                        <img class="bolg-image" src="images/HomePageImages/IMG_5185.jpg" alt=""/>
                    </a>
                    <div class="bolg-description">
                        <div class="bolg-description-inner">
                            <p class="bolg-text">
                                Excellent hostel. 
                                I was in the mixed room of six people and I had no problem with the spaces as the hostel has many places not to disturb anyone.
                                Excellent cleanliness and condition. Very good location,
                                I was walking everywhere.
                                <!--<a href="#" class="button button-ghost">READ MORE</a>-->
                            </p>
                        </div>
                    </div>
                </div>
                <div class="bolg-box ">
                    <a href="<%= request.getScheme() + "://" + request.getServerName()%>:8084<%= request.getContextPath()%>/images/HomePageImages/IMG_3864.JPG">
                        <img class="bolg-image" src="images/HomePageImages/IMG_3864.JPG" alt=""/>
                    </a>
                    <div class="bolg-description">
                        <div class="bolg-description-inner">
                            <p class="bolg-text">
                                Hostel located just four blocks from the Sagrada Familia, spacious and very cozy rooms. 
                                The attention is very close, with spaces for sharing, dining, ample kitchen, 
                                private bathrooms and different room options.
                                <!--<a href="#" class="button button-ghost">READ MORE</a>-->
                            </p>
                        </div>
                    </div>
                </div>
                <div class="bolg-box ">
                    <a href="<%= request.getScheme() + "://" + request.getServerName()%>:8084<%= request.getContextPath()%>/images/HomePageImages/IMG_5196.JPG">
                        <img class="bolg-image" src="images/HomePageImages/IMG_5196.JPG" alt=""/>
                    </a>
                    <div class="bolg-description">
                        <div class="bolg-description-inner">
                            <p class="bolg-text">
                                SMALL, PECULIAR, HOGAREÑO and GREAT.
                                Very nice cozy hostel with a lovely staff and bags of character.
                                Our room was bright, spacious, spacious and clean, with a comfortable bed.
                                The location is ideal, in the Eixample district, with convenient access to many of the best attractions.
                                <!--<a href="#" class="button button-ghost">READ MORE</a>-->
                            </p>
                        </div>
                    </div>
                </div>
                <div class="bolg-box ">
                    <a href="<%= request.getScheme() + "://" + request.getServerName()%>:8084<%= request.getContextPath()%>/images/HomePageImages/IMG_5616.JPG">
                        <img class="bolg-image" src="images/HomePageImages/IMG_5616.JPG" alt=""/>
                    </a>
                    <div class="bolg-description">
                        <div class="bolg-description-inner">
                            <p class="bolg-text">
                                Located in the beautiful Catalan quarter of Eixample, 
                                close to the wonders of Gaudi and a few blocks from a metro station. 
                                It is very modern, has spacious and bright common spaces and excellent hygiene. 
                                The price was very good, 100% recommended.
                                <!--<a href="#" class="button button-ghost">READ MORE</a>-->
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </section>



        <%@ include file="/WEB-INF/subviews/footer.jsp" %>
    </body>
</html>
