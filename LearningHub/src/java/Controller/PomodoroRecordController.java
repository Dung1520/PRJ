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
import jakarta.servlet.http.HttpSession;
import Service.PomodoroRecordService;
import Model.PomodoroRecord;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
@WebServlet(name = "PomodoroRecordController", urlPatterns = {"/pomodoro"})
public class PomodoroRecordController extends HttpServlet {

    private PomodoroRecordService pomodoroService = new PomodoroRecordService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("Logined") == null) {
            response.sendRedirect(request.getContextPath() + "/session/login.jsp");
            return;
        }

        String action = request.getParameter("action");
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId == null) {
            Object idObj = session.getAttribute("id");
            if (idObj != null) {
                userId = Integer.parseInt(idObj.toString());
            } else {
                response.sendRedirect(request.getContextPath() + "/session/login.jsp");
                return;
            }
        }

        if (action == null || action.equals("list")) {
            // List all pomodoro records
            List<PomodoroRecord> records = pomodoroService.getPomodoroRecordsByUserId(userId);
            int totalFocusTime = pomodoroService.getTotalFocusTimeByUserId(userId);
            request.setAttribute("records", records);
            request.setAttribute("totalFocusTime", totalFocusTime);
            RequestDispatcher rd = request.getRequestDispatcher("/pages/home.jsp");
            rd.forward(request, response);
        } else if (action.equals("delete")) {
            // Delete pomodoro record
            String recordIdStr = request.getParameter("recordId");
            if (recordIdStr != null) {
                try {
                    int recordId = Integer.parseInt(recordIdStr);
                    pomodoroService.deletePomodoroRecord(recordId);
                } catch (NumberFormatException e) {
                    // Invalid ID
                }
            }
            response.sendRedirect(request.getContextPath() + "/pomodoro?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("Logined") == null) {
            response.sendRedirect(request.getContextPath() + "/session/login.jsp");
            return;
        }

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            Object idObj = session.getAttribute("id");
            if (idObj != null) {
                userId = Integer.parseInt(idObj.toString());
            } else {
                response.sendRedirect(request.getContextPath() + "/session/login.jsp");
                return;
            }
        }

        String focusTimeStr = request.getParameter("focusTime");
        String recordDateStr = request.getParameter("recordDate");

        // Create new pomodoro record
        if (focusTimeStr != null) {
            try {
                int focusTime = Integer.parseInt(focusTimeStr);
                Date recordDate = null;
                if (recordDateStr != null && !recordDateStr.trim().isEmpty()) {
                    recordDate = Date.valueOf(recordDateStr);
                } else {
                    recordDate = new Date(System.currentTimeMillis());
                }
                pomodoroService.createPomodoroRecord(userId, focusTime, recordDate);
            } catch (IllegalArgumentException e) {
                // Invalid format
            }
        }
        response.sendRedirect(request.getContextPath() + "/pomodoro?action=list");
    }
}

