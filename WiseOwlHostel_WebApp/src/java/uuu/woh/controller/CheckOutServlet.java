/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.woh.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uuu.woh.entity.Customer;
import uuu.woh.entity.Order;
import uuu.woh.entity.OrderItem;
import uuu.woh.entity.PaymentType;
import uuu.woh.entity.ShoppingCart;
import uuu.woh.entity.WOHException;
import uuu.woh.model.MailService;
import uuu.woh.model.OrderService;

/**
 *
 * @author Sean
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/member/check_out.do"})
public class CheckOutServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        //1.讀取request中From的Parameter:
        String paymentType = request.getParameter("paymentType");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String bookingEstimatedArrival = request.getParameter("bookingEstimatedArrival");

        if (paymentType == null && paymentType.length() == 0) {
            errors.add("請選擇付款方式");
        }
        if (bookingEstimatedArrival == null && bookingEstimatedArrival.length() == 0) {
            errors.add("請選擇預計抵達時間");
        }

        HttpSession session = request.getSession();
        Customer member = (Customer) session.getAttribute("member");
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (member == null || cart == null) {
            response.sendRedirect(request.getContextPath() + "/check_out.jsp");
            return;
        }
        LocalDate startDate = cart.getStartDate();
        LocalDate endDate = cart.getEndDate();

        //2.若無誤，則呼叫商業邏輯
        if (errors.isEmpty()) {
            Order order = new Order(); //要準備把取得的資訊包成Order物件
            order.setMember(member);
            try {
                PaymentType pType = PaymentType.valueOf(paymentType);
                order.setPaymentType(pType);
            } catch (RuntimeException ex) {
                errors.add(ex.toString());
            }
            if (errors.isEmpty()) {
                order.setStartDate(startDate);
                order.setEndDate(endDate);
                order.setCustomerBookingEstimatedArrival(bookingEstimatedArrival);
                if (country != null && country.length() > 0) {
                    order.setCustomerCountry(country);
                }
                if (city != null && city.length() > 0) {
                    order.setCustomerCity(city);
                }
                order.add(cart);//包裝完成  // //到這邊已經把購物車資訊以及其他相關訂單資訊包裝進Order物件中 ，但是這裡沒有設定Order物件的 id 屬性[問題]那為什麼下一頁可以讀出id

                try {
                    OrderService service = new OrderService();
                    service.insert(order);

                    session.removeAttribute("cart"); //訂單新增完成，移除購物車

                    //3.1 結帳成功，內部轉交./order.jsp
                    request.setAttribute("order", order);//這裡把order物件存在request裡，是為了轉交畫面之後給order.jsp讀出來
                    //==================== javamail API (Gmail信件內容)=========================
                    String orderTime = order.getOrderTime().toString();
                                String[] tokens = orderTime.split(":");
                    String  orderTimeEdit = tokens[0]+":"+tokens[1];
                    
                    String htmlText1 = "<div style='font-size:24px; max-width: 80%;padding:50px 50px; margin: auto'>\n"
                            + "            <div>\n"
                            + "                 <div>\n"
                            + "                     <div><span>Dear "+ order.getMember().getName()+" "+order.getMember().getSurname()+" , This is your reservation detail.</span></div>\n"
                            + "                 </div>\n"
                            + "                 <br>\n"
                            + "                 <br>\n"
                            + "                <div>\n"
                            + "                    <div><span style='padding-right:30px'>Reservation Number: " + order.getFormatedId() + "</span><span>Order Time: " + order.getOrderDate() + "  " + orderTimeEdit + "</span></div>\n"
                            + "                </div>\n"
                            + "                <div>\n"
                            + "                    <div><span>Booking Period: " + order.getStartDate() + " ~ " + order.getEndDate() + "</span></div>\n"
                            + "                </div>\n"
                            + "                <div style='max-width: 90%; margin: auto; padding: 20px 0'>\n";
                    String htmlText2 = "";
                    String roomType = "";
                    for (OrderItem item : order.getOrderItemSet()) {
                        if (item.getRoom().getId() < 7) {
                            roomType = "Number of Rooms";
                        } else {
                            roomType = "Number of Beds";
                        }
                        String text = "                    <section>\n"
                                + "                        <div>\n"
                                + "                            <div>" + item.getRoom().getName() + "</div>\n"
                                + "                            <div>" + roomType + ": " + item.getNumber() + "</div>\n"
                                + "                            <div>Unit Price: " + item.getPrice() + " €</div>\n"
                                + "                        </div>\n"
                                + "                    </section>\n"
                                + "                    <hr noshade='' size='1'>\n";
                        htmlText2 += text;
                    }
                    String htmlText3 = "                </div>\n"
                            + "                <div>\n"
                            + "                    <div>Total Price: "+order.getTotalAmount()+" €</div>\n"
                            + "                    <div>Payment Type: "+order.getPaymentType().getDescription()+"</div>\n"
                            + "                    <div>Estimated time of arrival: "+order.getCustomerBookingEstimatedArrival()+"</div>\n"
                            + "                </div>\n"
                            + "            </div>\n"
                            + "        </div>";
                    String htmlText = htmlText1 + htmlText2 + htmlText3;
                    MailService.sendHelloMailWithLogo("musheng1986@gmail.com", htmlText);

                    //====若paymentType=PaymentType.CARD則轉交/WEB-INF/credit_card.jsp來送出對於第三方支付的請求======
                    if (order.getPaymentType() == PaymentType.CARD) {
                        request.getRequestDispatcher("/WEB-INF/credit_card.jsp").forward(request, response);
                        return;
                    }
                    //=========================================================================================================
                    request.getRequestDispatcher("order.jsp").forward(request, response);

                    

                    return;
                } catch (WOHException ex) {
                    this.log("結帳時建立訂單失敗", ex);
                    errors.add(ex.getMessage());
                } catch (Exception ex) {
                    this.log("結帳時建立訂單發生非預期錯誤", ex);
                    errors.add("結帳時建立訂單發生非預期錯誤" + ex.getMessage());
                }
            }
        }
        //3.2
        request.setAttribute("errors", errors);
        request.getRequestDispatcher("/member/check_out.jsp").forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
