package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.ConnectDB;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rishad
 */
public class Department {
    
    public static ResultSet getAllDepartments(){
        ResultSet data = null;
        try {
            Connection connection = ConnectDB.makeConnection();
            String sql = "select * from departments";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            data = preparedStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    public static ResultSet getDepartmentName(){
        ResultSet data = null;
        try {
            Connection connection = ConnectDB.makeConnection();
            String sql = "select department_id, department_name from departments";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            data = preparedStatement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    public static void insertNewDept(String deptName, String deptHead){
        try {
            Connection connection = ConnectDB.makeConnection();
            String sql = "insert into departments (department_name, department_head) " +
                    "values(?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, deptName);
            statement.setString(2, deptHead);
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
