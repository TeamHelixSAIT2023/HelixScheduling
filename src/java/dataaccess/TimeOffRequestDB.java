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
import model.TimeOffRequest;

/**
 *
 * @author Eric
 */
public class TimeOffRequestDB {
    public TimeOffRequest get(int requestID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        TimeOffRequest request;

        try {
            request = em.find(TimeOffRequest.class, requestID);
        } finally {
            em.close();
        }

        return request;
    }

    public List<TimeOffRequest> getByOrgUser(OrganizationUser orgUser) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<TimeOffRequest> requestList;

        try {
            requestList = em.createNamedQuery("TimeOffRequest.findByOrgUser", TimeOffRequest.class).setParameter("organizationuser", orgUser).getResultList();
        } finally {
            em.close();
        }

        return requestList;
    }

    public void insert(TimeOffRequest request) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUser orgUser;

        try {
            orgUser = request.getOrganizationUser();
            trans.begin();
            em.persist(request);
            orgUser.getTimeOffRequestList().add(request);
            em.merge(orgUser);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void update(TimeOffRequest request) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(request);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void delete(TimeOffRequest request) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUser orgUser;

        try {
            orgUser = request.getOrganizationUser();
            trans.begin();
            orgUser.getTimeOffRequestList().add(request);
            em.merge(orgUser);
            em.remove(request);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
