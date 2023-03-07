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
@Table(name = "shift")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shift.findAll", query = "SELECT s FROM Shift s")
    , @NamedQuery(name = "Shift.findByShiftID", query = "SELECT s FROM Shift s WHERE s.shiftID = :shiftID")
    , @NamedQuery(name = "Shift.findByStartDate", query = "SELECT s FROM Shift s WHERE s.startDate = :startDate")
    , @NamedQuery(name = "Shift.findByEndDate", query = "SELECT s FROM Shift s WHERE s.endDate = :endDate")
    , @NamedQuery(name = "Shift.findByShiftType", query = "SELECT s FROM Shift s WHERE s.shiftType = :shiftType")})
public class Shift implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "shiftID")
    private Integer shiftID;
    @Basic(optional = false)
    @Column(name = "startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "shiftType")
    private String shiftType;
    @JoinColumn(name = "organizationUser", referencedColumnName = "organizationUserID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private OrganizationUser organizationUser;
    @JoinColumn(name = "schedule", referencedColumnName = "scheduleID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Schedule schedule;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shift", fetch = FetchType.EAGER)
    private List<ShiftSwapBoard> shiftSwapBoardList;

    public Shift() {
    }

    public Shift(Integer shiftID) {
        this.shiftID = shiftID;
    }

    public Shift(Integer shiftID, Date startDate, Date endDate) {
        this.shiftID = shiftID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getShiftID() {
        return shiftID;
    }

    public void setShiftID(Integer shiftID) {
        this.shiftID = shiftID;
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

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
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

    @XmlTransient
    public List<ShiftSwapBoard> getShiftSwapBoardList() {
        return shiftSwapBoardList;
    }

    public void setShiftSwapBoardList(List<ShiftSwapBoard> shiftSwapBoardList) {
        this.shiftSwapBoardList = shiftSwapBoardList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shiftID != null ? shiftID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shift)) {
            return false;
        }
        Shift other = (Shift) object;
        if ((this.shiftID == null && other.shiftID != null) || (this.shiftID != null && !this.shiftID.equals(other.shiftID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Shift[ shiftID=" + shiftID + " ]";
    }
    
}
