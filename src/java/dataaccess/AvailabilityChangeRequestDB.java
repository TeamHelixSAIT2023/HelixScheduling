/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.AvailabilityChangeRequest;
import model.OrganizationUser;

/**
 *
 * @author Eric
 */
public class AvailabilityChangeRequestDB {
    public AvailabilityChangeRequest get(int requestID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        AvailabilityChangeRequest request;

        try {
            request = em.find(AvailabilityChangeRequest.class, requestID);
        } finally {
            em.close();
        }

        return request;
    }

    public List<AvailabilityChangeRequest> getBySender(OrganizationUser sender) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<AvailabilityChangeRequest> requestList;

        try {
            requestList = em.createNamedQuery("AvailabilityChangeRequest.findBySender", AvailabilityChangeRequest.class).setParameter("sender", sender).getResultList();
        } finally {
            em.close();
        }

        return requestList;
    }

    public List<AvailabilityChangeRequest> getByReceiver(OrganizationUser receiver) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<AvailabilityChangeRequest> requestList;

        try {
            requestList = em.createNamedQuery("AvailabilityChangeRequest.findByReceiver", AvailabilityChangeRequest.class).setParameter("receiver", receiver).getResultList();
        } finally {
            em.close();
        }

        return requestList;
    }

    public void insert(AvailabilityChangeRequest request) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUser sender, receiver;

        try {
            sender = request.getSender();
            receiver = request.getReceiver();
            trans.begin();
            em.persist(request);
            sender.getAvailabilityChangeRequestListSender().add(request);
            receiver.getAvailabilityChangeRequestListReceiver().add(request);
            em.merge(sender);
            em.merge(receiver);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void update(AvailabilityChangeRequest request) {
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

    public void delete(AvailabilityChangeRequest request) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUser sender, receiver;

        try {
            sender = request.getSender();
            receiver = request.getReceiver();
            trans.begin();
            sender.getAvailabilityChangeRequestListSender().remove(request);
            receiver.getAvailabilityChangeRequestListReceiver().remove(request);
            em.merge(sender);
            em.merge(receiver);
            em.remove(request);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
