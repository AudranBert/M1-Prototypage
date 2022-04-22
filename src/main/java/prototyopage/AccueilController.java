package prototyopage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import java.io.IOException;

public class AccueilController {
    private MainApp mainApp;
    @FXML
    private AnchorPane rootPane;

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        if (mainApp.getUser() != null)
        {
            welcomeText.setText("Bonjour " + mainApp.getUser().getFirstName());
        }
        else
        {
            welcomeText.setText("Bonjour à vous !");
        }
    }

    @FXML
    protected void showConnection() {
        mainApp.showConnection();
    }

    @FXML
    protected void showChat() {
        mainApp.showChat();
    }

    @FXML
    protected void showSearchBar() {
//        try{
//            AnchorPane pane= FXMLLoader.load(getClass().getResource("Recherche.fxml"));
//            rootPane.getChildren().setAll(pane);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
}
