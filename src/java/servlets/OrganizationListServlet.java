/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Organization;
import model.OrganizationUser;
import model.User;
import services.OrganizationService;
import services.OrganizationUserService;

/**
 *
 * @author Eric
 */
public class OrganizationListServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        OrganizationService orgService = new OrganizationService();
        List<Organization> orgList = new ArrayList<>();
        
        User user = (User) session.getAttribute("user");
        
        for (OrganizationUser ou : user.getOrganizationUserList()){
            orgList.add(ou.getOrganization());
        }
        
        session.setAttribute("orgList", orgList);
        
        getServletContext().getRequestDispatcher("/WEB-INF/OrganizationList.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
