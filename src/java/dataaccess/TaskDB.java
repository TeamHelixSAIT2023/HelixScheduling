/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.OrganizationUser;
import model.Task;

/**
 *
 * @author Eric
 */
public class TaskDB {
    public Task get (int taskID){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Task task;
        
        try {
            task = em.find(Task.class, taskID);
        } finally {
            em.close();
        }
        
        return task;
    }
    
    public List<Task> getByOrgUser (OrganizationUser orgUser) {
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
         List<Task> taskList;
         
         try {
            taskList = em.createNamedQuery("Task.findByOrgUser", Task.class).setParameter("organizationUser", orgUser).getResultList();
        } finally {
            em.close();
        }
        
        return taskList;
    }
    
    public List<Task> getByOrgUserUpcoming (OrganizationUser orgUser) {
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
         List<Task> taskList;
         
         try {
            taskList = em.createNamedQuery("Task.findByUpcoming", Task.class).setParameter("organizationUser", orgUser).getResultList();
        } finally {
            em.close();
        }
        
        return taskList;
    }
    
    public List<Task> getByOrgUserPrevious (OrganizationUser orgUser) {
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
         List<Task> taskList;
         
         try {
            taskList = em.createNamedQuery("Task.findByPrevious", Task.class).setParameter("organizationUser", orgUser).getResultList();
        } finally {
            em.close();
        }
        
        return taskList;
    }
    
    public void insert (Task task){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUser ou;
        
        try {
            ou = task.getOrganizationUser();
            trans.begin();
            em.persist(task);
            ou.getTaskList().add(task);
            em.merge(ou);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void update (Task task) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(task);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void delete (Task task) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUser ou;
        
        try {
            ou = task.getOrganizationUser();
            trans.begin();
            em.remove(em.merge(task));
            ou.getTaskList().remove(task);
            em.merge(ou);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
