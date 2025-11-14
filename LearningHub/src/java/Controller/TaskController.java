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
import Service.TaskService;
import Service.ListService;
import Model.Tasks;
import Model.Lists;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
@WebServlet(name = "TaskController", urlPatterns = {"/task"})
public class TaskController extends HttpServlet {

    private TaskService taskService = new TaskService();
    private ListService listService = new ListService();

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

        String listIdStr = request.getParameter("listId");
        
        if (action == null || action.equals("list")) {
            // List tasks by list
            if (listIdStr != null) {
                try {
                    int listId = Integer.parseInt(listIdStr);
                    Lists list = listService.getListById(listId);
                    if (list != null && list.getUserId() == userId) {
                        List<Tasks> tasks = taskService.getTasksByListId(listId);
                        request.setAttribute("list", list);
                        request.setAttribute("tasks", tasks);
                        RequestDispatcher rd = request.getRequestDispatcher("/pages/todolist.jsp");
                        rd.forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                    // Invalid ID
                }
            }
            response.sendRedirect(request.getContextPath() + "/list?action=list");
        } else if (action.equals("delete")) {
            // Delete task
            String taskIdStr = request.getParameter("taskId");
            if (taskIdStr != null && listIdStr != null) {
                try {
                    int taskId = Integer.parseInt(taskIdStr);
                    taskService.deleteTask(taskId);
                    response.sendRedirect(request.getContextPath() + "/task?action=list&listId=" + listIdStr);
                    return;
                } catch (NumberFormatException e) {
                    // Invalid ID
                }
            }
            response.sendRedirect(request.getContextPath() + "/list?action=list");
        } else if (action.equals("toggle")) {
            // Toggle task completion
            String taskIdStr = request.getParameter("taskId");
            String completedStr = request.getParameter("completed");
            if (taskIdStr != null && completedStr != null && listIdStr != null) {
                try {
                    int taskId = Integer.parseInt(taskIdStr);
                    boolean completed = Boolean.parseBoolean(completedStr);
                    taskService.toggleTaskCompletion(taskId, completed);
                    response.sendRedirect(request.getContextPath() + "/task?action=list&listId=" + listIdStr);
                    return;
                } catch (NumberFormatException e) {
                    // Invalid ID
                }
            }
            response.sendRedirect(request.getContextPath() + "/list?action=list");
        } else if (action.equals("edit")) {
            // Show edit form
            String taskIdStr = request.getParameter("taskId");
            if (taskIdStr != null && listIdStr != null) {
                try {
                    int taskId = Integer.parseInt(taskIdStr);
                    int listId = Integer.parseInt(listIdStr);
                    Lists list = listService.getListById(listId);
                    if (list != null && list.getUserId() == userId) {
                        Tasks task = taskService.getTaskById(taskId);
                        if (task != null && task.getListId() == listId) {
                            request.setAttribute("list", list);
                            request.setAttribute("task", task);
                            RequestDispatcher rd = request.getRequestDispatcher("/pages/todolist.jsp");
                            rd.forward(request, response);
                            return;
                        }
                    }
                } catch (NumberFormatException e) {
                    // Invalid ID
                }
            }
            response.sendRedirect(request.getContextPath() + "/list?action=list");
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

        String action = request.getParameter("action");
        String listIdStr = request.getParameter("listId");
        String taskName = request.getParameter("taskName");
        String description = request.getParameter("description");
        String dueDateStr = request.getParameter("dueDate");

        if (action == null || action.equals("create")) {
            // Create new task
            if (listIdStr != null && taskName != null && !taskName.trim().isEmpty()) {
                try {
                    int listId = Integer.parseInt(listIdStr);
                    Lists list = listService.getListById(listId);
                    if (list != null && list.getUserId() == userId) {
                        Date dueDate = null;
                        if (dueDateStr != null && !dueDateStr.trim().isEmpty()) {
                            dueDate = Date.valueOf(dueDateStr);
                        }
                        taskService.createTask(listId, taskName, description, dueDate);
                    }
                } catch (IllegalArgumentException e) {
                    // Invalid ID or date format
                }
            }
            if (listIdStr != null) {
                response.sendRedirect(request.getContextPath() + "/task?action=list&listId=" + listIdStr);
            } else {
                response.sendRedirect(request.getContextPath() + "/list?action=list");
            }
        } else if (action.equals("update")) {
            // Update task
            String taskIdStr = request.getParameter("taskId");
            String completedStr = request.getParameter("isCompleted");
            if (taskIdStr != null && listIdStr != null && taskName != null && !taskName.trim().isEmpty()) {
                try {
                    int taskId = Integer.parseInt(taskIdStr);
                    int listId = Integer.parseInt(listIdStr);
                    Lists list = listService.getListById(listId);
                    if (list != null && list.getUserId() == userId) {
                        Tasks task = taskService.getTaskById(taskId);
                        if (task != null && task.getListId() == listId) {
                            Date dueDate = null;
                            if (dueDateStr != null && !dueDateStr.trim().isEmpty()) {
                                dueDate = Date.valueOf(dueDateStr);
                            }
                            boolean isCompleted = completedStr != null && Boolean.parseBoolean(completedStr);
                            taskService.updateTask(taskId, taskName, description, dueDate, isCompleted);
                        }
                    }
                } catch (IllegalArgumentException e) {
                    // Invalid ID or date format
                }
            }
            if (listIdStr != null) {
                response.sendRedirect(request.getContextPath() + "/task?action=list&listId=" + listIdStr);
            } else {
                response.sendRedirect(request.getContextPath() + "/list?action=list");
            }
        }
    }
}

