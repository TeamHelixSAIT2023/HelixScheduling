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
public class OrganizationDB {

    public Organization get(int availabilityID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Organization organization;

        try {
            organization = em.find(Organization.class, availabilityID);
        } finally {
            em.close();
        }

        return organization;
    }

    public List<Organization> getAll() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Organization> organizationList;

        try {
            organizationList = em.createNamedQuery("Availability.findAll", Organization.class).getResultList();
        } finally {
            em.close();
        }

        return organizationList;
    }

    public void insert(Organization organization) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUser uo;

        try {
            trans.begin();
            em.persist(organization);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void update(Organization organization) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            for (OrganizationUser uo : organization.getOrganizationUserList()) {
                em.merge(uo);
            }
            for (Department dept : organization.getDepartmentList()){
                em.merge(dept);
            }
            em.merge(organization);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void delete(Organization organization) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUserDB uoDB = new OrganizationUserDB();
        DepartmentDB deptDB = new DepartmentDB();

        try {
            trans.begin();
            for (OrganizationUser uo : organization.getOrganizationUserList()){
                uoDB.delete(em.merge(uo));
            }
            for (Department dept : organization.getDepartmentList()){
                deptDB.delete(em.merge(dept));
            }
            em.remove(em.merge(organization));
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
