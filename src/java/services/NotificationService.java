/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.NotificationDB;
import java.util.List;
import model.Notification;
import model.User;

/**
 *
 * @author Eric
 */
public class NotificationService {
    public Notification get (int notificationID){
        NotificationDB notificationDB = new NotificationDB();
        Notification notification = notificationDB.get(notificationID);
        return notification;
    }
    
    public List<Notification> getByUser (User user) {
        NotificationDB notificationDB = new NotificationDB();
        List<Notification> notificationList = notificationDB.getByUser(user);
        return notificationList;
    }
    
    public void insert (User user, int requestID, String requestType, String title, String description) {
        NotificationDB notificationDB = new NotificationDB();
        Notification notification = new Notification();
        
        notification.setUser(user);
        notification.setRequestID(requestID);
        notification.setRequestType(requestType);
        notification.setTitle(title);
        notification.setDescription(description);
        notification.setDimissed(false);
        
        notificationDB.insert(notification);
    }
    
    public void update (int notificationID, boolean dismissed) {
        NotificationDB notificationDB = new NotificationDB();
        Notification notification = notificationDB.get(notificationID);
        notification.setDimissed(dismissed);
        notificationDB.update(notification);
    }
    
    public void delete (int notificationID){
        NotificationDB notificationDB = new NotificationDB();
        Notification notification = notificationDB.get(notificationID);
        notificationDB.delete(notification);
    }
}
