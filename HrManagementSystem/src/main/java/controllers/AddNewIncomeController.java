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
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Department;
import models.Employee;
import models.Income;
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
    private TextArea projectDescription;
    @FXML
    private TextField clientName;
    @FXML
    private TextField revenue;
    @FXML
    private ChoiceBox incomeDepartment;
    @FXML
    private DatePicker finished;
    @FXML
    private Button addIncome;
    
    
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
        addIncome.setOnAction(new EventHandler<ActionEvent>() {
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
            incomeDepartment.setItems(departmentNameList);
            incomeDepartment.getSelectionModel().selectFirst();
            dept = 1;
        } catch (SQLException ex) {
            Logger.getLogger(AddNewEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addIncome() throws ParseException{
        pName = CleanStringAttribute.clean(projectName.getText());
        pDescription = CleanStringAttribute.clean(projectDescription.getText());
        cName = CleanStringAttribute.clean(clientName.getText());
        rev = CleanStringAttribute.clean(revenue.getText());
        
        String incomeDepartmentData = incomeDepartment.getValue().toString();
        
        for(int i = 0; i < deptTemp.size(); i++){
            if(deptTemp.get(i).equals(incomeDepartmentData)){
                dept = departmentIdList.get(i);
            }
        }
        java.sql.Date finishedDate = getDate();
        
        Income.insertIncome(pName, pDescription, cName, dept, Double.valueOf(rev), finishedDate);
        Stage stage = (Stage) addIncome.getScene().getWindow();
        stage.close();
        
    }
    
    private java.sql.Date getDate(){
        java.sql.Date sql = null;
        try {
            LocalDate localDate = finished.getValue();
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
    
}
