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
public class Payroll {
    public static int getTotalSalary(){
        ResultSet data = null;
        int total_salary = 0;
        try {
            Connection connection = ConnectDB.makeConnection();
            String sql = "select sum(salary) as total_salary from payrolls where payroll_id in(select payroll_id from employees)";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            data = preparedStatement.executeQuery();
            while(data.next()){
                total_salary += (int)data.getDouble("total_salary");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total_salary;
    }
    
    public static int getAvgSalary(){
        ResultSet data = null;
        int avg_salary = 0;
        try {
            Connection connection = ConnectDB.makeConnection();
            String sql = "select avg(salary) as avg_salary from payrolls where payroll_id in(select payroll_id from employees)";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            data = preparedStatement.executeQuery();
            while(data.next()){
                avg_salary += (int)data.getDouble("avg_salary");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
        }
        return avg_salary;
    }
    
    public static ResultSet getPayrollTable(){
        ResultSet data = null;
        try {
            Connection connection = ConnectDB.makeConnection();
            String sql = "select * from payrolls";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            data = preparedStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    public static ResultSet getPayrollName(){
        ResultSet data = null;
        try {
            Connection connection = ConnectDB.makeConnection();
            String sql = "select payroll_id, salary from payrolls";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            data = preparedStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    
    public static void insertNewPayroll(String salary, String increment){
        try {
            Connection connection = ConnectDB.makeConnection();
            String sql = "insert into payrolls (salary, increment) " +
                    "values(?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, salary);
            statement.setString(2, increment);
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
