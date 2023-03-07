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
            schedules = em.createNamedQuery("schedules.findAll", Schedule.class).getResultList();
        } finally {
            em.close();
        }
        
        return schedules;
    }
    
    public void insert (Schedule schedule){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationDB orgDB = new OrganizationDB();
        Organization org;
        
        try {
            
            trans.begin();
            em.persist(schedule);
            org = schedule.getOrganization();
            org.getScheduleList().add(schedule);
            orgDB.update(org);
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
        OrganizationUserDB orgUserDB = new OrganizationUserDB();
        OrganizationDB orgDB = new OrganizationDB();
        
        try {
            trans.begin();
            em.merge(schedule);
            for (OrganizationUser ou : schedule.getOrganizationUserList()){
                orgUserDB.update(em.merge(ou));
            }
            orgDB.update(em.merge(schedule.getOrganization()));
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
        OrganizationUserDB orgUserDB = new OrganizationUserDB();
        OrganizationDB orgDB = new OrganizationDB();
        
        try {
            trans.begin();
            for (OrganizationUser ou : schedule.getOrganizationUserList()){
                orgUserDB.update(em.merge(ou));
            }
            em.remove(em.merge(schedule));
            orgDB.update(schedule.getOrganization());
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
