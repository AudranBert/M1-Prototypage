package prototyopage;

import DB.UserDB.User;
import DB.UserDB.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ChatController {
    private MainApp mainApp;

    @FXML
    private Button closeButton;

    @FXML
    private Label firstNameProfile;

    @FXML
    private Label nameProfile;

    @FXML
    private Label descriptionProfile;

    @FXML
    private Label mailProfile;

    @FXML
    private Label nbrVoyageurs;

    @FXML
    private Label nbrLits;

    @FXML
    private TextArea messageField;

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }

    public void initializeValues() {
        //Toutes ces valeurs sont fausses, il faut récupérer celles de l'hôte !
        firstNameProfile.setText("fakeFirstName");
        nameProfile.setText("fakeName");
        descriptionProfile.setText("blblblblblbllblblblbllbblblbllblblblbbll blblblbblblblbllblblbbllblblblblblblblblblbllblblblblblblllbblb");
        mailProfile.setText("fakeMail");
        nbrVoyageurs.setText("3 voyageurs");
        nbrLits.setText("2 lits");
    }

    @FXML
    protected void sendMessage(){
        //to do
    }

    @FXML
    protected void close(){ mainApp.showHome(); }
}