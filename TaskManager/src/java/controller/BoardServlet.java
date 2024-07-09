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
import model.Board;
import model.ListTask;
import model.Card;
import model.CheckList;
import model.Comment;

/**
 *
 * @author namdng09
 */
public class BoardServlet extends HttpServlet {

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
            out.println("<title>Servlet BoardServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BoardServlet at " + request.getContextPath() + "</h1>");
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
        Board board = new Board();
        ListTask listTask = new ListTask();

        String id = request.getParameter("boardID");
        String action = request.getParameter("action");

        if (id == null && action == null) {
            response.sendRedirect("home"); // Redirect to home or error page if parameters are missing
            return;
        }

        if (action != null) {
            switch (action) {
                case "rename" -> {
                    String newName = request.getParameter("param");
                    board.editBoardName(id, newName);
                }

                case "deleteBoard" -> {
                    board.deleteBoard(id);
                    response.sendRedirect("home");
                }

                case "addMember" -> {
                    String useruidAdd = request.getParameter("param");
                    board.addMember(useruidAdd, id);
                }

                case "removeMember" -> {
                    String useruidRemove = request.getParameter("param");
                    board.removeMember(useruidRemove, id);
                }

                case "changePermission" -> {
                    boolean publiced = Boolean.parseBoolean(request.getParameter("param"));
                    board.changeAccessControl(id, publiced);
                }

                case "addNewListTask" -> {
                    String name = request.getParameter("name");
                    if (name != null) {
                        listTask.createListTask(id, name);
                    }
                }

            }
            response.sendRedirect("board?boardID=" + id);
        } else {
            board = board.getBoardByBoardID(id);
            request.setAttribute("board", board);
            request.getRequestDispatcher("workspace-board.jsp").forward(request, response);
        }
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
        Board board = new Board();
        ListTask listTask = new ListTask();

        String id = request.getParameter("boardID");
        String action = request.getParameter("action");

        if (id == null && action == null) {
            response.sendRedirect("home"); // Redirect to home or error page if parameters are missing
            return;
        }

        if (action != null) {
            switch (action) {
                case "rename" -> {
                    String newName = request.getParameter("name");
                    board.editBoardName(id, newName);
                }

                case "deleteBoard" -> {
                    board.deleteBoard(id);
                    response.sendRedirect("home");
                }

                case "addMember" -> {
                    String useruidAdd = request.getParameter("param");
                    board.addMember(useruidAdd, id);
                }

                case "removeMember" -> {
                    String useruidRemove = request.getParameter("param");
                    board.removeMember(useruidRemove, id);
                }

                case "changePermission" -> {
                    boolean publiced = Boolean.parseBoolean(request.getParameter("param"));
                    board.changeAccessControl(id, publiced);
                }

                case "addNewListTask" -> {
                    String name = request.getParameter("name");
                    if (!name.equals("")) {
                        listTask.createListTask(id, name);
                    }
                }

            }
            response.sendRedirect("board?boardID=" + id);
        } else {
            board = board.getBoardByBoardID(id);
            request.setAttribute("board", board);
            request.getRequestDispatcher("workspace-board.jsp").forward(request, response);
        }
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
