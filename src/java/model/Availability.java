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
@Table(name = "availability")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Availability.findAll", query = "SELECT a FROM Availability a")
    , @NamedQuery(name = "Availability.findByAvailabilityID", query = "SELECT a FROM Availability a WHERE a.availabilityID = :availabilityID")
    , @NamedQuery(name = "Availability.findByDayOfWeek", query = "SELECT a FROM Availability a WHERE a.dayOfWeek = :dayOfWeek")
    , @NamedQuery(name = "Availability.findByStartTime", query = "SELECT a FROM Availability a WHERE a.startTime = :startTime")
    , @NamedQuery(name = "Availability.findByEndTime", query = "SELECT a FROM Availability a WHERE a.endTime = :endTime")
    , @NamedQuery(name = "Availability.findByOrgUser", query = "SELECT o FROM Availability o WHERE o.organizationUser = :organizationUser")})

public class Availability implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "availabilityID")
    private Integer availabilityID;
    @Basic(optional = false)
    @Column(name = "dayOfWeek")
    private String dayOfWeek;
    @Basic(optional = false)
    @Column(name = "startTime")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Basic(optional = false)
    @Column(name = "endTime")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    @JoinColumn(name = "organizationUser", referencedColumnName = "organizationUserID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private OrganizationUser organizationUser;

    public Availability() {
    }

    public Availability(Integer availabilityID) {
        this.availabilityID = availabilityID;
    }

    public Availability(Integer availabilityID, String dayOfWeek, Date startTime, Date endTime) {
        this.availabilityID = availabilityID;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getAvailabilityID() {
        return availabilityID;
    }

    public void setAvailabilityID(Integer availabilityID) {
        this.availabilityID = availabilityID;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
        hash += (availabilityID != null ? availabilityID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Availability)) {
            return false;
        }
        Availability other = (Availability) object;
        if ((this.availabilityID == null && other.availabilityID != null) || (this.availabilityID != null && !this.availabilityID.equals(other.availabilityID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Availability[ availabilityID=" + availabilityID + " ]";
    }
    
}
