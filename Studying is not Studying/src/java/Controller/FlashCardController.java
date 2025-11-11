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
import model.FlashCard;
import service.FlashCardService;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "FlashCardController", urlPatterns = {"/flashcard"})
public class FlashCardController extends HttpServlet {

    private FlashCardService flashCardService = new FlashCardService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");
        String deckIdParam = request.getParameter("deckId");

        if (action == null || action.equals("list")) {
            // Hiển thị danh sách flash cards theo deckId
            if (deckIdParam != null) {
                try {
                    int deckId = Integer.parseInt(deckIdParam);
                    List<FlashCard> flashCards = flashCardService.getFlashCardsByDeckId(deckId);
                    request.setAttribute("flashCards", flashCards);
                    request.setAttribute("deckId", deckId);
                    RequestDispatcher rd = request.getRequestDispatcher("/pages/flashcard-detail.jsp");
                    rd.forward(request, response);
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/flashdeck");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/flashdeck");
            }
        } else if (action.equals("delete")) {
            // Xóa flash card
            try {
                int cardId = Integer.parseInt(request.getParameter("cardId"));
                String deckId = request.getParameter("deckId");
                if (flashCardService.deleteFlashCard(cardId)) {
                    response.sendRedirect(request.getContextPath() + "/flashcard?action=list&deckId=" + deckId);
                } else {
                    request.setAttribute("error", "Không thể xóa flash card.");
                    response.sendRedirect(request.getContextPath() + "/flashcard?action=list&deckId=" + deckId);
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/flashdeck");
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
        String deckIdParam = request.getParameter("deckId");

        if (action == null || action.equals("create")) {
            // Tạo flash card mới
            if (deckIdParam != null) {
                try {
                    int deckId = Integer.parseInt(deckIdParam);
                    String term = request.getParameter("term");
                    String definition = request.getParameter("definition");

                    if (term != null && !term.trim().isEmpty()) {
                        if (flashCardService.createFlashCard(term, definition, deckId)) {
                            response.sendRedirect(request.getContextPath() + "/flashcard?action=list&deckId=" + deckId);
                        } else {
                            request.setAttribute("error", "Không thể tạo flash card.");
                            response.sendRedirect(request.getContextPath() + "/flashcard?action=list&deckId=" + deckId);
                        }
                    } else {
                        request.setAttribute("error", "Term không được để trống.");
                        response.sendRedirect(request.getContextPath() + "/flashcard?action=list&deckId=" + deckId);
                    }
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/flashdeck");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/flashdeck");
            }
        } else if (action.equals("update")) {
            // Cập nhật flash card
            try {
                int cardId = Integer.parseInt(request.getParameter("cardId"));
                String deckId = request.getParameter("deckId");
                String term = request.getParameter("term");
                String definition = request.getParameter("definition");

                if (term != null && !term.trim().isEmpty()) {
                    if (flashCardService.updateFlashCard(cardId, term, definition)) {
                        response.sendRedirect(request.getContextPath() + "/flashcard?action=list&deckId=" + deckId);
                    } else {
                        request.setAttribute("error", "Không thể cập nhật flash card.");
                        response.sendRedirect(request.getContextPath() + "/flashcard?action=list&deckId=" + deckId);
                    }
                } else {
                    request.setAttribute("error", "Term không được để trống.");
                    response.sendRedirect(request.getContextPath() + "/flashcard?action=list&deckId=" + deckId);
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/flashdeck");
            }
        }
    }
}

