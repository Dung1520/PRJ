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
import Service.ListService;
import Model.Lists;
import java.util.List;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ListController", urlPatterns = {"/list"})
public class ListController extends HttpServlet {

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

        if (action == null || action.equals("list")) {
            // List all lists
            List<Lists> lists = listService.getListsByUserId(userId);
            request.setAttribute("lists", lists);
            RequestDispatcher rd = request.getRequestDispatcher("/pages/todolist.jsp");
            rd.forward(request, response);
        } else if (action.equals("delete")) {
            // Delete list
            String listIdStr = request.getParameter("listId");
            if (listIdStr != null) {
                try {
                    int listId = Integer.parseInt(listIdStr);
                    listService.deleteList(listId);
                } catch (NumberFormatException e) {
                    // Invalid ID
                }
            }
            response.sendRedirect(request.getContextPath() + "/list?action=list");
        } else if (action.equals("edit")) {
            // Show edit form
            String listIdStr = request.getParameter("listId");
            if (listIdStr != null) {
                try {
                    int listId = Integer.parseInt(listIdStr);
                    Lists list = listService.getListById(listId);
                    if (list != null && list.getUserId() == userId) {
                        request.setAttribute("list", list);
                        RequestDispatcher rd = request.getRequestDispatcher("/pages/todolist.jsp");
                        rd.forward(request, response);
                        return;
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
        String listName = request.getParameter("listName");

        if (action == null || action.equals("create")) {
            // Create new list
            if (listName != null && !listName.trim().isEmpty()) {
                listService.createList(userId, listName);
            }
            response.sendRedirect(request.getContextPath() + "/list?action=list");
        } else if (action.equals("update")) {
            // Update list
            String listIdStr = request.getParameter("listId");
            if (listIdStr != null && listName != null && !listName.trim().isEmpty()) {
                try {
                    int listId = Integer.parseInt(listIdStr);
                    Lists list = listService.getListById(listId);
                    if (list != null && list.getUserId() == userId) {
                        listService.updateList(listId, listName);
                    }
                } catch (NumberFormatException e) {
                    // Invalid ID
                }
            }
            response.sendRedirect(request.getContextPath() + "/list?action=list");
        }
    }
}

