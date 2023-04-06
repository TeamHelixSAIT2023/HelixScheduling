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
@Table(name = "organizationrequest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrganizationRequest.findAll", query = "SELECT o FROM OrganizationRequest o")
    , @NamedQuery(name = "OrganizationRequest.findByOrganizationRequestID", query = "SELECT o FROM OrganizationRequest o WHERE o.organizationRequestID = :organizationRequestID")
    , @NamedQuery(name = "OrganizationRequest.findByType", query = "SELECT o FROM OrganizationRequest o WHERE o.type = :type")
    , @NamedQuery(name = "OrganizationRequest.findByApproved", query = "SELECT o FROM OrganizationRequest o WHERE o.approved = :approved")
    , @NamedQuery(name = "OrganizationRequest.findByUser", query = "SELECT o FROM OrganizationRequest o WHERE o.user = :user")
    , @NamedQuery(name = "OrganizationRequest.findByOrganization", query ="SELECT o FROM OrganizationRequest o WHERE o.organization = :organization")})
public class OrganizationRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "organizationRequestID")
    private Integer organizationRequestID;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Column(name = "approved")
    private Boolean approved;
    @JoinColumn(name = "organization", referencedColumnName = "organizationID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Organization organization;
    @JoinColumn(name = "user", referencedColumnName = "userID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;

    public OrganizationRequest() {
    }

    public OrganizationRequest(Integer organizationRequestID) {
        this.organizationRequestID = organizationRequestID;
    }

    public OrganizationRequest(Integer organizationRequestID, String type) {
        this.organizationRequestID = organizationRequestID;
        this.type = type;
    }

    public Integer getOrganizationRequestID() {
        return organizationRequestID;
    }

    public void setOrganizationRequestID(Integer organizationRequestID) {
        this.organizationRequestID = organizationRequestID;
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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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
        hash += (organizationRequestID != null ? organizationRequestID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrganizationRequest)) {
            return false;
        }
        OrganizationRequest other = (OrganizationRequest) object;
        if ((this.organizationRequestID == null && other.organizationRequestID != null) || (this.organizationRequestID != null && !this.organizationRequestID.equals(other.organizationRequestID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.OrganizationRequest[ organizationRequestID=" + organizationRequestID + " ]";
    }
    
}
