/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.AvailabilityDB;
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
}
