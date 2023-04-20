/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.AvailabilityChangeRequestDB;
import dataaccess.OrganizationRequestDB;
import dataaccess.OrganizationUserRequestDB;
import dataaccess.ShiftSwapRequestDB;
import dataaccess.TimeOffRequestDB;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.Availability;
import model.AvailabilityChangeRequest;
import model.Organization;
import model.OrganizationRequest;
import model.OrganizationUser;
import model.OrganizationUserRequest;
import model.Shift;
import model.ShiftSwapRequest;
import model.TimeOffRequest;
import model.User;

/**
 *
 * @author Eric
 */
public class RequestService {

    //Organization User Requests
    public OrganizationUserRequest getOrgUserRequest(int orgUserRequestID) {
        OrganizationUserRequestDB orgUserRequestDB = new OrganizationUserRequestDB();
        OrganizationUserRequest orgUserRequest = orgUserRequestDB.get(orgUserRequestID);
        return orgUserRequest;
    }

    public List<OrganizationUserRequest> getOrgUserRequestBySender(OrganizationUser sender) {
        OrganizationUserRequestDB orgUserRequestDB = new OrganizationUserRequestDB();
        List<OrganizationUserRequest> orgUserRequestList = orgUserRequestDB.getBySender(sender);
        return orgUserRequestList;
    }

    public List<OrganizationUserRequest> getOrgUserRequestByReceiver(OrganizationUser receiver) {
        OrganizationUserRequestDB orgUserRequestDB = new OrganizationUserRequestDB();
        List<OrganizationUserRequest> orgUserRequestList = orgUserRequestDB.getByReceiver(receiver);
        return orgUserRequestList;
    }

    public void insertOrgUserRequest(OrganizationUser sender, OrganizationUser receiver, String type) {
        OrganizationUserRequestDB orgUserRequestDB = new OrganizationUserRequestDB();
        OrganizationUserRequest orgUserRequest = new OrganizationUserRequest();
        orgUserRequest.setSender(sender);
        orgUserRequest.setReceiver(receiver);
        orgUserRequest.setType(type);
        orgUserRequest.setApproved(false);
        orgUserRequestDB.insert(orgUserRequest);

        NotificationService ns = new NotificationService();
        String title = sender.getUser().getFirstName() + " " + sender.getUser().getLastName();
        String description;

    }

    //Organization Requests
    public OrganizationRequest getOrgRequest(int orgRequestID) {
        OrganizationRequestDB orgRequestDB = new OrganizationRequestDB();
        OrganizationRequest orgRequest = orgRequestDB.get(orgRequestID);
        return orgRequest;
    }

    public List<OrganizationRequest> getOrgRequestByOrg(Organization org) {
        OrganizationRequestDB orgRequestDB = new OrganizationRequestDB();
        List<OrganizationRequest> orgRequestList = orgRequestDB.getByOrg(org);
        return orgRequestList;
    }

    public List<OrganizationRequest> getOrgRequestByUser(User user) {
        OrganizationRequestDB orgRequestDB = new OrganizationRequestDB();
        List<OrganizationRequest> orgRequestList = orgRequestDB.getByUser(user);
        return orgRequestList;
    }

    public void insertOrgRequest(User user, Organization org, String type) {
        OrganizationRequestDB orgRequestDB = new OrganizationRequestDB();
        OrganizationRequest orgRequest = new OrganizationRequest();
        orgRequest.setUser(user);
        orgRequest.setOrganization(org);
        orgRequest.setType(type);
        orgRequest.setApproved(false);
        orgRequestDB.insert(orgRequest);
        
        NotificationService ns = new NotificationService();
        String title = "", description;
        if (type.equals("org-to-user")) {
            title = org.getName() + " requested you to join their organization";
            ns.insert(user, orgRequest.getOrganizationRequestID(), "organizationRequest", title, "");
        } else if (type.equals("user-to-org")) {
            title = user.getFirstName() + " " + user.getLastName() + " has requested to join " + org.getName();
            for (OrganizationUser ou : org.getOrganizationUserList()){
                if (ou.getAdmin()){
                    ns.insert(ou.getUser(), orgRequest.getOrganizationRequestID(), "organizationRequest", title, "");
                }
            }
        }
    }

    public void updateOrganizationRequest(int requestID, boolean approved) {
        OrganizationRequestDB orgRequestDB = new OrganizationRequestDB();
        OrganizationRequest orgRequest = getOrgRequest(requestID);
        orgRequest.setApproved(approved);
        orgRequestDB.update(orgRequest);

        if (approved) {
            OrganizationUserService ous = new OrganizationUserService();
            ous.insert(orgRequest.getOrganization(), orgRequest.getUser(), null, null, 0, false, false);
        }

        NotificationService ns = new NotificationService();
        String title;
        if (orgRequest.getType().equals("org-to-user")) {
            for (OrganizationUser ou : orgRequest.getOrganization().getOrganizationUserList()) {
                if (ou.getAdmin()) {
                    if (approved) {
                        title = orgRequest.getUser().getFirstName() + " " + orgRequest.getUser().getLastName()
                                + " has accepted your invitation to " + orgRequest.getOrganization().getName();
                    } else {
                        title = orgRequest.getUser().getFirstName() + " " + orgRequest.getUser().getLastName()
                                + " has declined your invitation to " + orgRequest.getOrganization().getName();
                    }
                    ns.insert(ou.getUser(), orgRequest.getOrganizationRequestID(), "organization", title, "");
                }
            }
        } else if (orgRequest.getType().equals("user-to-org")) {
            if (approved) {
                title = orgRequest.getOrganization().getName() + " has accepted your request to join their organization";
            } else {
                title = orgRequest.getOrganization().getName() + " has declined your request to join their organization";
            }
            ns.insert(orgRequest.getUser(), orgRequest.getOrganizationRequestID(), "organization", title, "");
        }

        orgRequestDB.delete(orgRequest);
    }

    //Availability Change Requests
    public AvailabilityChangeRequest getAvailabilityChangeRequest(int acRequestID) {
        AvailabilityChangeRequestDB acDB = new AvailabilityChangeRequestDB();
        AvailabilityChangeRequest acRequest = acDB.get(acRequestID);
        return acRequest;
    }

    public List<AvailabilityChangeRequest> getAvailabilityChangeRequestBySender(OrganizationUser orgUser) {
        AvailabilityChangeRequestDB acDB = new AvailabilityChangeRequestDB();
        List<AvailabilityChangeRequest> acRequestList = acDB.getBySender(orgUser);
        return acRequestList;
    }

    public List<AvailabilityChangeRequest> getAvailabilityChangeRequestByReceiver(OrganizationUser orgUser) {
        AvailabilityChangeRequestDB acDB = new AvailabilityChangeRequestDB();
        List<AvailabilityChangeRequest> acRequestList = acDB.getByReceiver(orgUser);
        return acRequestList;
    }

    public void insertAvailabilityChangeRequest(OrganizationUser sender,
            Availability sunday, Availability monday, Availability tuesday, Availability wednesday,
            Availability thursday, Availability friday, Availability saturday) {
        AvailabilityChangeRequestDB acDB = new AvailabilityChangeRequestDB();
        AvailabilityService as = new AvailabilityService();

        as.insert(sender, "Sunday", sunday.getStartTime(), sunday.getEndTime());
        as.insert(sender, "Monday", monday.getStartTime(), monday.getEndTime());
        as.insert(sender, "Tuesday", tuesday.getStartTime(), tuesday.getEndTime());
        as.insert(sender, "Wednesday", wednesday.getStartTime(), wednesday.getEndTime());
        as.insert(sender, "Thursday", thursday.getStartTime(), thursday.getEndTime());
        as.insert(sender, "Friday", friday.getStartTime(), friday.getEndTime());
        as.insert(sender, "Saturday", saturday.getStartTime(), saturday.getEndTime());

        AvailabilityChangeRequest acRequest = new AvailabilityChangeRequest();
        //set reciever to be the orgUsers manager, if one is not set make the receiver the organizations owner
        OrganizationUser receiver = null;
        if (sender.getManagedBy() != null) {
            receiver = sender.getManagedBy();
        } else {
            for (OrganizationUser ou : sender.getOrganization().getOrganizationUserList()) {
                if (ou.getOwner()) {
                    receiver = ou;
                }
            }
        }
        acRequest.setSender(sender);
        acRequest.setReceiver(receiver);
        acRequest.setSunday(sunday);
        acRequest.setMonday(monday);
        acRequest.setTuesday(tuesday);
        acRequest.setWednesday(wednesday);
        acRequest.setThursday(thursday);
        acRequest.setFriday(friday);
        acRequest.setSaturday(saturday);
        acRequest.setApproved(false);

        acDB.insert(acRequest);

        NotificationService ns = new NotificationService();
        String title = sender.getUser().getFirstName() + " " + sender.getUser().getLastName() + " has request a change to their availability";
        ns.insert(receiver.getUser(), acRequest.getAvailabilityChangeRequestID(), "availabilityChange", title, "");
    }

    public void updateAvailabilityChangeRequest(int requestID, boolean approved) {
        AvailabilityChangeRequestDB acDB = new AvailabilityChangeRequestDB();
        AvailabilityChangeRequest acRequest = acDB.get(requestID);
        acRequest.setApproved(approved);

        acDB.update(acRequest);

        NotificationService ns = new NotificationService();
        User receiver = acRequest.getReceiver().getUser();
        String title;
        if (approved) {
            title = receiver.getFirstName() + " " + receiver.getLastName() + " has approved your availability change request";
        } else {
            title = receiver.getFirstName() + " " + receiver.getLastName() + " has denied your availability change request";
        }
        ns.insert(acRequest.getSender().getUser(), acRequest.getAvailabilityChangeRequestID(), "availabilityChange", title, "");

        acDB.delete(acRequest);
        AvailabilityService as = new AvailabilityService();
        as.delete(acRequest.getSunday().getAvailabilityID());
        as.delete(acRequest.getMonday().getAvailabilityID());
        as.delete(acRequest.getTuesday().getAvailabilityID());
        as.delete(acRequest.getWednesday().getAvailabilityID());
        as.delete(acRequest.getThursday().getAvailabilityID());
        as.delete(acRequest.getFriday().getAvailabilityID());
        as.delete(acRequest.getSaturday().getAvailabilityID());
    }

    //Shift Swap Requests
    public ShiftSwapRequest getShiftSwapRequest(int ssRequestID) {
        ShiftSwapRequestDB ssDB = new ShiftSwapRequestDB();
        ShiftSwapRequest ssRequest = ssDB.get(ssRequestID);
        return ssRequest;
    }

    public List<ShiftSwapRequest> getShiftSwapRequestBySender(OrganizationUser orgUser) {
        ShiftSwapRequestDB acDB = new ShiftSwapRequestDB();
        List<ShiftSwapRequest> ssRequestList = acDB.getBySender(orgUser);
        return ssRequestList;
    }

    public List<ShiftSwapRequest> getShiftSwapRequestReceiver(OrganizationUser orgUser) {
        ShiftSwapRequestDB ssDB = new ShiftSwapRequestDB();
        List<ShiftSwapRequest> ssRequestList = ssDB.getByReceiver(orgUser);
        return ssRequestList;
    }

    public void insertShiftSwapRequest(OrganizationUser sender, OrganizationUser receiver, Shift shift) {
        ShiftSwapRequestDB ssDB = new ShiftSwapRequestDB();
        ShiftSwapRequest ssRequest = new ShiftSwapRequest();
        ssRequest.setSender(sender);
        ssRequest.setReceiver(receiver);
        ssRequest.setShift(shift);
        ssRequest.setApproved(false);

        ssDB.insert(ssRequest);

        NotificationService ns = new NotificationService();
        User s = sender.getUser();
        String title = s.getFirstName() + " " + s.getLastName() + " has requested a shift swap";
        ns.insert(receiver.getUser(), ssRequest.getShiftSwapRequestID(), "shiftSwap", title, "");
    }

    public void updateShiftSwapRequest(int requestID, boolean approved) {
        ShiftSwapRequestDB ssDB = new ShiftSwapRequestDB();
        ShiftSwapRequest ssRequest = ssDB.get(requestID);
        ssRequest.setApproved(approved);

        if (approved) {
            //to do
        }
    }

    //Time off requests
    public TimeOffRequest getTimeOffRequest(int ssRequestID) {
        TimeOffRequestDB toDB = new TimeOffRequestDB();
        TimeOffRequest toRequest = toDB.get(ssRequestID);
        return toRequest;
    }

    public List<TimeOffRequest> getTimeOffRequestByOrgUser(OrganizationUser orgUser) {
        TimeOffRequestDB toDB = new TimeOffRequestDB();
        List<TimeOffRequest> toRequestList = toDB.getByOrgUser(orgUser);
        return toRequestList;
    }

    public void insertTimeOffRequest(OrganizationUser orgUser, Date startDate, Date endDate) {
        TimeOffRequestDB toDB = new TimeOffRequestDB();
        TimeOffRequest toRequest = new TimeOffRequest();
        toRequest.setOrganizationUser(orgUser);
        toRequest.setStartDate(startDate);
        toRequest.setEndDate(endDate);
        toRequest.setApproved(false);
        toDB.insert(toRequest);

        NotificationService ns = new NotificationService();
        User receiver = null;
        if (orgUser.getManagedBy() != null) {
            receiver = orgUser.getManagedBy().getUser();
        } else {
            for (OrganizationUser ou : orgUser.getOrganization().getOrganizationUserList()) {
                if (ou.getOwner()) {
                    receiver = ou.getUser();
                }
            }
        }

        String title = orgUser.getUser().getFirstName() + " " + orgUser.getUser().getLastName() + " has requested time off";
        ns.insert(receiver, toRequest.getTimeOffRequestID(), "timeOff", title, "");
    }

    public void updateTimeOffRequest(int requestID, boolean approved) {
        TimeOffRequestDB toDB = new TimeOffRequestDB();
        TimeOffRequest toRequest = toDB.get(requestID);
        toRequest.setApproved(approved);
        OrganizationUser orgUser = toRequest.getOrganizationUser();
        String title;

        User receiver = null;
        if (orgUser.getManagedBy() != null) {
            receiver = orgUser.getManagedBy().getUser();
        } else {
            for (OrganizationUser ou : orgUser.getOrganization().getOrganizationUserList()) {
                if (ou.getOwner()) {
                    receiver = ou.getUser();
                }
            }
        }

        if (approved) {
            UnavailableService us = new UnavailableService();
            long daysBetween = Duration.between(toRequest.getStartDate().toInstant(), toRequest.getEndDate().toInstant()).toDays();
            Date curr = toRequest.getStartDate();
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < daysBetween; i++) {
                us.insert(orgUser, curr, toRequest.getReason());
                cal.setTime(curr);
                cal.add(Calendar.DATE, 1);
                curr = cal.getTime();
            }

            title = receiver.getFirstName() + " " + receiver.getLastName() + " has approved your time off requeest";
        } else {
            title = receiver.getFirstName() + " " + receiver.getLastName() + " has denied your time off requeest";
        }
        
        NotificationService ns = new NotificationService();
        ns.insert(orgUser.getUser(), toRequest.getTimeOffRequestID(), "timeOff", title, "");
        
        toDB.delete(toRequest);
    }
}
