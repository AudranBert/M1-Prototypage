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
    protected void showProfil() {
        mainApp.showProfil();

    }
}