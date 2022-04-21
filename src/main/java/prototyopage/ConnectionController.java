package prototyopage;

import DB.UserDB.User;
import DB.UserDB.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

import static DB.UserDB.UserDbTest.fillDB;

public class ConnectionController {
    private MainApp mainApp;

    @FXML
    private Button closeButton;

    @FXML
    private Button connexionButton;

    @FXML
    private TextField mailField;

    @FXML
    private TextField passwordField;

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }

    @FXML
    protected void connect() {
        UserDAO dao = new UserDAO();

        ArrayList<User> list=dao.getUsers();
        for(User user : list ) {
            if (user.getEmail().equals(mailField.getText()) && user.getPassword().equals(passwordField.getText()))
            {
                System.out.println("Vous vous êtes bien connectés !");
                System.out.println("Bonjour " + user.getFirstName());
                mainApp.setUser(user);
                Stage stage = (Stage) closeButton.getScene().getWindow();
                stage.close();
            }
        }
    }

    @FXML
    protected void close(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}