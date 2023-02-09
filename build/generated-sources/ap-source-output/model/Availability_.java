package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.OrganizationUser;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-02-09T13:29:37")
@StaticMetamodel(Availability.class)
public class Availability_ { 

    public static volatile SingularAttribute<Availability, Short> dayOfWeek;
    public static volatile SingularAttribute<Availability, OrganizationUser> organizationUserID;
    public static volatile SingularAttribute<Availability, Integer> availabilityID;
    public static volatile SingularAttribute<Availability, Date> startTime;
    public static volatile SingularAttribute<Availability, Date> endTime;

}