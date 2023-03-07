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
import model.User;
import services.UserService;
import util.DBConnection;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        getServletContext().getRequestDispatcher("/WEB-INF/RegisterPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        
        String fname = request.getParameter("fName");
        String lname = request.getParameter("lName");
        String phone = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean status = true;

        
        
        UserService us = new UserService();
        try {
            User user = us.register(email, status , password, fname, lname);
        } catch (Exception ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
            
            
            
            
//        String url = "jdbc:mysql://localhost:3306/helixschedulingdb";
//        String user = "admin";
//        String dbpassword = "password";
//        
//        try{
//        String fname = request.getParameter("fName");
//        String lname = request.getParameter("lName");
//        String phone = request.getParameter("phoneNumber");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        int userID = 5;
//        String salt = "fdas";
//        boolean active = true;
//        boolean visible = true;
//        
//        Connection con = DriverManager.getConnection(url,user,dbpassword);
//
//        
//        String sql = "INSERT INTO user(userID, email, firstName, lastName, password, salt, phone, active, public) VALUES(?,?,?,?,?,?,?,?,?)";
//        PreparedStatement statement = con.prepareStatement(sql);
//        
//        statement.setInt(1, userID);
//        statement.setString(2, email);
//        statement.setString(3, fname);
//        statement.setString(4, lname);
//        statement.setString(5, password);
//        statement.setString(6, email);
//        statement.setString(7, phone);
//        statement.setBoolean(8, active);
//        statement.setBoolean(9, visible);
//
//
//        
//        statement.executeUpdate();
//        statement.close();
//        con.close();
//        
//        response.sendRedirect("/login");
//
//        
//        }
//        catch (Exception e){
//            
//        }
       
   }
   
    
}


