/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utils.ConnectDB;

/**
 *
 * @author Rishad
 */
public class CustomQuery {
    
    public static ResultSet customQeury(String sql) throws SQLException{
        ResultSet data = null;
        Connection connection = ConnectDB.makeConnection();
        Statement stmt = null;
        stmt = connection.createStatement();
        data = stmt.executeQuery(sql);
         
        return data;
    }
}
