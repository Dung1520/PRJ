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
import model.FlashDeck;
import service.FlashDeckService;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "FlashDeckController", urlPatterns = {"/flashdeck"})
public class FlashDeckController extends HttpServlet {

    private FlashDeckService flashDeckService = new FlashDeckService();

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
            // Hiển thị danh sách flash decks
            List<FlashDeck> flashDecks = flashDeckService.getFlashDecksByUserId(userId);
            request.setAttribute("flashDecks", flashDecks);
            RequestDispatcher rd = request.getRequestDispatcher("/pages/flashcard.jsp");
            rd.forward(request, response);
        } else if (action.equals("delete")) {
            // Xóa flash deck
            try {
                int deckId = Integer.parseInt(request.getParameter("deckId"));
                if (flashDeckService.deleteFlashDeck(deckId)) {
                    response.sendRedirect(request.getContextPath() + "/flashdeck?action=list");
                } else {
                    request.setAttribute("error", "Không thể xóa flash deck.");
                    response.sendRedirect(request.getContextPath() + "/flashdeck?action=list");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/flashdeck?action=list");
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
            // Tạo flash deck mới
            String deckName = request.getParameter("deckName");
            if (deckName != null && !deckName.trim().isEmpty()) {
                if (flashDeckService.createFlashDeck(deckName, userId)) {
                    response.sendRedirect(request.getContextPath() + "/flashdeck?action=list");
                } else {
                    request.setAttribute("error", "Không thể tạo flash deck.");
                    RequestDispatcher rd = request.getRequestDispatcher("/pages/flashcard.jsp");
                    rd.forward(request, response);
                }
            } else {
                request.setAttribute("error", "Tên flash deck không được để trống.");
                RequestDispatcher rd = request.getRequestDispatcher("/pages/flashcard.jsp");
                rd.forward(request, response);
            }
        } else if (action.equals("update")) {
            // Cập nhật flash deck
            try {
                int deckId = Integer.parseInt(request.getParameter("deckId"));
                String deckName = request.getParameter("deckName");
                if (deckName != null && !deckName.trim().isEmpty()) {
                    if (flashDeckService.updateFlashDeck(deckId, deckName)) {
                        response.sendRedirect(request.getContextPath() + "/flashdeck?action=list");
                    } else {
                        request.setAttribute("error", "Không thể cập nhật flash deck.");
                        response.sendRedirect(request.getContextPath() + "/flashdeck?action=list");
                    }
                } else {
                    request.setAttribute("error", "Tên flash deck không được để trống.");
                    response.sendRedirect(request.getContextPath() + "/flashdeck?action=list");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/flashdeck?action=list");
            }
        }
    }
}

