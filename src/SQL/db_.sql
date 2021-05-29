drop database OLFUQC;
create database OLFUQC;
use OLFUQC;
create table ADMIN(ID int primary key auto_increment not null, email varchar(50) not null, fname varchar(15) not null, lname varchar(15) not null, contact varchar(20), password varchar(20) not null);

insert into ADMIN(email,fname,lname,contact,password)
values
("jsparagas1@admin.fatima.edu.ph","jerbee","paragas","09991612445","helloworld1"),
("jlmagtalas1@admin.fatima.edu.ph","johnnel","magtalas","09324534423","helloworld2"),
("mrlorenzo1@admin.fatima.edu.ph"," mark bryan","lorenzo","09775862321","helloworld3"),
("arlisiguez@admin.fatima.edu.ph","alyessa","lisiguez","09442321335","helloworld4"),
("assibayan@admin.fatima.edu.ph","alberlee","sibayan","09302359953","helloworld5");

create table APP_OPTIONS(id int primary key auto_increment, keyword varchar(30) not null, val varchar(100) not null);

create table STUDENTS(id int primary key auto_increment,fname varchar(50) not null, mname varchar(50) not null,
lname varchar(50) not null,mobile_number varchar(11) not null, email varchar(50) not null, dateofbirth date not null,
gender varchar(20) not null, student_type varchar(20) not null, address varchar(60) not null,
department varchar(20) not null, year_level int not null, program varchar(100) not null,
isActive boolean, added_by varchar(50) not null, date_added date not null, section varchar(10) not null
);


insert into APP_OPTIONS(keyword,val)values("gender","Male"),
("gender","Female"),
("program","Bachelor Of Science In Information Technology"),
("program","Bachelor Of Science In Computer Science"),
("program","Bachelor Of Science In Nursing"),
("program","Bachelor Of Science In Psychology"),
("program","Bachelor Of Science In Accountancy"),
("student_type","Regular"),
("student_type","Irregular"),
("filter","id"),
("filter","fname"),
("filter","mname"),
("filter","lname"),
("filter","email"),
("filter","department"),
("filter","gender"),
("filter","year_level"),
("filter","program"),
("department","College"),
("department","Senior High"),
("section","1A1"),
("section","1A2"),
("section","1A3"),
("section","1A4"),
("year","1"),
("year","2"),
("year","3"),
("year","4");




alter table STUDENTS alter isActive set default 1;

insert into STUDENTS(fname, mname, lname, mobile_number, email, dateofbirth, gender, student_type, address, department, year_level, program, added_by, date_added, section)
values
("jerbee","sayre","paragas","09991612445","jsparagas1@student.fatima.edu.ph","2000-11-11","Male","Regular","Caloocan","College",2,"Bachelor Of Science In Information Technology","jerbee","2021-5-25","1A2"),
("rogelio","rabutin","lorenzo","09231402428","rrlorenzo@student.fatima.edu.ph","1992-9-21","Male","Regular","Caloocan","College",4,"Bachelor Of Science In Accountancy","jerbee","2017-5-22","1A1"),
("erron","geovir","ronato","0932647744","egronato@student.fatima.edu.ph","2001-3-12","Male","Regular","Quezon City","College",1,"Bachelor Of Science In Computer Science","johneel","2020-6-27","1A3"),
("Anna","cruz","Dela Punta","09241124225","rrlorenzo@student.fatima.edu.ph","2000-8-15","Female","Regular","Manila","College",3,"Bachelor Of Science In Computer Science","johnnel","2019-1-4","1A1"),
("jam","Mandi","Pidlaon","09231402428","jmpidlaon@student.fatima.edu.ph","1999-3-12","Female","Regular","Quezon City","College",4,"Bachelor Of Science In Nursing","alyessa","2018-5-13","1A4"),
("Jan","Isaiah","Alcid","0955376434","jialcid@student.fatima.edu.ph","2000-2-3","Male","Regular","Caloocan","College",2,"Bachelor Of Science In Accountancy","alberlee","2021-5-25","1A1"),
("Uzias","uriel","corcilles","09445983210","uucorcilles@student.fatima.edu.ph","2000-12-2","Male","Regular","Bulacan","College",2,"Bachelor Of Science In Information Technolgy","jerbee","2018-4-21","1A2"),
("Ralph","Albert","bolisay","09223142356","rabolisay@student.fatima.edu.ph","1999-4-20","Male","Regular","Quezon City","College",3,"Bachelor Of Science In Information Technology","mark bryan","2019-4-1","1A2"),
("Alliah","Crisanto","Eugenio","09123423572","aceugenio@student.fatima.edu.ph","2000-5-10","Female","Regular","Quezon City","College",2,"Bachelor Of Science In Computer Science","alyessa","2018-3-28","1A1"),
("Sabrina","Sentillecis","Acevedo","09778985323","ssacevedo@student.fatima.edu.ph","2000-5-10","Female","Regular","Caloocan","College",2,"Bachelor Of Science In Psychology","johnnel","2018-3-28","1A3"),
("Rolando","Niji","Suson","09442231239","rnsuson@student.fatima.edu.ph","1999-2-28","Male","Regular","Bulacan","College",3,"Bachelor Of Science In Accountancy","alberlee","2016-5-27","1A4"),
("ailyn","marites","bitoonan","09345532678","ambitoonan@student.fatima.edu.ph","1999-8-31","Female","Regular","Bulacan","College",2,"Bachelor Of Science In Psychology","mark bryan","2017-5-22","1A1");