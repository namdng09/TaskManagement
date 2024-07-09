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
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Board;
import model.User;

/**
 *
 * @author namdng09
 */
public class ManagerServlet extends HttpServlet {

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
            out.println("<title>Servlet ManagerBoard</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerBoard at " + request.getContextPath() + "</h1>");
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
        String boardID = request.getParameter("boardID");
        String action = request.getParameter("action");
        if (action.equals("manageBoard")) {
            board = board.getBoardByBoardID(boardID);

            request.setAttribute("board", board);

            // Forward to JSP
            request.getRequestDispatcher("workspace-manage-board.jsp").forward(request, response);
        }

        if ("manageUser".equals(action)) {
            ArrayList<User> searchUser = null;
            board = board.getBoardByBoardID(boardID);
            ArrayList<User> members = board.getAllMembers(boardID);
            String name = request.getParameter("memberName");
            if (name != null) {
                searchUser = board.searchUserNotInBoard(boardID, name);
            }
            request.setAttribute("board", board);
            request.setAttribute("members", members);
            request.setAttribute("searchUser", searchUser);

            // Forward to JSP
            request.getRequestDispatcher("workspace-manage-user.jsp").forward(request, response);
        }

        if (action.equals("addMem")) {
            String userID = request.getParameter("memID");
            board.addMember(userID, boardID);
            response.sendRedirect("manager?boardID=" + boardID + "&action=manageUser");
        }

        if (action.equals("removeMem")) {
            String userID = request.getParameter("userID");
            board.removeMember(userID, boardID);
            response.sendRedirect("manager?boardID=" + boardID + "&action=manageUser");
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
        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect("home");
        }

        if ("manageBoard".equals(action)) {
            String id = request.getParameter("boardID");
            String name = request.getParameter("boardName");
            boolean permissions = Boolean.parseBoolean(request.getParameter("permissions"));
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            String backgroundImage = request.getParameter("backgroundImage");
            String description = request.getParameter("describe");
            Board board = new Board(id, name, permissions, status, backgroundImage, description);

            board.editBoard(board);
            response.sendRedirect("home");
        }

        if ("manageUser".equals(action)) {
            String work = request.getParameter("work");
            if (work.equals("searchMem")) {
                String name = request.getParameter("memberName");
                String boardID = request.getParameter("boardID");
                response.sendRedirect("manager?boardID=" + boardID + "&action=manageUser" + "&memberName=" + name);
            }

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
