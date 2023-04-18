/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Availability;
import model.Organization;
import model.OrganizationUser;
import model.User;
import services.OrganizationService;
import services.OrganizationUserService;
import services.UnavailableService;

/**
 *
 * @author Eric
 */
public class AvailabilityServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        String organizationName = request.getParameter("organization");

        List<Organization> orgList = new ArrayList<Organization>();
        for (OrganizationUser orgUser : user.getOrganizationUserList()) {
            orgList.add(orgUser.getOrganization());
        }
        
        session.setAttribute("orgList", orgList);

        OrganizationService orgService = new OrganizationService();
        Organization org = null;

        if (organizationName != null && !organizationName.equals("")) {
            org = orgService.getByName(organizationName);
        } else if (!orgList.isEmpty()){
            org = orgList.get(0);
        }

        if (org != null) {
            OrganizationUserService ouService = new OrganizationUserService();
            OrganizationUser ou = ouService.getByOrgUser(org, user);

            session.setAttribute("orgUser", ou);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/AvailabilityPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        OrganizationUserService ouService = new OrganizationUserService();
        User user = (User) session.getAttribute("user");
        OrganizationUser ou = (OrganizationUser) session.getAttribute("orgUser");

        String action = request.getParameter("action");

        if (action != null && !action.equals("")) {
            if (action.equals("availability")) {
                List<Availability> availabilityList = new ArrayList<Availability>();
                Availability curr;
                String days[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
                Date startDate, endDate;
                boolean failed = false;

                for (int i = 0; i < days.length; i++) {
                    String unavailable = request.getParameter(days[i] + "-unavailable");
                    String start = request.getParameter(days[i] + "-start");
                    String end = request.getParameter(days[i] + "-end");

                    Calendar startCal, endCal;
                    if (unavailable != null && unavailable.equals("on")) {
                        startCal = Calendar.getInstance();
                        startCal.set(Calendar.HOUR_OF_DAY, 0);
                        startCal.set(Calendar.MINUTE, 0);
                        startCal.set(Calendar.SECOND, 0);

                        startDate = startCal.getTime();

                        endCal = Calendar.getInstance();
                        endCal.set(Calendar.HOUR_OF_DAY, 0);
                        endCal.set(Calendar.MINUTE, 0);
                        endCal.set(Calendar.SECOND, 0);

                        endDate = endCal.getTime();
                        curr = new Availability(0, days[i], startDate, endDate);
                        availabilityList.add(curr);

                    } else if (start != null && !start.equals("") && start.matches("\\d\\d:\\d\\d")
                            && end != null && !end.equals("") && end.matches("\\d\\d:\\d\\d")) {

                        startCal = Calendar.getInstance();
                        startCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(start.substring(0, 2)));
                        startCal.set(Calendar.MINUTE, Integer.parseInt(start.substring(3, 5)));
                        startCal.set(Calendar.SECOND, 0);

                        startDate = startCal.getTime();

                        endCal = Calendar.getInstance();
                        endCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(end.substring(0, 2)));
                        endCal.set(Calendar.MINUTE, Integer.parseInt(end.substring(3, 5)));
                        endCal.set(Calendar.SECOND, 0);

                        endDate = endCal.getTime();
                        curr = new Availability(0, days[i], startDate, endDate);
                        availabilityList.add(curr);
                    } else {
                        failed = true;
                        session.setAttribute("availabilityUpdateMessage", "One or more entries are invalid");
                        break;
                    }
                }
                if (!failed) {

                    try {
                        ouService.update(ou.getOrganization(), user, ou.getDept(), ou.getManagedBy(), ou.getHourly(), availabilityList);
                        session.setAttribute("availabilityUpdateMessage", "Availability updated");
                    } catch (Exception e) {
                        session.setAttribute("availabilityUpdateMessage", "Failed to update availability");
                    }
                }
            } else if (action != null && action.equals("unavailable")) {
                String dateString = request.getParameter("date");
                String reason = request.getParameter("reason");

                if (dateString != null && !dateString.equals("") && dateString.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")) {
                    int year = Integer.parseInt(dateString.substring(0, 4));
                    int month = Integer.parseInt(dateString.substring(5, 7));
                    int day = Integer.parseInt(dateString.substring(8));

                    Calendar cal = Calendar.getInstance();
                    cal.set(year, month, day);
                    Date date = cal.getTime();

                    UnavailableService us = new UnavailableService();
                    us.insert(ou, date, reason);
                }
            }
        }
        ou = ouService.get(ou.getOrganizationUserID());
        session.setAttribute("orgUser", ou);
        getServletContext().getRequestDispatcher("/WEB-INF/AvailabilityPage.jsp").forward(request, response);
    }
}
