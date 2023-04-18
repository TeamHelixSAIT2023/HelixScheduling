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
import model.OrganizationUserSchedule;
import model.Schedule;

/**
 *
 * @author Eric
 */
public class OrganizationUserScheduleDB {
    public OrganizationUserSchedule get (int orgUserScheduleID){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        OrganizationUserSchedule orgUserSchedule;
        
        try {
            orgUserSchedule = em.find(OrganizationUserSchedule.class, orgUserScheduleID);
        } finally {
            em.close();
        }
        
        return orgUserSchedule;
    }
    
    public List<OrganizationUserSchedule> getByOrgUser (OrganizationUser orgUser) {
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
         List<OrganizationUserSchedule> orgUserScheduleList;
         
         try {
            orgUserScheduleList = em.createNamedQuery("OrganizationUserSchedule.findByOrgUser", OrganizationUserSchedule.class).setParameter("organizationUser", orgUser).getResultList();
        } finally {
            em.close();
        }
        
        return orgUserScheduleList;
    }
    
    public List<OrganizationUserSchedule> getBySchedule (Schedule schedule) {
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
         List<OrganizationUserSchedule> orgUserScheduleList;
         
         try {
            orgUserScheduleList = em.createNamedQuery("OrganizationUserSchedule.findBySchedule", OrganizationUserSchedule.class).setParameter("schedule", schedule).getResultList();
        } finally {
            em.close();
        }
        
        return orgUserScheduleList;
    }
    
    public void insert (OrganizationUserSchedule orgUserSchedule){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUser ou;
        Schedule schedule;
        
        try {
            ou = orgUserSchedule.getOrganizationUser();
            schedule = orgUserSchedule.getSchedule();
            trans.begin();
            em.persist(orgUserSchedule);
            ou.getOrganizationUserScheduleList().add(orgUserSchedule);
            schedule.getOrganizationUserScheduleList().add(orgUserSchedule);
            em.merge(ou);
            em.merge(schedule);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void update (OrganizationUserSchedule orgUserSchedule) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(orgUserSchedule);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void delete (OrganizationUserSchedule orgUserSchedule) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUser ou;
        Schedule schedule;
        
        try {
            ou = orgUserSchedule.getOrganizationUser();
            schedule = orgUserSchedule.getSchedule();
            trans.begin();
            em.remove(em.merge(orgUserSchedule));
            ou.getOrganizationUserScheduleList().remove(orgUserSchedule);
            schedule.getOrganizationUserScheduleList().remove(orgUserSchedule);
            em.merge(ou);
            em.merge(schedule);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
