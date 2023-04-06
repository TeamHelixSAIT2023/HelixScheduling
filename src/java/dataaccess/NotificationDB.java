/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Notification;
import model.User;
/**
 *
 * @author Eric
 */
public class NotificationDB {
    public Notification get (int notificationID){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Notification notification;
        
        try {
            notification = em.find(Notification.class, notificationID);
        } finally {
            em.close();
        }
        
        return notification;
    }
    
    public List<Notification> getByUser (User user) {
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
         List<Notification> availabilityList;
         
         try {
            availabilityList = em.createNamedQuery("Notification.findByUser", Notification.class).setParameter("user", user).getResultList();
        } finally {
            em.close();
        }
        
        return availabilityList;
    }
    
    public List<Notification> getAll (){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Notification> notificationList;
        
        try {
            notificationList = em.createNamedQuery("Notification.findAll", Notification.class).getResultList();
        } finally {
            em.close();
        }
        
        return notificationList;
    }
    
    public void insert (Notification notification){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        User user;
        
        try {
            user = notification.getUser();
            trans.begin();
            em.persist(notification);
            user.getNotificationList().add(notification);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void update (Notification notification) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(notification);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void delete (Notification notification) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        User user;
        
        try {
            user = notification.getUser();
            trans.begin();
            user.getNotificationList().remove(notification);
            em.merge(user);
            em.remove(em.merge(notification));
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
