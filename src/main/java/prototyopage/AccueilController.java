package prototyopage;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class AccueilController {
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void showConnection() {
        mainApp.showConnection();
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