/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Eric
 */
@Entity
@Table(name = "organizationuserschedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrganizationUserSchedule.findAll", query = "SELECT o FROM OrganizationUserSchedule o")
    , @NamedQuery(name = "OrganizationUserSchedule.findByOrganizationUserScheduleID", query = "SELECT o FROM OrganizationUserSchedule o WHERE o.organizationUserScheduleID = :organizationUserScheduleID")})
public class OrganizationUserSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "organizationUserScheduleID")
    private Integer organizationUserScheduleID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizationUserSchedule", fetch = FetchType.EAGER)
    private List<Shift> shiftList;
    @JoinColumn(name = "organizationUser", referencedColumnName = "organizationUserID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private OrganizationUser organizationUser;
    @JoinColumn(name = "schedule", referencedColumnName = "scheduleID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Schedule schedule;

    public OrganizationUserSchedule() {
    }

    public OrganizationUserSchedule(Integer organizationUserScheduleID) {
        this.organizationUserScheduleID = organizationUserScheduleID;
    }

    public Integer getOrganizationUserScheduleID() {
        return organizationUserScheduleID;
    }

    public void setOrganizationUserScheduleID(Integer organizationUserScheduleID) {
        this.organizationUserScheduleID = organizationUserScheduleID;
    }

    @XmlTransient
    public List<Shift> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<Shift> shiftList) {
        this.shiftList = shiftList;
    }

    public OrganizationUser getOrganizationUser() {
        return organizationUser;
    }

    public void setOrganizationUser(OrganizationUser organizationUser) {
        this.organizationUser = organizationUser;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (organizationUserScheduleID != null ? organizationUserScheduleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrganizationUserSchedule)) {
            return false;
        }
        OrganizationUserSchedule other = (OrganizationUserSchedule) object;
        if ((this.organizationUserScheduleID == null && other.organizationUserScheduleID != null) || (this.organizationUserScheduleID != null && !this.organizationUserScheduleID.equals(other.organizationUserScheduleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.OrganizationUserSchedule[ organizationUserScheduleID=" + organizationUserScheduleID + " ]";
    }
    
}
