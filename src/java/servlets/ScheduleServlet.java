package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Department;
import model.Organization;
import model.OrganizationUser;
import model.Schedule;
import model.User;
import services.DepartmentService;
import services.OrganizationService;
import services.OrganizationUserService;
import services.ScheduleService;
import services.ShiftService;

public class ScheduleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ScheduleService ss = new ScheduleService();
        ShiftService shs = new ShiftService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        OrganizationUserService ous = new OrganizationUserService();
        OrganizationUser ou = null;

        String orgName = request.getParameter("organization");
        String startDate = request.getParameter("startDate");

        List<Schedule> scheduleList = null;
        List<Date> dateList;
        Schedule schedule = null;

        if (orgName != null && !orgName.equals("")) {
            OrganizationService os = new OrganizationService();
            Organization org = os.getByName(orgName);

            ou = ous.getByOrgUser(org, user);

            if (ou.getAdmin() || ou.getOwner()) {
                scheduleList = ss.getByOrg(org);
            } else {
                scheduleList = ss.getByOrgUser(ou);
            }

            if (startDate != null && !startDate.equals("") && startDate.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")) {
                Calendar cal = Calendar.getInstance();
                int year = Integer.parseInt(startDate.substring(0, 4));
                int month = Integer.parseInt(startDate.substring(5, 7)) - 1;
                int day = Integer.parseInt(startDate.substring(8));
                cal.set(year, month, day, 0, 0, 0);
                Date date = cal.getTime();

                try {
                    schedule = ss.getByOrgDeptStartDate(org, ou.getDept(), date);

                    String action = request.getParameter("action");
                    if (action != null && action.equals("delete")) {
                        try {
                            int shiftID = Integer.parseInt(request.getParameter("shift"));
                            shs.delete(shiftID);
                            schedule = ss.get(schedule.getScheduleID());
                        } catch (Exception e) {
                            session.setAttribute("message", "Shift could not be deleted");
                        }
                    }
                } catch (Exception e) {
                    session.setAttribute("message", "Schedule could not be found");
                }

            } else if (scheduleList != null && !scheduleList.isEmpty()) {
                schedule = scheduleList.get(0);
            }
        } else if (!user.getOrganizationUserList().isEmpty()) {
            ou = user.getOrganizationUserList().get(0);
            if (ou.getAdmin() || ou.getOwner()) {
                scheduleList = ss.getByOrg(ou.getOrganization());
            } else {
                scheduleList = ss.getByOrgUser(ou);
            }
            if (!scheduleList.isEmpty()) {
                schedule = scheduleList.get(0);
            }

        }

        if (schedule != null) {
            dateList = ss.getDateList(schedule);
            session.setAttribute("dateList", dateList);
        }

        session.setAttribute("schedule", schedule);
        session.setAttribute("orgScheduleList", scheduleList);
        session.setAttribute("shiftList", shs.getShiftsByUser(user));
        session.setAttribute("orgUser", ou);

        getServletContext().getRequestDispatcher("/WEB-INF/SchedulePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ScheduleService ss = new ScheduleService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        boolean redirect = false;

        String action = request.getParameter("action");

        String message = null;
        if (action != null && action.equals("new-shift")) {
            Schedule schedule = (Schedule) session.getAttribute("schedule");
            int orgUserID = Integer.parseInt(request.getParameter("orgMemberShift"));
            String dateString = request.getParameter("date");
            String startTime = request.getParameter("start-time");
            String endTime = request.getParameter("end-time");
            String shiftType = request.getParameter("shift-type");

            if (schedule != null && orgUserID != 0 && dateString != null && !dateString.equals("") && dateString.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")
                    && startTime != null && !startTime.equals("") && startTime.matches("\\d\\d:\\d\\d")
                    && endTime != null && !endTime.equals("") && endTime.matches("\\d\\d:\\d\\d")) {
                OrganizationUserService ous = new OrganizationUserService();
                OrganizationUser ou = ous.get(orgUserID);
                if (ou != null) {
                    Calendar cal = Calendar.getInstance();
                    int year = Integer.parseInt(dateString.substring(0, 4));
                    int month = Integer.parseInt(dateString.substring(5, 7)) - 1;
                    int day = Integer.parseInt(dateString.substring(8));
                    cal.set(year, month, day);

                    cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.substring(0, 2)));
                    cal.set(Calendar.MINUTE, Integer.parseInt(startTime.substring(3, 5)));
                    cal.set(Calendar.SECOND, 0);
                    Date start = cal.getTime();

                    cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endTime.substring(0, 2)));
                    cal.set(Calendar.MINUTE, Integer.parseInt(endTime.substring(3, 5)));
                    cal.set(Calendar.SECOND, 0);
                    Date end = cal.getTime();

                    long daysBetween = Duration.between(schedule.getStartDate().toInstant(), start.toInstant()).toDays();
                    if (daysBetween >= 0 && daysBetween < 7 && start.before(end)) {
                        try {
                            ss.addShift(schedule.getScheduleID(), ou, start, end, shiftType);
                        } catch (Exception e) {
                            message = "Failed to add shift";
                        }
                        cal.setTime(schedule.getStartDate());
                        year = cal.get(Calendar.YEAR);
                        month = cal.get(Calendar.MONTH) + 1;
                        day = cal.get(Calendar.DAY_OF_MONTH);
                        String redirectDate = year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
                        response.sendRedirect("schedule?organization=" + ou.getOrganization().getName() + "&startDate=" + redirectDate);
                        redirect = true;
                    } else {
                        message = "Shift date not on schedule or end time is before start time";

                    }
                } else {
                    message = "Organization member not found";
                }
            } else {
                message = "Empty or incorrectly formatted parameters";
            }
        } else if (action != null && action.equals("new-schedule")) {
            String orgName = request.getParameter("orgName");
            String deptName = request.getParameter("dept");
            String dateString = request.getParameter("start-date");
            boolean copyForward = false;

            if (request.getParameter("copy-forward") != null) {
                copyForward = true;
            }

            if (orgName != null && !orgName.equals("") && deptName != null && !deptName.equals("")
                    && dateString != null && !dateString.equals("") && dateString.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")) {
                OrganizationService os = new OrganizationService();
                Organization org = os.getByName(orgName);

                if (org != null) {
                    DepartmentService ds = new DepartmentService();
                    Department dept = ds.get(org, deptName);

                    if (dept != null) {
                        Calendar cal = Calendar.getInstance();
                        int year = Integer.parseInt(dateString.substring(0, 4));
                        int month = Integer.parseInt(dateString.substring(5, 7)) - 1;
                        int day = Integer.parseInt(dateString.substring(8));
                        cal.set(year, month, day);

                        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
                            Date startDate = cal.getTime();
                            cal.add(Calendar.DATE, 6);
                            Date endDate = cal.getTime();

                            OrganizationUser ou = (OrganizationUser) session.getAttribute("orgUser");

                            if (ou != null) {
                                try {
                                    Schedule newSchedule = ss.insert(ou.getOrganization(), dept, startDate, endDate);
                                    month++;
                                    String redirectDate = year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
                                    response.sendRedirect("schedule?organization=" + org.getName() + "&startDate=" + redirectDate);
                                    redirect = true;
                                } catch (Exception e) {
                                    message = "A schedule already exists for that time period";

                                }
                            } else {
                                message = "Organization member not found";
                            }
                        } else {
                            message = "Schedule must start on a Sunday";
                        }
                    } else {
                        message = "Could not find department";
                    }
                } else {
                    message = "Could not find organization";
                }
            } else {
                message = "Parameters empty or invalid";
            }
        }

        session.setAttribute("message", message);
        //response.sendRedirect("/schedule");
        if (!redirect) {
            getServletContext().getRequestDispatcher("/WEB-INF/SchedulePage.jsp").forward(request, response);
        }
        return;
    }
}
