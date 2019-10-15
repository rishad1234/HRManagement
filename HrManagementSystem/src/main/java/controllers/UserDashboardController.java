/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.Employee;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class UserDashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label employeeName;
    @FXML
    private Label designation;
    @FXML
    private Label email;
    @FXML
    private Label phone;
    @FXML
    private Label birthday;
    @FXML
    private Label salary;
    @FXML
    private Label department;
    @FXML
    private Label gender;
    @FXML
    private Label editName;
    @FXML
    private Label editDesignation;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            System.out.println(Employee.EmployeeEmail);
            System.out.println(Employee.EmployeePassword);
            
            ResultSet data = Employee.getEmployee();
            System.out.println(data);
            while(data.next()){
                if(data.getString("email").equals(Employee.EmployeeEmail) 
                        && data.getString("password").equals(Employee.EmployeePassword)){
                    designation.setText(data.getString("designation"));
                    employeeName.setText(data.getString("first_name") + " " + data.getString("last_name"));
                    email.setText(data.getString("email"));
                    phone.setText(data.getString("phone"));
                    salary.setText(data.getDouble("salary") + " BDT");
                    birthday.setText(data.getDate("date_of_birth").toString());
                    department.setText(data.getString("department_name"));
                    gender.setText(data.getString("gender"));
                    
                    editName.setText(data.getString("first_name") + " " + data.getString("last_name"));
                    editDesignation.setText(data.getString("designation"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
