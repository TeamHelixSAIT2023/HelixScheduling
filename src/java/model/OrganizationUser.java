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
    , @NamedQuery(name = "OrganizationUser.findByHourly", query = "SELECT o FROM OrganizationUser o WHERE o.hourly = :hourly")
    , @NamedQuery(name = "OrganizationUser.findByAdmin", query = "SELECT o FROM OrganizationUser o WHERE o.admin = :admin")
    , @NamedQuery(name = "OrganizationUser.findByOwner", query = "SELECT o FROM OrganizationUser o WHERE o.owner = :owner")
    , @NamedQuery(name = "OrganizationUser.findByOrgUser", query = "SELECT o FROM OrganizationUser o WHERE o.organization = :organization AND o.user = :user")
    , @NamedQuery(name = "OrganizationUser.findByOrganization", query = "SELECT o FROM OrganizationUser o WHERE o.organization = :organization")
    , @NamedQuery(name = "OrganizationUser.findByUser", query = "SELECT o FROM OrganizationUser o WHERE o.user = :user")})
public class OrganizationUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "organizationUserID")
    private Integer organizationUserID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "hourly")
    private Double hourly;
    @Column(name = "admin")
    private Boolean admin;
    @Column(name = "owner")
    private Boolean owner;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver", fetch = FetchType.EAGER)
    private List<OrganizationUserRequest> organizationUserRequestListReceiver;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", fetch = FetchType.EAGER)
    private List<OrganizationUserRequest> organizationUserRequestListSender;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizationUser", fetch = FetchType.EAGER)
    private List<Unavailable> unavailableList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver", fetch = FetchType.EAGER)
    private List<AvailabilityChangeRequest> availabilityChangeRequestListReceiver;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", fetch = FetchType.EAGER)
    private List<AvailabilityChangeRequest> availabilityChangeRequestListSender;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizationUser", fetch = FetchType.EAGER)
    private List<Availability> availabilityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver", fetch = FetchType.EAGER)
    private List<ShiftSwapRequest> shiftSwapRequestListReceiver;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", fetch = FetchType.EAGER)
    private List<ShiftSwapRequest> shiftSwapRequestListSender;
    @JoinColumn(name = "dept", referencedColumnName = "deptID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Department dept;
    @OneToMany(mappedBy = "managedBy", fetch = FetchType.EAGER)
    private List<OrganizationUser> organizationUserList;
    @JoinColumn(name = "managedBy", referencedColumnName = "organizationUserID")
    @ManyToOne(fetch = FetchType.EAGER)
    private OrganizationUser managedBy;
    @JoinColumn(name = "organization", referencedColumnName = "organizationID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Organization organization;
    @JoinColumn(name = "user", referencedColumnName = "userID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizationUser", fetch = FetchType.EAGER)
    private List<TimeOffRequest> timeOffRequestList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizationUser", fetch = FetchType.EAGER)
    private List<OrganizationUserSchedule> organizationUserScheduleList;

    public OrganizationUser() {
    }

    public OrganizationUser(Integer organizationUserID) {
        this.organizationUserID = organizationUserID;
    }

    public Integer getOrganizationUserID() {
        return organizationUserID;
    }

    public void setOrganizationUserID(Integer organizationUserID) {
        this.organizationUserID = organizationUserID;
    }

    public Double getHourly() {
        return hourly;
    }

    public void setHourly(Double hourly) {
        this.hourly = hourly;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getOwner() {
        return owner;
    }

    public void setOwner(Boolean owner) {
        this.owner = owner;
    }

    @XmlTransient
    public List<OrganizationUserRequest> getOrganizationUserRequestListReceiver() {
        return organizationUserRequestListReceiver;
    }

    public void setOrganizationUserRequestListReceiver(List<OrganizationUserRequest> organizationUserRequestList) {
        this.organizationUserRequestListReceiver = organizationUserRequestList;
    }

    @XmlTransient
    public List<OrganizationUserRequest> getOrganizationUserRequestListSender() {
        return organizationUserRequestListSender;
    }

    public void setOrganizationUserRequestListSender(List<OrganizationUserRequest> organizationUserRequestList1) {
        this.organizationUserRequestListSender = organizationUserRequestList1;
    }

    @XmlTransient
    public List<Unavailable> getUnavailableList() {
        return unavailableList;
    }

    public void setUnavailableList(List<Unavailable> unavailableList) {
        this.unavailableList = unavailableList;
    }

    @XmlTransient
    public List<AvailabilityChangeRequest> getAvailabilityChangeRequestListReceiver() {
        return availabilityChangeRequestListReceiver;
    }

    public void setAvailabilityChangeRequestListReceiver(List<AvailabilityChangeRequest> availabilityChangeRequestList) {
        this.availabilityChangeRequestListReceiver = availabilityChangeRequestList;
    }

    @XmlTransient
    public List<AvailabilityChangeRequest> getAvailabilityChangeRequestListSender() {
        return availabilityChangeRequestListSender;
    }

    public void setAvailabilityChangeRequestListSender(List<AvailabilityChangeRequest> availabilityChangeRequestList1) {
        this.availabilityChangeRequestListSender = availabilityChangeRequestList1;
    }

    @XmlTransient
    public List<Availability> getAvailabilityList() {
        return availabilityList;
    }

    public void setAvailabilityList(List<Availability> availabilityList) {
        this.availabilityList = availabilityList;
    }

    @XmlTransient
    public List<ShiftSwapRequest> getShiftSwapRequestListReceiver() {
        return shiftSwapRequestListReceiver;
    }

    public void setShiftSwapRequestListReceiver(List<ShiftSwapRequest> shiftSwapRequestList) {
        this.shiftSwapRequestListReceiver = shiftSwapRequestList;
    }

    @XmlTransient
    public List<ShiftSwapRequest> getShiftSwapRequestListSender() {
        return shiftSwapRequestListSender;
    }

    public void setShiftSwapRequestSender(List<ShiftSwapRequest> shiftSwapRequestList1) {

        this.shiftSwapRequestListSender = shiftSwapRequestList1;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
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

    @XmlTransient
    public List<TimeOffRequest> getTimeOffRequestList() {
        return timeOffRequestList;
    }

    public void setTimeOffRequestList(List<TimeOffRequest> timeOffRequestList) {
        this.timeOffRequestList = timeOffRequestList;
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
