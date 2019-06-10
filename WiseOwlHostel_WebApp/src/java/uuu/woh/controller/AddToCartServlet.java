/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.woh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uuu.woh.entity.Customer;
import uuu.woh.entity.Room;
import uuu.woh.entity.ShoppingCart;
import uuu.woh.entity.WOHException;
import uuu.woh.model.RoomService;

/**
 *
 * @author Sean
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/add_cart.do"})
public class AddToCartServlet extends HttpServlet {

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

        //0.前置作業
        List<String> errors = new ArrayList<>();
        response.setContentType("text/html;charset=UTF-8");

        //1.取得request中的From Data: roomId_? , room_?_number
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        RoomService service = new RoomService();//為了取得下面for迴圈中的quantity，先呼叫商業邏輯
        int quantity = 0;
        try {
            quantity = service.searchQuantityOfRoomType();
            System.out.println("room quantity=" + quantity);
        } catch (WOHException ex) {
            this.log("查詢房型數量失敗", ex);
            errors.add("查詢房型數量失敗" + ex.getMessage());
        }
        for (int i = 1; i <= quantity; i++) {
            String roomId = request.getParameter("roomId_" + i);
            String roomNumber = request.getParameter("roomId_" + i + "_number");

            if (roomNumber != null && roomNumber.length() > 0) {  //如果前端使用者不對房型數量做選取，則抓到的roomNumber的值，會是空字串
                System.out.println(roomId);
                //2. 當房間數量不是null且非空字串 ，則執行商業邏輯
                try {
                    Room r = service.searchRoomById(roomId);
                    if (r != null) {
                        HttpSession session = request.getSession();
                        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
                        if (cart == null) {
                            cart = new ShoppingCart();
                            session.setAttribute("cart", cart);
                        }
                        //依我的網站的操作邏輯，我每次使用addToCart，不是直接add房間數量，而是重新定義房間數量-->對Map 做push的概念
                        cart.addToCart(r, Integer.parseInt(roomNumber));
                        cart.setStartDate(startDate);
                        cart.setEndDate(endDate);
                        
                        Customer member = (Customer)session.getAttribute("member");
                        cart.setMember(member);
                    }
                    
                } catch (WOHException ex) {
                    this.log("加入購物車發生錯誤", ex);
                    errors.add("加入購物車發生錯誤" + ex.getMessage());
                } catch (Exception ex) {
                    this.log("加入購物車發生非預期錯誤", ex);
                    errors.add("加入購物車發生非預期錯誤" + ex.getMessage());
                }
            }
        }
        if (errors.size() > 0) {
            this.log(errors.toString());
        }
        //3.切換畫面 
            response.sendRedirect(request.getContextPath() + "/member/check_out.jsp");
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
