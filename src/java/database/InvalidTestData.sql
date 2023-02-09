/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Eric
 * Created: 8-Feb-2023
 */


insert into user(email,firstName,lastName,password,phone,active,public) 
    values('david','david','fletcher','DCBA', 'fdsaf','1234890',0,0),
    (null,'david','fletcher','DCBA','1234891', 'fdsaf',0,0),
    ('','david','fletcher','DCBA','1234892','fdsaf',0,0),
    ('fdsafsfdafassadafsafdsafasddsafasfds@gmail.com','david','fletcher','DCBA','1234891','fdsaf',0,0),
    ('john@gmail.com','john','Mcconaughey','DCBA','6436576876', 'fdsaf',0,1),
    ('henry@gmail.com','Henry','Cavil','Dcba','7896784588','fdsaf',1,1);

insert into organization (name,description,public,shiftSwapBoardID) 
    values ('Google','Internet Search Engine',1,null),
    ('Microsoft','Operating System Provider',1,1),
    ('Facebook','Social Media Platform',1,0),
    ('Tesla','Automobile Manufacturer',0,4);

insert into department (deptNo,organizationID,title,description) 
    values(1205,2,'console department','Terminal managers'),
    (1102,1,'UI department','User interface management'),
    (1100,3,'Messenger department','Message security team'),
    (3423,4,'Welding department','Weld checkings');

insert into organizationUser (organizationID,userID,deptID,scheduleID,managedBy,hourly,availability) 
    values (1,2,3,345,null,56.98,null),
    (2,4,1,312,null,78.01,12),
    (3,4,1,213,null,23.77,10),
    (4,2,2,564,null,98.90,8);

insert into availability(organizationUserID,dayOfWeek,startTime,endTime)
    values (9,2,'08:30','20:45'),
    (10,1,'10:15','17:45'),
    (11,4,'12:00','19:30'),
    (12,3,'11:45','15:30');