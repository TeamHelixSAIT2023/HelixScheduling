/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.DepartmentDB;
import model.Department;
import model.Organization;

/**
 *
 * @author Eric
 */
public class DepartmentService {
    public Department get(int deptID) throws Exception {
        DepartmentDB departmentDB = new DepartmentDB();
        Department dept = departmentDB.get(deptID);
        return dept;
    }
    
    public void insert (Organization org, int deptNo, String title, String description) {
        DepartmentDB departmentDB = new DepartmentDB();
        Department dept = new Department();
        dept.setOrganization(org);
        dept.setDeptNo(deptNo);
        dept.setTitle(title);
        dept.setDescription(description);
        departmentDB.insert(dept);
    }
    
    public void update (int deptId, int deptNo, String title, String description) {
        DepartmentDB departmentDB = new DepartmentDB();
        Department dept = departmentDB.get(deptId);
        dept.setDeptNo(deptNo);
        dept.setTitle(title);
        dept.setDescription(description);
        departmentDB.update(dept);
    }
    
    public void delete (int deptID){
        DepartmentDB departmentDB = new DepartmentDB();
        Department dept = departmentDB.get(deptID);
        departmentDB.delete(dept);
    }
}
