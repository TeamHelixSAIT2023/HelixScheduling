/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.OrganizationUserDB;
import java.util.List;
import model.OrganizationUser;
import model.Availability;
import model.Department;
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
    
    public OrganizationUser getByUserIDOrgID (int userID, int orgID){
        OrganizationUserDB uoDB = new OrganizationUserDB();
        OrganizationUser ou = uoDB.getByUserIDOrgID(userID, orgID);
        return ou;
    }
    
    public List<OrganizationUser> getByOrg (int orgID) {
        OrganizationUserDB uoDB = new OrganizationUserDB();
        List<OrganizationUser> ouList = uoDB.getAllByOrg(orgID);
        return ouList;
    }
    
    public List<OrganizationUser> getByUser (int userID) {
        OrganizationUserDB uoDB = new OrganizationUserDB();
        List<OrganizationUser> ouList = uoDB.getAllByUser(userID);
        return ouList;
    }
    
    public void insert(int organizationID, int userID, Department dept, int scheduleID, OrganizationUser managedBy, double hourly, List<Availability> availabilityList) throws Exception {
        OrganizationUser uo = new OrganizationUser(organizationID, userID);
        uo.setDeptID(dept);
        uo.setScheduleID(scheduleID);
        uo.setManagedBy(managedBy);
        uo.setHourly(hourly);
        uo.setAvailabilityList(availabilityList);
        OrganizationUserDB uoDB = new OrganizationUserDB();
        uoDB.insert(uo);
    }

    public void update(int organizationID, int userID, Department dept, int scheduleID, OrganizationUser managedBy, double hourly, List<Availability> availabilityList) throws Exception {
        OrganizationUserDB uoDB = new OrganizationUserDB();
        OrganizationUser uo = uoDB.getByUserIDOrgID(userID, organizationID);
        uo.setDeptID(dept);
        uo.setScheduleID(scheduleID);
        uo.setManagedBy(managedBy);
        uo.setHourly(hourly);
        uo.setAvailabilityList(availabilityList);
        
        uoDB.update(uo);
    }
    
    public void delete (int organizationID, int userID) {
        OrganizationUserDB uoDB = new OrganizationUserDB();
        OrganizationUser uo = uoDB.getByUserIDOrgID(userID, organizationID);
        uoDB.delete(uo);
    }
}
