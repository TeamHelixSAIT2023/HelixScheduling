package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Organization;
import model.OrganizationUser;
import model.Schedule;
import model.User;
import services.OrganizationService;
import services.OrganizationUserService;
import services.ScheduleService;


public class ScheduleServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ScheduleService ss = new ScheduleService();
        HttpSession session = request.getSession();
        
        String orgName = request.getParameter("organization");
        User user = (User) session.getAttribute("user");
        
        List<Schedule> schedule = ss.getByUser(user);
        getServletContext().getRequestDispatcher("/WEB-INF/SchedulePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            getServletContext().getRequestDispatcher("/WEB-INF/SchedulePage.jsp").forward(request, response);
            return;
        }
}

