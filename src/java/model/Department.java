/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Eric
 */
@Entity
@Table(name = "department")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d")
    , @NamedQuery(name = "Department.findByDeptID", query = "SELECT d FROM Department d WHERE d.deptID = :deptID")
    , @NamedQuery(name = "Department.findByDeptNo", query = "SELECT d FROM Department d WHERE d.deptNo = :deptNo")
    , @NamedQuery(name = "Department.findByTitle", query = "SELECT d FROM Department d WHERE d.title = :title")
    , @NamedQuery(name = "Department.findByDescription", query = "SELECT d FROM Department d WHERE d.description = :description")})
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "deptID")
    private Integer deptID;
    @Basic(optional = false)
    @Column(name = "deptNo")
    private int deptNo;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "deptID", fetch = FetchType.EAGER)
    private List<OrganizationUser> organizationUserList;
    @JoinColumn(name = "organizationID", referencedColumnName = "organizationID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Organization organizationID;

    public Department() {
    }

    public Department(Integer deptID) {
        this.deptID = deptID;
    }

    public Department(Integer deptID, int deptNo, String title, String description) {
        this.deptID = deptID;
        this.deptNo = deptNo;
        this.title = title;
        this.description = description;
    }

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<OrganizationUser> getOrganizationUserList() {
        return organizationUserList;
    }

    public void setOrganizationUserList(List<OrganizationUser> organizationUserList) {
        this.organizationUserList = organizationUserList;
    }

    public Organization getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(Organization organizationID) {
        this.organizationID = organizationID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deptID != null ? deptID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.deptID == null && other.deptID != null) || (this.deptID != null && !this.deptID.equals(other.deptID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Department[ deptID=" + deptID + " ]";
    }
    
}
