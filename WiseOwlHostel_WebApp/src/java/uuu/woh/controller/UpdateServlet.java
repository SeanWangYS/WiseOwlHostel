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
@WebServlet("/member/update.do")
public class UpdateServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        Customer member = (Customer) session.getAttribute("member");
        if (member == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        List<String> errors = new ArrayList<>();
        //1. 取得Form Data, 並檢查之
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");
        String changePwd = request.getParameter("changePwd");
        String pwd1 = request.getParameter("pwd1");
        String pwd2 = request.getParameter("pwd2");
        String surname = request.getParameter("surname");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        if (email == null || (!email.equals(member.getEmail()))) {
            errors.add("會員Email不正確");
        }
        if (pwd == null || (!pwd.equals(member.getPassword()))) {
            errors.add("會員密碼不正確");
        }
        if (changePwd != null) {
            if (pwd1 == null || pwd1.length() < 6 || pwd1.length() > 20) {
                errors.add("必須輸入6~20字元密碼");
            } else if (!pwd1.equals(pwd2)) {
                errors.add("必須輸入6~20字元且一致的密碼與確認密碼");
            }
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

        String captcha = request.getParameter("captcha");
        if (captcha == null || (captcha = captcha.trim()).length() == 0) {
            errors.add("必須輸入驗證碼");
        } else {
            String sessionCaptcha = (String) session.getAttribute("captcha");
            if (!captcha.equalsIgnoreCase(sessionCaptcha)) {
                errors.add("驗證碼不正確");
            }
        }
        session.removeAttribute("captcha");

        //2.若檢查無誤則呼叫商業邏輯
        if (errors.isEmpty()) {
            try {
                Customer c = member.getClass().newInstance();
                c.setEmail(email);
                c.setPassword(changePwd!=null?pwd1:member.getPassword());
                c.setSurname(surname);
                c.setName(name);
                c.setPhone(phone);
                c.setGender(gender.charAt(0));

                CustomerService service = new CustomerService();
                service.update(c);
                
                //3.1 success: Redirect to HOME page
                session.setAttribute("member", c);
                response.sendRedirect(request.getContextPath());
                return;
            } catch (WOHException ex) {
                this.log("會員修改發生錯誤", ex);
                errors.add(ex.getMessage());
            } catch (Exception ex) {
                this.log("會員修改發生非預期錯誤", ex);
                errors.add("發生非預期錯誤" + ex);
            }
        }
        //3.2 檢查有誤或商業邏輯執行失敗: forward to /profile_update.jsp
        request.setAttribute("errors", errors);
        request.getRequestDispatcher("profile_update.jsp").forward(request, response);
		//return;

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
