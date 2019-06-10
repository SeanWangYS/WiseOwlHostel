<%-- 
    Document   : global
    Created on : 2019/4/4, 下午 05:36:35
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--golbal.jsp-->
<link href="https://fonts.googleapis.com/css?family=Gudea" rel="stylesheet"><!--google font-->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/woh.css">
<link href="<%=request.getContextPath()%>/OwlCarouse/owl.carousel.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/OwlCarouse/owl.theme.default.min.css" rel="stylesheet" type="text/css"/>
<!--<script
    src="https://code.jquery.com/jquery-3.0.0.js"
    integrity="sha256-jrPLZ+8vDxt2FnE1zvZXCkCcebI/C8Dt5xyaQBjxQIo="
    crossorigin="anonymous">
</script>-->
<script src="<%=request.getContextPath()%>/jquery/jquery-3.3.1.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/OwlCarouse/owl.carousel.js" type="text/javascript"></script>

<link href="<%=request.getContextPath()%>/fancybox/jquery.fancybox.css" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/fancybox/jquery.fancybox.js"></script>

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

<script>
    function login() {
        //同步GET請求
        //location.href="login.jsp"
        //非同步請求
        $.ajax({
            url: '<%=request.getContextPath()%>/login.jsp',
            method: 'GET',
            data: {referedURI: "<%= request.getRequestURI()%>" + "?" + "<%= request.getQueryString()%>"}
        }).done(longinDoneHandler);

        function longinDoneHandler(result, status, xhr) {
            //console.log("Login Done" + result);
            $("#loginBox").html(result);
            $.fancybox.open({src: '#loginBox'})
        }
    }

    function register() {
        //同步GET請求
        //location.href="register.jsp"
        //非同步請求
        $.ajax({
            url: '<%=request.getContextPath()%>/register.jsp',
            method: 'GET'
        }).done(registerDoneHandler);
        function registerDoneHandler(result, status, xhr) {
            //console.log("Register Done" + result);
            $("#registerBox").html(result);
            $.fancybox.open({src: '#registerBox'})
        }
    }

    $(document).ready(init);
    function init() {
        $("#member-btn").click(mouseClickHandler);
        $("#membership").mouseleave(mouseLeaveHandler);
        $('.owl-carousel').owlCarousel({
            loop: true,
            margin: 0,
            nav: true, /* 控制列 */

            items: 1, /* 一頁出現的張數 */
            itemsDesktop: [1920, 1],
            autoplay: 3000, /* 自動輪播 */
//            autoplayHoverPause: true
            animateOut: 'fadeOut'
        });
    }
    function mouseClickHandler() {
        $("#membership").slideDown(0);
    }
    function mouseLeaveHandler() {
        $("#membership").fadeOut();
    }




</script>
<!--golbal.jsp end-->
