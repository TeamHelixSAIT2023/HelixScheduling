insert into user(email,firstName,lastName,password,phone,active,public)
        values(null,'david','fletcher','DCBA','1234890',0,0),
        (1,'john','Mcconaughey','DCBA','6436576876',0,1),
        ('','Henry','Cavil','Dcba','7896784588',1,1),
        ('fdfdsafdsafsdafsadasdfadsfdsafdsafas@gmail.com','Henry','Cavil','Dcba','7896784588',1,1);

insert into organization (name,description,public,shiftSwapBoardID)
        values ('Google','532',1,null),
        ('Microsoft',null,1,1),
        ('Facebook',null,1,0),
        ('Tesla','0',0,4);

insert into department (deptNo,organizationID,title,description)
        values(1205,2,'console department',''),
        (1102,1,'UI department',null),
        (1100,3,'Messenger department','1'),
        (3423,4,'Welding department','215456');

insert into organizationUser (organizationID,userID,deptID,scheduleID,managedBy,hourly,availability)
        values (1,2,3,345,null,56.98,'dcs'),
        (2,4,1,312,null,78.01,''),
        (3,4,1,213,null,23.77,-84),
        (4,2,2,564,null,98.90,58900000);

insert into availability(organizationUserID,dayOfWeek,startTime,endTime)
        values (9,2,'00:30','20:45'),
        (10,1,'am','17:45'),
        (11,4,'','19:30'),
        (12,3,null,'15:30');