package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Task;
import model.User;

public class TaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           getServletContext().getRequestDispatcher("/WEB-INF/TaskPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data from request
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String organization = request.getParameter("organization");
        String Priority = request.getParameter("priority");
        int assignedUser = Integer.parseInt(request.getParameter("assignedUser"));

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setOrganization(organization);
        task.setPriority(Priority); // Set the priority value from request parameter
        task.setAssignedUser(new User(assignedUser));

        // Forward to the TaskPage.jsp
        request.setAttribute("task", task); // Set the Task object as an attribute
        getServletContext().getRequestDispatcher("/WEB-INF/TaskPage.jsp").forward(request, response);
    }
}
