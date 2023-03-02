/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.User;
import model.OrganizationUser;

/**
 *
 * @author Eric
 */
public class UserDB {
    public User get (String email){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        User user;
        
        try {
            user = em.createNamedQuery("User.findByEmail", User.class).setParameter("email", email).getSingleResult();
        } finally {
            em.close();
        }
        
        return user;
    }
    
    public List<User> getAll (){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<User> users;
        
        try {
            users = em.createNamedQuery("User.findAll", User.class).getResultList();
        } finally {
            em.close();
        }
        
        return users;
    }
    
    public void insert (User user){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void update (User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUserDB orgUserDB = new OrganizationUserDB();
        
        try {
            trans.begin();
            em.merge(user);
            for (OrganizationUser ou : user.getOrganizationUserList()){
                orgUserDB.update(em.merge(ou));
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void delete (User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUserDB orgUserDB = new OrganizationUserDB();
        
        try {
            trans.begin();
            em.remove(em.merge(user));
            for (OrganizationUser ou : user.getOrganizationUserList()){
                orgUserDB.delete(em.merge(ou));
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
