/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Eric
 */
@Entity
@Table(name = "schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s")
    , @NamedQuery(name = "Schedule.findByScheduleID", query = "SELECT s FROM Schedule s WHERE s.scheduleID = :scheduleID")
    , @NamedQuery(name = "Schedule.findByStartDate", query = "SELECT s FROM Schedule s WHERE s.startDate = :startDate")
    , @NamedQuery(name = "Schedule.findByEndDate", query = "SELECT s FROM Schedule s WHERE s.endDate = :endDate")
    , @NamedQuery(name = "Schedule.findByOrg", query = "SELECT s FROM Schedule s WHERE s.organization = :organization")
    , @NamedQuery(name = "Schedule.findByDept", query = "SELECT s FROM Schedule s WHERE s.dept = :dept")
    , @NamedQuery(name = "Schedule.findByOrgDept", query = "SELECT s FROM Schedule s WHERE s.organization = :organization AND s.dept = :dept")
    , @NamedQuery(name = "Schedule.findByOrgDeptStartDate", query = "SELECT s FROM Schedule s WHERE s.organization = :organization AND s.dept = :dept AND s.startDate = :startDate")})
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "scheduleID")
    private Integer scheduleID;
    @Basic(optional = false)
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @JoinColumn(name = "dept", referencedColumnName = "deptID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Department dept;
    @JoinColumn(name = "organization", referencedColumnName = "organizationID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Organization organization;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schedule", fetch = FetchType.EAGER)
    private List<OrganizationUserSchedule> organizationUserScheduleList;

    public Schedule() {
    }

    public Schedule(Integer scheduleID) {
        this.scheduleID = scheduleID;
    }

    public Schedule(Integer scheduleID, Date startDate, Date endDate) {
        this.scheduleID = scheduleID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(Integer scheduleID) {
        this.scheduleID = scheduleID;
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

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @XmlTransient
    public List<OrganizationUserSchedule> getOrganizationUserScheduleList() {
        return organizationUserScheduleList;
    }

    public void setOrganizationUserScheduleList(List<OrganizationUserSchedule> organizationUserScheduleList) {
        this.organizationUserScheduleList = organizationUserScheduleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scheduleID != null ? scheduleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schedule)) {
            return false;
        }
        Schedule other = (Schedule) object;
        if ((this.scheduleID == null && other.scheduleID != null) || (this.scheduleID != null && !this.scheduleID.equals(other.scheduleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Schedule[ scheduleID=" + scheduleID + " ]";
    }
    
}
