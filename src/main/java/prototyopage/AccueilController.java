package prototyopage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

import java.io.IOException;

public class AccueilController {
    private MainApp mainApp;

    // user
    @FXML
    private VBox userBox;
    @FXML
    private javafx.scene.text.Text userIsTravelerText;
    @FXML
    private javafx.scene.text.Text userNameText;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button profileButton;

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
        //hideProfileButton(); //cacher ou non le boutton en fonction du profil
    }

    @FXML
    private Label welcomeText;

    @FXML
    private Button connexionButton;

    @FXML
    protected void showConnection() {
        mainApp.showConnection();
    }

    @FXML
    protected void showSearchBar() {
        mainApp.showSearchBar();
    }

    @FXML
    protected void demSej() {
        mainApp.showDemSej();
    }

    @FXML
    protected void showProfil() {
        mainApp.showProfil();
    }


    public void setUserBox(){
        if (mainApp.getUser()!=null){
            userNameText.setText(mainApp.getUser().getFirstName());
            if (mainApp.getUser().isHost()){
                userIsTravelerText.setText("Hote");
            }
            else{
                userIsTravelerText.setText("Voyageur");
            }
            userBox.setVisible(true);
            welcomeText.setText("Bonjour " + mainApp.getUser().getFirstName());
            welcomeText.setVisible(true);
            connexionButton.setVisible(false);
        }
        else {
            userBox.setVisible(false);
            welcomeText.setVisible(false);
            connexionButton.setVisible(true);
        }
    }
}

