/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Department;
import models.Payroll;
import utils.CleanStringAttribute;

/**
 * FXML Controller class
 *
 * @author Rishad
 */
public class AddNewPayrollController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField newSalary;
    @FXML
    private TextField newIncrement;
    @FXML
    private Button addNewPayroll;
    
    
    private String salary = "";
    private String increment = "";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        addNewPayroll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    addPayroll();
                } catch (Exception ex) {
                    Logger.getLogger(AddNewEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void addPayroll() throws ParseException{
        salary = CleanStringAttribute.clean(newSalary.getText());
        increment = CleanStringAttribute.clean(newIncrement.getText());
        
        
        
        Payroll.insertNewPayroll(salary, increment);
        Stage stage = (Stage) addNewPayroll.getScene().getWindow();
        stage.close();
        
    }
    
}
