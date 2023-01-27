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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eric
 */
@Entity
@Table(name = "organization")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organization.findAll", query = "SELECT o FROM Organization o")
    , @NamedQuery(name = "Organization.findByOrganizationID", query = "SELECT o FROM Organization o WHERE o.organizationID = :organizationID")
    , @NamedQuery(name = "Organization.findByName", query = "SELECT o FROM Organization o WHERE o.name = :name")
    , @NamedQuery(name = "Organization.findByDescription", query = "SELECT o FROM Organization o WHERE o.description = :description")
    , @NamedQuery(name = "Organization.findByPublic1", query = "SELECT o FROM Organization o WHERE o.public1 = :public1")})
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "organizationID")
    private Integer organizationID;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "public")
    private boolean public1;

    public Organization() {
    }

    public Organization(Integer organizationID) {
        this.organizationID = organizationID;
    }

    public Organization(Integer organizationID, String name, String description, boolean public1) {
        this.organizationID = organizationID;
        this.name = name;
        this.description = description;
        this.public1 = public1;
    }

    public Integer getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(Integer organizationID) {
        this.organizationID = organizationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getPublic1() {
        return public1;
    }

    public void setPublic1(boolean public1) {
        this.public1 = public1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (organizationID != null ? organizationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organization)) {
            return false;
        }
        Organization other = (Organization) object;
        if ((this.organizationID == null && other.organizationID != null) || (this.organizationID != null && !this.organizationID.equals(other.organizationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Organization[ organizationID=" + organizationID + " ]";
    }
    
}
