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
import Service.FlashdeckService;
import Model.Flashdecks;
import java.util.List;

/**
 *
 * @author Admin
 */
@WebServlet(name = "FlashdeckController", urlPatterns = {"/flashdeck"})
public class FlashdeckController extends HttpServlet {

    private FlashdeckService flashdeckService = new FlashdeckService();

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
            // Try to get userId from session attribute "id" or "user_id"
            Object idObj = session.getAttribute("id");
            if (idObj != null) {
                userId = Integer.parseInt(idObj.toString());
            } else {
                response.sendRedirect(request.getContextPath() + "/session/login.jsp");
                return;
            }
        }

        if (action == null || action.equals("list")) {
            // List all flashdecks
            List<Flashdecks> flashdecks = flashdeckService.getFlashdecksByUserId(userId);
            request.setAttribute("flashdecks", flashdecks);
            RequestDispatcher rd = request.getRequestDispatcher("/pages/flashcard.jsp");
            rd.forward(request, response);
        } else if (action.equals("delete")) {
            // Delete flashdeck
            String deckIdStr = request.getParameter("deckId");
            if (deckIdStr != null) {
                try {
                    int deckId = Integer.parseInt(deckIdStr);
                    flashdeckService.deleteFlashdeck(deckId);
                } catch (NumberFormatException e) {
                    // Invalid ID
                }
            }
            response.sendRedirect(request.getContextPath() + "/flashdeck?action=list");
        } else if (action.equals("edit")) {
            // Show edit form
            String deckIdStr = request.getParameter("deckId");
            if (deckIdStr != null) {
                try {
                    int deckId = Integer.parseInt(deckIdStr);
                    Flashdecks deck = flashdeckService.getFlashdeckById(deckId);
                    if (deck != null && deck.getUserId() == userId) {
                        request.setAttribute("deck", deck);
                        RequestDispatcher rd = request.getRequestDispatcher("/pages/flashcard.jsp");
                        rd.forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                    // Invalid ID
                }
            }
            response.sendRedirect(request.getContextPath() + "/flashdeck?action=list");
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
        String deckName = request.getParameter("deckName");

        if (action == null || action.equals("create")) {
            // Create new flashdeck
            if (deckName != null && !deckName.trim().isEmpty()) {
                flashdeckService.createFlashdeck(userId, deckName);
            }
            response.sendRedirect(request.getContextPath() + "/flashdeck?action=list");
        } else if (action.equals("update")) {
            // Update flashdeck
            String deckIdStr = request.getParameter("deckId");
            if (deckIdStr != null && deckName != null && !deckName.trim().isEmpty()) {
                try {
                    int deckId = Integer.parseInt(deckIdStr);
                    Flashdecks deck = flashdeckService.getFlashdeckById(deckId);
                    if (deck != null && deck.getUserId() == userId) {
                        flashdeckService.updateFlashdeck(deckId, deckName);
                    }
                } catch (NumberFormatException e) {
                    // Invalid ID
                }
            }
            response.sendRedirect(request.getContextPath() + "/flashdeck?action=list");
        }
    }
}

