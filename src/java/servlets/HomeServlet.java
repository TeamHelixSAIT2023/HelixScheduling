package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import static model.User_.firstName;
import services.OrganizationUserService;
import services.UserService;


public class HomeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService userService = new UserService();
        OrganizationUserService uoService = new OrganizationUserService();
        
        User user =  (User) session.getAttribute("user");
       
        
        
        request.setAttribute("name", user); 
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/HomePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            getServletContext().getRequestDispatcher("/WEB-INF/HomePage.jsp").forward(request, response);
            return;
        }
}

