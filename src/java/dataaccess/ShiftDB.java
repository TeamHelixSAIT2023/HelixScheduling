/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.OrganizationUserSchedule;
import model.Schedule;
import model.Shift;

/**
 *
 * @author Eric
 */
public class ShiftDB {
    public Shift get (int shiftID){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        Shift shift;
        
        try {
            shift = em.find(Shift.class, shiftID);
        } finally {
            em.close();
        }
        
        return shift;
    }
    
    public List<Shift> getAll (){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Shift> shifts;
        
        try {
            shifts = em.createNamedQuery("Shift.findAll", Shift.class).getResultList();
        } finally {
            em.close();
        }
        
        return shifts;
    }
    
    public List<Shift> getByOrgUserSchedule (OrganizationUserSchedule orgUserSchedule){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Shift> shifts;
        
        try {
            shifts = em.createNamedQuery("Schedule.findByOrgUserSchedule", Shift.class).setParameter("organizationUserSchedule", orgUserSchedule).getResultList();
        } finally {
            em.close();
        }
        
        return shifts;
    }
    
    public List<Shift> getByUpcoming (OrganizationUserSchedule orgUserSchedule){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Shift> shifts;
        
        try {
            shifts = em.createNamedQuery("Shift.findByUpcoming", Shift.class).setParameter("organizationUserSchedule", orgUserSchedule).getResultList();
        } finally {
            em.close();
        }
        
        return shifts;
    }
    
    public void insert (Shift shift){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUserSchedule orgUserSchedule;
        
        try {
            orgUserSchedule = shift.getOrganizationUserSchedule();
            trans.begin();
            em.persist(shift);
            orgUserSchedule.getShiftList().add(shift);
            em.merge(orgUserSchedule);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void update (Shift shift) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(shift);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void delete (Shift shift) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUserSchedule orgUserSchedule;
        
        try {
            orgUserSchedule = shift.getOrganizationUserSchedule();
            trans.begin();
            orgUserSchedule.getShiftList().remove(shift);
            em.remove(em.merge(shift));
            em.merge(orgUserSchedule);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
