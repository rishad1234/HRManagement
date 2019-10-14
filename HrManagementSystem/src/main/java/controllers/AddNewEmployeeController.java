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
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Department;
import models.Employee;
import models.Payroll;
import utils.AttributeHash;
import utils.CleanStringAttribute;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AddNewEmployeeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private ChoiceBox department;
    @FXML
    private ChoiceBox payroll;
    @FXML
    private DatePicker joiningDate;
    @FXML
    private Button addEmployee;
    @FXML
    private TextField designation;
    
    private String fname;
    private String lname;
    private String e;
    private String desig;
    private String pass;
    private int dept;
    private int pay;
    
    
    private ObservableList departmentNameList = FXCollections.observableArrayList();
    private ArrayList<Integer> departmentIdList = new ArrayList<>();
    private ObservableList salaryList = FXCollections.observableArrayList();
    private ArrayList<Integer> payrollIdList = new ArrayList<>();
    private ArrayList<String> deptTemp = new ArrayList<>();
    private ArrayList<Integer> payTemp = new ArrayList<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        setDepartmentValues();
        setPayrollValues();
        addEmployee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    addEmployee();
                } catch (ParseException ex) {
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
            dept = 0;
        } catch (SQLException ex) {
            Logger.getLogger(AddNewEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setPayrollValues(){
        ResultSet data = Payroll.getPayrollName();
        try {
            while(data.next()){
                salaryList.add(data.getDouble("salary"));
                payrollIdList.add(data.getInt("payroll_id"));
                payTemp.add(data.getInt("payroll_id"));
                System.out.println(data.getDouble("salary"));
                System.out.println(data.getInt("payroll_id"));
            }
            payroll.setItems(salaryList);
            payroll.getSelectionModel().selectFirst();
            pay = 0;
        } catch (SQLException ex) {
            Logger.getLogger(AddNewEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addEmployee() throws ParseException{
        fname = CleanStringAttribute.clean(firstName.getText());
        lname = CleanStringAttribute.clean(lastName.getText());
        e = CleanStringAttribute.clean(email.getText());
        pass = AttributeHash.hash(CleanStringAttribute.clean(password.getText()));
        desig = CleanStringAttribute.clean(designation.getText());
        
        if(!CleanStringAttribute.stringValidate(fname) && 
                !CleanStringAttribute.stringValidate(lname) &&
                !CleanStringAttribute.stringValidate(e) && 
                !CleanStringAttribute.stringValidate(pass)){

            java.sql.Date joining = getDate();
            
            String departmentData = department.getValue().toString();
            String payrollData = payroll.getValue().toString();
            
            for(int i = 0; i < deptTemp.size(); i++){
                if(deptTemp.get(i).equals(departmentData)){
                    dept = departmentIdList.get(i);
                }
            }
            
            for(int i = 0; i < salaryList.size(); i++){
                if(salaryList.get(i).toString().equals(payrollData)){
                    pay = payrollIdList.get(i);
                }
            }
            
            Employee.insertEmployee(fname, lname, e, pass, dept, pay, joining, desig);
            Stage stage = (Stage) addEmployee.getScene().getWindow();
            stage.close();
        }
        
    }
    
    private java.sql.Date getDate(){
        java.sql.Date sql = null;
        try {
            LocalDate localDate = joiningDate.getValue();
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
