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
import model.Department;
import model.Organization;
import model.OrganizationUser;
import model.User;
import services.DepartmentService;
import services.OrganizationService;
import services.OrganizationUserService;
import services.UserService;

/**
 *
 * @author Eric
 */
public class OrganizationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String orgName = request.getParameter("organization");

        if (orgName == null || orgName.equals("")) {
            response.sendRedirect("organizationlist");
        } else {

            OrganizationService os = new OrganizationService();
            Organization org = os.getByName(orgName);

            if (org == null) {
                response.sendRedirect("organizationlist");
            } else {

                session.setAttribute("org", org);

                User user = (User) session.getAttribute("user");
                if (user == null) {
                    response.sendRedirect("login");
                } else {
                    OrganizationUserService ous = new OrganizationUserService();
                    OrganizationUser ou = ous.getByOrgUser(org, user);
                    session.setAttribute("orgUser", ou);

                    getServletContext().getRequestDispatcher("/WEB-INF/OrganizationPage.jsp").forward(request, response);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        Organization org = (Organization) session.getAttribute("org");
        OrganizationUser orgUser = (OrganizationUser) session.getAttribute("orgUser");

        String action = request.getParameter("action");

        OrganizationService os = new OrganizationService();

        if (action != null && !action.equals("")) {
            if (action.equals("info") && orgUser.getOwner()) {
                String orgName = request.getParameter("name");
                String description = request.getParameter("description");
                boolean public1 = (request.getParameter("public") != null && request.getParameter("public").equals("on"));
                boolean managerApprovedAvailabilityChange = (request.getParameter("man-approved-avail-change") != null && request.getParameter("man-approved-avail-change").equals("on"));
                boolean managerApprovedShiftSwap = (request.getParameter("man-approved-shift-swap") != null && request.getParameter("man-approved-shift-swap").equals("on"));
                boolean managerApprovedTimeOff = (request.getParameter("man-approved-time-off") != null && request.getParameter("man-approved-time-off").equals("on"));

                if (orgName != null && !orgName.equals("") && description != null && !description.equals("")) {
                    os.updateInfo(org.getOrganizationID(), orgName, description, public1, managerApprovedAvailabilityChange, managerApprovedShiftSwap, managerApprovedTimeOff);
                }
            } else if (action.equals("edit-user") && orgUser.getAdmin()) {
                String email = request.getParameter("email");
                try {
                    int deptID = Integer.parseInt(request.getParameter("dept"));
                    int managedBy = Integer.parseInt(request.getParameter("manager"));
                    double hourly = Double.parseDouble(request.getParameter("hourly"));
                    boolean admin = Boolean.parseBoolean(request.getParameter("admin"));

                    if (email != null && !email.equals("")) {
                        OrganizationUserService ous = new OrganizationUserService();
                        UserService us = new UserService();
                        DepartmentService ds = new DepartmentService();

                        User user = us.get(email);
                        OrganizationUser manager = ous.get(managedBy);
                        Department dept = ds.get(deptID);

                        ous.update(org, user, dept, null, manager, hourly, admin, null);
                        session.setAttribute("orgUserMessage", "");
                    }
                } catch (Exception e) {
                    session.setAttribute("orgUserMessage", "User information could not be updated");
                }
            }
        } else if (action.equals("new-user") && orgUser.getAdmin()) {
            String email = request.getParameter("email");
            try {
                int deptID = Integer.parseInt(request.getParameter("dept"));
                int managedBy = Integer.parseInt(request.getParameter("manager"));
                double hourly = Double.parseDouble(request.getParameter("hourly"));
                boolean admin = Boolean.parseBoolean(request.getParameter("admin"));

                if (email != null && !email.equals("")) {
                    OrganizationUserService ous = new OrganizationUserService();
                    UserService us = new UserService();
                    DepartmentService ds = new DepartmentService();

                    User user = us.get(email);
                    OrganizationUser manager = ous.get(managedBy);
                    Department dept = ds.get(deptID);

                    ous.insert(org, user, dept, null, manager, hourly, false, admin);
                    session.setAttribute("orgUserMessage", "");
                }
            } catch (Exception e) {
                session.setAttribute("orgUserMessage", "User could not be added");
            }
        } else if (action.equals("new-dept") && orgUser.getAdmin()) {
            String title = request.getParameter("dept-title");
            String description = request.getParameter("dept-description");
            try {
                int deptNo = Integer.parseInt(request.getParameter("dept-no"));
                DepartmentService ds = new DepartmentService();

                ds.insert(org, deptNo, title, description);
                session.setAttribute("deptMessage", "");
            } catch (Exception e) {
                session.setAttribute("deptMessage", "Department could not be added");
            }
        }

        org = os.get(org.getOrganizationID());

        if (org == null) {
            response.sendRedirect("organizationlist");
        } else {
            session.setAttribute("org", org);
            getServletContext().getRequestDispatcher("/WEB-INF/OrganizationPage.jsp").forward(request, response);
        }
    }
}
