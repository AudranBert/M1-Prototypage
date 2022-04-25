package prototyopage.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import prototyopage.Context;
import prototyopage.MainApp;

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

    @FXML
    private Button voirDemandes;

    @FXML
    private Button voirHoteSejours;

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

    @FXML
    protected void showHoteSejours() { mainApp.showHoteSejours(); }

    public void setUserBox(){
        if (Context.getUser()!=null){
            userNameText.setText(Context.getUser().getFirstName());
            if (Context.getUser().isHost()){
                userIsTravelerText.setText("Hote");
                voirDemandes.setVisible(true);
                voirHoteSejours.setVisible(true);
            }
            else{
                userIsTravelerText.setText("Voyageur");
                voirDemandes.setVisible(false);
                voirHoteSejours.setVisible(false);
            }
            userBox.setVisible(true);
            welcomeText.setText("Bonjour " + Context.getUser().getFirstName());
            welcomeText.setVisible(true);
            connexionButton.setVisible(false);

        }
        else {
            userBox.setVisible(false);
            welcomeText.setVisible(false);
            connexionButton.setVisible(true);
            voirDemandes.setVisible(false);
            voirHoteSejours.setVisible(false);
        }
    }
}

