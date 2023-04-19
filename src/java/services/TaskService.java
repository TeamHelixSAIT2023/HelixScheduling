/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.TaskDB;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Organization;
import model.OrganizationUser;
import model.Task;
import model.User;

/**
 *
 * @author Eric
 */
public class TaskService {

    public Task get(int taskID) throws Exception {
        TaskDB taskDB = new TaskDB();
        Task task = taskDB.get(taskID);
        return task;
    }

    public List<Task> getByUser(User user) {
        List<Task> taskList = new ArrayList<Task>();

        for (OrganizationUser ou : user.getOrganizationUserList()) {
            taskList.addAll(getByOrgUser(ou));
        }

        return taskList;
    }
    
    public List<Task> getByUserUpcoming(User user) {
        List<Task> taskList = new ArrayList<Task>();

        for (OrganizationUser ou : user.getOrganizationUserList()) {
            taskList.addAll(getByOrgUserUpcoming(ou));
        }

        return taskList;
    }
    
    public List<Task> getByUserPrevious(User user) {
        List<Task> taskList = new ArrayList<Task>();

        for (OrganizationUser ou : user.getOrganizationUserList()) {
            taskList.addAll(getByOrgUserPrevious(ou));
        }

        return taskList;
    }

    public List<Task> getByOrg(Organization org) {
        List<Task> taskList = new ArrayList<Task>();

        for (OrganizationUser ou : org.getOrganizationUserList()) {
            taskList.addAll(getByOrgUser(ou));
        }

        return taskList;
    }
    
    public List<Task> getByOrgUpcoming(Organization org) {
        List<Task> taskList = new ArrayList<Task>();

        for (OrganizationUser ou : org.getOrganizationUserList()) {
            taskList.addAll(getByOrgUserUpcoming(ou));
        }

        return taskList;
    }
    
    public List<Task> getByOrgPrevious(Organization org) {
        List<Task> taskList = new ArrayList<Task>();

        for (OrganizationUser ou : org.getOrganizationUserList()) {
            taskList.addAll(getByOrgUserPrevious(ou));
        }

        return taskList;
    }

    public List<Task> getByOrgUser(OrganizationUser ou) {
        TaskDB taskDB = new TaskDB();
        List<Task> taskList = taskDB.getByOrgUser(ou);
        return taskList;
    }

    public List<Task> getByOrgUserUpcoming(OrganizationUser ou) {
        TaskDB taskDB = new TaskDB();
        List<Task> taskList = taskDB.getByOrgUserUpcoming(ou);
        return taskList;
    }

    public List<Task> getByOrgUserPrevious(OrganizationUser ou) {
        TaskDB taskDB = new TaskDB();
        List<Task> taskList = taskDB.getByOrgUserPrevious(ou);
        return taskList;
    }

    public void insert(OrganizationUser ou, Date startDate, Date endDate, String title,
            String description, String status, String priority) {
        TaskDB taskDB = new TaskDB();
        Task task = new Task();

        task.setOrganizationUser(ou);
        task.setStartDate(startDate);
        task.setEndDate(endDate);
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setPriority(priority);

        taskDB.insert(task);
    }

    public void update(int taskID, OrganizationUser ou, Date startDate, Date endDate, 
            String title, String description, String status, String priority) {
        TaskDB taskDB = new TaskDB();
        Task task = taskDB.get(taskID);

        task.setOrganizationUser(ou);
        task.setStartDate(startDate);
        task.setEndDate(endDate);
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setPriority(priority);

        taskDB.update(task);
    }

    public void updateStatus(int taskID, String status) {
        TaskDB taskDB = new TaskDB();
        Task task = taskDB.get(taskID);

        task.setStatus(status);

        taskDB.update(task);
    }

    public void delete(int taskID) {
        TaskDB taskDB = new TaskDB();
        Task task = taskDB.get(taskID);
        taskDB.delete(task);
    }
}
