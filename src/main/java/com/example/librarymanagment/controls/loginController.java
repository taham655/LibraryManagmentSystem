package com.example.librarymanagment.controls;

import com.example.librarymanagment.model.JDBC;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;
    @FXML
    private Button button_login;





    public void validateLogin(){
        System.out.println("less go");
        JDBC connectNow = new JDBC();
        Connection connectDB = connectNow.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //String verifyLogin = "SELECT COUNT(1) FROM users WHERE username = '" + tf_username.getText() + "' AND PASSWORD = '" + pf_password.getText()  + "'";

        try {
            preparedStatement = connectDB.prepareStatement("SELECT COUNT(1) FROM users WHERE username = ? AND password = ?");
            preparedStatement.setString(1, tf_username.getText());
            resultSet = preparedStatement.executeQuery();



                if(!resultSet.isBeforeFirst()){
                    loginMessageLabel.setText("User not found");
                } else {
                    while(resultSet.next()){
                        String retrievedUsername = resultSet.getString("username");
                        String retrievedPassword = resultSet.getString("password");
                        if(retrievedUsername.equals(tf_username.getText()) && retrievedPassword.equals(pf_password.getText())){
                            loginMessageLabel.setText("Login successful");
                        } else {
                            loginMessageLabel.setText("Invalid login. Please try again");
                        }
                    }

                }

            connectDB.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        button_login.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                try {
//                    //Utils.changeScene(actionEvent, "signup.fxml", "Sign Up",null);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
    }
    public void setUserInformation(String username, String password){
        tf_username.setText(username);
        pf_password.setText(password);
    }
}
