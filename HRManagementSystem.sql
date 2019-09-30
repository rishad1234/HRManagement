
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
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	phone varchar(255) not null,
	email varchar(255) not null,
	password varchar(255) not null,
	gender varchar(2),
	joining_date date not null,
	date_of_birth date not null,
	retirement_date date,
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


INSERT INTO employees(department_id, payroll_id, first_name, last_name, phone, email, password, gender, joining_date, date_of_birth, retirement_date, designation) VALUES
(1, 6, 'Rishad', 'Khan', '01716523594', 'rishad@gmail.com', HashBytes('MD5', 'rishad'), 'M', '2016-02-12', '1998-07-08', null),
(2, 5, 'Arafat', 'Uddin', '01526985634', 'arafat@gmail.com', HashBytes('MD5', 'arafat'), 'M', '2018-05-19', '1997-11-01', null),
(3, 4, 'Rafat', 'Haque', '01915632587', 'rafat@gmail.com', HashBytes('MD5', 'rafat'), 'M', '2015-01-01', '1995-12-01', null),
(4, 3, 'Faraz', 'Khan', '01716352469', 'faraz@gmail.com', HashBytes('MD5', 'faraz'), 'M', '2019-05-19', '1998-04-12', null),
(5, 2, 'Tonmoy', 'Mirza', '01715469636', 'tonmoy@gmail.com', HashBytes('MD5', 'tonmoy'), 'M', '2018-01-11', '1998-07-08', null),
(6, 1, 'Tashreef', 'Dragneel', '01712829879', 'tashreef@gmail.com', HashBytes('MD5', 'tashreef'), 'M', '2017-06-03', '1997-02-05', null),
(7, 2, 'Tashfiq', 'Tasin', '01521489863', 'tashfiq@gmail.com', HashBytes('MD5', 'tashfiq'), 'M', '2015-04-01', '1995-07-07', null),
(8, 3, 'Hasanath', 'Jamy', '01823698548', 'hasanath@gmail.com', HashBytes('MD5', 'hasanath'), 'M', '2019-08-01', '1996-03-09', null),
(7, 4, 'Shahriar', 'Moin', '01521429409', 'shahriar@gmail.com', HashBytes('MD5', 'shahriar'), 'M', '2017-04-12', '1998-04-06', null),
(6, 5, 'Shafayet', 'Islam', '01716249682', 'shafayet@gmail.com', HashBytes('MD5', 'shafayet'), 'M', '2017-05-19', '1998-10-08', null),
(1, 6, 'Shah', 'Alam', '01521986325', 'shah@gmail.com', HashBytes('MD5', 'shah'), 'M', '2015-02-12', '1992-07-01', '2019-01-01'),
(1, 5, 'Mujahidul', 'Islam', '01915365872', 'mujahidul@gmail.com', HashBytes('MD5', 'mujahidul'), 'M', '2013-04-01', '1991-01-12', null),
(1, 4, 'Rezone', 'Khan', '01712698563', 'rezone@gmail.com', HashBytes('MD5', 'rezone'), 'M', '2016-08-22', '1992-12-01', null),
(4, 3, 'Dhrubojit', 'Shaha', '01917856325', 'dhrubojit@gmail.com', HashBytes('MD5', 'dhrubojit'), 'M', '2017-04-11', '1998-04-12', null),
(5, 2, 'Rakib', 'Khan', '01678965412', 'rakib@gmail.com', HashBytes('MD5', 'rakib'), 'M', '2015-01-11', '1998-07-20', null),
(6, 1, 'Asif', 'Ikbal', '01635985847', 'asif@gmail.com', HashBytes('MD5', 'asif'), 'M', '2017-06-03', '1997-05-01', '2019-01-01'),
(7, 2, 'Shuvom', 'Shaha', '01715632598', 'shuvom@gmail.com', HashBytes('MD5', 'shuvom'), 'M', '2019-04-01', '1995-11-01', '2019-01-01'),
(8, 3, 'Fuad', 'Islam', '01521478965', 'fuad@gmail.com', HashBytes('MD5', 'fuad'), 'M', '2017-08-01', '1996-10-01', '2019-01-01'),
(7, 4, 'Dihan', 'Tonmoy', '01619632578', 'dihan@gmail.com', HashBytes('MD5', 'dihan'), 'M', '2012-01-01', '1990-10-06', null),
(6, 5, 'Dibbo', 'Nath', '01715326978', 'dibbo@gmail.com', HashBytes('MD5', 'dibbo'), 'M', '2019-05-01', '1998-10-08', null),
(1, 1, 'Rashid', 'Khan', '01715632548', 'rashid@gmail.com', HashBytes('MD5', 'radhid'), 'M', '2012-01-01', '1990-10-06', null),
(2, 1, 'Jeni', 'Bess', '01619632578', 'jeni@gmail.com', HashBytes('MD5', 'jeni'), 'M', '2011-01-01', '1990-10-06', null),
(3, 2, 'Nam', 'Comacho', '01521896879', 'nam@gmail.com', HashBytes('MD5', 'nam'), 'M', '2013-01-01', '1990-10-06', null),
(4, 3, 'Kelly', 'Vanscyoc', '01715632548', 'kelly@gmail.com', HashBytes('MD5', 'kelly'), 'F', '2012-01-01', '1990-10-06', null),
(5, 4, 'Margert', 'Rieke', '0185695823', 'margert@gmail.com', HashBytes('MD5', 'margert'), 'M', '2014-01-01', '1990-10-06', null),
(6, 5, 'Kent', 'Kroh', '01523698745', 'kent@gmail.com', HashBytes('MD5', 'kent'), 'M', '2015-01-01', '1990-10-06', null),
(7, 6, 'Alan', 'Baker', '0189632745', 'alan@gmail.com', HashBytes('MD5', 'alan'), 'M', '2012-01-01', '1990-10-06', null),
(8, 6, 'Boris', 'Wickliffe', '0125896325', 'boris@gmail.com', HashBytes('MD5', 'boris'), 'M', '2012-01-01', '1990-10-06', null),
(2, 4, 'Arnita', 'Rahman', '01857454525', 'arnita@gmail.com', HashBytes('MD5', 'arnita'), 'F', '2016-04-12', '1998-07-11', null),
(4, 5, 'Syeda', 'Sultana', '01552757584', 'syeda@gmail.com', HashBytes('MD5', 'syeda'), 'F', '2015-02-24', '1997-07-13', null),
(2, 6, 'Nishat', 'Haque', '01754212136', 'nishat@gmail.com', HashBytes('MD5', 'nishat'), 'F', '2015-01-01', '1999-06-03', null),
(1, 2, 'Maria', 'Noor', '01658454578', 'maria@gmail.com', HashBytes('MD5', 'maria'), 'F', '2017-06-11', '1998-05-17', null),
(4, 5, 'Umama', 'Zaman', '01845787821', 'umama@gmail.com', HashBytes('MD5', 'umama'), 'F', '2016-04-10', '1999-03-27', null),
(3, 5, 'Ayesha', 'Siddique', '01554896235', 'ayesha@gmail.com', HashBytes('MD5', 'ayesha'), 'F', '2017-03-21', '1998-04-15', null),
(2, 4, 'Rubaba', 'Doula', '01774212636', 'rubaba@gmail.com', HashBytes('MD5', 'rubaba'), 'F', '2016-03-11', '1998-07-05', null),
(1, 3, 'Zarin', 'Tasnim', '0178451525', 'zarin@gmail.com', HashBytes('MD5', 'zarin'), 'F', '2017-04-21', '1999-05-14', null),
(4, 2, 'Sinthia', 'Zaman', '01458987852', 'sinthia@gmail.com', HashBytes('MD5', 'sinthia'), 'F', '2016-11-15', '1998-04-12', null)