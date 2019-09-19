package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utils.AttributeHash;
import utils.CleanStringAttribute;
import utils.ConnectDB;
import utils.EmailValidator;

public class LoginController implements Initializable {
    
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
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
                        !CleanStringAttribute.stringValidate(userPassword) &&
                        EmailValidator.emailValidate(userEmail)){
                    
                    errorLabel.setText("");
                    userPassword = AttributeHash.hash(userPassword);
                    
                    checkLogin(userEmail, userPassword);
                    
                }else if(!EmailValidator.emailValidate(userEmail)){
                    errorLabel.setText("Email is not in correct form");
                    password.setText("");
                }else{
                    errorLabel.setText("All the fields are required");
                    email.setText("");
                    password.setText("");
                }
            }
        });
    }

    private void checkLogin(String userEmail, String userPassword){
        Connection connection = ConnectDB.makeConnection();
        // do the code here
        String sql = "select * from employees where email=? and password=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userEmail);
            preparedStatement.setString(2, userPassword);

            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) { /* looping through the resultset */
                if(data.getString("email").equals(userEmail) &&
                        data.getString("password").equals(userPassword) &&
                        data.getString("designation").equals("Administrator")){
                    System.out.println("login successful");
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectDB.close();
    }
}
