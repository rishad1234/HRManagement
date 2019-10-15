/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Department;
import models.Employee;
import models.Expense;
import models.Income;
import models.Payroll;

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
    private Button addNewDepartmentButton;
    @FXML
    private Button addNewPayrollsButton;
    @FXML
    private Button addNewEmployeesButton;
    @FXML
    private Button addNewExpensesButton;
    @FXML
    private Button reloadEmployee;
    @FXML
    private Button reloadDepartment;
    @FXML
    private Button reloadPayroll;
    @FXML
    private Button addNewIncomeButton;
    @FXML
    private Label totalSalary;
    @FXML
    private Label numberOfEmployees;
    @FXML
    private Label averageSalary;
    @FXML
    private Label newEmployee;
    
    @FXML
    private TableView departmentTable;
    @FXML
    private TableView payrollTable;
    @FXML
    private TableView employeeTable;
    @FXML
    private TableView incomesTable;
    @FXML
    private TableView expensesTable;
    @FXML
    private Button reloadIncome;
    
    
    private ObservableList<ObservableList> departmentData;
    private ObservableList<ObservableList> payrollData;
    private ObservableList<ObservableList> employeeData;
    private ObservableList<ObservableList> incomeData;
    private ObservableList<ObservableList> expenseData;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<PieChart.Data> incomeExpenseChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Income", Income.getTotalIncome()),
                new PieChart.Data("Expense", Expense.getTotalExpense()));
        incomeExpensePieChart.setData(incomeExpenseChartData);
        incomeExpensePieChart.setTitle("Income-Expense Chart");
        
        //this method sets the piechart of income pie chart
        getIncomeData();
        
        //this method set the piechart of expense pie chart
        getExpenseData();
        
        totalSalary.setText(String.valueOf(Payroll.getTotalSalary()));
        
        numberOfEmployees.setText(String.valueOf(Employee.countEmployees()));
        
        averageSalary.setText(String.valueOf(Payroll.getAvgSalary()));
        
        newEmployee.setText(String.valueOf(Employee.getNewEmployee()));
        
        maleProgress.setProgress((double)Employee.totalMale()/Employee.countEmployees());
        
        femaleProgress.setProgress(1.00 - (double)Employee.totalMale()/Employee.countEmployees());
        
        //this method adds data to the department table
        addDepartmentDataToTable();
        
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
        
        // reload button to reload the departments after adding new
        reloadDepartment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                departmentTable.getItems().clear();
                departmentTable.getColumns().clear();
                addDepartmentDataToTable();
            }
        });
        
        //this method adds values to payroll tab
        addPayrollDataToTable();
        
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
        
        reloadPayroll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                payrollTable.getItems().clear();
                payrollTable.getColumns().clear();
                addPayrollDataToTable();
            }
        });
        
        
        /// Employee Table Code
        addEmployeeDataToTable();
        
        addNewEmployeesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                stage.setTitle("Add new Employee");
                Pane myPane = null;
                try {
                    myPane = FXMLLoader.load(getClass().getResource("/fxml/add_new_employee.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(myPane);
                stage.setScene(scene);    
                stage.show();
            }
        });
        
        
        /// Employee Table Code Ends Here
        addIncomeDataToTable();
        
        addNewIncomeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                stage.setTitle("Add new Income");
                Pane myPane = null;
                try {
                    myPane = FXMLLoader.load(getClass().getResource("/fxml/add_new_income.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(myPane);
                stage.setScene(scene);    
                stage.show();
                System.out.println("dhukse");
                        
            }
        });
        
        reloadIncome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                incomesTable.getItems().clear();
                incomesTable.getColumns().clear();
                addIncomeDataToTable();
            }
        });
        
        /// Expenses Code Here
        addExpenseDataToTable();
        
        addNewExpensesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /// code goes here
                
                Stage stage = new Stage();
                stage.setTitle("Add new Expense");
                Pane myPane = null;
                try {
                    myPane = FXMLLoader.load(getClass().getResource("/fxml/add_new_expense.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(myPane);
                stage.setScene(scene);    
                stage.show();
                
            }
        });
        
        reloadEmployee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                employeeTable.getItems().clear();
                employeeTable.getColumns().clear();
                addEmployeeDataToTable();
            }
        });
        /// Expenses Code End here
    }    
    
    public void getIncomeData(){
        ObservableList<PieChart.Data> incomeChartData = FXCollections.observableArrayList();
        ResultSet data = Income.getTotalIncomesByDepartments();
        try {
            while(data.next()){
                incomeChartData.add(new PieChart.Data(data.getString("department_name"), 
                        (int)data.getDouble("total_income")));
            }
            incomePieChart.setData(incomeChartData);
            incomePieChart.setTitle("Income Chart");
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getExpenseData(){
        ObservableList<PieChart.Data> expenseChartData = FXCollections.observableArrayList();
        ResultSet data = Expense.getTotalExpensesByDepartments();
        try {
            while(data.next()){
                expenseChartData.add(new PieChart.Data(data.getString("department_name"), 
                        (int)data.getDouble("total_expense")));
            }
            expensePieChart.setData(expenseChartData);
            expensePieChart.setTitle("Expense Chart");
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addDepartmentDataToTable(){
        try {
            ResultSet data = Department.getAllDepartments();
            
            ResultSetMetaData rsmd = data.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 0; i < columnCount; i++ ) {
                final int j = i;
                String name = rsmd.getColumnName(i + 1);
                TableColumn col = new TableColumn(name);
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
                departmentTable.getColumns().addAll(col);
            }
            
            departmentData = FXCollections.observableArrayList();
            while(data.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                System.out.println("column count: " + data.getMetaData().getColumnCount());
                for(int i=1 ; i<=data.getMetaData().getColumnCount(); i++){
                    row.add(data.getString(i));   
                }
                System.out.println("Row [1] added "+row );
                departmentData.add(row);

            }

            departmentTable.setItems(departmentData);
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addPayrollDataToTable(){
        try {
            ResultSet data = Payroll.getPayrollTable();
            
            ResultSetMetaData rsmd = data.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 0; i < columnCount; i++ ) {
                final int j = i;
                String name = rsmd.getColumnName(i + 1);
                TableColumn col = new TableColumn(name);
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
                payrollTable.getColumns().addAll(col);
            }
            
            payrollData = FXCollections.observableArrayList();
            while(data.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                System.out.println("column count: " + data.getMetaData().getColumnCount());
                for(int i=1 ; i<=data.getMetaData().getColumnCount(); i++){
                    row.add(data.getString(i));   
                }
                System.out.println("Row [1] added "+row );
                payrollData.add(row);

            }

            payrollTable.setItems(payrollData);
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    public void addEmployeeDataToTable(){
        try {
            ResultSet data = Employee.getAllEmployees();
            
            ResultSetMetaData rsmd = data.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 0; i < columnCount; i++ ) {
                final int j = i;
                String name = rsmd.getColumnName(i + 1);
                TableColumn col = new TableColumn(name);
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
                employeeTable.getColumns().addAll(col);
            }
            
            employeeData = FXCollections.observableArrayList();
            while(data.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                System.out.println("column count: " + data.getMetaData().getColumnCount());
                for(int i=1 ; i<=data.getMetaData().getColumnCount(); i++){
                    row.add(data.getString(i));   
                }
                System.out.println("Row [1] added "+row );
                employeeData.add(row);

            }

            employeeTable.setItems(employeeData);
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addIncomeDataToTable(){
        try {
            ResultSet data = Income.getAllIncomes();
            
            ResultSetMetaData rsmd = data.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 0; i < columnCount; i++ ) {
                final int j = i;
                String name = rsmd.getColumnName(i + 1);
                TableColumn col = new TableColumn(name);
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
                incomesTable.getColumns().addAll(col);
            }
            
            incomeData = FXCollections.observableArrayList();
            while(data.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                System.out.println("column count: " + data.getMetaData().getColumnCount());
                for(int i=1 ; i<=data.getMetaData().getColumnCount(); i++){
                    row.add(data.getString(i));   
                }
                System.out.println("Row [1] added "+row );
                incomeData.add(row);

            }

            incomesTable.setItems(incomeData);
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addExpenseDataToTable(){
        try {
            ResultSet data = Expense.getAllExpenses();
            
            ResultSetMetaData rsmd = data.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 0; i < columnCount; i++ ) {
                final int j = i;
                String name = rsmd.getColumnName(i + 1);
                TableColumn col = new TableColumn(name);
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
                expensesTable.getColumns().addAll(col);
            }
            
            expenseData = FXCollections.observableArrayList();
            while(data.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                System.out.println("column count: " + data.getMetaData().getColumnCount());
                for(int i=1 ; i<=data.getMetaData().getColumnCount(); i++){
                    row.add(data.getString(i));   
                }
                System.out.println("Row [1] added "+row );
                expenseData.add(row);

            }

            expensesTable.setItems(expenseData);
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
