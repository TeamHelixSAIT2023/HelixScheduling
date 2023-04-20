/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Organization;
import services.OrganizationService;
import dataaccess.OrganizationDB;
import model.Department;
import model.OrganizationUser;
import model.User;
import services.DepartmentService;
import services.OrganizationUserService;
import services.UserService;

/**
 *
 * @author Sukhpal
 */
public class RergisterOrganizationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        getServletContext().getRequestDispatcher("/WEB-INF/RegisterOrganization.jsp").forward(request, response);

        User user = (User) session.getAttribute("user");

        request.setAttribute("name", user);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String name = request.getParameter("orgName");
        String description = request.getParameter("orgDesc");

        boolean public1;
        public1 = (request.getParameter("public") != null);

        OrganizationService os = new OrganizationService();
        try {

            Organization organization = os.register(name, description, public1);

            OrganizationUserService ous = new OrganizationUserService();
            User user = (User) session.getAttribute("user");
            ous.insert(organization, user, null, null, 0, true, true);
            session.setAttribute("orgUserMessage", "");

        } catch (Exception exc) {
            Logger.getLogger(RergisterOrganizationServlet.class.getName()).log(Level.SEVERE, null, exc);
        }

        response.sendRedirect("home");

    }

}
