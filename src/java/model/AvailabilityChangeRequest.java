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
@Table(name = "availabilitychangerequest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AvailabilityChangeRequest.findAll", query = "SELECT a FROM AvailabilityChangeRequest a")
    , @NamedQuery(name = "AvailabilityChangeRequest.findByAvailabilityChangeRequestID", query = "SELECT a FROM AvailabilityChangeRequest a WHERE a.availabilityChangeRequestID = :availabilityChangeRequestID")
    , @NamedQuery(name = "AvailabilityChangeRequest.findByApproved", query = "SELECT a FROM AvailabilityChangeRequest a WHERE a.approved = :approved")
    , @NamedQuery(name = "AvailabilityChangeRequest.findBySender", query = "SELECT o FROM AvailabilityChangeRequest o WHERE o.sender = :sender")
    , @NamedQuery(name = "AvailabilityChangeRequest.findByReceiever", query = "SELECT o FROM AvailabilityChangeRequest o WHERE o.receiver = :receiver")})
public class AvailabilityChangeRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "availabilityChangeRequestID")
    private Integer availabilityChangeRequestID;
    @Column(name = "approved")
    private Boolean approved;
    @JoinColumn(name = "friday", referencedColumnName = "availabilityID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Availability friday;
    @JoinColumn(name = "monday", referencedColumnName = "availabilityID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Availability monday;
    @JoinColumn(name = "receiver", referencedColumnName = "organizationUserID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private OrganizationUser receiver;
    @JoinColumn(name = "saturday", referencedColumnName = "availabilityID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Availability saturday;
    @JoinColumn(name = "sender", referencedColumnName = "organizationUserID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private OrganizationUser sender;
    @JoinColumn(name = "sunday", referencedColumnName = "availabilityID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Availability sunday;
    @JoinColumn(name = "thursday", referencedColumnName = "availabilityID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Availability thursday;
    @JoinColumn(name = "tuesday", referencedColumnName = "availabilityID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Availability tuesday;
    @JoinColumn(name = "wednesday", referencedColumnName = "availabilityID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Availability wednesday;

    public AvailabilityChangeRequest() {
    }

    public AvailabilityChangeRequest(Integer availabilityChangeRequestID) {
        this.availabilityChangeRequestID = availabilityChangeRequestID;
    }

    public Integer getAvailabilityChangeRequestID() {
        return availabilityChangeRequestID;
    }

    public void setAvailabilityChangeRequestID(Integer availabilityChangeRequestID) {
        this.availabilityChangeRequestID = availabilityChangeRequestID;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Availability getFriday() {
        return friday;
    }

    public void setFriday(Availability friday) {
        this.friday = friday;
    }

    public Availability getMonday() {
        return monday;
    }

    public void setMonday(Availability monday) {
        this.monday = monday;
    }

    public OrganizationUser getReceiver() {
        return receiver;
    }

    public void setReceiver(OrganizationUser receiver) {
        this.receiver = receiver;
    }

    public Availability getSaturday() {
        return saturday;
    }

    public void setSaturday(Availability saturday) {
        this.saturday = saturday;
    }

    public OrganizationUser getSender() {
        return sender;
    }

    public void setSender(OrganizationUser sender) {
        this.sender = sender;
    }

    public Availability getSunday() {
        return sunday;
    }

    public void setSunday(Availability sunday) {
        this.sunday = sunday;
    }

    public Availability getThursday() {
        return thursday;
    }

    public void setThursday(Availability thursday) {
        this.thursday = thursday;
    }

    public Availability getTuesday() {
        return tuesday;
    }

    public void setTuesday(Availability tuesday) {
        this.tuesday = tuesday;
    }

    public Availability getWednesday() {
        return wednesday;
    }

    public void setWednesday(Availability wednesday) {
        this.wednesday = wednesday;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (availabilityChangeRequestID != null ? availabilityChangeRequestID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AvailabilityChangeRequest)) {
            return false;
        }
        AvailabilityChangeRequest other = (AvailabilityChangeRequest) object;
        if ((this.availabilityChangeRequestID == null && other.availabilityChangeRequestID != null) || (this.availabilityChangeRequestID != null && !this.availabilityChangeRequestID.equals(other.availabilityChangeRequestID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.AvailabilityChangeRequest[ availabilityChangeRequestID=" + availabilityChangeRequestID + " ]";
    }
    
}
