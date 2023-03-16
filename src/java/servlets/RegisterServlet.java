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
import java.util.logging.Level;
import java.util.logging.Logger;


public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        getServletContext().getRequestDispatcher("/WEB-INF/RegisterPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        
        
        String fname = request.getParameter("fName");
        String lname = request.getParameter("lName");
        String phone = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        UserService us = new UserService();
        try {
            User user = us.register(email, password, fname, lname, phone);
        } catch (Exception ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect("login");
       
   }
   
    
}


