package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
        User user = (User) session.getAttribute("user");

        String orgName = request.getParameter("organization");
        String startDate = request.getParameter("startDate");

        List<Schedule> scheduleList;
        List<Date> dateList;

        if (!user.getOrganizationUserList().isEmpty()) {
            scheduleList = ss.getByDept(user.getOrganizationUserList().get(0).getDept());
            session.setAttribute("orgScheduleList", scheduleList);
            if (!scheduleList.isEmpty()) {
                dateList = ss.getDateList(scheduleList.get(0));
                session.setAttribute("dateList", dateList);
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/SchedulePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/SchedulePage.jsp").forward(request, response);
        return;
    }
}
