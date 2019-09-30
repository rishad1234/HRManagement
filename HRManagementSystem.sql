
create table departments(
	department_id int identity(1, 1) primary key,
	department_name varchar(50) not null,
	department_head varchar(50) not null,
)

create table expenses(
	expense_id int identity(1, 1) primary key,
	department_id int NOT NULL FOREIGN KEY REFERENCES departments (department_id),
	created_at date not null,
	amount_of_expense decimal(18,2) not null,
	expense_title varchar(210) not null,
	expense_description varchar(1000) not null,
)

create table incomes(
	income_id int identity(1, 1) primary key,
	department_id int NOT NULL FOREIGN KEY REFERENCES departments (department_id),
	client_name varchar(50),
	project_name varchar(50) not null,
	project_description varchar(1000) not null,
	finished_date date not null,
	income_amount decimal(18,2),
)

create table payrolls(
	payroll_id int identity(1, 1) primary key,
	salary decimal(18, 2) not null,
	increment decimal(18, 2) not null
)

create table employees(
	employee_id int identity(1, 1) primary key,
	department_id int NOT NULL FOREIGN KEY REFERENCES departments (department_id),
	payroll_id int NOT NULL FOREIGN KEY REFERENCES payrolls (payroll_id),
	first_name varchar(255),
	last_name varchar(255),
	phone varchar(255) unique,
	email varchar(255) unique,
	password varchar(255),
	gender varchar(2),
	joining_date date,
	date_of_birth date,
	retirement_date date,
	designation varchar(255)
)


create table holiday(
	employee_id int NOT NULL FOREIGN KEY REFERENCES employees (employee_id),
	holiday_date_taken date not null,
)


use HRManagementSystem

select * from employees
select * from incomes
select * from departments
select * from payrolls

Insert into departments values
('Marketing', 'Kelly Brook')


INSERT INTO employees(department_id, payroll_id, first_name, last_name, phone, email, password, gender, joining_date, date_of_birth, designation) VALUES
(8, 6, ' Boris', 'Wickliffe', '01716523594', 'boriswickliffe@gmail.com', HashBytes('MD5', 'admin'), 'M', '2016-02-12', '1998-07-08', 'Administrator')


INSERT INTO employees(department_id, payroll_id, first_name, last_name, phone, email, password, gender, joining_date, date_of_birth, designation) VALUES
(1, 6, 'Rishad', 'Khan', '01716523592', 'rishad@gmail.com', HashBytes('MD5', 'rishad'), 'M', '2016-02-12', '1998-07-08', 'HR Specialist'),
(2, 5, 'Arafat', 'Uddin', '01526985634', 'arafat@gmail.com', HashBytes('MD5', 'arafat'), 'M', '2018-05-19', '1997-11-01', 'District Account Manager'),
(4, 4, 'Rafat', 'Haque', '01915632587', 'rafat@gmail.com', HashBytes('MD5', 'rafat'), 'M', '2015-01-01', '1995-12-01','Senior Developer'),
(4, 3, 'Faraz', 'Khan', '01716352469', 'faraz@gmail.com', HashBytes('MD5', 'faraz'), 'M', '2019-05-19', '1998-04-12', 'Junior Developer'),
(5, 2, 'Tonmoy', 'Mirza', '01715469636', 'tonmoy@gmail.com', HashBytes('MD5', 'tonmoy'), 'M', '2018-01-11', '1998-07-08', 'UX Engineer'),
(6, 1, 'Tashreef', 'Dragneel', '01712829879', 'tashreef@gmail.com', HashBytes('MD5', 'tashreef'), 'M', '2017-06-03', '1997-02-05', 'Junior Product Designer'),
(7, 2, 'Tashfiq', 'Tasin', '01521489863', 'tashfiq@gmail.com', HashBytes('MD5', 'tashfiq'), 'M', '2015-04-01', '1995-07-07', 'QA Specialist'),
(8, 3, 'Hasanath', 'Jamy', '01823698548', 'hasanath@gmail.com', HashBytes('MD5', 'hasanath'), 'M', '2019-08-01', '1996-03-09', 'Administator'),
(7, 4, 'Shahriar', 'Moin', '01521429409', 'shahriar@gmail.com', HashBytes('MD5', 'shahriar'), 'M', '2017-04-12', '1998-04-06', 'Junior QA Engineer'),
(6, 5, 'Shafayet', 'Islam', '01716249682', 'shafayet@gmail.com', HashBytes('MD5', 'shafayet'), 'M', '2017-05-19', '1998-10-08', 'Senior Product Designer'),
(1, 5, 'Mujahidul', 'Islam', '01915365872', 'mujahidul@gmail.com', HashBytes('MD5', 'mujahidul'), 'M', '2013-04-01', '1991-01-12', 'Recruiting Manager'),
(1, 4, 'Rezone', 'Khan', '01712698563', 'rezone@gmail.com', HashBytes('MD5', 'rezone'), 'M', '2016-08-22', '1992-12-01', 'HR Director'),
(4, 3, 'Dhrubojit', 'Shaha', '01917856325', 'dhrubojit@gmail.com', HashBytes('MD5', 'dhrubojit'), 'M', '2017-04-11', '1998-04-12', 'Android Developer'),
(5, 2, 'Rakib', 'Khan', '01678965412', 'rakib@gmail.com', HashBytes('MD5', 'rakib'), 'M', '2015-01-11', '1998-07-20', 'UI/UX Developer'),
(7, 4, 'Dihan', 'Tonmoy', '01619632579', 'dihan@gmail.com', HashBytes('MD5', 'dihan'), 'M', '2012-01-01', '1990-10-06', 'Administrator'),
(6, 5, 'Dibbo', 'Nath', '01715326978', 'dibbo@gmail.com', HashBytes('MD5', 'dibbo'), 'M', '2019-05-01', '1998-10-08', 'Animation Designer'),
(1, 1, 'Rashid', 'Khan', '01715632544', 'rashid@gmail.com', HashBytes('MD5', 'radhid'), 'M', '2012-01-01', '1990-10-06', 'Recruiter'),
(2, 1, 'Jeni', 'Bess', '01619632578', 'jeni@gmail.com', HashBytes('MD5', 'jeni'), 'M', '2011-01-01', '1990-10-06', 'Area Sales Manager'),
(5, 2, 'Nam', 'Comacho', '01521896879', 'nam@gmail.com', HashBytes('MD5', 'nam'), 'M', '2013-01-01', '1990-10-06', 'UX Developer'),
(4, 3, 'Kelly', 'Vanscyoc', '01715632548', 'kelly@gmail.com', HashBytes('MD5', 'kelly'), 'F', '2012-01-01', '1990-10-06', 'iOS Developer'),
(5, 4, 'Margert', 'Rieke', '0185695823', 'margert@gmail.com', HashBytes('MD5', 'margert'), 'M', '2014-01-01', '1990-10-06', 'UI Designer'),
(6, 5, 'Kent', 'Kroh', '01523698745', 'kent@gmail.com', HashBytes('MD5', 'kent'), 'M', '2015-01-01', '1990-10-06', 'Senior UI/UX Designer'),
(7, 6, 'Alan', 'Baker', '0189632745', 'alan@gmail.com', HashBytes('MD5', 'alan'), 'M', '2012-01-01', '1990-10-06', 'Junior QA Engineer'),
(8, 6, 'Boris', 'Wickliffe', '0125896325', 'boris@gmail.com', HashBytes('MD5', 'boris'), 'M', '2012-01-01', '1990-10-06', 'Administrator'),
(2, 4, 'Arnita', 'Rahman', '01857454525', 'arnita@gmail.com', HashBytes('MD5', 'arnita'), 'F', '2016-04-12', '1998-07-11', 'Direct Sales Manager'),
(4, 5, 'Syeda', 'Sultana', '01552757584', 'syeda@gmail.com', HashBytes('MD5', 'syeda'), 'F', '2015-02-24', '1997-07-13', 'Dev OPs'),
(2, 6, 'Nishat', 'Haque', '01754212136', 'nishat@gmail.com', HashBytes('MD5', 'nishat'), 'F', '2015-01-01', '1999-06-03', 'Inside Sales Manager'),
(1, 2, 'Maria', 'Noor', '01658454578', 'maria@gmail.com', HashBytes('MD5', 'maria'), 'F', '2017-06-11', '1998-05-17', 'Recruiter'),
(4, 5, 'Umama', 'Zaman', '01845787821', 'umama@gmail.com', HashBytes('MD5', 'umama'), 'F', '2016-04-10', '1999-03-27', 'Cloud Architect Engineer'),
(4, 5, 'Ayesha', 'Siddique', '01554896235', 'ayesha@gmail.com', HashBytes('MD5', 'ayesha'), 'F', '2017-03-21', '1998-04-15', 'Senior Web Developer'),
(2, 4, 'Rubaba', 'Doula', '01774212636', 'rubaba@gmail.com', HashBytes('MD5', 'rubaba'), 'F', '2016-03-11', '1998-07-05', 'Market Development Manager'),
(1, 3, 'Zarin', 'Tasnim', '0178451525', 'zarin@gmail.com', HashBytes('MD5', 'zarin'), 'F', '2017-04-21', '1999-05-14', 'Senior Recruiter'),
(4, 2, 'Sinthia', 'Zaman', '01458987852', 'sinthia@gmail.com', HashBytes('MD5', 'sinthia'), 'F', '2016-11-15', '1998-04-12', 'Backend Specialist')



-- expense table

insert into expenses(department_id,created_at,amount_of_expense,expense_title,expense_description) values
(1,'2017-04-21',25000.00,'Furniture','Bought 1500 Office Furniture'),
(4,'2016-08-22',95000.00,'Laptops','For Developers Team'),
(6,'2017-03-12',195000.00,'Desktop','iMac for Designers'),
(5,'2016-08-23',5000.00,'White Boards','For UX purpose, bought 6 white boards'),
(5,'2017-07-21',3000.00,'Wireframe Pro','Software wireframe pro bought for UX developers'),
(4,'2017-08-21',1500.00,'Internet Connection','Mainly for developeers team, used by whole office'),
(4,'2017-04-11',35000.00,'iPhone','For iOS Developement testing Purpose'),
(1,'2016-02-18',3000.00,'Sofa','For outsider guest to wait'),
(4,'2016-02-18',5000.00,'Router','For internet connection purpose'),
(1,'2016-01-02',23000.00,'Office Rent','Office Rent')



-- income table
insert into incomes(department_id,client_name,project_name,project_description,finished_date,income_amount)values
(4,'Bangladesh Brand Forum','Commward','Award Giving Web Platform', '2016-02-12',35000.00),
(4,'Matra','Matra','Official Website for Matra', '2017-05-02',38000.00),
(5,'Prime Bank','Live More with Prime Bank','UX Developed of live more with prime bank website', '2016-05-01',48000.00),
(6,'Bangladesh Brand Forum','BBF TV','Designed UI of BBF TV', '2018-05-17',52000.00),
(4,'Selise','CMS','Content Management Software', '2017-05-27',62000.00),
(4,'JoomShaper','Wordpress Plugin','plugin developed for wordpress', '2017-02-17',16000.00),
(4,'IDLC','AML','AML Website designed ofr IDLC', '2017-02-22',36000.00),
(4,'IPDC','IPDC Official','IPDC Official Website', '2016-03-12',78000.00),
(5,'SKA Arch.','Offical SKA','Website UI Designed for SKA', '2017-05-11',22000.00),
(5,'Uniliver','Chatbot','Chatbot Management System', '2015-05-18',44000.00)