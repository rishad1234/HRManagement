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
public class Expense {
    public static int getTotalExpense(){
        int totalExpense = 0;
        ResultSet data = null;
        try {
            Connection connection = ConnectDB.makeConnection();
            String sql = "select sum(amount_of_expense) as total_expense from expenses";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            data = preparedStatement.executeQuery();
            while(data.next()){
                totalExpense += (int)data.getDouble("total_expense");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalExpense;
    }
    
    public static ResultSet getTotalExpensesByDepartments(){
        ResultSet data = null;
        try {
            Connection connection = ConnectDB.makeConnection();
            String sql = "select department_name, sum(amount_of_expense) as total_expense from expenses join departments on " +
                        "expenses.department_id = departments.department_id group by department_name";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            data = preparedStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public static ResultSet getAllExpenses(){
        ResultSet data = null;
        try {
            Connection connection = ConnectDB.makeConnection();
            String sql = "select * from expenses";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            data = preparedStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    public static void insertExpense(String title, String description, double amount, Date created, int dept){
        try {
            Connection connection = ConnectDB.makeConnection();
            String sql = "insert into expenses (department_id, created_at, amount_of_expense, expense_title, expense_description ) " +
                    "values(?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, dept);
            statement.setDate(2, created);
            statement.setDouble(3, amount);
            statement.setString(4, title);
            statement.setString(5, description);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
