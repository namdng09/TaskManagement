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
import java.sql.Date;
import model.User;

/**
 *
 * @author namdng09
 */
public class SettingServlet extends HttpServlet {

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
            out.println("<title>Servlet SettingServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SettingServlet at " + request.getContextPath() + "</h1>");
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
        User user = (User) session.getAttribute("account");

        if (session == null || session.getAttribute("account") == null) {
            response.sendRedirect("login"); // Redirect to login page if not logged in
            return;
        }
        User account = user.getAccountByUsername(user.getUsername());

        request.setAttribute("user", account);
        request.getRequestDispatcher("workspace-setting-account.jsp").forward(request, response);
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
        User user = (User) session.getAttribute("account");

        if (session == null || session.getAttribute("account") == null) {
            response.sendRedirect("login"); // Redirect to login page if not logged in
            return;
        }

        String message;
        String status;
        try {
            String fName = request.getParameter("fName");
            String lName = request.getParameter("lName");
            Date birthDate = Date.valueOf(request.getParameter("birthDate"));

            user = new User(user.getUserUID(), fName, lName, birthDate);
            user.editUserProfile(user);

            status = "success";
            message = "Board created successfully!";
        } catch (Exception e) {
            //TODO: handle exception
            status = "error";
            message = e.getMessage();
        }

        request.setAttribute("status", status);
        request.setAttribute("message", message);
        request.getRequestDispatcher("workspace-setting-account.jsp").forward(request, response);
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
