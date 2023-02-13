/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Barsha
 * Created: 8-Feb-2023
 */
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE `availability`;
TRUNCATE `organizationUser`;
TRUNCATE `department`;
TRUNCATE `organization`;
TRUNCATE `user`;
SET FOREIGN_KEY_CHECKS = 1;

insert into `user` (`email`,`firstName`,`lastName`,`password`,`salt`,`phone`,`active`,`public`)
    values('david@gmail.com','david','fletcher','password','fdsaf','1234890',true,false),
    ('john@gmail.com','john','Mcconaughey','password','fdsaf','6436576876',true,true),
    ('henry@gmail.com','Henry','Cavil','password','fdsaf','7896784588',true,true),
    ('eric.mclaughlin42@gmail.com', 'Eric', 'McLaughlin', 'password', 'fdsa', '4038526364', true, true);

insert into `organization` (`name`,`description`,`public`,`shiftSwapBoardID`)
    values ('Google','Internet Search Engine',1,null),
    ('Microsoft','Operating System Provider',1,1),
    ('Facebook','Social Media Platform',1,0),
    ('Tesla','Automobile Manufacturer',0,4);

insert into `department` (`deptNo`,`organizationID`,`title`,`description`)
    values(1205,1,'console department','Terminal managers'),
    (1102,1,'UI department','User interface management'),
    (1100,1,'Messenger department','Message security team'),
    (3423,1,'Welding department','Weld checkings');

insert into `organizationUser` (`organizationID`,`userID`,`deptID`,`scheduleID`,`managedBy`,`hourly`,`availability`)
    values (1,2,3,345,null,56.98,null),
    (2,3,1,312,null,78.01,12),
    (3,3,1,213,null,23.77,10),
    (4,2,2,564,null,98.90,8);

insert into `availability`(`organizationUserID`,`dayOfWeek`,`startTime`,`endTime`)
    values (1,1,'08:30','20:45'),
    (1,2,'10:15','17:45'),
    (1,3,'12:00','19:30'),
    (1,4,'11:45','15:30');
