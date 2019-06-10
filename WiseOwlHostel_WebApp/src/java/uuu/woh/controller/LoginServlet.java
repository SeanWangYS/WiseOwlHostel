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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/login.do"})
public class LoginServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        List<String> errors = new ArrayList<>();

        //1.取得request中的parameter
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");
        String captcha = request.getParameter("captcha");
        String auto = request.getParameter("auto"); //for Coockie 程式用的屬性

        //檢查email, pwd, chaptcha為必要欄位
        if (email == null || email.length() == 0) {
            errors.add("必須輸入帳號");
        }
        if (pwd == null || pwd.length() < 6 || pwd.length() > 20) {
            errors.add("必須輸入6~20個字的密碼");
        }
        HttpSession session = request.getSession();
        if (captcha == null || captcha.length() == 0) {
            errors.add("必須輸入驗證碼");
        } else {
            String oldCaptcha = (String) session.getAttribute("captcha");
            if (!captcha.equalsIgnoreCase(oldCaptcha)) {
                errors.add("驗證碼不正確" );
            }
        }
        session.removeAttribute("captcha");

        if (errors.isEmpty()) {
            //2.執行商業邏輯
            CustomerService service = new CustomerService();
            try {
                Customer c = service.login(email, pwd);
                
                //login成功則依據使用者選擇來add Cookie / remove Cookie
                Cookie emailCookie = new Cookie("email", email);//這邊先把前端取得的資訊加入cookie
                Cookie autoCookie = new Cookie("auto", "checked");//注意這個小技巧，直接先設成checked ，為了配合 checkbox的屬性
                if(auto==null){//使用者選擇不要記住
                    emailCookie.setMaxAge(0);
                    autoCookie.setMaxAge(0);
                }else{//使用者選擇要記住
                    emailCookie.setMaxAge(7*24*60*60);
                    autoCookie.setMaxAge(7*24*60*60);
                }
                response.addCookie(emailCookie);
                response.addCookie(autoCookie);
                
                session.setAttribute("member", c);// 取得會員資料後，也把產生的會員物件存在session內
                
                //3.1登入成功畫面
                String referedURI = request.getParameter("referedURI");
                System.out.println("referedURI = " + referedURI);
                response.sendRedirect(referedURI); //登入成功後返回前一頁面，終於寫出來，爽
                
                return;
            } catch (WOHException ex) {
                System.out.println("登入失敗:" + ex);
                if(ex.getCause()!=null){
                    errors.add(ex.getMessage() + " ,請聯絡系統管理人員");
                }else{
                    errors.add(ex.getMessage());
                }
            }catch (Exception ex) {
                this.log("登入發生非預期錯誤", ex); //log 還會紀錄日期跟時間，log是給管理人看的
                errors.add("登入發生非預期錯誤" + ex.toString());
            }
        }
        if(errors.size()>0){
            System.out.println("errors:" + errors);
        }
        
        //3.2產生登入失敗畫面
        request.setAttribute("errors", errors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response); //這是內部轉交
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
