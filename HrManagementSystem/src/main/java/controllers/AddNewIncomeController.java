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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
public class AddNewIncomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField projectName;
    @FXML
    private TextField projectDesccription;
    @FXML
    private TextField clintName;
    @FXML
    private TextField revenue;
    @FXML
    private ChoiceBox department;
    @FXML
    private ChoiceBox finishedDate;
    @FXML
    private Button addNewIncome;
    
    
    private ArrayList<Integer> departmentIdList = new ArrayList<>();
    private String pName = "";
    private String  pDescription = "";
    private String  cName = "";
    private String rev = "";
    private int dept;
    
    private ObservableList departmentNameList = FXCollections.observableArrayList();
    private ArrayList<String> deptTemp = new ArrayList<>();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setDepartmentValues();
        addNewIncome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    addIncome();
                } catch (Exception ex) {
                    Logger.getLogger(AddNewEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }    
    
    public void setDepartmentValues(){
        ResultSet data = Department.getDepartmentName();
        try {
            while(data.next()){
                departmentNameList.add(data.getString("department_name"));
                deptTemp.add(data.getString("department_name"));
                departmentIdList.add(data.getInt("department_id"));
                System.out.println(data.getString("department_name"));
                System.out.println(data.getInt("department_id"));
            }
            department.setItems(departmentNameList);
            department.getSelectionModel().selectFirst();
            dept = 1;
        } catch (SQLException ex) {
            Logger.getLogger(AddNewEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    public void addIncome() throws ParseException{
        pName = CleanStringAttribute.clean(projectName.getText());
        pDescription = CleanStringAttribute.clean(projectDesccription.getText());
        cName = CleanStringAttribute.clean(clintName.getText());
        rev = CleanStringAttribute.clean(revenue.getText());
        
        
        
    }
    
}
