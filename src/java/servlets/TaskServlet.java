package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.OrganizationUser;
import model.Task;
import model.User;
import services.OrganizationUserService;
import services.TaskService;

public class TaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        TaskService ts = new TaskService();
        User user = (User) session.getAttribute("user");

        List<Task> taskList = ts.getByUser(user);
        List<Task> currentTaskList = ts.getByUserUpcoming(user);
        List<Task> previousTaskList = ts.getByUserPrevious(user);

        session.setAttribute("taskList", taskList);
        session.setAttribute("currentTaskList", currentTaskList);
        session.setAttribute("archivedTaskList", previousTaskList);

        String[] priorityList = {"High", "Medium", "Low"};
        String[] statusList = {"Not Started", "In-Progress", "Completed"};

        session.setAttribute("priorityList", priorityList);
        session.setAttribute("statusList", statusList);

        getServletContext().getRequestDispatcher("/WEB-INF/TaskPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        TaskService ts = new TaskService();

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String priority = request.getParameter("priority");
        String orgUserString = request.getParameter("assign-to");
        String startDateString = request.getParameter("start-date");
        String endDateString = request.getParameter("end-date");
        String status = request.getParameter("status");

        if (title != null && !title.equals("") && priority != null && !priority.equals("")
                && orgUserString != null && !orgUserString.equals("")
                && startDateString != null && !startDateString.equals("") && startDateString.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")
                && status != null && !status.equals("")) {
            OrganizationUserService ous = new OrganizationUserService();
            int orgUserID = Integer.parseInt(orgUserString);
            OrganizationUser ou = ous.get(orgUserID);

            if (ou != null) {
                Calendar cal = Calendar.getInstance();
                int year = Integer.parseInt(startDateString.substring(0, 4));
                int month = Integer.parseInt(startDateString.substring(5, 7));
                int day = Integer.parseInt(startDateString.substring(8));
                cal.set(year, month - 1, day);
                Date startDate = cal.getTime();
                Date endDate;

                if (endDateString != null && !endDateString.equals("") && endDateString.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")) {
                    year = Integer.parseInt(endDateString.substring(0, 4));
                    month = Integer.parseInt(endDateString.substring(5, 7));
                    day = Integer.parseInt(endDateString.substring(8));
                    cal.set(year, month - 1, day);
                    endDate = cal.getTime();
                } else {
                    endDate = null;
                }
                String recurringType = null;
                Date recurringUntilDate = null;
                boolean recurring = request.getParameter("recurring") != null && request.getParameter("recurring").equals("on");
                if (recurring) {
                    recurringType = request.getParameter("recurring-type");

                    recurringUntilDate = null;
                    boolean recurringEnd = request.getParameter("recurring-end") != null && request.getParameter("recurring-end").equals("on");
                    if (recurringEnd) {
                        String recurringEndString = request.getParameter("until-date");

                        if (recurringEndString != null && !recurringEndString.equals("") && recurringEndString.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")) {
                            year = Integer.parseInt(recurringEndString.substring(0, 4));
                            month = Integer.parseInt(recurringEndString.substring(5, 7));
                            day = Integer.parseInt(recurringEndString.substring(8));
                            cal.set(year, month - 1, day);
                            recurringUntilDate = cal.getTime();
                        }
                    }
                }

                if (recurring) {
                    ts.insertRecurring(ou, startDate, endDate, title, description, status, priority, recurringType, recurringUntilDate);
                } else {
                    ts.insert(ou, startDate, endDate, title, description, status, priority);
                }
            }

        }

        response.sendRedirect("/task");
        //getServletContext().getRequestDispatcher("/WEB-INF/TaskPage.jsp").forward(request, response);
        return;
    }
}
