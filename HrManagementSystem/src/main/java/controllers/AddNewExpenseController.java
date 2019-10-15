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
import jdk.nashorn.internal.parser.DateParser;
import models.Department;
import models.Expense;
import utils.CleanStringAttribute;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AddNewExpenseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField expenseTitle;
    @FXML
    private TextArea expenseDescription;
    @FXML
    private TextField expenseAmount;
    @FXML
    private DatePicker expenseCreated;
    @FXML
    private ChoiceBox expenseDepartment;
    @FXML
    private Button addExpense;
    
    private String title;
    private String description;
    private String amount;
    private int dept;
    
    private ArrayList<Integer> departmentIdList = new ArrayList<>();
    private ObservableList departmentNameList = FXCollections.observableArrayList();
    private ArrayList<String> deptTemp = new ArrayList<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setDepartmentValues();
        addExpense.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                java.sql.Date created = getDate();
                title = CleanStringAttribute.clean(expenseTitle.getText());
                description = CleanStringAttribute.clean(expenseDescription.getText());
                amount = CleanStringAttribute.clean(expenseAmount.getText());
                
                String expenseDepartmentData = expenseDepartment.getValue().toString();

                for(int i = 0; i < deptTemp.size(); i++){
                    if(deptTemp.get(i).equals(expenseDepartmentData)){
                        dept = departmentIdList.get(i);
                    }
                }
                
                Expense.insertExpense(title, description, Double.valueOf(amount), created, dept);
                Stage stage = (Stage) addExpense.getScene().getWindow();
                stage.close();
            }
        });
    }  
    
    private java.sql.Date getDate(){
        java.sql.Date sql = null;
        try {
            LocalDate localDate = expenseCreated.getValue();
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
            expenseDepartment.setItems(departmentNameList);
            expenseDepartment.getSelectionModel().selectFirst();
            dept = 1;
        } catch (SQLException ex) {
            Logger.getLogger(AddNewEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
