/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rishad
 */
public class AdminDashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private PieChart incomeExpensePieChart;
    @FXML
    private PieChart incomePieChart;
    @FXML
    private PieChart expensePieChart;
    @FXML
    private ProgressIndicator maleProgress;
    @FXML
    private ProgressIndicator femaleProgress;
    @FXML
    private TableView departmentTable;
    @FXML
    private Button addNewDepartmentButton;
    @FXML
    private Button addNewPayrollsButton;
    @FXML
    private TableView payrollTable;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<PieChart.Data> incomeExpenseChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Income", 130000),
                new PieChart.Data("Expense", 75000));
        incomeExpensePieChart.setData(incomeExpenseChartData);
        incomeExpensePieChart.setTitle("Income-Expense Chart");
        
        ObservableList<PieChart.Data> incomeChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Sales", 25000),
                new PieChart.Data("Marketing", 50000),
                new PieChart.Data("Development", 65000));
        incomePieChart.setData(incomeChartData);
        
        incomePieChart.setTitle("Income Chart");
        
        ObservableList<PieChart.Data> expenseChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Sales", 25000),
                new PieChart.Data("Marketing", 35000),
                new PieChart.Data("Development", 15000));
        expensePieChart.setData(expenseChartData);
        
        expensePieChart.setTitle("Expense Chart");
        
        maleProgress.setProgress(0.75);
        
        femaleProgress.setProgress(0.25);
        
        TableColumn deptId = new TableColumn("Department ID");
        TableColumn deptName = new TableColumn("Department Name");
        TableColumn deptHead = new TableColumn("Department Head");
        
        departmentTable.getColumns().addAll(deptId, deptName, deptHead);
        
        addNewDepartmentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                stage.setTitle("Add new Department");
                Pane myPane = null;
                try {
                    myPane = FXMLLoader.load(getClass().getResource("/fxml/add_new_department.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(myPane);
                stage.setScene(scene);    
                stage.show();
            }
        });
        
        
        TableColumn payrollId = new TableColumn("Payroll ID");
        TableColumn payrollSalary = new TableColumn("Salary");
        TableColumn payrollIncrement = new TableColumn("Increment");
        
        payrollTable.getColumns().addAll(payrollId, payrollSalary, payrollIncrement);
        
        addNewPayrollsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                stage.setTitle("Add new Payroll");
                Pane myPane = null;
                try {
                    myPane = FXMLLoader.load(getClass().getResource("/fxml/add_new_payroll.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(myPane);
                stage.setScene(scene);    
                stage.show();
            }
        });
    }    
    
}
