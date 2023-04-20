/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eric
 */
@Entity
@Table(name = "notification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n")
    , @NamedQuery(name = "Notification.findByNotificationID", query = "SELECT n FROM Notification n WHERE n.notificationID = :notificationID")
    , @NamedQuery(name = "Notification.findByRequestID", query = "SELECT n FROM Notification n WHERE n.requestID = :requestID")
    , @NamedQuery(name = "Notification.findByRequestType", query = "SELECT n FROM Notification n WHERE n.requestType = :requestType")
    , @NamedQuery(name = "Notification.findByTitle", query = "SELECT n FROM Notification n WHERE n.title = :title")
    , @NamedQuery(name = "Notification.findByDescription", query = "SELECT n FROM Notification n WHERE n.description = :description")
    , @NamedQuery(name = "Notification.findByDimissed", query = "SELECT n FROM Notification n WHERE n.dimissed = :dimissed")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "notificationID")
    private Integer notificationID;
    @Column(name = "requestID")
    private Integer requestID;
    @Column(name = "requestType")
    private String requestType;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "dimissed")
    private Boolean dimissed;
    @JoinColumn(name = "user", referencedColumnName = "userID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;

    public Notification() {
    }

    public Notification(Integer notificationID) {
        this.notificationID = notificationID;
    }

    public Notification(Integer notificationID, String title) {
        this.notificationID = notificationID;
        this.title = title;
    }

    public Integer getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(Integer notificationID) {
        this.notificationID = notificationID;
    }

    public Integer getRequestID() {
        return requestID;
    }

    public void setRequestID(Integer requestID) {
        this.requestID = requestID;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDimissed() {
        return dimissed;
    }

    public void setDimissed(Boolean dimissed) {
        this.dimissed = dimissed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notificationID != null ? notificationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.notificationID == null && other.notificationID != null) || (this.notificationID != null && !this.notificationID.equals(other.notificationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Notification[ notificationID=" + notificationID + " ]";
    }
    
}
