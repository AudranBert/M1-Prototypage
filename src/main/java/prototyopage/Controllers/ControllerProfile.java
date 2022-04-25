package prototyopage.Controllers;

import DB.Connexion;
import DB.UserDB.User;
import javafx.fxml.FXML;


import java.sql.*;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import prototyopage.Context;
import prototyopage.MainApp;

public class ControllerProfile {

    // user
    @FXML
    private VBox userBox;
    @FXML
    private javafx.scene.text.Text userIsTravelerText;
    @FXML
    private javafx.scene.text.Text userNameText;

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
    private PasswordField passworduser;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) { this.mainApp = mainApp; }




    public void initialize() throws SQLException, ClassNotFoundException{
//Restrictions
        telephoneuser.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                telephoneuser.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        prenomuser.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                prenomuser.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        nomuser.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                nomuser.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        ageuser.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                ageuser.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public void setProfileValues() {

        if (Context.getUser() != null) {
            //Diplay after loading from Database
            Connexion connexion = new Connexion("Database/DB.db");
            connexion.connect();
            ResultSet resultSet = connexion.query("SELECT * FROM User");
            ArrayList<User> userArrayList = new ArrayList<>();
            try {
                while (resultSet.next()) {
                    if (resultSet.getInt("UserId") == Context.getUser().getUserId()) {////remplacer id
                        prenomuser.setText(resultSet.getString("FirstName"));
                        nomuser.setText(resultSet.getString("LastName"));

                        mailuser.setText(resultSet.getString("email"));
                        ageuser.setText(String.valueOf(resultSet.getInt("age")));
                        telephoneuser.setText(String.valueOf(resultSet.getInt("telephone")));
                        passworduser.setText(resultSet.getString("password"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connexion.close();
        }
    }


    @FXML
    void modifierProfile(ActionEvent event) {////remplacer id
        Connexion connexion = new Connexion("Database/DB.db");
        connexion.connect();
        if (!prenomuser.getText().trim().isEmpty() && !nomuser.getText().trim().isEmpty() && !mailuser.getText().trim().isEmpty() && !ageuser.getText().trim().isEmpty() && !telephoneuser.getText().trim().isEmpty() && passworduser.getText().length() >= 4) {
            String query = "UPDATE `User` SET `FirstName` = '" + prenomuser.getText() + "', `LastName` = '" + nomuser.getText() + "' , `email` = '" + mailuser.getText() + "' , `age` = '" + Integer.parseInt(ageuser.getText()) + "' , `telephone` = '" + Integer.parseInt(telephoneuser.getText()) + "', `password` = '" + passworduser.getText() + "'  WHERE `UserId` = " + Context.getUser().getUserId();
            connexion.submitQuery(query);
            connexion.close();
        }
    }


    @FXML
    void logout(ActionEvent event) {
        Context.setUser(null);
        mainApp.showHome();
    }


    public void setUserBox(){
        if (Context.getUser()!=null){
            userNameText.setText(Context.getUser().getFirstName());
            if (Context.getUser().isHost()){
                userIsTravelerText.setText("Hote");
            }
            else{
                userIsTravelerText.setText("Voyageur");
            }
            userBox.setVisible(true);
        }
        else {
            userBox.setVisible(false);
        }
    }
}



