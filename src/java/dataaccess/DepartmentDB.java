/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Department;
import model.Organization;
import model.OrganizationUser;

/**
 *
 * @author Eric
 */
public class DepartmentDB {

    public Department get(int departmentID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Department availability;

        try {
            availability = em.find(Department.class, departmentID);
        } finally {
            em.close();
        }

        return availability;
    }

    public List<Department> getAll() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Department> departmentList;

        try {
            departmentList = em.createNamedQuery("Availability.findAll", Department.class).getResultList();
        } finally {
            em.close();
        }

        return departmentList;
    }

    public void insert(Department department) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        Organization organization;

        try {
            organization = department.getOrganizationID();
            trans.begin();
            organization.getDepartmentList().add(department);
            em.persist(organization);
            em.persist(department);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void update(Department department) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        List<OrganizationUser> ouList;
        Organization organization;

        try {
            ouList = department.getOrganizationUserList();
            organization = department.getOrganizationID();
            trans.begin();
            for (OrganizationUser ou : ouList) {
                em.merge(ou);
            }
            em.merge(organization);
            em.merge(department);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void delete(Department department) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        List<OrganizationUser> ouList;
        Organization organization;

        try {
            ouList = department.getOrganizationUserList();
            organization = department.getOrganizationID();
            trans.begin();
            for (OrganizationUser ou : ouList) {
                ou.setDeptID(null);
                em.merge(ou);
            }
            organization.getDepartmentList().remove(department);
            em.persist(organization);
            em.remove(em.merge(department));
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
