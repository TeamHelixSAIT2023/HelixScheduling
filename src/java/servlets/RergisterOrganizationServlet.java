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
import model. Organization;
import services.OrganizationService;


/**
 *
 * @author Sukhpal
 */

public class RergisterOrganizationServlet extends HttpServlet {

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        getServletContext().getRequestDispatcher("/WEB-INF/RegisterOrganization.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        
        String name = request.getParameter("orgName");
        String description = request.getParameter("orgDesc");
        
        boolean public1 = Boolean.parseBoolean(request.getParameter("pub"));
        
        
        
        OrganizationService os = new OrganizationService();
        try {
            Organization organization = os.register(name,description,public1);
        } catch (Exception exc) {
            Logger.getLogger(RergisterOrganizationServlet.class.getName()).log(Level.SEVERE, null, exc);
        }
        
        response.sendRedirect("home");
       
   }
   
    
}
