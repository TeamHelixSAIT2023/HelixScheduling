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
@Table(name = "organizationuserrequest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrganizationUserRequest.findAll", query = "SELECT o FROM OrganizationUserRequest o")
    , @NamedQuery(name = "OrganizationUserRequest.findByOrganizationUserRequestID", query = "SELECT o FROM OrganizationUserRequest o WHERE o.organizationUserRequestID = :organizationUserRequestID")
    , @NamedQuery(name = "OrganizationUserRequest.findByType", query = "SELECT o FROM OrganizationUserRequest o WHERE o.type = :type")
    , @NamedQuery(name = "OrganizationUserRequest.findByApproved", query = "SELECT o FROM OrganizationUserRequest o WHERE o.approved = :approved")
    , @NamedQuery(name = "OrganizationUserRequest.findBySender", query = "SELECT o FROM OrganizationUserRequest o WHERE o.sender = :sender")
    , @NamedQuery(name = "OrganizationUserRequest.findByReceiever", query = "SELECT o FROM OrganizationUserRequest o WHERE o.receiver = :receiver")})
public class OrganizationUserRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "organizationUserRequestID")
    private Integer organizationUserRequestID;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Column(name = "approved")
    private Boolean approved;
    @JoinColumn(name = "receiver", referencedColumnName = "organizationUserID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private OrganizationUser receiver;
    @JoinColumn(name = "sender", referencedColumnName = "organizationUserID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private OrganizationUser sender;

    public OrganizationUserRequest() {
    }

    public OrganizationUserRequest(Integer organizationUserRequestID) {
        this.organizationUserRequestID = organizationUserRequestID;
    }

    public OrganizationUserRequest(Integer organizationUserRequestID, String type) {
        this.organizationUserRequestID = organizationUserRequestID;
        this.type = type;
    }

    public Integer getOrganizationUserRequestID() {
        return organizationUserRequestID;
    }

    public void setOrganizationUserRequestID(Integer organizationUserRequestID) {
        this.organizationUserRequestID = organizationUserRequestID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (organizationUserRequestID != null ? organizationUserRequestID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrganizationUserRequest)) {
            return false;
        }
        OrganizationUserRequest other = (OrganizationUserRequest) object;
        if ((this.organizationUserRequestID == null && other.organizationUserRequestID != null) || (this.organizationUserRequestID != null && !this.organizationUserRequestID.equals(other.organizationUserRequestID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.OrganizationUserRequest[ organizationUserRequestID=" + organizationUserRequestID + " ]";
    }
    
}
