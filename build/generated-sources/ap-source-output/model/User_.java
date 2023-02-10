package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.OrganizationUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-02-09T13:29:37")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Boolean> public1;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> password;
    public static volatile ListAttribute<User, OrganizationUser> organizationUserList;
    public static volatile SingularAttribute<User, String> salt;
    public static volatile SingularAttribute<User, String> phone;
    public static volatile SingularAttribute<User, Boolean> active;
    public static volatile SingularAttribute<User, Integer> userID;
    public static volatile SingularAttribute<User, String> email;

}