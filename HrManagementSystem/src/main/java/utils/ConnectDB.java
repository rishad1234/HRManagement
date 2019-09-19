/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rishad
 */
public class ConnectDB {
public static String databaseName = "HRManagementSystem";
    public static final String username = "sa";
    public static final String password = "123456";
    private static Connection connection;
    
    private static String makeConnectionString(){
        return "jdbc:sqlserver://localhost:1433;databaseName="+ databaseName +";selectMethod=cursor";
    }
    
    public static Connection makeConnection(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                        makeConnectionString(),
                        "sa", "123456");
            System.out.println(connection.getMetaData().getDatabaseProductName());
                
            if(connection.getMetaData().getDatabaseProductName().equals("Microsoft SQL Server")){
                System.out.println("Connection Established");
            }else{
                System.out.println("Some Problem Occured during connection");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return connection;
    }
    public static void close(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
