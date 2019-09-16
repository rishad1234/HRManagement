
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