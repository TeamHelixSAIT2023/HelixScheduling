/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.OrganizationDB;
import java.util.List;
import model.Department;
import model.Organization;
import model.OrganizationUser;
import model.User;

/**
 *
 * @author Eric
 */
public class OrganizationService {
    public Organization get (int organizationID){
        OrganizationDB orgDB = new OrganizationDB();
        Organization org = orgDB.get(organizationID);
        return org;
    }
    
    public Organization getByName (String name) {
        OrganizationDB orgDB = new OrganizationDB();
        Organization org = orgDB.getByName(name);
        return org;
    }
    
    public List<Organization> getAll (){
        OrganizationDB orgDB = new OrganizationDB();
        List<Organization> orgList = orgDB.getAll();
        return orgList;
    }
    
    public void insert (User user, String name, String description, boolean public1) throws Exception{
        OrganizationDB orgDB = new OrganizationDB();
        Organization org = new Organization();
        org.setName(name);
        org.setDescription(description);
        org.setPublic1(public1);
        orgDB.insert(org);
        
        OrganizationUserService ous = new OrganizationUserService();
        ous.insert(org, user, null, null, 0, true, true);
    }
    
    public void updateInfo(int organizationID, String name, String description, boolean public1, boolean managerApprovedAvailabilityChange, boolean managerApprovedShiftSwap, boolean managerApprovedTimeOff){
        OrganizationDB orgDB = new OrganizationDB();
        Organization org = orgDB.get(organizationID);
        
        org.setName(name);
        org.setDescription(description);
        org.setPublic1(public1);
        org.setManagerApprovedAvailabilityChange(managerApprovedAvailabilityChange);
        org.setManagerApprovedShiftSwap(managerApprovedShiftSwap);
        org.setManagerApprovedTimeOff(managerApprovedTimeOff);
        
        orgDB.update(org);
    }
    
    public void update (int organizationID, String name, String description, boolean public1, int shiftSwapBoardID, List<OrganizationUser> uoList, List<Department> deptList){
        OrganizationDB orgDB = new OrganizationDB();
        Organization org = orgDB.get(organizationID);
        
        org.setName(name);
        org.setDescription(description);
        org.setPublic1(public1);
        org.setOrganizationUserList(uoList);
        org.setDepartmentList(deptList);
        
        orgDB.update(org);
    }
    
    public void delete(int organizationID) {
        OrganizationDB orgDB = new OrganizationDB();
        Organization org = orgDB.get(organizationID);
        orgDB.delete(org);
    }
    
    public Organization register(String orgName, String orgDesc, boolean pub) {
        OrganizationDB orgDB = new OrganizationDB();
        Organization org = new Organization();
        org.setName(orgName);
        org.setDescription(orgDesc);
        org.setPublic1(pub);
        orgDB.insert(org);
        return org;
    }
}
