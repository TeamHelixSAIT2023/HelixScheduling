/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.ArrayList;
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

        if (organizationName != null && !organizationName.equals("")) {
            OrganizationService orgService = new OrganizationService();
            Organization org = orgService.getByName(organizationName);

            if (org != null) {
                List<Organization> orgList = new ArrayList<Organization>();
                for (OrganizationUser orgUser : user.getOrganizationUserList()){
                    orgList.add(orgUser.getOrganization());
                }
                session.setAttribute("orgList", orgList);
                
                OrganizationUserService ouService = new OrganizationUserService();
                OrganizationUser ou = ouService.getByOrgUser(org, user);
                List<Availability> availabilityList = ou.getAvailabilityList();
                session.setAttribute("availabilityList", availabilityList);
                
                getServletContext().getRequestDispatcher("/WEB-INF/AvailabilityPage.jsp").forward(request, response);
            } else {
                response.sendRedirect("organizationList");
            }
        } else {
            response.sendRedirect("organizationList");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
