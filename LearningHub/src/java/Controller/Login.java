/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.UserRepository;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Service.userManager;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="login", urlPatterns={"/login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher rq = request.getRequestDispatcher("/session/login.jsp");
        rq.forward(request, response);
    } 


@Override
/*Nhận: userName, password từ form đăng nhập
Lấy: role_name - set vào session từ id người dùng - check sesion hiển thị ở trang admin
check null ở mọi trang cần thông tin người dùng
Chuyển đến trang chủ nếu đúng, chuyển đến Login nếu sai
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        userManager lsv = new userManager();

        // validate input
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập email và mật khẩu.");
            RequestDispatcher rq = request.getRequestDispatcher("/session/login.jsp");
            rq.forward(request, response);
            return;
        }
        if (lsv.checkLogin(email, password)) {
            
            //Trả role 
            UserRepository use = new UserRepository();
            int id = use.getUserByUserNameAndPassword(email, password).get(0).getId();
            
            //Tạo session - check sesion cũ trước 
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            //tạo
            HttpSession session = request.getSession(true);
            session.setAttribute("role_name",  lsv.checkRole(id));
            
            session.setMaxInactiveInterval(30 * 60); //time out
            
            //chuyển đến home page
            response.sendRedirect(request.getContextPath() + "/pages/home.jsp");
        } else {
            request.setAttribute("error", "Email hoặc mật khẩu không đúng.");
            RequestDispatcher rq = request.getRequestDispatcher("/session/login.jsp");
            rq.forward(request, response);
            return;
        }
    } catch (Exception e) {
        //nên tạo 1 trang 404 để hiển thị.
        log("Error when processing login", e);
        request.setAttribute("error", "Đã có lỗi xảy ra khi đăng nhập. Vui lòng thử lại sau.");
        RequestDispatcher rq = request.getRequestDispatcher("/sesion/login.jsp");
        rq.forward(request, response);
    }
    
    
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
