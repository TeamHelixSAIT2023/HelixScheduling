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
import model.OrganizationUserRequest;

/**
 *
 * @author Eric
 */
public class OrganizationUserRequestDB {

    public OrganizationUserRequest get(int requestID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        OrganizationUserRequest request;

        try {
            request = em.find(OrganizationUserRequest.class, requestID);
        } finally {
            em.close();
        }

        return request;
    }

    public List<OrganizationUserRequest> getBySender(OrganizationUser sender) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<OrganizationUserRequest> requestList;

        try {
            requestList = em.createNamedQuery("OrganizationUserRequest.findBySender", OrganizationUserRequest.class).setParameter("sender", sender).getResultList();
        } finally {
            em.close();
        }

        return requestList;
    }

    public List<OrganizationUserRequest> getByReceiver(OrganizationUser receiver) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<OrganizationUserRequest> requestList;

        try {
            requestList = em.createNamedQuery("OrganizationUserRequest.findByReceiver", OrganizationUserRequest.class).setParameter("receiver", receiver).getResultList();
        } finally {
            em.close();
        }

        return requestList;
    }

    public void insert(OrganizationUserRequest request) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUser sender, receiver;

        try {
            sender = request.getSender();
            receiver = request.getReceiver();
            trans.begin();
            em.persist(request);
            sender.getOrganizationUserRequestListSender().add(request);
            receiver.getOrganizationUserRequestListReceiver().add(request);
            em.merge(sender);
            em.merge(receiver);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void update(OrganizationUserRequest request) {
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

    public void delete(OrganizationUserRequest request) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        OrganizationUser sender, receiver;

        try {
            sender = request.getSender();
            receiver = request.getReceiver();
            trans.begin();
            sender.getOrganizationUserRequestListReceiver().remove(request);
            receiver.getOrganizationUserRequestListReceiver().remove(request);
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
