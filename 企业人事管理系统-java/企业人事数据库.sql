--创建数据库
use master 

create database EnterprisePersonnel
on( Name=EnterprisePersonnel1
,Filename='E:\上机实验\SQL实验\EnterprisePersonneldata.mdf'
)
log on (Name=EnterprisePersonnel2
,filename='E:\上机实验\SQL实验\EnterprisePersonnellog.ldf'
)
alter database EnterprisePersonnel
add file(name=EnterprisePersonnel3
,filename='E:\上机实验\SQL实验\EnterprisePersonneldata2.mdf'
)
--创建数据库

--创建表
use EnterprisePersonnel
create table Dept--部门信息
(DeptNo varchar(10) not null unique,
DeptName varchar (20) not null unique,
ManagerNo varchar(6)not null
)

create table Position--职务信息
(PositionNo varchar(10) not null unique,
PositionName varchar(20) not null unique,
Base dec(6,1)not null check(Base>=2000 and Base<=50000)
)

create table Person--职工信息
(No varchar(6) not null primary key,
Name varchar(10) not null,
Sex char(2) not null default '未知'check(Sex='男'or Sex='女'or Sex='未知'),
Phone varchar(12) not null,
Birthday datetime null default '2000-01-01',
Education char(10)not null default '未知',
Entrytime datetime null default '2017-01-01',
DeptNo varchar(10) not null,
PositionNo varchar(10) not null foreign key(PositionNo) references Position(PositionNo)on update cascade
)

create table Attendance--考勤管理
(No varchar(6) not null foreign key(No) references person(No)ON DELETE CASCADE,
Year int not null,
Month int not null check(Month between 1 and 12),
Workdays int not null check(Workdays between 0 and 31),
Latedays int not null check(Latedays between 0 and 31),
Overtime int not null check(Overtime between 0 and 31),
Bonus dec(6,1) not null default 0,
Deduct dec(6,1) not null default 0
primary key(No,Year,Month)
)
create table CheckIn--考勤情况
(No varchar(6) not null foreign key(No) references Person(No)ON Delete cascade,
Year int not null,
Month int not null check(Month between 1 and 12),
Day int not null default 0 check(Day between 0 and 31),
Night int not null default 0 check(Night between 0 and 31),
primary key(No,year,month,day,Night)
)

create table Pay--工资情况
(No varchar(6) not null foreign key(No) references Person(No)ON DELETE CASCADE,
Year int not null,
Month int not null,
Base dec(6,1),--check(Base between 2000 and 50000)
Bonus dec(6,1) default 0,
Deduct dec(6,1)default 0,
Fact as (Base+Bonus-Deduct)
unique(no,year,month)
)

create table Users--用户
(No varchar(6) not null unique foreign key(no) references Person(No)ON DELETE CASCADE,
UserName varchar(10)not null unique,
PassWord varchar(16) not null
)

create table WorkTime
(hour int not null check(hour between 0 and 24),
minute int not null check(minute between 0 and 60)
)

create table PersonChange
(No char(6),
Name varchar(10) not null,
Event varchar(100) not null,
Time datetime not null,
DeptNo varchar(10) not null,
PositionNo varchar(10) not null foreign key(PositionNo) references Position(PositionNo)on update cascade
)


alter table dept
add foreign key (ManagerNo)
references Person(No)
--创建表

--创建触发器
use EnterprisePersonnel

create trigger pay_attendance_deduct_bonus
on attendance
after update
as
if @@ROWCOUNT=0
return
update pay
set pay.Deduct=i.Deduct,pay.Bonus=i.Bonus
from pay,deleted d,inserted i
where pay.no=d.No and pay.year=i.year and pay.month=i.month



create trigger pay_position_base
on position
after update
as
if @@ROWCOUNT=0
return
update pay
set pay.Base=i.Base
from pay,inserted i
where pay.No in(select No from person
			where person.PositionNo=i.PositionNo)


create trigger checkin_attendance_day
on checkin
after insert
as
if @@ROWCOUNT=0
return
declare @no char(6)
declare @year int
declare @month int
declare @day int
declare @night int
select @no=no,@year=year,@month=month,@day=day,@night=night from inserted
if(@night=0)
begin
if (exists(select no,year,month from Attendance where no=@no and year=@year and month=@month))
update Attendance set Workdays=Workdays+1 where Attendance.No=@no and Attendance.Year=@year and Attendance.Month=@month
else
insert into Attendance values(@no,@year,@month,1,0,0,0,0)
end
if (@day=0)
begin 
if (exists(select no,year,month from Attendance where no=@no and year=@year and month=@month))
update Attendance set Overtime=Overtime+1,Bonus=Bonus+100 where Attendance.No=@no and Attendance.Year=@year and Attendance.Month=@month
else
insert into Attendance values(@no,@year,@month,0,0,1,100,0)
end



create trigger attendance_pay_insert
on attendance
after insert
as
if @@ROWCOUNT=0
return
declare @no char(6)
declare @year int
declare @month int
declare @base decimal(6, 1)
declare @bonus decimal(6, 1)
declare @deduct decimal(6, 1)
select @no=no,@year=year,@month=month,@bonus=bonus,@deduct=deduct from inserted
select @base=base from Position where PositionNo in(select PositionNo from person where Person.No=@no)
insert into pay values(@no,@year,@month,@base,@bonus,@deduct)


create trigger person_delete
on person
after delete
as
if @@ROWCOUNT=0
return
declare @temp char(6)
select @temp=no from deleted
delete from users
where users.No=@temp


create trigger person_insert
on person
after insert
as
if @@ROWCOUNT=0
return
declare @no char(6)
declare @name char(10)
declare @year int
declare @month int
declare @time datetime
declare @deptno varchar(10)
declare @positionno varchar(10)
declare @base decimal(6, 1)
select @time=entrytime,@name=name,@positionno=Positionno,@deptno=DeptNo,@no=no,@year= Datename(year,entrytime),@month=Datename(month,entrytime)from inserted
select @base=Base from Position where @positionno=PositionNo
insert into attendance(No,year,Month,Workdays,Latedays,Overtime,Bonus,Deduct)
values(@no,@year,@month,0,0,0,0,0)
insert into PersonChange(No,Name,Event,Time,DeptNo,PositionNo)
values(@No,@name,'员工入职',@time,@deptno,@positionno)


--创建触发器
drop trigger pay_attendance_deduct_bonus

SELECT * FROM Sysobjects WHERE xtype = 'TR'--显示所有触发器

--创建服务器角色
use EnterprisePersonnel
create role worker
grant insert on checkin to worker
grant update,insert on attendance to worker
grant select on attendance to worker
grant select on checkin to worker
grant select on dept to worker
grant select on pay to worker
grant select on person to worker
grant select on personchange to worker
grant select on position to worker
grant select on users to worker
grant select on worktime to worker
grant select on pay to worker




use EnterprisePersonnel
drop user ccc
drop login ccc
--创建服务器角色



--插入数据

insert into Position(PositionNo,PositionName,Base)
values('00201','高级',30000)
insert into Position(PositionNo,PositionName,Base)
values('00202','中级',20000)
insert into Position(PositionNo,PositionName,Base)
values('00203','初级',10000)

insert into person(No,Name,Sex,Phone,Birthday,Education,Entrytime,DeptNo,PositionNo)
values('000001','陈川','男','159951113','1998-02-14','本科','2017-12-01','00101','00201')
insert into person(No,Name,Sex,Phone,Birthday,Education,Entrytime,DeptNo,PositionNo)
values('000002','陈接杰','男','159961113','1998-02-14','本科','2017-12-01','00102','00201')
insert into person(No,Name,Sex,Phone,Birthday,Education,Entrytime,DeptNo,PositionNo)
values('000003','丁中宇','男','159963513','1998-02-14','本科','2017-12-01','00102','00202')
insert into person(No,Name,Sex,Phone,Birthday,Education,Entrytime,DeptNo,PositionNo)
values('000004','陈国','男','159951113','1998-02-14','本科','2017-12-01','00102','00203')

insert into dept(DeptNo,DeptName,ManagerNo)
values('00101','人事部','000001')
insert into dept(DeptNo,DeptName,ManagerNo)
values('00102','财务部','000002')
insert into dept(DeptNo,DeptName,ManagerNo)
values('00103','市场部','000003')


insert into WorkTime(hour,minute)
values(8,30)
insert into Users(No,UserName,PassWord)
values('000001','chuanchuan','257173')




--创建表


--查询语句
use EnterprisePersonnel select Dept.*,person.Name,person.Phone from Dept,person where dept.managerno=person.No

use EnterprisePersonnel select * from person

use EnterprisePersonnel select * from Position

use EnterprisePersonnel select deptno,DeptName from Dept
use EnterprisePersonnel select PositionNo,PositionName from Position

use EnterprisePersonnel select Person.Name,Pay.* from pay,Person where pay.No=Person.No



update attendance
set Bonus=30000
where No='000001'

update attendance
set Deduct=300
where No='000001'

update Position
set Base=2500
where PositionNo='00202'

insert into CheckIn(No,Year,Month,Day,Night)
values('000002',2017,10,28,0)

use EnterprisePersonnel insert into dept values('00105','ccc','000005')

use EnterprisePersonnel  select no from Person where no='000001'

delete from person where no='000001'



select Name from Person where no='000001'

use EnterprisePersonnel update Attendance set Bonus=Bonus+10 where No='000001'

use EnterprisePersonnel select name,attendance.* from person,attendance where person.no=attendance.no

use EnterprisePersonnel update Attendance set Workdays=Workdays+1 where No='000001'and year=2017 and month=12

use EnterprisePersonnel select person.* from person where DeptNo='00101'
use EnterprisePersonnel select deptno from Dept where Deptname='人事部'
use EnterprisePersonnel select positionNo from position where positionname='高级'

insert into Attendance
values('000001',2017,11,1,0,0,0)

use EnterprisePersonnel  select no from Person where no='000001'
use EnterprisePersonnel  select DeptNo from dept where DeptNo='00101'
use EnterprisePersonnel  select deptname from dept where DeptName='市场部'

use EnterprisePersonnel  select DeptName from Dept
use EnterprisePersonnel select PositionName from Position

use EnterprisePersonnel select DeptName from Dept
use EnterprisePersonnel select dept.* from Dept where DeptNo='00101'

use EnterprisePersonnel update dept set DeptName='141123',ManagerNo='000002' where DeptNo='00101'
use EnterprisePersonnel delete from Dept where DeptNo='00103'

use EnterprisePersonnel select person.* from Person where person.No='000001'

use EnterprisePersonnel update person set person.Name='cll',person.Sex='女',person.Phone='15996351114',
person.Birthday='1999-2-6',person.Education='博士',person.DeptNo='00102',person.PositionNo='00202' where person.No='000001'

use EnterprisePersonnel delete from person where person.No='000003'

use EnterprisePersonnel select * from WorkTime

use EnterprisePersonnel update Attendance set Latedays=latedays+1,Deduct=Deduct+50 where Attendance.No='000001' and Attendance.Year='2017' and Attendance.Month='12'

use EnterprisePersonnel update WorkTime set hour='10',minute='20'

use EnterprisePersonnel select* from worktime

use EnterprisePersonnel select* from PersonChange

use EnterprisePersonnel insert into PersonChange
values('000001','陈川川','员工离职','2017-12-26','00101','00201')

use EnterprisePersonnel insert into PersonChange values('000002','陈川川','员工离职','2017-12-26','00101','00201')

use EnterprisePersonnel insert into PersonChange values('000002','陈杰      ','员工离职','2017-12-26','00102','00202')

use EnterprisePersonnel select* from Users where Users.UserName='chanchuan' and Users.PassWord='257173'
use EnterprisePersonnel select* from Users where Users.UserName='chuanchuan' and Users.PassWord='257173'

use EnterprisePersonnel select no from person where no='000001'

use EnterprisePersonnel select no from Users where no='000001'

use EnterprisePersonnel select UserName from Users where UserName='chuanchuan'

use EnterprisePersonnel insert into Users values('000001','ccc','222')

create login ll with password='111',
check_expiration=on,default_database=EnterprisePersonnel

use EnterprisePersonnel create user ll



insert into CheckIn values('000001',2017,12,28,0)
insert into Attendance values('000001',2017,12,0,0,0,0,0)



EXEC sp_revokedbaccess 'll'

exec sp_droprole 'worker'

exec sp_addrolemember 'db_datareader','ll'

drop role worker
use EnterprisePersonnel select deptno from person where No='000001'
use EnterprisePersonnel select positionNo from person where No='000001'

insert into Users(No,UserName,PassWord) values('000003','cc','11')

create login cccc with password='000',default_database=EnterprisePersonnel use EnterprisePersonnel create user cccc exec sp_addrolemember 'worker','cccc'
use EnterprisePersonnel insert into Users(No,UserName,PassWord) values('000004','cccc','000')

use EnterprisePersonnel drop user cccc drop login cccc

use EnterprisePersonnel select* from Users where Users.No='000001'

use EnterprisePersonnel drop user dy         drop login dy   

create login dy         with password='222',default_database=EnterprisePersonnel use EnterprisePersonnel create user dy 

use EnterprisePersonnel
create role manager authorization db_owner

create login cc with password='111222',default_database=EnterprisePersonnel
use EnterprisePersonnel create user cc
exec sp_addrolemember 'db_owner','cc'
create login ccc with password='111',default_database=EnterprisePersonneluse EnterprisePersonnel create user cccexec sp_addrolemember 'db_owner','ccc'
create login ccc with password='222',default_database=EnterprisePersonnel use EnterprisePersonnel create user ccc exec sp_addrolemember 'db_owner','ccc'
create login ccc with password='111',default_database=EnterprisePersonnel use EnterprisePersonnel create user ccc exec sp_addrolemember 'db_owner','ccc'
use EnterprisePersonnel insert into Users(No,UserName,PassWord) values('000004','czg','111')



use EnterprisePersonnel drop user dy drop login dy
create login dy with password='111',default_database=EnterprisePersonnel use EnterprisePersonnel create user dy exec sp_addrolemember 'db_owner','dy'
create login dy with password='111',default_database=EnterprisePersonnel use EnterprisePersonnel create user dy exec sp_addrolemember 'worker','111'
