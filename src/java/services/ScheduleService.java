/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.OrganizationUserScheduleDB;
import dataaccess.ScheduleDB;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import model.Department;
import model.Organization;
import model.OrganizationUser;
import model.OrganizationUserSchedule;
import model.Schedule;
import model.Shift;
import model.User;
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
            addNullsToOrgUserSchedule(ous);
        }
        return schedule;
    }

    public List<Schedule> getByOrg(Organization org) {
        ScheduleDB sDB = new ScheduleDB();
        List<Schedule> scheduleList = sDB.getByOrg(org);
        for (Schedule s : scheduleList) {
            for (OrganizationUserSchedule ous : s.getOrganizationUserScheduleList()) {
                Collections.sort(ous.getShiftList(), new SortShiftByDate());
                addNullsToOrgUserSchedule(ous);
            }
        }
        return scheduleList;
    }

    public Schedule getByOrgDeptStartDate(Organization org, Department dept, Date startDate) {
        ScheduleDB sDB = new ScheduleDB();
        Schedule schedule = sDB.getByOrgDeptStartDate(org, dept, startDate);
        for (OrganizationUserSchedule ous : schedule.getOrganizationUserScheduleList()) {
            Collections.sort(ous.getShiftList(), new SortShiftByDate());
            addNullsToOrgUserSchedule(ous);
        }
        return schedule;
    }

    public List<Schedule> getByDept(Department dept) {
        ScheduleDB sDB = new ScheduleDB();
        List<Schedule> scheduleList = sDB.getByDept(dept);
        for (Schedule s : scheduleList) {
            for (OrganizationUserSchedule ous : s.getOrganizationUserScheduleList()) {
                Collections.sort(ous.getShiftList(), new SortShiftByDate());
                addNullsToOrgUserSchedule(ous);
            }
        }
        return scheduleList;
    }

    public List<Schedule> getByOrgUser(OrganizationUser ou) {
        OrganizationUserScheduleDB ousDB = new OrganizationUserScheduleDB();
        List<OrganizationUserSchedule> ousList = ousDB.getByOrgUser(ou);
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        for (OrganizationUserSchedule ous : ousList) {
            scheduleList.add(ous.getSchedule());
            Collections.sort(ous.getShiftList(), new SortShiftByDate());
            addNullsToOrgUserSchedule(ous);
        }
        return scheduleList;
    }

    public List<Schedule> getByUser(User user) {
        ScheduleDB sDB = new ScheduleDB();
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        for (OrganizationUser orgUser : user.getOrganizationUserList()) {
            scheduleList.addAll(sDB.getByDept(orgUser.getDept()));
        }
        return scheduleList;
    }

    public Schedule insert(Organization org, Department dept, Date startDate, Date endDate) throws Exception {
        ScheduleDB sDB = new ScheduleDB();
        Schedule schedule = new Schedule();

        try {
            Schedule checkDate = sDB.getByOrgDeptStartDate(org, dept, startDate);
            throw new Exception("Schedule already exists");
        } catch (Exception e) {
            schedule.setOrganization(org);
            schedule.setDept(dept);
            schedule.setStartDate(startDate);
            schedule.setEndDate(endDate);
            sDB.insert(schedule);
        }
        return schedule;
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
        if (!userOnSchedule) {
            OrganizationUserScheduleDB ousDB = new OrganizationUserScheduleDB();
            OrganizationUserSchedule ous = new OrganizationUserSchedule();
            ous.setOrganizationUser(orgUser);
            ous.setSchedule(schedule);
            ousDB.insert(ous);

            ss.insert(ous, startDate, endDate, shiftType);
        }
    }

    public void removeShift(int scheduleID, int shiftID) {
        ScheduleDB sDB = new ScheduleDB();
        Schedule schedule = sDB.get(scheduleID);

        ShiftService ss = new ShiftService();
        ss.delete(shiftID);
    }

    public void delete(int scheduleID) {
        ScheduleDB sDB = new ScheduleDB();
        Schedule schedule = sDB.get(scheduleID);

        OrganizationUserScheduleDB ousDB = new OrganizationUserScheduleDB();

        for (OrganizationUserSchedule ous : schedule.getOrganizationUserScheduleList()) {
            ousDB.delete(ous);
        }

        sDB.delete(schedule);
    }

    public List<Date> getDateList(Schedule schedule) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(schedule.getStartDate());

        List<Date> dateList = new ArrayList<Date>();
        for (int i = 0; i < Duration.between(schedule.getStartDate().toInstant(), schedule.getEndDate().toInstant()).toDays() + 2; i++) {
            dateList.add(cal.getTime());
            cal.add(Calendar.DATE, 1);
        }
        return dateList;
    }

    private void addNullsToOrgUserSchedule(OrganizationUserSchedule ous) {
        String[] dayOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        Calendar cal = Calendar.getInstance();
        Shift shift;
        for (int i = 0; i < 7; i++) {
            try {
                shift = ous.getShiftList().get(i);
                cal.setTime(shift.getStartDate());
                if (cal.get(Calendar.DAY_OF_WEEK) != i + 1) {
                    ous.getShiftList().add(i, null);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                ous.getShiftList().add(null);
            }
        }
    }
}
