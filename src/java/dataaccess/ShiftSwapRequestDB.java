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
import model.ShiftSwapRequest;

/**
 *
 * @author Eric
 */
public class ShiftSwapRequestDB {
    public ShiftSwapRequest get(int requestID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        ShiftSwapRequest request;

        try {
            request = em.find(ShiftSwapRequest.class, requestID);
        } finally {
            em.close();
        }

        return request;
    }

    public List<ShiftSwapRequest> getBySender(OrganizationUser sender) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<ShiftSwapRequest> requestList;

        try {
            requestList = em.createNamedQuery("ShiftSwapRequest.findBySender", ShiftSwapRequest.class).setParameter("sender", sender).getResultList();
        } finally {
            em.close();
        }

        return requestList;
    }

    public List<ShiftSwapRequest> getByReceiver(OrganizationUser receiver) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<ShiftSwapRequest> requestList;

        try {
            requestList = em.createNamedQuery("ShiftSwapRequest.findByReceiver", ShiftSwapRequest.class).setParameter("receiver", receiver).getResultList();
        } finally {
            em.close();
        }

        return requestList;
    }

    public void insert(ShiftSwapRequest request) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUser sender, receiver;

        try {
            sender = request.getSender();
            receiver = request.getReceiver();
            trans.begin();
            em.persist(request);
            sender.getShiftSwapRequestListSender().add(request);
            receiver.getShiftSwapRequestListReceiver().add(request);
            em.merge(sender);
            em.merge(receiver);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void update(ShiftSwapRequest request) {
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

    public void delete(ShiftSwapRequest request) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUser sender, receiver;

        try {
            sender = request.getSender();
            receiver = request.getReceiver();
            trans.begin();
            sender.getShiftSwapRequestListSender().remove(request);
            receiver.getShiftSwapRequestListReceiver().remove(request);
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
