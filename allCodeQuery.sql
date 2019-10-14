select * from employees
select * from incomes
select * from departments

select sum(income_amount) as total_income from incomes
select * from expenses

select sum(amount_of_expense) as total_income from expenses
select * from departments

select department_name, sum(income_amount) from incomes join departments on
	incomes.department_id = departments.department_id group by department_name

select department_name, sum(amount_of_expense) from expenses join departments on
	expenses.department_id = departments.department_id group by department_name

select * from payrolls

select sum(salary) from payrolls where payroll_id in(select payroll_id from employees)

select count(employee_id) as number from employees

select avg(salary) from payrolls where payroll_id in(select payroll_id from employees)

SELECT count(*) as new_employee FROM employees
WHERE DATEPART(m, joining_date) = DATEPART(m, DATEADD(m, -2, getdate()))
AND DATEPART(yyyy, joining_date) = DATEPART(yyyy, DATEADD(m, -2, getdate()))


select employees.employee_id, departments.department_name,
		 payrolls.salary, employees.first_name, employees.last_name, employees.email,
		 employees.phone from employees inner join departments 
		 on employees.department_id = departments.department_id inner join
		 payrolls on employees.payroll_id = payrolls.payroll_id

