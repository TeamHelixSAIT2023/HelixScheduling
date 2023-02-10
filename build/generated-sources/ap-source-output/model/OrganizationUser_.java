package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Availability;
import model.Department;
import model.Organization;
import model.OrganizationUser;
import model.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-02-09T13:29:37")
@StaticMetamodel(OrganizationUser.class)
public class OrganizationUser_ { 

    public static volatile SingularAttribute<OrganizationUser, Organization> organizationID;
    public static volatile ListAttribute<OrganizationUser, OrganizationUser> organizationUserList;
    public static volatile SingularAttribute<OrganizationUser, Integer> organizationUserID;
    public static volatile SingularAttribute<OrganizationUser, OrganizationUser> managedBy;
    public static volatile SingularAttribute<OrganizationUser, Department> deptID;
    public static volatile SingularAttribute<OrganizationUser, Double> hourly;
    public static volatile SingularAttribute<OrganizationUser, Integer> availability;
    public static volatile SingularAttribute<OrganizationUser, User> userID;
    public static volatile ListAttribute<OrganizationUser, Availability> availabilityList;
    public static volatile SingularAttribute<OrganizationUser, Integer> scheduleID;

}