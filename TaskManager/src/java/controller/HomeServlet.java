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
import java.util.List;
import model.Board;
import model.User;

/**
 *
 * @author namdng09
 */
public class HomeServlet extends HttpServlet {

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
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(false); // Use false to prevent creating a new session
        if (session == null || session.getAttribute("account") == null) {
            response.sendRedirect("login"); // Redirect to login page if not logged in
            return;
        }

        User user = (User) session.getAttribute("account");
        Board board = new Board();

        // Get the current page number, default to 1 if not provided
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Number of items per page
        int itemsPerPage = 5;

        // Get the total number of boards
        ArrayList<Board> listBoardlist = board.getAllBoardByUserUID(user.getUserUID());
        int totalItems = listBoardlist.size();

        // Calculate start and end indexes for the current page
        int start = (page - 1) * itemsPerPage;
        int end = Math.min(start + itemsPerPage, totalItems);

        // Get the sublist for the current page
        List<Board> boardsForPage = listBoardlist.subList(start, end);

        // Calculate the total number of pages
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        // Set attributes for JSP
        request.setAttribute("boards", boardsForPage);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // Forward to JSP
        request.getRequestDispatcher("workspace-dashboards.jsp").forward(request, response);
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
        HttpSession session = request.getSession(false); // Use false to prevent creating a new session
        if (session == null || session.getAttribute("account") == null) {
            response.sendRedirect("login"); // Redirect to login page if not logged in
            return;
        }

        User user = (User) session.getAttribute("account");
        Board board = new Board();

        // Get the current page number, default to 1 if not provided
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Number of items per page
        int itemsPerPage = 5;

        String searchName = request.getParameter("searchBoard");
        
        // Get the total number of boards
        ArrayList<Board> listBoardlist = board.searchBoardsByName(user.getUserUID(), searchName);
        int totalItems = listBoardlist.size();

        // Calculate start and end indexes for the current page
        int start = (page - 1) * itemsPerPage;
        int end = Math.min(start + itemsPerPage, totalItems);

        // Get the sublist for the current page
        List<Board> boardsForPage = listBoardlist.subList(start, end);

        // Calculate the total number of pages
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        // Set attributes for JSP
        request.setAttribute("boards", boardsForPage);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // Forward to JSP
        request.getRequestDispatcher("workspace-dashboards.jsp").forward(request, response);
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
