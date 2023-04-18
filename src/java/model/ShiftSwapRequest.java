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
@Table(name = "shiftswaprequest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShiftSwapRequest.findAll", query = "SELECT s FROM ShiftSwapRequest s")
    , @NamedQuery(name = "ShiftSwapRequest.findByShiftSwapRequestID", query = "SELECT s FROM ShiftSwapRequest s WHERE s.shiftSwapRequestID = :shiftSwapRequestID")
    , @NamedQuery(name = "ShiftSwapRequest.findByApproved", query = "SELECT s FROM ShiftSwapRequest s WHERE s.approved = :approved")
    , @NamedQuery(name = "ShiftSwapRequest.findBySender", query = "SELECT o FROM ShiftSwapRequest o WHERE o.sender = :sender")
    , @NamedQuery(name = "ShiftSwapRequest.findByReceiever", query = "SELECT o FROM ShiftSwapRequest o WHERE o.receiver = :receiver")})
public class ShiftSwapRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "shiftSwapRequestID")
    private Integer shiftSwapRequestID;
    @Column(name = "approved")
    private Boolean approved;
    @JoinColumn(name = "receiver", referencedColumnName = "organizationUserID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private OrganizationUser receiver;
    @JoinColumn(name = "sender", referencedColumnName = "organizationUserID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private OrganizationUser sender;
    @JoinColumn(name = "shift", referencedColumnName = "shiftID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Shift shift;

    public ShiftSwapRequest() {
    }

    public ShiftSwapRequest(Integer shiftSwapRequestID) {
        this.shiftSwapRequestID = shiftSwapRequestID;
    }

    public Integer getShiftSwapRequestID() {
        return shiftSwapRequestID;
    }

    public void setShiftSwapRequestID(Integer shiftSwapRequestID) {
        this.shiftSwapRequestID = shiftSwapRequestID;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public OrganizationUser getReceiver() {
        return receiver;
    }

    public void setReceiver(OrganizationUser receiver) {
        this.receiver = receiver;
    }

    public OrganizationUser getSender() {
        return sender;
    }

    public void setSender(OrganizationUser sender) {
        this.sender = sender;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shiftSwapRequestID != null ? shiftSwapRequestID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShiftSwapRequest)) {
            return false;
        }
        ShiftSwapRequest other = (ShiftSwapRequest) object;
        if ((this.shiftSwapRequestID == null && other.shiftSwapRequestID != null) || (this.shiftSwapRequestID != null && !this.shiftSwapRequestID.equals(other.shiftSwapRequestID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ShiftSwapRequest[ shiftSwapRequestID=" + shiftSwapRequestID + " ]";
    }
    
}
