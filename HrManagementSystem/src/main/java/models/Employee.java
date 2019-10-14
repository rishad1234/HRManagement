/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
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
                    "payrolls.salary, employees.first_name, employees.last_name, employees.email, " +
                    "employees.phone from employees inner join departments " +
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
