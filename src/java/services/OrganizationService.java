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
    
    public List<Organization> getAll (){
        OrganizationDB orgDB = new OrganizationDB();
        List<Organization> orgList = orgDB.getAll();
        return orgList;
    }
    
    public void insert (String name, String description, boolean public1){
        OrganizationDB orgDB = new OrganizationDB();
        Organization org = new Organization();
        org.setName(name);
        org.setDescription(description);
        org.setPublic1(public1);
        orgDB.insert(org);
    }
    
    public void update (int organizationID, String name, String description, boolean public1, int shiftSwapBoardID, List<OrganizationUser> uoList, List<Department> deptList){
        OrganizationDB orgDB = new OrganizationDB();
        Organization org = orgDB.get(organizationID);
        
        org.setName(name);
        org.setDescription(description);
        org.setPublic1(public1);
        org.setShiftSwapBoardID(shiftSwapBoardID);
        org.setOrganizationUserList(uoList);
        org.setDepartmentList(deptList);
        
        orgDB.update(org);
    }
    
    public void delete(int organizationID) {
        OrganizationDB orgDB = new OrganizationDB();
        Organization org = orgDB.get(organizationID);
        orgDB.delete(org);
    }
}
