package prototyopage;

import DB.UserDB.User;
import DB.UserDB.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;

import static DB.UserDB.UserDbTest.fillDB;

public class ConnectionController {
    private MainApp mainApp;

    @FXML
    private Button connexionButton;

    @FXML
    private TextField nameField;

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
            if (user.getEmail().equals(nameField.getText()) && user.getPassword().equals(passwordField.getText()))
            {
                System.out.println("Vous vous êtes bien connectés !");
            }
        }
    }
}