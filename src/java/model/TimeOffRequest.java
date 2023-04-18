/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eric
 */
@Entity
@Table(name = "timeoffrequest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TimeOffRequest.findAll", query = "SELECT t FROM TimeOffRequest t")
    , @NamedQuery(name = "TimeOffRequest.findByTimeOffRequestID", query = "SELECT t FROM TimeOffRequest t WHERE t.timeOffRequestID = :timeOffRequestID")
    , @NamedQuery(name = "TimeOffRequest.findByStartDate", query = "SELECT t FROM TimeOffRequest t WHERE t.startDate = :startDate")
    , @NamedQuery(name = "TimeOffRequest.findByEndDate", query = "SELECT t FROM TimeOffRequest t WHERE t.endDate = :endDate")
    , @NamedQuery(name = "TimeOffRequest.findByReason", query = "SELECT t FROM TimeOffRequest t WHERE t.reason = :reason")
    , @NamedQuery(name = "TimeOffRequest.findByApproved", query = "SELECT t FROM TimeOffRequest t WHERE t.approved = :approved")})
public class TimeOffRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "timeOffRequestID")
    private Integer timeOffRequestID;
    @Basic(optional = false)
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "reason")
    private String reason;
    @Column(name = "approved")
    private Boolean approved;
    @JoinColumn(name = "organizationUser", referencedColumnName = "organizationUserID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private OrganizationUser organizationUser;

    public TimeOffRequest() {
    }

    public TimeOffRequest(Integer timeOffRequestID) {
        this.timeOffRequestID = timeOffRequestID;
    }

    public TimeOffRequest(Integer timeOffRequestID, Date startDate, Date endDate) {
        this.timeOffRequestID = timeOffRequestID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getTimeOffRequestID() {
        return timeOffRequestID;
    }

    public void setTimeOffRequestID(Integer timeOffRequestID) {
        this.timeOffRequestID = timeOffRequestID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public OrganizationUser getOrganizationUser() {
        return organizationUser;
    }

    public void setOrganizationUser(OrganizationUser organizationUser) {
        this.organizationUser = organizationUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (timeOffRequestID != null ? timeOffRequestID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TimeOffRequest)) {
            return false;
        }
        TimeOffRequest other = (TimeOffRequest) object;
        if ((this.timeOffRequestID == null && other.timeOffRequestID != null) || (this.timeOffRequestID != null && !this.timeOffRequestID.equals(other.timeOffRequestID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TimeOffRequest[ timeOffRequestID=" + timeOffRequestID + " ]";
    }
    
}
