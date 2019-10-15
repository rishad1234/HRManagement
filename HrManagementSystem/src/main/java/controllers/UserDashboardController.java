/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
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
    @FXML
    private ChoiceBox editGender;
    @FXML
    private DatePicker editBirthday;
    @FXML
    private Button submit;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        setUi();
        
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                java.sql.Date birthday = getDate();
                String g = editGender.getValue().toString();
                
                Employee.updateEmployee(Employee.EmployeeEmail, Employee.EmployeePassword, birthday, g);
                setUi();
            }
        });
    }    
    
    private java.sql.Date getDate(){
        java.sql.Date sql = null;
        try {
            LocalDate localDate = editBirthday.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String joining = format.format(date);
            Date parsed = format.parse(joining);
            sql = new java.sql.Date(parsed.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(AddNewEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sql;
    }
    
    public void setUi(){
        editGender.setItems(FXCollections.observableArrayList("M", "F"));
        editGender.getSelectionModel().selectFirst();
        try {
            
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
