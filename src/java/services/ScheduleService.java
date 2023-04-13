/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.OrganizationUserScheduleDB;
import dataaccess.ScheduleDB;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import model.Department;
import model.Organization;
import model.OrganizationUser;
import model.OrganizationUserSchedule;
import model.Schedule;
import model.Shift;
import util.SortShiftByDate;

/**
 *
 * @author Eric
 */
public class ScheduleService {

    public Schedule get(int scheduleID) {
        ScheduleDB sDB = new ScheduleDB();
        Schedule schedule = sDB.get(scheduleID);
        for (OrganizationUserSchedule ous : schedule.getOrganizationUserScheduleList()) {
            Collections.sort(ous.getShiftList(), new SortShiftByDate());
        }
        return schedule;
    }

    public List<Schedule> getByOrg(Organization org) {
        ScheduleDB sDB = new ScheduleDB();
        List<Schedule> scheduleList = sDB.getByOrg(org);
        return scheduleList;
    }

    public List<OrganizationUserSchedule> getByOrgUser(OrganizationUser ou) {
        OrganizationUserScheduleDB ousDB = new OrganizationUserScheduleDB();
        List<OrganizationUserSchedule> ousList = ousDB.getByOrgUser(ou);
        for (OrganizationUserSchedule ous : ousList) {
            Collections.sort(ous.getShiftList(), new SortShiftByDate());
        }
        return ousList;
    }

    public void insert(Organization org, Department dept, Date startDate, Date endDate) {
        ScheduleDB sDB = new ScheduleDB();
        Schedule schedule = new Schedule();
        schedule.setOrganization(org);
        schedule.setDept(dept);
        schedule.setStartDate(startDate);
        schedule.setEndDate(endDate);
        sDB.insert(schedule);
    }

    public void update(int scheduleID, Department dept, Date startDate, Date endDate) {
        ScheduleDB sDB = new ScheduleDB();
        Schedule schedule = sDB.get(scheduleID);
        schedule.setDept(dept);
        schedule.setStartDate(startDate);
        schedule.setEndDate(endDate);
        sDB.insert(schedule);
    }

    public void addShift(int scheduleID, OrganizationUser orgUser, Date startDate, Date endDate, String shiftType) {
        ScheduleDB sDB = new ScheduleDB();
        Schedule schedule = sDB.get(scheduleID);

        ShiftService ss = new ShiftService();
        
        boolean userOnSchedule = false;

        for (OrganizationUserSchedule ous : schedule.getOrganizationUserScheduleList()) {
            if (ous.getOrganizationUser().equals(orgUser)) {
                userOnSchedule = true;
                ss.insert(ous, startDate, endDate, shiftType);
            }
        }
        if (!userOnSchedule){
            OrganizationUserScheduleDB ousDB = new OrganizationUserScheduleDB();
            OrganizationUserSchedule ous = new OrganizationUserSchedule();
            ous.setOrganizationUser(orgUser);
            ous.setSchedule(schedule);
            ousDB.insert(ous);
            
            ss.insert(ous, startDate, endDate, shiftType);
        }
    }
    
    public void removeShift (int scheduleID, int shiftID){
        ScheduleDB sDB = new ScheduleDB();
        Schedule schedule = sDB.get(scheduleID);
        
        ShiftService ss = new ShiftService();
        ss.delete(shiftID);
    }

}
