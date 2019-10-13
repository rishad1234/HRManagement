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
public class Income {
    
    public static int getTotalIncome(){
        int totalIncome = 0;
        ResultSet data = null;
        try {
            Connection connection = ConnectDB.makeConnection();
            String sql = "select sum(income_amount) as total_income from incomes";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            data = preparedStatement.executeQuery();
            while(data.next()){
                totalIncome += (int)data.getDouble("total_income");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalIncome;
    }
    
    public static ResultSet getTotalIncomesByDepartments(){
        ResultSet data = null;
        try {
            Connection connection = ConnectDB.makeConnection();
            String sql = "select department_name, sum(income_amount) as total_income from incomes join departments on " +
                        "incomes.department_id = departments.department_id group by department_name";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            data = preparedStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
}
