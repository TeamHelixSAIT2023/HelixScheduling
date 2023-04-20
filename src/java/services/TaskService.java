/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.TaskDB;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import model.Organization;
import model.OrganizationUser;
import model.Task;
import model.User;
import util.SortTaskByDate;

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
        Collections.sort(taskList, new SortTaskByDate());

        return taskList;
    }

    public List<Task> getByUserUpcoming(User user) {
        List<Task> taskList = new ArrayList<Task>();

        for (OrganizationUser ou : user.getOrganizationUserList()) {
            taskList.addAll(getByOrgUserUpcoming(ou));
        }
        Collections.sort(taskList, new SortTaskByDate());

        return taskList;
    }

    public List<Task> getByUserPrevious(User user) {
        List<Task> taskList = new ArrayList<Task>();

        for (OrganizationUser ou : user.getOrganizationUserList()) {
            taskList.addAll(getByOrgUserPrevious(ou));
        }
        Collections.sort(taskList, new SortTaskByDate());

        return taskList;
    }

    public List<Task> getByOrg(Organization org) {
        List<Task> taskList = new ArrayList<Task>();

        for (OrganizationUser ou : org.getOrganizationUserList()) {
            taskList.addAll(getByOrgUser(ou));
        }
        Collections.sort(taskList, new SortTaskByDate());

        return taskList;
    }

    public List<Task> getByOrgUpcoming(Organization org) {
        List<Task> taskList = new ArrayList<Task>();

        for (OrganizationUser ou : org.getOrganizationUserList()) {
            taskList.addAll(getByOrgUserUpcoming(ou));
        }
        Collections.sort(taskList, new SortTaskByDate());

        return taskList;
    }

    public List<Task> getByOrgPrevious(Organization org) {
        List<Task> taskList = new ArrayList<Task>();

        for (OrganizationUser ou : org.getOrganizationUserList()) {
            taskList.addAll(getByOrgUserPrevious(ou));
        }
        Collections.sort(taskList, new SortTaskByDate());

        return taskList;
    }

    public List<Task> getByOrgUser(OrganizationUser ou) {
        TaskDB taskDB = new TaskDB();
        List<Task> taskList = taskDB.getByOrgUser(ou);
        Collections.sort(taskList, new SortTaskByDate());
        return taskList;
    }

    public List<Task> getByOrgUserUpcoming(OrganizationUser ou) {
        TaskDB taskDB = new TaskDB();
        List<Task> taskList = taskDB.getByOrgUserUpcoming(ou);
        Collections.sort(taskList, new SortTaskByDate());
        return taskList;
    }

    public List<Task> getByOrgUserPrevious(OrganizationUser ou) {
        TaskDB taskDB = new TaskDB();
        List<Task> taskList = taskDB.getByOrgUserPrevious(ou);
        Collections.sort(taskList, new SortTaskByDate());
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

    public void insertRecurring(OrganizationUser ou, Date startDate, Date endDate, String title,
            String description, String status, String priority, String recurringType, Date recurringEndDate) {
        TaskDB taskDB = new TaskDB();
        Task task = new Task();

        Calendar startCal = Calendar.getInstance();
        Calendar endCal = null;
        if (endDate != null) {
            endCal = Calendar.getInstance();
        }
        while (startDate.compareTo(recurringEndDate) <= 0) {
            task.setOrganizationUser(ou);
            task.setStartDate(startDate);
            task.setEndDate(endDate);
            task.setTitle(title);
            task.setDescription(description);
            task.setStatus(status);
            task.setPriority(priority);

            taskDB.insert(task);

            startCal.setTime(startDate);
            if (endDate != null){
                endCal.setTime(endDate);
            }

            switch (recurringType) {
                case "Daily":
                    startCal.add(Calendar.DATE, 1);
                    startDate = startCal.getTime();

                    if (endDate != null) {
                        endCal.add(Calendar.DATE, 1);
                        endDate = endCal.getTime();
                    }
                    break;
                case "Weekly":
                    startCal.add(Calendar.DATE, 7);
                    startDate = startCal.getTime();

                    if (endDate != null) {
                        endCal.add(Calendar.DATE, 7);
                        endDate = endCal.getTime();
                    }
                    break;
                case "Monthly":
                    startCal.add(Calendar.MONTH, 1);
                    startDate = startCal.getTime();

                    if (endDate != null) {
                        endCal.add(Calendar.MONTH, 1);
                        endDate = endCal.getTime();
                    }
                    break;
            }
        }
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
