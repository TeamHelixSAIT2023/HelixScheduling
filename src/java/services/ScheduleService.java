/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.OrganizationUserScheduleDB;
import dataaccess.ScheduleDB;
import java.util.List;
import model.Organization;
import model.OrganizationUser;
import model.OrganizationUserSchedule;
import model.Schedule;

/**
 *
 * @author Eric
 */
public class ScheduleService {
    public Schedule get(int scheduleID){
        ScheduleDB sDB = new ScheduleDB();
        Schedule schedule = sDB.get(scheduleID);
        return schedule;
    }
    
    public List<Schedule> getByOrg (Organization org){
        ScheduleDB sDB = new ScheduleDB();
        List<Schedule> scheduleList = sDB.getByOrg(org);
        return scheduleList;
    }
    
    public List<OrganizationUserSchedule> getByOrgUser (OrganizationUser ou) {
        OrganizationUserScheduleDB ousDB = new OrganizationUserScheduleDB();
        List<OrganizationUserSchedule> ousList = ousDB.getByOrgUser(ou);
        return ousList;
    }
    
}
