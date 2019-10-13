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
}
