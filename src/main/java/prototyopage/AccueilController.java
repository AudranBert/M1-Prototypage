package prototyopage;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
    protected void showSearchBar(){mainApp.showSearchBar();}
}