/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ProgressIndicator;

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
    }    
    
}
