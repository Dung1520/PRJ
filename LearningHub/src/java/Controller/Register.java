/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import Service.userManager;
import Utils.Validator;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="register", urlPatterns={"/register"})
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher rq = request.getRequestDispatcher("/session/register.jsp");
        rq.forward(request, response);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        
        String errors = Validator.validateRegistration(username, email, password, confirm);
        
        if(!password.equals(confirm)){
            errors = "vui lòng nhập đúng mật khẩu!";
        }
        
        if (errors.isEmpty()) {
            userManager um = new userManager();
            boolean created = um.createUser(username.trim(), email.trim(), password);
            if (created) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            } else {
                errors="Không thể tạo tài khoản. Username hoặc Email có thể đã tồn tại.";
            }
        }
        // forward lại form nếu có error
        request.setAttribute("error", errors); //list các error - yêu cầu in ra với từng trường nhập liệu trong form
        request.setAttribute("username", username);
        request.setAttribute("email", email);
        RequestDispatcher rd = request.getRequestDispatcher("/session/register.jsp");//trờ  về form kèm error
        rd.forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
