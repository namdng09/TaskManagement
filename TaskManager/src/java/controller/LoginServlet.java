/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author namdng09
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        Cookie arr[] = request.getCookies();
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].getName().equals("cName")) {
                    request.setAttribute("uName", arr[i].getValue());
                }
                if (arr[i].getName().equals("cPass")) {
                    request.setAttribute("uPass", arr[i].getValue());
                }
                if (arr[i].getName().equals("cRem")) {
                    request.setAttribute("reMem", arr[i].getValue());
                }
            }
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        User user = new User();

        String username = request.getParameter("name");
        String password = request.getParameter("pass");
        String rem = request.getParameter("rem");
        String status;
        String message;

        Cookie userCookie = new Cookie("cName", username);
        Cookie passCookie = new Cookie("cPass", password);
        Cookie remCookie = new Cookie("cRem", password);

        if (rem != null) {
            userCookie.setMaxAge(60 * 60 * 24);
            passCookie.setMaxAge(60 * 60 * 24);
            remCookie.setMaxAge(60 * 60 * 24);
        } else {
            userCookie.setMaxAge(0);
            passCookie.setMaxAge(0);
            remCookie.setMaxAge(0);
        }

        response.addCookie(userCookie);
        response.addCookie(passCookie);
        response.addCookie(remCookie);

        if (!user.checkValidLogin(username, password)) {
            status = "error";
            message = "username or password is not correct!";
            request.setAttribute("status", status);
            request.setAttribute("message", message);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            user = user.getAccountByUsername(username);
            HttpSession session = request.getSession();
            session.setAttribute("account", user);
            response.sendRedirect("home");
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
