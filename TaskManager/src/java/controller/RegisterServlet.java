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
public class RegisterServlet extends HttpServlet {

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
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User user = new User();
        String stage = request.getParameter("stage");

        String message;
        String status;
        if (stage == null) {
            try {
                String username = request.getParameter("name");
                String email = request.getParameter("email");
                String password = request.getParameter("pass");

                user.checkValidUserName(username);
                user.checkValidEmail(email);
                user.checkValidPassword(password);

                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);

                session.setAttribute("loginInfor", user);
                request.getRequestDispatcher("registerInformation.jsp").forward(request, response);
            } catch (Exception e) {
                //TODO: handle exception
                status = "error";
                message = e.getMessage();
                request.setAttribute("status", status);
                request.setAttribute("message", message);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else {
            try {
                user = (User) session.getAttribute("loginInfor");
                if (user == null) {
                    throw new IllegalStateException("No user information found in session.");
                }
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                Date birthDate = Date.valueOf(request.getParameter("birthDate"));
                String phoneNumber = request.getParameter("phone");

                user.checkValidNameString(firstName);
                user.checkValidNameString(lastName);
                user.checkValidPhoneNumber(phoneNumber);
                

                user.registerUser(user.getUsername(), user.getEmail(), user.getPassword(), firstName, lastName, birthDate, phoneNumber);
                response.sendRedirect("login");
            } catch (Exception e) {
                //TODO: handle exception
                status = "error";
                message = e.getMessage();
                request.setAttribute("status", status);
                request.setAttribute("message", message);
                request.getRequestDispatcher("registerInformation.jsp").forward(request, response);
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
