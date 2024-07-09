/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.Card;
import model.Comment;
import model.CheckList;

/**
 *
 * @author namdng09
 */
public class CardServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CardServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CardServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Card card = new Card();
        CheckList checkList = new CheckList();
        Comment comment = new Comment();
        String id = request.getParameter("cardID");
        String action = request.getParameter("action");
        String boardID = request.getParameter("boardID");

        switch (action) {
            case "removeCard" -> {
                card.removeCard(id);
            }

            case "renameCard" -> {
                String name = request.getParameter("param");
                card.editCardName(id, name);
            }

            case "moveCard" -> {
                String moveID = request.getParameter("moveID");
                card.moveCard(id, moveID);
                response.sendRedirect("board?boardID=" + boardID);
            }

            case "changeDescription" -> {
                String text = request.getParameter("textarea");
                if (!text.equals("")) {
                    card.editCardDescription(id, text);
                }
            }

            case "setDueDate" -> {
                String meetingTimeString = request.getParameter("date");
                if (meetingTimeString == null || meetingTimeString.isEmpty()) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing date-time parameter");
                    return;
                }

                try {
                    // Specify the date-time format matching the HTML input
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                    java.util.Date parsedDate = format.parse(meetingTimeString);
                    Timestamp timestamp = new Timestamp(parsedDate.getTime());

                    card.setCardDueDate(id, timestamp);

                } catch (ParseException e) {
                    e.printStackTrace(); // Consider logging this instead
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date-time format");
                    return;
                }
            }

            case "addCheckList" -> {
                String name = request.getParameter("param");
                checkList.createCheckList(id, name);
            }

            case "renameCheckList" -> {
                String checkListID = request.getParameter("checkListID");
                String name = request.getParameter("param");
                checkList.editCheckListTitle(checkListID, name);
            }

            case "removeCheckList" -> {
                String checkListID = request.getParameter("checkListID");
                checkList.removeCheckList(checkListID);
            }

            case "checked" -> {
                String checkListID = request.getParameter("checkListID");
                boolean checked = Boolean.parseBoolean(request.getParameter("param"));
                checkList.editCheckListChecked(checkListID, checked);
            }

            case "addComment" -> {
                String uid = request.getParameter("user_uid");
                String text = request.getParameter("textarea");
                if (!text.equals("")) {
                    comment.createComment(id, uid, text);
                }
            }

            case "editComment" -> {
                String commentID = request.getParameter("commentID");
                String text = request.getParameter("comment");
                if (!text.equals("")) {
                    comment.editComment(commentID, text);
                }
            }

            case "removeComment" -> {
                String commentID = request.getParameter("commentID");
                comment.removeComment(commentID);
            }
        }
        response.sendRedirect("board?boardID=" + boardID + "&#cardModal_" + id);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Card card = new Card();
        CheckList checkList = new CheckList();
        Comment comment = new Comment();
        String id = request.getParameter("cardID");
        String action = request.getParameter("action");
        String boardID = request.getParameter("boardID");

        switch (action) {
            case "removeCard" -> {
                card.removeCard(id);
            }

            case "renameCard" -> {
                String name = request.getParameter("param");
                card.editCardName(id, name);
            }

            case "moveCard" -> {
                String moveID = request.getParameter("listTaskID");
                card.moveCard(id, moveID);
                response.sendRedirect("board?boardID=" + boardID);
            }

            case "changeDescription" -> {
                String text = request.getParameter("textarea");
                if (!text.equals("")) {
                    card.editCardDescription(id, text);
                }
            }

            case "setDueDate" -> {
                String meetingTimeString = request.getParameter("dateTime");
                if (meetingTimeString == null || meetingTimeString.isEmpty()) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing date-time parameter");
                    return;
                }

                try {
                    // Specify the date-time format matching the HTML input
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                    java.util.Date parsedDate = format.parse(meetingTimeString);
                    Timestamp timestamp = new Timestamp(parsedDate.getTime());

                    card.setCardDueDate(id, timestamp);

                } catch (ParseException e) {
                    e.printStackTrace(); // Consider logging this instead
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date-time format");
                    return;
                }
            }

            case "addCheckList" -> {
                String name = request.getParameter("checkListName");
                if (!name.equals("")) {
                    checkList.createCheckList(id, name);
                }
            }

            case "renameCheckList" -> {
                String checkListID = request.getParameter("checkListID");
                String name = request.getParameter("param");
                checkList.editCheckListTitle(checkListID, name);
            }

            case "removeCheckList" -> {
                String checkListID = request.getParameter("checkListID");
                checkList.removeCheckList(checkListID);
            }

            case "updateCheckListStatus" -> {
                String checkListID = request.getParameter("checkListID");
                boolean checked = request.getParameter("checkListStatus") != null;
                checkList.editCheckListChecked(checkListID, checked);
            }

            case "addComment" -> {
                String uid = request.getParameter("user_uid");
                String text = request.getParameter("textarea");
                if (!text.equals("")) {
                    comment.createComment(id, uid, text);
                }
            }

            case "editComment" -> {
                String commentID = request.getParameter("commentID");
                String text = request.getParameter("comment");
                if (!text.equals("")) {
                    comment.editComment(commentID, text);
                }
            }

            case "removeComment" -> {
                String commentID = request.getParameter("commentID");
                comment.removeComment(commentID);
            }
        }
        response.sendRedirect("board?boardID=" + boardID + "&#cardModal_" + id);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
