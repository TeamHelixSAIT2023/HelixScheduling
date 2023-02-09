package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Department;
import model.OrganizationUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-02-09T13:29:37")
@StaticMetamodel(Organization.class)
public class Organization_ { 

    public static volatile SingularAttribute<Organization, Integer> organizationID;
    public static volatile SingularAttribute<Organization, Boolean> public1;
    public static volatile ListAttribute<Organization, OrganizationUser> organizationUserList;
    public static volatile SingularAttribute<Organization, String> name;
    public static volatile SingularAttribute<Organization, String> description;
    public static volatile ListAttribute<Organization, Department> departmentList;
    public static volatile SingularAttribute<Organization, Integer> shiftSwapBoardID;

}