/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import services.UserService;

/**
 *
 * @author Eric
 */
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        getServletContext().getRequestDispatcher("/WEB-INF/LoginPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserService us = new UserService();
        User user = us.login(email, password);

        HttpSession session = request.getSession();

        if (user == null) {
            session.setAttribute("email", email);
            session.setAttribute("message", "Invalid credentials");
            getServletContext().getRequestDispatcher("/WEB-INF/LoginPage.jsp").forward(request, response);
            return;
        } else if (!user.getActive()) {
            session.setAttribute("message", "Account deactivated, contact an administrator for reactivation");
            getServletContext().getRequestDispatcher("/WEB-INF/LoginPage.jsp").forward(request, response);
            return;
        }

        session.setAttribute("user", user);
        
        
        response.sendRedirect("home");
    }
    
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        if (user == null) {
            request.setAttribute("Error", "Email not found"); 
            
        }
    }
}
