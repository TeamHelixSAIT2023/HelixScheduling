package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

import services.UserService;

public class AccountServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
       
        getServletContext().getRequestDispatcher("/WEB-INF/AccountPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        
        String email = user.getEmail();
//        String oldPassword = request.getParameter("oldPassword");
//        String newPassword1 = request.getParameter("newPassword1");
//        String newPassword2 = request.getParameter("newPassword2");
        String password = request.getParameter("newPassword");
        
       
        UserService us = new UserService();
        try {
            us.updatePassword(email, password);
        } catch (Exception ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect("account");
}
}

