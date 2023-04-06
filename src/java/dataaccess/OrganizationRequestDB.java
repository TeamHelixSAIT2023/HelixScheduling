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
import model.OrganizationRequest;
import model.User;

/**
 *
 * @author Eric
 */
public class OrganizationRequestDB {
    public OrganizationRequest get (int requestID){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        OrganizationRequest request;
        
        try {
            request = em.find(OrganizationRequest.class, requestID);
        } finally {
            em.close();
        }
        
        return request;
    }
    
    public List<OrganizationRequest> getByUser (User user) {
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
         List<OrganizationRequest> requestList;
         
         try {
            requestList = em.createNamedQuery("OrganizationRequest.findByUser", OrganizationRequest.class).setParameter("user", user).getResultList();
        } finally {
            em.close();
        }
        
        return requestList;
    }
    
    public List<OrganizationRequest> getByOrg (Organization org) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
         List<OrganizationRequest> requestList;
         
         try {
            requestList = em.createNamedQuery("OrganizationRequest.findByOrganization", OrganizationRequest.class).setParameter("organization", org).getResultList();
        } finally {
            em.close();
        }
        
        return requestList;
    }
    
    public void insert (OrganizationRequest request){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        User user;
        Organization org;
        
        try {
            user = request.getUser();
            org = request.getOrganization();
            trans.begin();
            em.persist(request);
            user.getOrganizationRequestList().add(request);
            org.getOrganizationRequestList().add(request);
            em.merge(user);
            em.merge(org);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
        
//        try {
//            sender = request.getSender();
//            receiver = request.getReceiver();
//            trans.begin();
//            em.persist(request);
//            sender.getRequestSenderList().add(request);
//            receiver.getRequestReceiverList().add(request);
//            em.merge(sender);
//            em.merge(receiver);
//            trans.commit();
//        } catch (Exception e) {
//            trans.rollback();
//        } finally {
//            em.close();
//        }
    }
    
    public void update (OrganizationRequest request) {
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
    
    public void delete (OrganizationRequest request) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        User user;
        Organization org;
        
        try {
            user = request.getUser();
            org = request.getOrganization();
            trans.begin();
            user.getOrganizationRequestList().remove(request);
            org.getOrganizationRequestList().remove(request);
            em.merge(user);
            em.merge(org);
            em.remove(request);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
