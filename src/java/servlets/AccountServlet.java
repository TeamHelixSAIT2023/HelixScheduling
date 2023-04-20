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
        UserService us = new UserService();
        User user = (User) session.getAttribute("user");

        user = us.get(user.getUserID());
        session.setAttribute("user", user);

        getServletContext().getRequestDispatcher("/WEB-INF/AccountPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserService us = new UserService();
        User user = (User) session.getAttribute("user");

        String action = request.getParameter("action");

        if (action != null && action.equals("info")) {
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            boolean public1 = request.getParameter("public") != null && request.getParameter("public").equals("on");
            boolean active = request.getParameter("active") != null && request.getParameter("active").equals("on");

            if (email != null && !email.equals("") && phone != null && !phone.equals("")) {
                try {
                    us.update(email, user.getFirstName(), user.getLastName(), phone, active, public1);
                    if (!active){
                        session.setAttribute("infoMessage", "Your account has been deactived, the next time you log out you will not be able to log back in");
                    }
                    session.setAttribute("infoMessage", "Your account deatils have been updated");
                } catch (Exception e) {
                    session.setAttribute("infoMessage", "User infomation could not be updated");
                }
            }

        } else if (action != null && action.equals("password")) {
            String email = user.getEmail();
            String password = request.getParameter("old-password");
            String newPassword1 = request.getParameter("new-password-first");
            String newPassword2 = request.getParameter("new-password-second");

            if (password != null && !password.equals("")
                    && newPassword1 != null && !newPassword1.equals("")
                    && newPassword2 != null && !newPassword2.equals("")) {
                try {
                    us.updatePassword(user.getEmail(), password, newPassword1, newPassword2);
                    session.setAttribute("passwordMessage", "Password updated");
                } catch (Exception e) {
                    session.setAttribute("passwordMessage", e.getMessage());
                }
            }
        }

        response.sendRedirect("/account");
    }
}
