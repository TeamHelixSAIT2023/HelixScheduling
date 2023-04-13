/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Department;
import model.Organization;
import model.OrganizationUser;
import model.OrganizationUserSchedule;
import model.Schedule;

/**
 *
 * @author Eric
 */
public class ScheduleDB {
    public Schedule get (int scheduleID){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Schedule schedule;
        
        try {
            schedule = em.find(Schedule.class, scheduleID);
        } finally {
            em.close();
        }
        
        return schedule;
    }
    
    public List<Schedule> getAll (){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Schedule> schedules;
        
        try {
            schedules = em.createNamedQuery("Schedule.findAll", Schedule.class).getResultList();
        } finally {
            em.close();
        }
        
        return schedules;
    }
    
    public List<Schedule> getByOrg (Organization org){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Schedule> schedules;
        
        try {
            schedules = em.createNamedQuery("Schedule.findByOrg", Schedule.class).setParameter("organization", org).getResultList();
        } finally {
            em.close();
        }
        
        return schedules;
    }
    
    public List<Schedule> getByDept (Department dept){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Schedule> depts;
        
        try {
            depts = em.createNamedQuery("Schedule.findByDept", Schedule.class).setParameter("dept", dept).getResultList();
        } finally {
            em.close();
        }
        
        return depts;
    }
    
    public void insert (Schedule schedule){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        Organization org;
        
        try {
            org = schedule.getOrganization();
            trans.begin();
            em.persist(schedule);
            org.getScheduleList().add(schedule);
            em.merge(org);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void update (Schedule schedule) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(schedule);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void delete (Schedule schedule) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        Organization org;
        
        try {
            org = schedule.getOrganization();
            trans.begin(); 
            em.remove(em.merge(schedule));
            org.getScheduleList().remove(schedule);
            for (OrganizationUserSchedule ous : schedule.getOrganizationUserScheduleList()){
                em.remove(ous);
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
