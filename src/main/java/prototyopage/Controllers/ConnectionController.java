package prototyopage.Controllers;

import DB.UserDB.User;
import DB.UserDB.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import prototyopage.Context;
import prototyopage.MainApp;

import java.util.ArrayList;

public class ConnectionController {
    private MainApp mainApp;

    @FXML
    private Button closeButton;

    @FXML
    private Button connexionButton;

    @FXML
    private Label wrongMailLabel;

    @FXML
    private Label wrongPasswordLabel;

    @FXML
    private TextField mailField;

    @FXML
    private PasswordField passwordField;

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }

    @FXML
    protected void connect() {
        UserDAO dao = new UserDAO();

        ArrayList<User> list=dao.getUsers();
        boolean mailFound = false;
        for(User user : list ) {
            if (user.getEmail().equals(mailField.getText()))
            {
                mailFound = true;
                if (user.getPassword().equals(passwordField.getText()))
                {
                    System.out.println("Vous vous êtes bien connectés !");
                    System.out.println("Bonjour " + user.getFirstName());
                    Context.setUser(user);
                    Stage stage = (Stage) closeButton.getScene().getWindow();
                    stage.close();
                    mainApp.showHome();
                }
                else
                {
                    wrongMailLabel.setText("");
                    wrongPasswordLabel.setText("Mot de passe invalide");
                }
            }
        }
        if (!mailFound)
        {
            wrongMailLabel.setText("Email inexistant");
            wrongPasswordLabel.setText("");
        }
    }

    @FXML
    protected void close(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        mainApp.showHome();
    }

}