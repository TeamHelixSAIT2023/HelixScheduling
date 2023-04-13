/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.AvailabilityDB;
import dataaccess.OrganizationUserDB;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
    
    public void insert(Organization org, User user, Department dept, Schedule schedule, OrganizationUser managedBy, double hourly, boolean owner, boolean admin) {
        OrganizationUser ou = new OrganizationUser();
        ou.setOrganization(org);
        ou.setUser(user);
        ou.setDept(dept);
        ou.setSchedule(schedule);
        ou.setManagedBy(managedBy);
        ou.setHourly(hourly);
        ou.setAdmin(admin);
        ou.setOwner(owner);
        
        //insert new OrganizationUser
        OrganizationUserDB ouDB = new OrganizationUserDB();
        ouDB.insert(ou);
        
        //generate Availability objects
        AvailabilityService as = new AvailabilityService();
        List<Availability> availabilityList = new ArrayList<Availability>();
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        for (int i = 0; i < daysOfWeek.length; i++){
            Calendar defaultTime = Calendar.getInstance();
            defaultTime.set(Calendar.HOUR_OF_DAY, 0);
            defaultTime.set(Calendar.MINUTE, 0);
            defaultTime.set(Calendar.SECOND, 0);
            as.insert(ou, daysOfWeek[i], defaultTime.getTime(), defaultTime.getTime());
        }
    }

    public void update(Organization org, User user, Department dept, Schedule schedule, OrganizationUser managedBy, double hourly,boolean admin, List<Availability> availabilityList) throws Exception {
        OrganizationUserDB uoDB = new OrganizationUserDB();
        OrganizationUser uo = uoDB.getByOrgUser(org, user);
        uo.setDept(dept);
        uo.setSchedule(schedule);
        uo.setManagedBy(managedBy);
        uo.setHourly(hourly);
        uo.setAdmin(admin);
        uo.getAvailabilityList();
        
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
