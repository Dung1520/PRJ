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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import model.Task;
import service.TaskService;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "TaskController", urlPatterns = {"/task"})
public class TaskController extends HttpServlet {

    private TaskService taskService = new TaskService();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");
        String listIdParam = request.getParameter("listId");

        if (action == null || action.equals("list")) {
            // Hiển thị danh sách tasks theo listId
            if (listIdParam != null) {
                try {
                    int listId = Integer.parseInt(listIdParam);
                    List<Task> tasks = taskService.getTasksByListId(listId);
                    request.setAttribute("tasks", tasks);
                    request.setAttribute("listId", listId);
                    RequestDispatcher rd = request.getRequestDispatcher("/pages/task.jsp");
                    rd.forward(request, response);
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/todolist");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/todolist");
            }
        } else if (action.equals("delete")) {
            // Xóa task
            try {
                int taskId = Integer.parseInt(request.getParameter("taskId"));
                String listId = request.getParameter("listId");
                if (taskService.deleteTask(taskId)) {
                    response.sendRedirect(request.getContextPath() + "/task?action=list&listId=" + listId);
                } else {
                    request.setAttribute("error", "Không thể xóa task.");
                    response.sendRedirect(request.getContextPath() + "/task?action=list&listId=" + listId);
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/todolist");
            }
        } else if (action.equals("toggle")) {
            // Toggle completion status
            try {
                int taskId = Integer.parseInt(request.getParameter("taskId"));
                boolean isCompleted = Boolean.parseBoolean(request.getParameter("isCompleted"));
                String listId = request.getParameter("listId");
                if (taskService.toggleTaskCompletion(taskId, isCompleted)) {
                    response.sendRedirect(request.getContextPath() + "/task?action=list&listId=" + listId);
                } else {
                    request.setAttribute("error", "Không thể cập nhật trạng thái task.");
                    response.sendRedirect(request.getContextPath() + "/task?action=list&listId=" + listId);
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/todolist");
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

        String action = request.getParameter("action");
        String listIdParam = request.getParameter("listId");

        if (action == null || action.equals("create")) {
            // Tạo task mới
            if (listIdParam != null) {
                try {
                    int listId = Integer.parseInt(listIdParam);
                    String taskName = request.getParameter("taskName");
                    String description = request.getParameter("description");
                    String fromDateStr = request.getParameter("fromDate");
                    String dueDateStr = request.getParameter("dueDate");

                    Timestamp fromDate = null;
                    Timestamp dueDate = null;

                    try {
                        if (fromDateStr != null && !fromDateStr.trim().isEmpty()) {
                            fromDate = new Timestamp(dateFormat.parse(fromDateStr).getTime());
                        }
                        if (dueDateStr != null && !dueDateStr.trim().isEmpty()) {
                            dueDate = new Timestamp(dateFormat.parse(dueDateStr).getTime());
                        }
                    } catch (ParseException e) {
                        request.setAttribute("error", "Định dạng ngày không hợp lệ.");
                        response.sendRedirect(request.getContextPath() + "/task?action=list&listId=" + listId);
                        return;
                    }

                    if (taskName != null && !taskName.trim().isEmpty()) {
                        if (taskService.createTask(taskName, description, fromDate, dueDate, listId)) {
                            response.sendRedirect(request.getContextPath() + "/task?action=list&listId=" + listId);
                        } else {
                            request.setAttribute("error", "Không thể tạo task.");
                            response.sendRedirect(request.getContextPath() + "/task?action=list&listId=" + listId);
                        }
                    } else {
                        request.setAttribute("error", "Tên task không được để trống.");
                        response.sendRedirect(request.getContextPath() + "/task?action=list&listId=" + listId);
                    }
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/todolist");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/todolist");
            }
        } else if (action.equals("update")) {
            // Cập nhật task
            try {
                int taskId = Integer.parseInt(request.getParameter("taskId"));
                String listId = request.getParameter("listId");
                String taskName = request.getParameter("taskName");
                String description = request.getParameter("description");
                String fromDateStr = request.getParameter("fromDate");
                String dueDateStr = request.getParameter("dueDate");

                Timestamp fromDate = null;
                Timestamp dueDate = null;

                try {
                    if (fromDateStr != null && !fromDateStr.trim().isEmpty()) {
                        fromDate = new Timestamp(dateFormat.parse(fromDateStr).getTime());
                    }
                    if (dueDateStr != null && !dueDateStr.trim().isEmpty()) {
                        dueDate = new Timestamp(dateFormat.parse(dueDateStr).getTime());
                    }
                } catch (ParseException e) {
                    request.setAttribute("error", "Định dạng ngày không hợp lệ.");
                    response.sendRedirect(request.getContextPath() + "/task?action=list&listId=" + listId);
                    return;
                }

                if (taskName != null && !taskName.trim().isEmpty()) {
                    if (taskService.updateTask(taskId, taskName, description, fromDate, dueDate)) {
                        response.sendRedirect(request.getContextPath() + "/task?action=list&listId=" + listId);
                    } else {
                        request.setAttribute("error", "Không thể cập nhật task.");
                        response.sendRedirect(request.getContextPath() + "/task?action=list&listId=" + listId);
                    }
                } else {
                    request.setAttribute("error", "Tên task không được để trống.");
                    response.sendRedirect(request.getContextPath() + "/task?action=list&listId=" + listId);
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/todolist");
            }
        }
    }
}

