/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.OrganizationUserDB;
import dataaccess.AvailabilityDB;
import dataaccess.UserDB;
import java.util.List;
import model.OrganizationUser;
import model.Availability;
/**
 *
 * @author Eric
 */
public class OrganizationUserService {
    
    public OrganizationUser get(int organizationUserID){
        OrganizationUserDB uoDB = new OrganizationUserDB();
        OrganizationUser uo = uoDB.get(organizationUserID);
        return uo;
    }
    
    public OrganizationUser getByUserIDOrgID (int userID, int orgID){
        OrganizationUserDB uoDB = new OrganizationUserDB();
        OrganizationUser uo = uoDB.getByUserIDOrgID(userID, orgID);
        return uo;
    }
}
