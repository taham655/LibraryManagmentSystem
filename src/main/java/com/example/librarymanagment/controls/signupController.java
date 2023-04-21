package com.example.librarymanagment.controls;

import com.example.librarymanagment.model.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class signupController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label labelMessage;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;

    public void button_signupOnAction(ActionEvent e) throws IOException {
        signingUp();
        root = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    public void signingUp (){
        JDBC connectNow = new JDBC();
        Connection connectDB = connectNow.getConnection();
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        String signup = "INSERT INTO users (username, password) VALUES (' " +tf_username.getText() +" ' , '" + pf_password.getText() + " ')";

        try {
            psCheckUserExists = connectDB.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1, tf_username.getText());
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.next()) {
                labelMessage.setText("Username already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("You can not use this username");
                alert.show();
            } else {
                psInsert = connectDB.prepareStatement(signup);
                psInsert.executeUpdate();
                labelMessage.setText("Signed up successfully!");

            }

            /*
            Statement stm = connectDB.createStatement();
            stm.executeUpdate(signup);
            labelMessage.setText("Signed up successfully!");
            connectDB.close();

             */
        } catch (SQLException e) {
            labelMessage.setText("Lmaoo no luck");
            throw new RuntimeException(e);
        } finally {
            try {
                if (psInsert != null) {
                    psInsert.close();
                }
                if (psCheckUserExists != null) {
                    psCheckUserExists.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
