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
import uuu.woh.entity.WOHException;
import uuu.woh.model.CustomerService;

/**
 *
 * @author Sean
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register.do"})
public class RegisterServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        //1.取得request中的parameter，建立客戶物件
        String email = request.getParameter("email");
        String pwd1 = request.getParameter("pwd1");
        String pwd2 = request.getParameter("pwd2");
        String surname = request.getParameter("surname");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String captcha = request.getParameter("captcha");

        //檢查parameter
        if (email == null || email.length() == 0) {
            errors.add("必須輸入帳號");
        }
        if (pwd1 == null || pwd1.length() < 6 || pwd1.length() > 20) {
            errors.add("必須輸入6~20個字元的密碼");
        } else if (!pwd1.equals(pwd2)) {
            errors.add("必須輸入6~20個字元的密碼與相同的確認密碼");
        }
        if (surname == null || surname.length() == 0) {
            errors.add("必須輸入surname");
        }
        if (name == null || name.length() == 0) {
            errors.add("必須輸入name");
        }
        if (phone == null || phone.length() == 0) {
            errors.add("必須輸入phone");
        }
        if (gender == null || gender.length() != 1) {
            errors.add("必須輸入gender");
        }

        HttpSession session = request.getSession();
        if (captcha == null || captcha.length() == 0) {
            errors.add("必須輸入captcha");
        } else {
            String oldCaptcha = (String) session.getAttribute("captcha");
            System.out.println("oldCaptcha=  " + oldCaptcha);
            if (!oldCaptcha.equalsIgnoreCase(captcha)) {
                errors.add("驗證碼不正確");
            }
        }
        session.removeAttribute("captcha");
        
        //檢查完成，建立客戶物件
        if(errors.isEmpty()){
            try {
                Customer c =  new Customer();
                c.setEmail(email);
                c.setPassword(pwd1);
                c.setSurname(surname);
                c.setName(name);
                c.setPhone(phone);
                c.setGender(gender.charAt(0));
                
                //2.呼叫CustomerService的register(執行商業邏輯)
                CustomerService service = new CustomerService();
                service.register(c);
                session.setAttribute("member", c);
                
                //3.1 redirect首頁
                response.sendRedirect(request.getContextPath());
                return;
            } catch (WOHException ex) {
               this.log("會員註冊發生錯誤", ex);
                errors.add(ex.getMessage());
            }catch (Exception ex) {
                this.log("會員註冊發生非預期錯誤", ex);
                errors.add("會員註冊發生非預期錯誤:" + ex);
            }
        }
        //3.2 產生註冊失敗畫面
        request.setAttribute("errors", errors);
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
