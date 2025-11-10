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
import Model.Flashcards;
import Model.Flashdecks;
import java.util.List;
import Service.FlashcardService;

/**
 *
 * @author Admin
 */
@WebServlet(name = "FlashcardController", urlPatterns = {"/flashcard"})
public class FlashcardController extends HttpServlet {

    private FlashcardService flashcardService = new FlashcardService();
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
            Object idObj = session.getAttribute("id");
            if (idObj != null) {
                userId = Integer.parseInt(idObj.toString());
            } else {
                response.sendRedirect(request.getContextPath() + "/session/login.jsp");
                return;
            }
        }

        String deckIdStr = request.getParameter("deckId");
        
        if (action == null || action.equals("list")) {
            // List flashcards by deck
            if (deckIdStr != null) {
                try {
                    int deckId = Integer.parseInt(deckIdStr);
                    Flashdecks deck = flashdeckService.getFlashdeckById(deckId);
                    if (deck != null && deck.getUserId() == userId) {
                        List<Flashcards> flashcards = flashcardService.getFlashcardsByDeckId(deckId);
                        request.setAttribute("deck", deck);
                        request.setAttribute("flashcards", flashcards);
                        RequestDispatcher rd = request.getRequestDispatcher("/pages/quiz.jsp");
                        rd.forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                    // Invalid ID
                }
            }
            response.sendRedirect(request.getContextPath() + "/flashdeck?action=list");
        } else if (action.equals("delete")) {
            // Delete flashcard
            String cardIdStr = request.getParameter("cardId");
            if (cardIdStr != null && deckIdStr != null) {
                try {
                    int cardId = Integer.parseInt(cardIdStr);
                    flashcardService.deleteFlashcard(cardId);
                    response.sendRedirect(request.getContextPath() + "/flashcard?action=list&deckId=" + deckIdStr);
                    return;
                } catch (NumberFormatException e) {
                    // Invalid ID
                }
            }
            response.sendRedirect(request.getContextPath() + "/flashdeck?action=list");
        } else if (action.equals("edit")) {
            // Show edit form
            String cardIdStr = request.getParameter("cardId");
            if (cardIdStr != null && deckIdStr != null) {
                try {
                    int cardId = Integer.parseInt(cardIdStr);
                    int deckId = Integer.parseInt(deckIdStr);
                    Flashdecks deck = flashdeckService.getFlashdeckById(deckId);
                    if (deck != null && deck.getUserId() == userId) {
                        Flashcards card = flashcardService.getFlashcardById(cardId);
                        if (card != null && card.getDeckId() == deckId) {
                            request.setAttribute("deck", deck);
                            request.setAttribute("card", card);
                            RequestDispatcher rd = request.getRequestDispatcher("/pages/quiz.jsp");
                            rd.forward(request, response);
                            return;
                        }
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
        String deckIdStr = request.getParameter("deckId");
        String term = request.getParameter("term");
        String definition = request.getParameter("definition");

        if (action == null || action.equals("create")) {
            // Create new flashcard
            if (deckIdStr != null && term != null && definition != null 
                    && !term.trim().isEmpty() && !definition.trim().isEmpty()) {
                try {
                    int deckId = Integer.parseInt(deckIdStr);
                    Flashdecks deck = flashdeckService.getFlashdeckById(deckId);
                    if (deck != null && deck.getUserId() == userId) {
                        flashcardService.createFlashcard(deckId, term, definition);
                    }
                } catch (NumberFormatException e) {
                    // Invalid ID
                }
            }
            if (deckIdStr != null) {
                response.sendRedirect(request.getContextPath() + "/flashcard?action=list&deckId=" + deckIdStr);
            } else {
                response.sendRedirect(request.getContextPath() + "/flashdeck?action=list");
            }
        } else if (action.equals("update")) {
            // Update flashcard
            String cardIdStr = request.getParameter("cardId");
            if (cardIdStr != null && deckIdStr != null && term != null && definition != null
                    && !term.trim().isEmpty() && !definition.trim().isEmpty()) {
                try {
                    int cardId = Integer.parseInt(cardIdStr);
                    int deckId = Integer.parseInt(deckIdStr);
                    Flashdecks deck = flashdeckService.getFlashdeckById(deckId);
                    if (deck != null && deck.getUserId() == userId) {
                        Flashcards card = flashcardService.getFlashcardById(cardId);
                        if (card != null && card.getDeckId() == deckId) {
                            flashcardService.updateFlashcard(cardId, term, definition);
                        }
                    }
                } catch (NumberFormatException e) {
                    // Invalid ID
                }
            }
            if (deckIdStr != null) {
                response.sendRedirect(request.getContextPath() + "/flashcard?action=list&deckId=" + deckIdStr);
            } else {
                response.sendRedirect(request.getContextPath() + "/flashdeck?action=list");
            }
        }
    }
}

