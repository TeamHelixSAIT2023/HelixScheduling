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
@Table(name = "organizationuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrganizationUser.findAll", query = "SELECT o FROM OrganizationUser o")
    , @NamedQuery(name = "OrganizationUser.findByOrganizationUserID", query = "SELECT o FROM OrganizationUser o WHERE o.organizationUserID = :organizationUserID")
    , @NamedQuery(name = "OrganizationUser.findByScheduleID", query = "SELECT o FROM OrganizationUser o WHERE o.scheduleID = :scheduleID")
    , @NamedQuery(name = "OrganizationUser.findByHourly", query = "SELECT o FROM OrganizationUser o WHERE o.hourly = :hourly")
    , @NamedQuery(name = "OrganizationUser.findByAvailability", query = "SELECT o FROM OrganizationUser o WHERE o.availability = :availability")
    , @NamedQuery(name = "OrganizationUser.findByUserIDOrgID", query = "SELECT o FROM OrganizationUser o WHERE o.userID = :userID AND o.organizationID = :organziationID")
    , @NamedQuery(name = "OrganizationUser.findByOrganizationID", query = "SELECT o FROM OrganizationUser o WHERE o.organizationID = :organizationID")
    , @NamedQuery(name = "OrganizationUser.findByUserID", query = "SELECT o FROM OrganizationUser o WHERE o.userID = :userID")})
public class OrganizationUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "organizationUserID")
    private Integer organizationUserID;
    @Basic(optional = false)
    @Column(name = "scheduleID")
    private int scheduleID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "hourly")
    private Double hourly;
    @Column(name = "availability")
    private Integer availability;
    @JoinColumn(name = "deptID", referencedColumnName = "deptID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Department deptID;
    @OneToMany(mappedBy = "managedBy", fetch = FetchType.EAGER)
    private List<OrganizationUser> organizationUserList;
    @JoinColumn(name = "managedBy", referencedColumnName = "organizationUserID")
    @ManyToOne(fetch = FetchType.EAGER)
    private OrganizationUser managedBy;
    @JoinColumn(name = "organizationID", referencedColumnName = "organizationID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Organization organizationID;
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User userID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizationUserID", fetch = FetchType.EAGER)
    private List<Availability> availabilityList;

    public OrganizationUser() {
    }

    public OrganizationUser(Integer organizationUserID) {
        this.organizationUserID = organizationUserID;
    }

    public OrganizationUser(Integer organizationUserID, int scheduleID) {
        this.organizationUserID = organizationUserID;
        this.scheduleID = scheduleID;
    }

    public Integer getOrganizationUserID() {
        return organizationUserID;
    }

    public void setOrganizationUserID(Integer organizationUserID) {
        this.organizationUserID = organizationUserID;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public Double getHourly() {
        return hourly;
    }

    public void setHourly(Double hourly) {
        this.hourly = hourly;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public Department getDeptID() {
        return deptID;
    }

    public void setDeptID(Department deptID) {
        this.deptID = deptID;
    }

    @XmlTransient
    public List<OrganizationUser> getOrganizationUserList() {
        return organizationUserList;
    }

    public void setOrganizationUserList(List<OrganizationUser> organizationUserList) {
        this.organizationUserList = organizationUserList;
    }

    public OrganizationUser getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(OrganizationUser managedBy) {
        this.managedBy = managedBy;
    }

    public Organization getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(Organization organizationID) {
        this.organizationID = organizationID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    @XmlTransient
    public List<Availability> getAvailabilityList() {
        return availabilityList;
    }

    public void setAvailabilityList(List<Availability> availabilityList) {
        this.availabilityList = availabilityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (organizationUserID != null ? organizationUserID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrganizationUser)) {
            return false;
        }
        OrganizationUser other = (OrganizationUser) object;
        if ((this.organizationUserID == null && other.organizationUserID != null) || (this.organizationUserID != null && !this.organizationUserID.equals(other.organizationUserID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.OrganizationUser[ organizationUserID=" + organizationUserID + " ]";
    }
    
}
