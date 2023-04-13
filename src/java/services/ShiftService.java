/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.OrganizationUserDB;
import dataaccess.OrganizationUserScheduleDB;
import dataaccess.ShiftDB;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.OrganizationUser;
import model.OrganizationUserSchedule;
import model.Schedule;
import model.Shift;

/**
 *
 * @author Eric
 */
public class ShiftService {
    public Shift get(int shiftID){
        ShiftDB sDB = new ShiftDB();
        Shift shift = sDB.get(shiftID);
        return shift;
    }
    
    public List<Shift> getShiftsByOrgUser (OrganizationUser ou) {
        ShiftDB sDB = new ShiftDB();
        List<Shift> shiftList = new ArrayList<Shift>();
        for (OrganizationUserSchedule ous : ou.getOrganizationUserScheduleList()){
            shiftList.addAll(ous.getShiftList());
        }
        return shiftList;
    }
    
    public void insert (OrganizationUserSchedule ous, Date startDate, Date endDate, String shiftType){
        ShiftDB sDB = new ShiftDB();
        Shift shift = new Shift();
        shift.setOrganizationUserSchedule(ous);
        shift.setStartDate(startDate);
        shift.setShiftType(shiftType);
        sDB.insert(shift);
    }
    
    public void update (int shiftID, OrganizationUserSchedule ous, Date startDate, Date endDate, String shiftType){
        ShiftDB sDB = new ShiftDB();
        Shift shift = sDB.get(shiftID);
        shift.setOrganizationUserSchedule(ous);
        shift.setStartDate(startDate);
        shift.setEndDate(endDate);
        shift.setShiftType(shiftType);
        sDB.update(shift);
    }
    
    public void shiftSwap (int shiftID, int orgUserID) {
        ShiftDB sDB = new ShiftDB();
        OrganizationUserDB ouDB = new OrganizationUserDB();
        OrganizationUserScheduleDB ousDB = new OrganizationUserScheduleDB();
        
        Shift shift = sDB.get(shiftID);
        Schedule schedule = shift.getOrganizationUserSchedule().getSchedule();
        
        OrganizationUser ou = ouDB.get(orgUserID);
        boolean orgUserScheduleFound = false;
        
        for (OrganizationUserSchedule ous : schedule.getOrganizationUserScheduleList()){
            if (ous.getOrganizationUser().equals(ou)){
                shift.getOrganizationUserSchedule().getShiftList().remove(shift);
                shift.setOrganizationUserSchedule(ous);
                ous.getShiftList().add(shift);
                sDB.update(shift);
                if(ous.getShiftList().isEmpty()){
                    ousDB.delete(ous);
                }
                orgUserScheduleFound = true;
            }
        }
        
        if (!orgUserScheduleFound){
            OrganizationUserSchedule ous = new OrganizationUserSchedule();
            ous.setOrganizationUser(ou);
            ous.setSchedule(schedule);
            ousDB.insert(ous);
        }
    }
}
