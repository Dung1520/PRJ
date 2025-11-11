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
import service.userManager;
import utils.Validator;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="register", urlPatterns={"/register"})
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher rq = request.getRequestDispatcher("/include/register.jsp");
        rq.forward(request, response);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        
        // Validate input
        StringBuilder errors = new StringBuilder();
        if (username == null || username.trim().isEmpty()) {
            errors.append("Username là bắt buộc.\n");
        } else if (!utils.Validator.isValidUsername(username)) {
            errors.append("Username không hợp lệ (3-50 ký tự: chữ, số, '_' hoặc '.').\n");
        }
        
        if (password == null || password.isEmpty()) {
            errors.append("Password là bắt buộc.\n");
        } else if (!utils.Validator.isValidPassword(password)) {
            errors.append("Password phải có ít nhất 8 ký tự và chứa cả chữ và số.\n");
        }
        
        if (!password.equals(confirm)) {
            errors.append("Password và Confirm password không khớp.\n");
        }
        
        if (errors.length() == 0) {
            userManager um = new userManager();
            boolean created = um.createUser(username.trim(), null, password);
            if (created) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            } else {
                errors.append("Không thể tạo tài khoản. Username có thể đã tồn tại.");
            }
        }
        // forward lại form nếu có error
        request.setAttribute("error", errors.toString());
        request.setAttribute("username", username);
        RequestDispatcher rd = request.getRequestDispatcher("/session/register.jsp");
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
