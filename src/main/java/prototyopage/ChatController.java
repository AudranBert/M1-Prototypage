package prototyopage;

import DB.UserDB.User;
import DB.UserDB.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChatController {
    private MainApp mainApp;

    private int nbrMessages = 7;

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

    @FXML
    private VBox leftVbox;

    @FXML
    private VBox rightVbox;

    public void setMainApp(MainApp mainApp) { this.mainApp = mainApp; }

    public void initializeValues() {
        //Toutes ces valeurs sont fausses, il faut récupérer celles de l'hôte !
        firstNameProfile.setText("fakeFirstName");
        nameProfile.setText("fakeName");
        descriptionProfile.setText("blblblblblbllblblblbllbblblbllblblblbbll blblblbblblblbllblblbbllblblblblblblblblblbllblblblblblblllbblb");
        mailProfile.setText("fakeMail");
        nbrVoyageurs.setText("3 voyageurs");
        nbrLits.setText("2 lits");


        //Valeurs du chat :
        int numSejour = 60;
        int idHost = 22;
        int idTraveler = 21;
        int idUser = 21;
        if (mainApp.getChat().get(numSejour) != null)
        {
            for (Message msg : mainApp.getChat().get(numSejour))
            {
                if ((msg.getSender() == idHost && msg.getReceiver() == idTraveler) || msg.getSender() == idTraveler && msg.getReceiver() == idHost)
                {
                    if (msg.getSender() == idUser)
                    {
                        ((Label) rightVbox.getChildren().get(msg.getNumMessage())).setText(msg.getContent());
                    }
                    else
                    {
                        ((Label) leftVbox.getChildren().get(msg.getNumMessage())).setText(msg.getContent());
                    }
                }
            }
        }
    }

    @FXML
    protected void sendMessage(){
        //to do
        for (int i = 0; i < nbrMessages - 1; i++)
        {
            ((Label) leftVbox.getChildren().get(i)).setText(((Label) leftVbox.getChildren().get(i + 1)).getText());
            ((Label) rightVbox.getChildren().get(i)).setText(((Label) rightVbox.getChildren().get(i + 1)).getText());
        }
        String messageContent = messageField.getText();
        ((Label) rightVbox.getChildren().get(nbrMessages - 1)).setText(messageContent);
        ((Label) leftVbox.getChildren().get(nbrMessages - 1)).setText("");
        int sender = 21;
        int receiver = 22;
        int numSejour = 60;

        if (mainApp.getChat().get(numSejour) != null)
        {
            for (Message msg : mainApp.getChat().get(numSejour))
            {
                if ((msg.getSender() == sender && msg.getReceiver() == receiver) || msg.getSender() == receiver && msg.getReceiver() == sender)
                {
                    if (msg.getNumMessage() > 0)
                    {
                        msg.setNumMessage(msg.getNumMessage() - 1);
                    }
                    else if (msg.getNumMessage() == 0)
                    {
                        mainApp.getChat().get(numSejour).remove(msg);
                    }
                }
            }
        }

        Message newMessage = new Message(sender, receiver, messageContent, 6);
        if (mainApp.getChat().containsKey(numSejour))
        {
            mainApp.getChat().get(numSejour).add(newMessage);
        }
        else
        {
            ArrayList<Message> newMessageList = new ArrayList<Message>();
            newMessageList.add(newMessage);
            mainApp.getChat().put(numSejour, newMessageList);
        }
    }

    @FXML
    protected void close(){ mainApp.showHome(); }
}