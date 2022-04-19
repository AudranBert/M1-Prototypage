package com.example.m1prototyopage;

import BookBddExemple.Connexion;
import javafx.fxml.FXML;
import UserDB.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerProfile {

    @FXML
    private TextField ageuser;
    @FXML
    private Button logoutuser;
    @FXML
    private TextField mailuser;

    @FXML
    private TextField nomuser;

    @FXML
    private TextField prenomuser;

    @FXML
    private TextField telephoneuser;

    @FXML
    void display(MouseEvent event) {

    }

    @FXML
    void modifierProfile(ActionEvent event) {
        Connexion connexion = new Connexion("Database/User.db");
        connexion.connect();
        String query  = "UPDATE `User` SET `FirstName` = '"+prenomuser.getText()+"', `LastName` = '"+nomuser.getText()+"' WHERE `UserId` = "+3;
        connexion.submitQuery(query);
        connexion.close();
    }







    @FXML
    void logout(ActionEvent event) {
        Stage stage = (Stage) logoutuser.getScene().getWindow();
        stage.close();
    }
}



