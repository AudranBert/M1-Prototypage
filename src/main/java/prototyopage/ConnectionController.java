package prototyopage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ConnectionController {
    private MainApp mainApp;

    @FXML
    private Button connexionButton;

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }

    @FXML
    protected void connect() {
        //to do
    }

}