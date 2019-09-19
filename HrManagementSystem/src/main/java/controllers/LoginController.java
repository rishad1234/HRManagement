package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utils.AttributeHash;
import utils.CleanStringAttribute;

public class LoginController implements Initializable {
    
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private Button loginButton;
    @FXML
    private Label errorLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String userEmail = CleanStringAttribute.clean(email.getText());
                String userPassword = CleanStringAttribute.clean(password.getText());
               
                if(!CleanStringAttribute.stringValidate(userEmail) &&
                        !CleanStringAttribute.stringValidate(userPassword)){
                    
                    userPassword = AttributeHash.hash(userPassword);
                }else{
                    errorLabel.setText("All the fields are required");
                    
                }
            }
        });
    }    
}
