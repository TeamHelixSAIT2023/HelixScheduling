/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.ArrayList;
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
public class AvailabilityDB {
    public Availability get (int availabilityID){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Availability availability;
        
        try {
            availability = em.find(Availability.class, availabilityID);
        } finally {
            em.close();
        }
        
        return availability;
    }
    
    public List<Availability> getByOrgUser (Organization org, User user) {
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
         List<Availability> availabilityList;
         
         try {
            availabilityList = em.createNamedQuery("Availability.findByOrgUser", Availability.class).setParameter("organization", org).setParameter("user", user).getResultList();
        } finally {
            em.close();
        }
        
        return availabilityList;
    }
    
    public List<Availability> getAll (){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Availability> availabilityList;
        
        try {
            availabilityList = em.createNamedQuery("Availability.findAll", Availability.class).getResultList();
        } finally {
            em.close();
        }
        
        return availabilityList;
    }
    
    public void insert (Availability availability){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUser ou;
        
        try {
            ou = availability.getOrganizationUser();
            trans.begin();
            em.persist(availability);
            ou.getAvailabilityList().add(availability);
            em.merge(ou);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void update (Availability availability) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(availability);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void delete (Availability availability) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            OrganizationUser uo = availability.getOrganizationUser();
            trans.begin();
            em.remove(em.merge(availability));
            uo.getAvailabilityList().remove(availability);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
