/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UnavailableDB;
import java.util.Date;
import java.util.List;
import model.OrganizationUser;
import model.Unavailable;

/**
 *
 * @author Eric
 */
public class UnavailableService {

    public Unavailable get(int unavailableID) throws Exception {
        UnavailableDB unavailableDB = new UnavailableDB();
        Unavailable unavailable = unavailableDB.get(unavailableID);
        return unavailable;
    }

    public List<Unavailable> getByOrgUser(OrganizationUser ou) {
        UnavailableDB unavailableDB = new UnavailableDB();
        List<Unavailable> unavailableList = unavailableDB.getByOrgUser(ou.getOrganization(), ou.getUser());
        return unavailableList;
    }

    public void insert(OrganizationUser ou, Date date, String reason) {
        UnavailableDB unavailableDB = new UnavailableDB();
        Unavailable unavailable = new Unavailable();
        unavailable.setOrganizationUser(ou);
        unavailable.setDate(date);
        unavailable.setReason(reason);

        unavailableDB.insert(ou, unavailable);
    }

    public void update(OrganizationUser ou, int unavailableID, Date date, String reason) throws Exception {
        UnavailableDB unavailableDB = new UnavailableDB();

        Unavailable unavailable = get(unavailableID);
        unavailable.setDate(date);
        unavailable.setReason(reason);
        unavailableDB.update(unavailable);
    }
}
