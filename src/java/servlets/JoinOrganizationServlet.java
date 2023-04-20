/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.OrganizationDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Organization;
import model.User;
import services.OrganizationUserService;

/**
 *
 * @author Sukhpal
 */
@WebServlet(name = "JoinOrganizationServlet", urlPatterns = {"/joinOrganization"})
public class JoinOrganizationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        getServletContext().getRequestDispatcher("/WEB-INF/JoinOrganization.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String organizationName = request.getParameter("orgName");

        if (organizationName != null && !organizationName.equals("")) {
            OrganizationDB orgDB = new OrganizationDB();
            Organization org = orgDB.getByName(organizationName);

            if (org != null) {
                // request.setAttribute("orgName", org);

                OrganizationUserService ous = new OrganizationUserService();
                ous.insert(org, user, null, null, 0, false, false);

                response.sendRedirect("/joinConfirmation");
            } else {
                request.setAttribute("errorMessage", "Organization not found.");
                getServletContext().getRequestDispatcher("/WEB-INF/JoinOrganization.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("errorMessage", "Nothing entered");
            getServletContext().getRequestDispatcher("/WEB-INF/JoinOrganization.jsp").forward(request, response);
        }
    }
}
