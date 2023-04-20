package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Shift;
import model.Task;
import model.User;
import static model.User_.firstName;
import services.OrganizationUserService;
import services.ShiftService;
import services.TaskService;
import services.UserService;


public class HomeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShiftService ss = new ShiftService();
        TaskService ts = new TaskService();
        
        User user =  (User) session.getAttribute("user");
       
        List<Shift> shiftList = ss.getShiftsByUser(user);
        List<Task> taskList = ts.getByUserUpcoming(user);
        
        session.setAttribute("shiftList", shiftList);
        session.setAttribute("taskList", taskList);
        
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/HomePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            getServletContext().getRequestDispatcher("/WEB-INF/HomePage.jsp").forward(request, response);
            return;
        }
}

