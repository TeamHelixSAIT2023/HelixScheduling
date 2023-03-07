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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eric
 */
@Entity
@Table(name = "shiftswapboard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShiftSwapBoard.findAll", query = "SELECT s FROM ShiftSwapBoard s")
    , @NamedQuery(name = "ShiftSwapBoard.findByShiftSwapBoardID", query = "SELECT s FROM ShiftSwapBoard s WHERE s.shiftSwapBoardID = :shiftSwapBoardID")})
public class ShiftSwapBoard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "shiftSwapBoardID")
    private Integer shiftSwapBoardID;
    @JoinColumn(name = "dept", referencedColumnName = "deptID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Department dept;
    @JoinColumn(name = "organization", referencedColumnName = "organizationID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Organization organization;
    @JoinColumn(name = "shift", referencedColumnName = "shiftID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Shift shift;

    public ShiftSwapBoard() {
    }

    public ShiftSwapBoard(Integer shiftSwapBoardID) {
        this.shiftSwapBoardID = shiftSwapBoardID;
    }

    public Integer getShiftSwapBoardID() {
        return shiftSwapBoardID;
    }

    public void setShiftSwapBoardID(Integer shiftSwapBoardID) {
        this.shiftSwapBoardID = shiftSwapBoardID;
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

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shiftSwapBoardID != null ? shiftSwapBoardID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShiftSwapBoard)) {
            return false;
        }
        ShiftSwapBoard other = (ShiftSwapBoard) object;
        if ((this.shiftSwapBoardID == null && other.shiftSwapBoardID != null) || (this.shiftSwapBoardID != null && !this.shiftSwapBoardID.equals(other.shiftSwapBoardID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ShiftSwapBoard[ shiftSwapBoardID=" + shiftSwapBoardID + " ]";
    }
    
}
