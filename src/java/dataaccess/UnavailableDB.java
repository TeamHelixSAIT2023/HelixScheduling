/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Organization;
import model.OrganizationUser;
import model.Unavailable;
import model.User;

/**
 *
 * @author Eric
 */
public class UnavailableDB {
    public Unavailable get (int unavailableID){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Unavailable unavailable;
        
        try {
            unavailable = em.find(Unavailable.class, unavailableID);
        } finally {
            em.close();
        }
        
        return unavailable;
    }
    
    public List<Unavailable> getByOrgUser (Organization org, User user) {
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
         List<Unavailable> unavailableList;
         
         try {
            unavailableList = em.createNamedQuery("Unavailable.findByOrgUser", Unavailable.class).setParameter("organization", org).setParameter("user", user).getResultList();
        } finally {
            em.close();
        }
        
        return unavailableList;
    }
    
    public List<Unavailable> getAll (){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Unavailable> unavailableList;
        
        try {
            unavailableList = em.createNamedQuery("Unavailable.findAll", Unavailable.class).getResultList();
        } finally {
            em.close();
        }
        
        return unavailableList;
    }
    
    public void insert (OrganizationUser ou, Unavailable unavailable){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUser uo;
        
        try {
            uo = unavailable.getOrganizationUser();
            trans.begin();
            em.persist(unavailable);
            uo.getUnavailableList().add(unavailable);
            em.merge(ou);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void update (Unavailable unavailable) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(unavailable);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void delete (Unavailable unavailable) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            OrganizationUser uo = unavailable.getOrganizationUser();
            trans.begin();
            em.remove(em.merge(unavailable));
            uo.getUnavailableList().remove(unavailable);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
