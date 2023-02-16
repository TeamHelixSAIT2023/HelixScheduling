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
    values('david','david','fletcher','password','fdsaf','1234890',true,false),--invalid mail
    ('john@gmail.com','12345','Mcconaughey','password','fdsaf','643657687',true,true),--invalid first name
    ('henry@gmail.com','Henry','Cavil123123','password','fdsaf','789678458',true,true),--invalid lastname
    ('eric.mclaughlin42@gmail.com', 'Eric', 'McLaughlin', 12312431234, 'fdsa', '403852636', true, true),--invalid password because 11 characters int type
    ('Mike','12314','Kenedy','asdfg12',True,'1231413423',true,false),--invalid salt boolean type
    ('Jack@hotmail.com','Jack','Sky','asdfg12',True,'123141abdd',1,false),-- boolean doesnot do invalid insert
    ('Tom@12gmail.com','Tom','Scott','asdfg12',True,'123141ab',false,0);--same here boolean doesnot do invalid insert

insert into `organization` (`name`,`description`,`public`,`shiftSwapBoardID`)
    values (1231243,'Internet Search Engine',1,null),--invalid name insert with int type
    ('Microsoft',True,1,1),--invalid boolean insert in varchar type
    ('Facebook','Social Media Platform',True,0),--boolean doesnot do invalid insert
    ('Tesla','Automobile Manufacturer',0,True);--invalid boolean insert at Int type
    --(True,'Boolean',false,'5'); --invalid insert

insert into `department` (`deptNo`,`organizationID`,`title`,`description`)
    values(-1205,1,'console department','Terminal managers'),--invalid negative number
    (1102,4,'UI department','User interface management'),--invalid organizationID failed due to foreign key
    (1100,1,234823432423423424234,'Message security team'),--invalid integer insert in title
    (3423,1,'Welding department',true);--invalid boolean insert in description

insert into `organizationUser` (`organizationID`,`userID`,`deptID`,`scheduleID`,`managedBy`,`hourly`,`availability`)
    values (1,2,3,0,null,56.98456,null),--invalid insert for hourly floating points with more than 2 digits
    (2,3,1,456,null,78.01,-11),--invalid insert of availability with negative points
    (3,3,1,234,null,-23.77,10),--hourly also does negative points
    (4,2,2,564,True,98.90,8);--managed by does boolean values, rest of the columns are foreign key so cant be changed

insert into `availability`(`organizationUserID`,`dayOfWeek`,`startTime`,`endTime`)
    values (1,1,08,'20:45'),--invalid time insert at start time
    (1,2,'10:15',17),--invalid time inseart at endtime
    (1,-0,'12:00','19:30'),--invalid inseart of day of week as zero is ill defined
    (1,4,'11:45','15:30');--organizationuserid is foreign key so cant be changed
