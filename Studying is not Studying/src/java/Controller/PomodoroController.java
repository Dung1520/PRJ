/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.PomodoroRecord;
import service.PomodoroService;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "PomodoroController", urlPatterns = {"/pomodoro"})
public class PomodoroController extends HttpServlet {

    private PomodoroService pomodoroService = new PomodoroService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int userId = (Integer) session.getAttribute("user_id");
        String action = request.getParameter("action");

        if (action == null || action.equals("list")) {
            // Hiển thị danh sách pomodoro records
            List<PomodoroRecord> records = pomodoroService.getPomodoroRecordsByUserId(userId);
            int totalTime = pomodoroService.getTotalTimeByUserId(userId);
            request.setAttribute("records", records);
            request.setAttribute("totalTime", totalTime);
            RequestDispatcher rd = request.getRequestDispatcher("/pages/pomodoro.jsp");
            rd.forward(request, response);
        } else if (action.equals("delete")) {
            // Xóa pomodoro record
            try {
                int recordId = Integer.parseInt(request.getParameter("recordId"));
                if (pomodoroService.deletePomodoroRecord(recordId)) {
                    response.sendRedirect(request.getContextPath() + "/pomodoro?action=list");
                } else {
                    request.setAttribute("error", "Không thể xóa record.");
                    response.sendRedirect(request.getContextPath() + "/pomodoro?action=list");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/pomodoro?action=list");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int userId = (Integer) session.getAttribute("user_id");
        String action = request.getParameter("action");

        if (action == null || action.equals("create")) {
            // Tạo pomodoro record mới
            try {
                int time = Integer.parseInt(request.getParameter("time"));
                if (time > 0) {
                    if (pomodoroService.createPomodoroRecord(time, userId)) {
                        response.sendRedirect(request.getContextPath() + "/pomodoro?action=list");
                    } else {
                        request.setAttribute("error", "Không thể lưu pomodoro record.");
                        response.sendRedirect(request.getContextPath() + "/pomodoro?action=list");
                    }
                } else {
                    request.setAttribute("error", "Thời gian phải lớn hơn 0.");
                    response.sendRedirect(request.getContextPath() + "/pomodoro?action=list");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Thời gian không hợp lệ.");
                response.sendRedirect(request.getContextPath() + "/pomodoro?action=list");
            }
        }
    }
}

