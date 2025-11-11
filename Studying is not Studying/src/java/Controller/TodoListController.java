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
import model.TodoList;
import service.TodoListService;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "TodoListController", urlPatterns = {"/todolist"})
public class TodoListController extends HttpServlet {

    private TodoListService todoListService = new TodoListService();

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
            // Hiển thị danh sách todo lists
            List<TodoList> todoLists = todoListService.getTodoListsByUserId(userId);
            request.setAttribute("todoLists", todoLists);
            RequestDispatcher rd = request.getRequestDispatcher("/pages/todolist.jsp");
            rd.forward(request, response);
        } else if (action.equals("delete")) {
            // Xóa todo list
            try {
                int listId = Integer.parseInt(request.getParameter("listId"));
                if (todoListService.deleteTodoList(listId)) {
                    response.sendRedirect(request.getContextPath() + "/todolist?action=list");
                } else {
                    request.setAttribute("error", "Không thể xóa todo list.");
                    response.sendRedirect(request.getContextPath() + "/todolist?action=list");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/todolist?action=list");
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
            // Tạo todo list mới
            String listName = request.getParameter("listName");
            if (listName != null && !listName.trim().isEmpty()) {
                if (todoListService.createTodoList(userId, listName)) {
                    response.sendRedirect(request.getContextPath() + "/todolist?action=list");
                } else {
                    request.setAttribute("error", "Không thể tạo todo list.");
                    RequestDispatcher rd = request.getRequestDispatcher("/pages/todolist.jsp");
                    rd.forward(request, response);
                }
            } else {
                request.setAttribute("error", "Tên todo list không được để trống.");
                RequestDispatcher rd = request.getRequestDispatcher("/pages/todolist.jsp");
                rd.forward(request, response);
            }
        } else if (action.equals("update")) {
            // Cập nhật todo list
            try {
                int listId = Integer.parseInt(request.getParameter("listId"));
                String listName = request.getParameter("listName");
                if (listName != null && !listName.trim().isEmpty()) {
                    if (todoListService.updateTodoList(listId, listName)) {
                        response.sendRedirect(request.getContextPath() + "/todolist?action=list");
                    } else {
                        request.setAttribute("error", "Không thể cập nhật todo list.");
                        response.sendRedirect(request.getContextPath() + "/todolist?action=list");
                    }
                } else {
                    request.setAttribute("error", "Tên todo list không được để trống.");
                    response.sendRedirect(request.getContextPath() + "/todolist?action=list");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/todolist?action=list");
            }
        }
    }
}

