/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnectDB;

/**
 *
 * @author Rishad
 */
public class Employee {
    public static String EmployeeEmail;
    public static String EmployeePassword;
    
    public static int countEmployees(){
        ResultSet data = null;
        int numberOfEmployees = 0;
        try {
            Connection connection = ConnectDB.makeConnection();
            String sql = "select count(employee_id) as number from employees";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            data = preparedStatement.executeQuery();
            while(data.next()){
                numberOfEmployees += data.getInt("number");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numberOfEmployees;
    }
    
    public static int getNewEmployee(){
        ResultSet data = null;
        int newEmployee = 0;
        try {
            Connection connection = ConnectDB.makeConnection();
            String sql = "SELECT count(*) as new_employee FROM employees " + 
                    "WHERE DATEPART(m, joining_date) = DATEPART(m, DATEADD(m, -2, getdate())) " +
                    "AND DATEPART(yyyy, joining_date) = DATEPART(yyyy, DATEADD(m, -2, getdate()))";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            data = preparedStatement.executeQuery();
            while(data.next()){
                newEmployee += data.getInt("new_employee");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newEmployee;
    }
    
    public static double totalMale(){
        ResultSet data = null;
        int totalMaleCount = 0;
        try {
            Connection connection = ConnectDB.makeConnection();
            String sql = "select count(*) as total_male from employees where gender = 'M'";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            data = preparedStatement.executeQuery();
            while(data.next()){
                totalMaleCount += data.getInt("total_male");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalMaleCount;
    }
    
    public static ResultSet getAllEmployees(){
        ResultSet data = null;
        try {
            Connection connection = ConnectDB.makeConnection();
            String sql = "select employees.employee_id, departments.department_name, " + 
                    "payrolls.salary, employees.first_name, employees.last_name, employees.email " +
                    "from employees inner join departments " +
                    "on employees.department_id = departments.department_id inner join " +
                    "payrolls on employees.payroll_id = payrolls.payroll_id";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            data = preparedStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    public static void insertEmployee(String fname, String lname,
                                        String e, String pass, int dept, int pay, Date joiningDate, String designation,
                                        String phone){
        try {
            Connection connection = ConnectDB.makeConnection();
            String sql = "insert into employees (first_name, last_name, email, password, joining_date, department_id, payroll_id, designation, phone, gender, date_of_birth) " +
                    "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, fname);
            statement.setString(2, lname);
            statement.setString(3, e);
            statement.setString(4, pass);
            statement.setDate(5, joiningDate);
            statement.setInt(6, dept);
            statement.setInt(7, pay);
            statement.setString(8, designation);
            statement.setString(9, phone);
            statement.setString(10, "M");
            statement.setDate(11, java.sql.Date.valueOf("1990-1-1"));
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ResultSet getEmployee(){
        ResultSet data = null;
        try {
            Connection connection = ConnectDB.makeConnection();
            String sql = "select employees.employee_id, departments.department_name, employees.password, employees.phone, " + 
                    "payrolls.salary, employees.first_name, employees.last_name, employees.email, employees.date_of_birth, employees.designation, employees.gender " +
                    "from employees inner join departments " +
                    "on employees.department_id = departments.department_id inner join " +
                    "payrolls on employees.payroll_id = payrolls.payroll_id";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            data = preparedStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
}
