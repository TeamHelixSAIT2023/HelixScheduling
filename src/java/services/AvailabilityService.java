/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.AvailabilityDB;
import dataaccess.OrganizationUserDB;
import java.util.Date;
import java.util.List;
import model.Availability;
import model.OrganizationUser;
/**
 *
 * @author Eric
 */
public class AvailabilityService {
    
    public Availability get(int availabilityID) throws Exception {
        AvailabilityDB availabilityDB = new AvailabilityDB();
        Availability availability = availabilityDB.get(availabilityID);
        return availability;
    }
    
    public List<Availability> getByOrgUser (OrganizationUser ou) {
        AvailabilityDB availabilityDB = new AvailabilityDB();
        List<Availability> availabilityList = availabilityDB.getByOrgUser(ou.getOrganization(), ou.getUser());
        return availabilityList;
    }
    
    public void insert (OrganizationUser ou, String dayOfWeek, Date start, Date end) {
        AvailabilityDB aDB = new AvailabilityDB();
        Availability a = new Availability();
        
        a.setOrganizationUser(ou);
        a.setDayOfWeek(dayOfWeek);
        a.setStartTime(start);
        a.setEndTime(end);
        
        aDB.insert(a);
    }
    
    public void update (OrganizationUser ou, List<Availability> updateList) {
        AvailabilityDB aDB = new AvailabilityDB();
        List<Availability> oldList = ou.getAvailabilityList();
        
        for (int i = 0; i < oldList.size(); i++){
            oldList.get(i).setStartTime(updateList.get(i).getStartTime());
            oldList.get(i).setEndTime(updateList.get(i).getEndTime());
            aDB.update(oldList.get(i));
        }
    }
    
    public void delete (int availabilityID) {
        AvailabilityDB aDB = new AvailabilityDB();
        Availability availability = aDB.get(availabilityID);
        aDB.delete(availability);
    }
}
