package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {
    
    @FXML
    private TextField email;
    private TextField password;
    private Button loginButton;
    private Label errorLabel;
    
//    @FXML
//    private void handleButtonAction(ActionEvent event) {
//        Connection connection = null;
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            connection = DriverManager.getConnection(
//                    "jdbc:sqlserver://localhost:1433;databaseName=HRManagementSystem;selectMethod=cursor",
//                    "sa", "123456");
//        } catch (SQLException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            System.out.println(connection.getMetaData().getDatabaseProductName());
//            
//            if(connection.getMetaData().getDatabaseProductName().equals("Microsoft SQL Server")){
//                label.setText("Connection established");
//            }else{
//                label.setText("Some problem occured during connection");
//            }
//        } catch (SQLException ex) {
//
//        }
//        System.out.println("You clicked me!");
//    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                errorLabel.setText("checked");
                email.setText("checked");
                password.setText("checked");
            }
        });
    }    
}
