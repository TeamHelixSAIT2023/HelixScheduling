package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Organization;
import model.OrganizationUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-02-09T13:29:37")
@StaticMetamodel(Department.class)
public class Department_ { 

    public static volatile SingularAttribute<Department, Organization> organizationID;
    public static volatile ListAttribute<Department, OrganizationUser> organizationUserList;
    public static volatile SingularAttribute<Department, Integer> deptID;
    public static volatile SingularAttribute<Department, String> description;
    public static volatile SingularAttribute<Department, String> title;
    public static volatile SingularAttribute<Department, Integer> deptNo;

}