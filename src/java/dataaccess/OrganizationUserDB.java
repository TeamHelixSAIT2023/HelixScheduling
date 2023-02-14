/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Availability;
import model.OrganizationUser;

/**
 *
 * @author Eric
 */
public class OrganizationUserDB {
    public OrganizationUser get (int organizationUserID){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        OrganizationUser uo;
        
        try {
            uo = em.find(OrganizationUser.class, organizationUserID);
        } finally {
            em.close();
        }
        
        return uo;
    }
    
    public OrganizationUser getByUserIDOrgID (int userID, int organizationID){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        OrganizationUser uo;
        
        try {
            uo = em.createNamedQuery("OrganizationUser.findByUserIDOrgID", OrganizationUser.class).setParameter("userID", userID).setParameter("orgID", organizationID).getSingleResult();
        } finally {
            em.close();
        }
        
        return uo;
    }
    
    public List<OrganizationUser> getAll (){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<OrganizationUser> uoList;
        
        try {
            uoList = em.createNamedQuery("OrganizationUser.findAll", OrganizationUser.class).getResultList();
        } finally {
            em.close();
        }
        
        return uoList;
    }
    
    public List<OrganizationUser> getAllByOrg (int orgID){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<OrganizationUser> uoList;
        
        try {
            uoList = em.createNamedQuery("OrganizationUser.findByOrganizationID", OrganizationUser.class).setParameter("organizationID", orgID).getResultList();
        } finally {
            em.close();
        }
        
        return uoList;
    }
    
    public void insert (OrganizationUser uo){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        List<Availability> availabilityList;
        
        try {
            trans.begin();
            em.persist(uo);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void update (OrganizationUser uo) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(uo);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void delete (OrganizationUser uo) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        AvailabilityDB avDB = new AvailabilityDB();
        
        try {
            trans.begin();
            for (Availability a : uo.getAvailabilityList()){
                avDB.delete(em.merge(a));
            }
            em.remove(em.merge(uo));
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
