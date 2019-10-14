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
import models.Employee;
import utils.AttributeHash;
import utils.CleanStringAttribute;

/**
 * FXML Controller class
 *
 * @author Rishad
 */
public class AddNewDepartmentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField newDeptName;
    @FXML
    private TextField newDeptHead;
    @FXML
    private Button addDept;
    
    
    
    
    
    private String dName = "";
    private String dHead = "";
    
    public void getValue(){
        dName = CleanStringAttribute.clean(newDeptName.getText());
        dHead = CleanStringAttribute.clean(newDeptHead.getText());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addDept.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    addDept();
                } catch (Exception ex) {
                    Logger.getLogger(AddNewEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    
    
    public void addDept() throws ParseException{
        dName = CleanStringAttribute.clean(newDeptName.getText());
        dHead = CleanStringAttribute.clean(newDeptHead.getText());
        
        
        
        Department.insertNewDept(dName, dHead);
        Stage stage = (Stage) addDept.getScene().getWindow();
        stage.close();
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}    
    
    
  
