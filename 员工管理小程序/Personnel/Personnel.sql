create database Person
on( Name=Person_data
,Filename='D:\SQL\Person_data.mdf'
)
log on (Name=Person_log
,filename='D:\SQL\Person_log.ldf'
)

use Person

create table usertable(
user_id int primary key identity(1,1),
user_name varchar(30),
user_password varchar(30),
user_dept varchar(30),
user_position varchar(30),
user_salary int
)

insert into usertable(user_name,user_password,user_dept,user_position,user_salary) values('smy','12345','dept','pos',100)
insert into usertable(user_name,user_password,user_dept,user_position,user_salary) values('cz','12345','dept','pos',100)
insert into usertable(user_name,user_password,user_dept,user_position,user_salary) values('xzl','12345','dept','pos',100)
