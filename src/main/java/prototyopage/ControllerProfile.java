package prototyopage;

import DB.Connexion;
import DB.UserDB.User;
import javafx.fxml.FXML;


import java.sql.*;
import java.util.ArrayList;

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


    //Diplay after loading from Database

            Connexion connexion = new Connexion("Database/User.db");
            connexion.connect();
            ResultSet resultSet = connexion.query("SELECT * FROM User");
            ArrayList<User> userArrayList=new ArrayList<>();
            try {
                while (resultSet.next()) {
                    if(resultSet.getInt("UserId")==9){////remplacer id
                        prenomuser.setText(resultSet.getString("FirstName"));
                        nomuser.setText(resultSet.getString("LastName" ));

                        mailuser.setText(resultSet.getString("email"));
                        ageuser.setText(String.valueOf(resultSet.getInt("age")));
                         telephoneuser.setText(String.valueOf(resultSet.getInt("telephone")));


                    }


                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connexion.close();




    }




    @FXML
    void modifierProfile(ActionEvent event) {////remplacer id
        Connexion connexion = new Connexion("Database/User.db");
        connexion.connect();
        if (!prenomuser.getText().trim().isEmpty() && !nomuser.getText().trim().isEmpty() && !mailuser.getText().trim().isEmpty() && !ageuser.getText().trim().isEmpty() && !telephoneuser.getText().trim().isEmpty()) {
            String query = "UPDATE `User` SET `FirstName` = '" + prenomuser.getText() + "', `LastName` = '" + nomuser.getText() + "' , `email` = '" + mailuser.getText() + "' , `age` = '" + Integer. parseInt(ageuser.getText()) + "' , `telephone` = '" + Integer. parseInt(telephoneuser.getText()) + "'  WHERE `UserId` = " + 9;
            connexion.submitQuery(query);
            connexion.close();
        }

    }





    @FXML
    void logout(ActionEvent event) {
        Stage stage = (Stage) logoutuser.getScene().getWindow();
        stage.close();
    }
}



