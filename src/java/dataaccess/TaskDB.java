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
import model.Task;

/**
 *
 * @author Sukhpal
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
            taskList = em.createNamedQuery("Task.findByOrgUser", Task.class).setParameter("orgUser", orgUser).getResultList();
        } finally {
            em.close();
        }
        
        return taskList;
    }
    
    public List<Task> getByOrg (Organization org) {
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
         List<Task> taskList;
         
         try {
            taskList = em.createNamedQuery("Task.findByOrg", Task.class).setParameter("org", org).getResultList();
        } finally {
            em.close();
        }
        
        return taskList;
    }
    
    public List<Task> getAll (){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Task> taskList;
        
        try {
            taskList = em.createNamedQuery("Task.findAll", Task.class).getResultList();
        } finally {
            em.close();
        }
        
        return taskList;
    }
    
    public void insert (Task task){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUser ou;
        Organization org;
        
        try {
            ou = task.getAssignedUser();
            org= task.getOrganization();
            trans.begin();
            em.persist(task);
            ou.getTaskList().add(task);
            org.getTaskList().add(task);
            em.merge(ou);
            em.merge(org);
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
        Organization org;
        
        try {
            ou = task.getAssignedUser();
            org = task.getOrganization();
            trans.begin();
            em.remove(em.merge(task));
            ou.getTaskList().remove(task);
            org.getTaskList().remove(task);
            em.merge(ou);
            em.merge(org);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
