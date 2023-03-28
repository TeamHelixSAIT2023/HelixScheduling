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
import model.Organization;
import model.OrganizationUser;
import model.User;

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
    
    public OrganizationUser getByOrgUser (Organization org, User user){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        OrganizationUser uo;
        
        try {
            uo = em.createNamedQuery("OrganizationUser.findByOrgUser", OrganizationUser.class).setParameter("organization", org).setParameter("user", user).getSingleResult();
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
    
    public List<OrganizationUser> getAllByOrg (Organization org){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<OrganizationUser> uoList;
        
        try {
            uoList = em.createNamedQuery("OrganizationUser.findByOrganization", OrganizationUser.class).setParameter("organization", org).getResultList();
        } finally {
            em.close();
        }
        
        return uoList;
    }
    
    public List<OrganizationUser> getAllByUser (User user){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<OrganizationUser> uoList;
        
        try {
            uoList = em.createNamedQuery("OrganizationUser.findByUser", OrganizationUser.class).setParameter("user", user).getResultList();
        } finally {
            em.close();
        }
        
        return uoList;
    }
    
    public void insert (OrganizationUser ou){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.persist(ou);
            ou.getOrganization().getOrganizationUserList().add(ou);
            em.merge(ou.getOrganization());
            ou.getUser().getOrganizationUserList().add(ou);
            em.merge(ou.getUser());
            ou.getDept().getOrganizationUserList().add(ou);
            em.merge(ou.getDept());
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void update (OrganizationUser ou) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(ou);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void delete (OrganizationUser ou) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        AvailabilityDB avDB = new AvailabilityDB();
        
        try {
            trans.begin();
            em.remove(em.merge(ou));
            for (Availability a : ou.getAvailabilityList()){
                avDB.delete(em.merge(a));
            }
            em.merge(ou.getOrganization().getOrganizationUserList().remove(ou));
            em.merge(ou.getUser().getOrganizationUserList().remove(ou));
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
