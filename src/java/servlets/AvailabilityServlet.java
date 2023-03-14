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
        Organization org;
        
        if ((organizationName == null || organizationName.equals("")) && orgList != null) {
            org = orgList.get(0);
        } else {
            org = orgService.getByName(organizationName.toLowerCase());
        }
        
        if (org != null) {
            OrganizationUserService ouService = new OrganizationUserService();
            OrganizationUser ou = ouService.getByOrgUser(org, user);

            session.setAttribute("orgUser", ou);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/availability.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        OrganizationUser ou = (OrganizationUser) session.getAttribute("orgUser");
        
        
        String action = request.getParameter("action");

        if (action != null && !action.equals("")) {
            if (action.equals("availability")) {
                Instant epoch = Instant.EPOCH;
                List<Availability> availabilityList = new ArrayList<Availability>();
                Availability sunday, monday, tuesday, wednesday, thursday, friday, saturday;
                
                String sundayUnavailable = request.getParameter("Sunday-unavailable");
                Date sunStartDate, sunEndDate;
                if (sundayUnavailable == null) {
                    String sunStart = request.getParameter("Sunday-start");
                    String sunEnd = request.getParameter("Sunday-end");
                    
                    Calendar startCal = Calendar.getInstance();
                    startCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sunStart.substring(0, 2)));
                    startCal.set(Calendar.MINUTE, Integer.parseInt(sunStart.substring(3, 5)));
                    startCal.set(Calendar.SECOND, 0);
                    
                    sunStartDate = startCal.getTime();
                    
                    Calendar endCal = Calendar.getInstance();
                    endCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sunEnd.substring(0, 2)));
                    endCal.set(Calendar.MINUTE, Integer.parseInt(sunEnd.substring(3, 5)));
                    endCal.set(Calendar.SECOND, 0);
                    
                    sunEndDate = endCal.getTime();
                } else {
                    sunStartDate = new Date(epoch.toEpochMilli());
                    sunEndDate = new Date(epoch.toEpochMilli());
                }
                sunday = new Availability(0, "Sunday", sunStartDate, sunEndDate);
                availabilityList.add(sunday);
                
                String mondayUnavailable = request.getParameter("Monday-unavailable");
                Date monStartDate, monEndDate;
                if (mondayUnavailable == null) {
                    String monStart = request.getParameter("Monday-start");
                    String monEnd = request.getParameter("Monday-end");
                    
                    Calendar startCal = Calendar.getInstance();
                    startCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(monStart.substring(0, 2)));
                    startCal.set(Calendar.MINUTE, Integer.parseInt(monStart.substring(3, 5)));
                    startCal.set(Calendar.SECOND, 0);
                    
                    monStartDate = startCal.getTime();
                    
                    Calendar endCal = Calendar.getInstance();
                    endCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(monEnd.substring(0, 2)));
                    endCal.set(Calendar.MINUTE, Integer.parseInt(monEnd.substring(3, 5)));
                    endCal.set(Calendar.SECOND, 0);
                    
                    monEndDate = endCal.getTime();
                } else {
                    monStartDate = new Date(epoch.toEpochMilli());
                    monEndDate = new Date(epoch.toEpochMilli());
                }
                monday = new Availability(0, "Monday", monStartDate, monEndDate);
                availabilityList.add(monday);
                
                String tuesdayUnavailable = request.getParameter("Tuesday-unavailable");
                Date tuesStartDate, tuesEndDate;
                if (tuesdayUnavailable == null) {
                    String tuesStart = request.getParameter("Tuesday-start");
                    String tuesEnd = request.getParameter("Tuesday-end");
                    
                    Calendar startCal = Calendar.getInstance();
                    startCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(tuesStart.substring(0, 2)));
                    startCal.set(Calendar.MINUTE, Integer.parseInt(tuesStart.substring(3, 5)));
                    startCal.set(Calendar.SECOND, 0);
                    
                    tuesStartDate = startCal.getTime();
                    
                    Calendar endCal = Calendar.getInstance();
                    endCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(tuesEnd.substring(0, 2)));
                    endCal.set(Calendar.MINUTE, Integer.parseInt(tuesEnd.substring(3, 5)));
                    endCal.set(Calendar.SECOND, 0);
                    
                    tuesEndDate = endCal.getTime();
                } else {
                    tuesStartDate = new Date(epoch.toEpochMilli());
                    tuesEndDate = new Date(epoch.toEpochMilli());
                }
                tuesday = new Availability(0, "Tuesday", tuesStartDate, tuesEndDate);
                availabilityList.add(tuesday);
                
                String wednesdayUnavailable = request.getParameter("Wednesday-unavailable");
                Date wedStartDate, wedEndDate;
                if (wednesdayUnavailable == null) {
                    String wedStart = request.getParameter("Wednesday-start");
                    String wedEnd = request.getParameter("Wednesday-end");
                    
                    Calendar startCal = Calendar.getInstance();
                    startCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(wedStart.substring(0, 2)));
                    startCal.set(Calendar.MINUTE, Integer.parseInt(wedStart.substring(3, 5)));
                    startCal.set(Calendar.SECOND, 0);
                    
                    wedStartDate = startCal.getTime();
                    
                    Calendar endCal = Calendar.getInstance();
                    endCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(wedEnd.substring(0, 2)));
                    endCal.set(Calendar.MINUTE, Integer.parseInt(wedEnd.substring(3, 5)));
                    endCal.set(Calendar.SECOND, 0);
                    
                    wedEndDate = endCal.getTime();
                } else {
                    wedStartDate = new Date(epoch.toEpochMilli());
                    wedEndDate = new Date(epoch.toEpochMilli());
                }
                wednesday = new Availability(0, "Wednesday", wedStartDate, wedEndDate);
                availabilityList.add(wednesday);
               
                String thursdayUnavailable = request.getParameter("Thursday-unavailable");
                Date thursStartDate, thursEndDate;
                if (thursdayUnavailable == null) {
                    String thursStart = request.getParameter("Thursday-start");
                    String thursEnd = request.getParameter("Thursday-end");
                    
                    Calendar startCal = Calendar.getInstance();
                    startCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(thursStart.substring(0, 2)));
                    startCal.set(Calendar.MINUTE, Integer.parseInt(thursStart.substring(3, 5)));
                    startCal.set(Calendar.SECOND, 0);
                    
                    thursStartDate = startCal.getTime();
                    
                    Calendar endCal = Calendar.getInstance();
                    endCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(thursEnd.substring(0, 2)));
                    endCal.set(Calendar.MINUTE, Integer.parseInt(thursEnd.substring(3, 5)));
                    endCal.set(Calendar.SECOND, 0);
                    
                    thursEndDate = endCal.getTime();
                } else {
                    thursStartDate = new Date(epoch.toEpochMilli());
                    thursEndDate = new Date(epoch.toEpochMilli());
                }
                thursday = new Availability(0, "Thursday", thursStartDate, thursEndDate);
                availabilityList.add(thursday);
                
                String fridayUnavailable = request.getParameter("Friday-unavailable");
                Date friStartDate, friEndDate;
                if (fridayUnavailable == null) {
                    String friStart = request.getParameter("Friday-start");
                    String friEnd = request.getParameter("Friday-end");
                    
                    Calendar startCal = Calendar.getInstance();
                    startCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(friStart.substring(0, 2)));
                    startCal.set(Calendar.MINUTE, Integer.parseInt(friStart.substring(3, 5)));
                    startCal.set(Calendar.SECOND, 0);
                    
                    friStartDate = startCal.getTime();
                    
                    Calendar endCal = Calendar.getInstance();
                    endCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(friEnd.substring(0, 2)));
                    endCal.set(Calendar.MINUTE, Integer.parseInt(friEnd.substring(3, 5)));
                    endCal.set(Calendar.SECOND, 0);
                    
                    friEndDate = endCal.getTime();
                } else {
                    friStartDate = new Date(epoch.toEpochMilli());
                    friEndDate = new Date(epoch.toEpochMilli());
                }
                friday = new Availability(0, "Friday", friStartDate, friEndDate);
                availabilityList.add(friday);
                
                String saturdayUnvailable = request.getParameter("Saturday-unavailable");
                Date satStartDate, satEndDate;
                if (saturdayUnvailable == null) {
                    String satStart = request.getParameter("Saturday-start");
                    String satEnd = request.getParameter("Saturday-end");
                    
                    Calendar startCal = Calendar.getInstance();
                    startCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(satStart.substring(0, 2)));
                    startCal.set(Calendar.MINUTE, Integer.parseInt(satStart.substring(3, 5)));
                    startCal.set(Calendar.SECOND, 0);
                    
                    satStartDate = startCal.getTime();
                    
                    Calendar endCal = Calendar.getInstance();
                    endCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(satEnd.substring(0, 2)));
                    endCal.set(Calendar.MINUTE, Integer.parseInt(satEnd.substring(3, 5)));
                    endCal.set(Calendar.SECOND, 0);
                    
                    satEndDate = endCal.getTime();
                } else {
                    satStartDate = new Date(epoch.toEpochMilli());
                    satEndDate = new Date(epoch.toEpochMilli());
                }
                saturday = new Availability(0, "Saturday", satStartDate, satEndDate);
                availabilityList.add(saturday);
                
                OrganizationUserService ouService = new OrganizationUserService();
                try {
                    ouService.update(ou.getOrganization(), user, ou.getDept(), ou.getSchedule(), ou.getManagedBy(), ou.getHourly(), availabilityList);
                    session.setAttribute("availabilityUpdateMessage", "Availability updated");
                } catch (Exception e){
                    session.setAttribute("availabilityUpdateMessage", "Failed to update availability");
                }
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/availability.jsp").forward(request, response);
    }
}
