# Task-Manager
--------------------------------------------------------------------------------------------

#Create Some Specific User "springrest" from "root" user as mentioned in create_user.sql file/ or follow given below queries
CREATE USER 'springrest'@'localhost' IDENTIFIED BY 'springrest';

GRANT ALL PRIVILEGES ON * . * TO 'springrest'@'localhost';

-----------------------------------------------------------------------------------------------
#Create database and tables as per the queries provided in task_manager_app.sql file/ or follow given below queries

CREATE DATABASE  IF NOT EXISTS `task_manager_app` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `task_manager_app`;

--
-- Table structure for table `label`
--

SET FOREIGN_KEY_CHECKS= 0;
DROP TABLE IF EXISTS `label`;

CREATE TABLE `label` (
`id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(40) DEFAULT NULL,
  `description` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



--
-- Table structure for table `task_manager`
--



DROP TABLE IF EXISTS `task_manager`;

CREATE TABLE `task_manager` (
  `taskId` int(11) NOT NULL AUTO_INCREMENT,
  `dateTime` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `labelId` int(11) DEFAULT NULL,
  PRIMARY KEY (`taskId`),
 KEY `FK_DETAIL_idx` (`labelId`),
   CONSTRAINT  `FK_DETAIL` FOREIGN KEY (`labelId`) REFERENCES `label` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS= 1;

------------------------------------------------------------------------------------------------------------------
#Version details
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: task_manager_app
-- ------------------------------------------------------
-- Server version	5.6.16

------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
#There are 2 entities task_manager & label on which the CRUD operation performed.
1) Path for GET all the Tasks from database.
http://localhost:8080/spring-task-manager-rest/api/tasks
This will get all the Task and corresponding cascaded label as well
------------------------------------------------------------------------------------------------------------------- 
2) Path for GET particular Tasks from database.
http://localhost:8080/spring-task-manager-rest/api/tasks/1
This will get the Task and cascaded label as well
-------------------------------------------------------------------------------------------------------------------
3) Path to POST the Task to database.
http://localhost:8080/spring-task-manager-rest/api/tasks
This will post the Task and cascaded label as well

sample JSON input in the body=====>>>>>>
{
        "dateTime": "2019-04-25 10:25:20",
        "priority": "Critical",
        "labelId": {
            "category": "Personal",
            "description": "Gym"
        }
    }
 
------------------------------------------------------------------------------------------------------------------------
4)Path to GET all priority tasks(critical & High priority) from database.
http://localhost:8080/spring-task-manager-rest/api/tasks/priorityTasks
 
 -----------------------------------------------------------------------------------------------------------------------
5)Path to GET next 2 days tasks, here we have to pass tomorrows date as start & 2 days as size. 
This is the pagination like we mention starting point and size of records we need from dB.
Send date in format of “YYYY-MM-DD” and need not to send time as given below
http://localhost:8080/spring-task-manager-rest/api/tasks?start=2019-04-24&size=2
 
--------------------------------------------------------------------------------------------------------------------------
6) Path to DELETE particular task of unique task id
http://localhost:8080/spring-task-manager-rest/api/tasks/1
This will delete the Task and cascaded label as well.
 
--------------------------------------------------------------------------------------------------------------------------
7) Path to PUT(update) already existing task.
http://localhost:8080/spring-task-manager-rest/api/tasks/2
Please pass the body in below mentioned JSON having both task Id and label Id with other fields as well.
{
    "id": 2,
    "dateTime": "2019-04-22 10:00:20",
    "priority": "Critical",
    "labelId": {
        "id": 5,
        "category": "Personal",
        "description": "Gym Workout"
    }
}
 
--------------------------------------------------------------------------------------------------------------------------
Consider only 2 Categories as PERSONAL & OFFICIAL for convenience so all tasks can have either one of these as category.
Description can be anything.

For example Morning GYM, Friday Evening Movie, Shopping, Doctors appointment etc come under PERSONAL category.
Presentation of project, CLient visit, Weekend Production Release etc comes under OFFICIAL
===========================================================================================================================
