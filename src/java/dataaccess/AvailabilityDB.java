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
        OrganizationUserDB uoDB = new OrganizationUserDB();
        OrganizationUser uo;
        
        try {
            uo = availability.getOrganizationUserID();
            trans.begin();
            uo.getAvailabilityList().add(availability);
            uoDB.update(uo);
            em.persist(availability);
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
            OrganizationUser uo = availability.getOrganizationUserID();
            trans.begin();
            uo.getAvailabilityList().remove(availability);
            em.remove(em.merge(availability));
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
