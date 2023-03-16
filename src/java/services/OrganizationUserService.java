/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.AvailabilityDB;
import dataaccess.OrganizationUserDB;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.OrganizationUser;
import model.Availability;
import model.Department;
import model.Organization;
import model.Schedule;
import model.User;
/**
 *
 * @author Eric
 */
public class OrganizationUserService {
    
    public OrganizationUser get(int organizationUserID){
        OrganizationUserDB uoDB = new OrganizationUserDB();
        OrganizationUser uo = uoDB.get(organizationUserID);
        return uo;
    }
    
    public OrganizationUser getByOrgUser (Organization org, User user){
        OrganizationUserDB uoDB = new OrganizationUserDB();
        OrganizationUser ou = uoDB.getByOrgUser(org, user);
        return ou;
    }
    
    public List<OrganizationUser> getByOrg (Organization org) {
        OrganizationUserDB uoDB = new OrganizationUserDB();
        List<OrganizationUser> ouList = uoDB.getAllByOrg(org);
        return ouList;
    }
    
    public List<OrganizationUser> getByUser (User user) {
        OrganizationUserDB uoDB = new OrganizationUserDB();
        List<OrganizationUser> ouList = uoDB.getAllByUser(user);
        return ouList;
    }
    
    public void insert(Organization org, User user, Department dept, Schedule schedule, OrganizationUser managedBy, double hourly) throws Exception {
        OrganizationUser uo = new OrganizationUser();
        uo.setOrganization(org);
        uo.setUser(user);
        uo.setDept(dept);
        uo.setSchedule(schedule);
        uo.setManagedBy(managedBy);
        uo.setHourly(hourly);
        
        //generate Availability objects
        List<Availability> availabilityList = new ArrayList<Availability>();
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        for (int i = 0; i < daysOfWeek.length; i++){
            availabilityList.add(new Availability(0, daysOfWeek[i], new Date(), new Date()));
            availabilityList.get(i).setOrganizationUser(uo);
        }
        uo.setAvailabilityList(availabilityList);
        
        //insert new OrganizationUser
        OrganizationUserDB uoDB = new OrganizationUserDB();
        uoDB.insert(uo);
        
        //insert Availability objects
        AvailabilityDB aDB = new AvailabilityDB();
        for (Availability a : availabilityList){
            aDB.insert(a);
        }
    }

    public void update(Organization org, User user, Department dept, Schedule schedule, OrganizationUser managedBy, double hourly, List<Availability> availabilityList) throws Exception {
        OrganizationUserDB uoDB = new OrganizationUserDB();
        OrganizationUser uo = uoDB.getByOrgUser(org, user);
        uo.setDept(dept);
        uo.setSchedule(schedule);
        uo.setManagedBy(managedBy);
        uo.setHourly(hourly);
        
        AvailabilityService as = new AvailabilityService();
        as.update(uo, availabilityList);
        
        uoDB.update(uo);
    }
    
    public void delete (Organization org, User user) {
        OrganizationUserDB uoDB = new OrganizationUserDB();
        OrganizationUser uo = uoDB.getByOrgUser(org, user);
        uoDB.delete(uo);
    }
}
