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
import model.ListTask;
import model.Card;

/**
 *
 * @author namdng09
 */
public class ListTaskServlet extends HttpServlet {

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
            out.println("<title>Servlet ListTaskServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListTaskServlet at " + request.getContextPath() + "</h1>");
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
        ListTask listTask = new ListTask();
        Card card = new Card();
        String id = request.getParameter("listTaskID");
        String action = request.getParameter("action");
        String boardID = request.getParameter("boardID");

        switch (action) {
            case "removeListTask" -> {
                listTask.removeListTask(id);
            }

            case "renameListTask" -> {
                String name = request.getParameter("name");
                listTask.renameListTask(id, name);
            }

            case "addNewCard" -> {
                String name = request.getParameter("param");
                if (!name.equals("")) {
                    card.createCard(id, name);
                }
            }

        }
        response.sendRedirect("board?boardID=" + boardID);

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
        ListTask listTask = new ListTask();
        Card card = new Card();
        String id = request.getParameter("listTaskID");
        String action = request.getParameter("action");
        String boardID = request.getParameter("boardID");

        switch (action) {
            case "removeListTask" -> {
                listTask.removeListTask(id);
            }

            case "renameListTask" -> {
                String name = request.getParameter("name");
                if (!name.equals("")) {
                    listTask.renameListTask(id, name);
                }
            }

            case "addNewCard" -> {
                String name = request.getParameter("cardName");
                if (!name.equals("")) {
                    card.createCard(id, name);
                }
            }
        }
        response.sendRedirect("board?boardID=" + boardID);
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
