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
import model.Board;
import model.User;

/**
 *
 * @author namdng09
 */
public class CreateBoardServlet extends HttpServlet {

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
            out.println("<title>Servlet CreateBoardServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateBoardServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("workspace-create-a-board.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Use false to prevent creating a new session
        Board board = new Board();
        User user = (User) session.getAttribute("account");

        if (session == null || session.getAttribute("account") == null) {
            response.sendRedirect("login"); // Redirect to login page if not logged in
            return;
        }

        String message;
        String status;
        try {
            String name = request.getParameter("boardName");
            boolean publiced = Boolean.parseBoolean(request.getParameter("permissions"));
            String image = request.getParameter("backgroundImage");
            String description = request.getParameter("describe");

            board.checkValidBoardName(name);

            board.createBoard(user.getUserUID(), name, publiced, description, image);
            status = "success";
            message = "Board created successfully!";
        } catch (Exception e) {
            status = "error";
            message = e.getMessage();
        }

        request.setAttribute("status", status);
        request.setAttribute("message", message);
        request.getRequestDispatcher("workspace-create-a-board.jsp").forward(request, response);
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
