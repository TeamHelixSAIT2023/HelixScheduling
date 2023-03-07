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
@Table(name = "unavailable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unavailable.findAll", query = "SELECT u FROM Unavailable u")
    , @NamedQuery(name = "Unavailable.findByUnavailableID", query = "SELECT u FROM Unavailable u WHERE u.unavailableID = :unavailableID")
    , @NamedQuery(name = "Unavailable.findByDate", query = "SELECT u FROM Unavailable u WHERE u.date = :date")})
public class Unavailable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "unavailableID")
    private Integer unavailableID;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "organizationUser", referencedColumnName = "organizationUserID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private OrganizationUser organizationUser;

    public Unavailable() {
    }

    public Unavailable(Integer unavailableID) {
        this.unavailableID = unavailableID;
    }

    public Unavailable(Integer unavailableID, Date date) {
        this.unavailableID = unavailableID;
        this.date = date;
    }

    public Integer getUnavailableID() {
        return unavailableID;
    }

    public void setUnavailableID(Integer unavailableID) {
        this.unavailableID = unavailableID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        hash += (unavailableID != null ? unavailableID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unavailable)) {
            return false;
        }
        Unavailable other = (Unavailable) object;
        if ((this.unavailableID == null && other.unavailableID != null) || (this.unavailableID != null && !this.unavailableID.equals(other.unavailableID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Unavailable[ unavailableID=" + unavailableID + " ]";
    }
    
}
