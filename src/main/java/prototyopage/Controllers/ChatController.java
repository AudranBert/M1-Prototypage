package prototyopage.Controllers;

import DB.Connexion;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import prototyopage.Chat;
import prototyopage.MainApp;
import prototyopage.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class ChatController{
    private MainApp mainApp;

    private int nbrMessages = 7;

    private int idReceiver;

    private int idSender;

    private int numSejour;

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
    private TextArea messageField;

    @FXML
    private VBox leftVbox;

    @FXML
    private VBox rightVbox;

    public void setMainApp(MainApp mainApp) { this.mainApp = mainApp; }

    public void initializeValues(int idSenderCopy, int idReceiverCopy, int numSejourCopy) {
        //Valeurs de la page
        idSender = idSenderCopy;
        idReceiver = idReceiverCopy;
        numSejour = numSejourCopy;
        System.out.println("sender : " + idSenderCopy);
        System.out.println("receiver : " + idReceiverCopy);
        System.out.println("numsejour : " + numSejourCopy);

        //Valeurs du destinataire
        Connexion connexion = new Connexion("Database/DB.db");
        connexion.connect();
        ResultSet resultReq = connexion.query("SELECT * FROM User where UserId = '" + idReceiverCopy + "';"); //Récupération des données du destinataire
        try {
            firstNameProfile.setText(resultReq.getString("FirstName"));
            nameProfile.setText(resultReq.getString("LastName"));
            descriptionProfile.setText(resultReq.getString("description"));
            mailProfile.setText(resultReq.getString("email"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connexion.close();

        if (mainApp.getChats() != null) {
            for (Chat chat : mainApp.getChats()) {
                //Si les envoyeurs et destinataires sont bien ceux de ce chat et de ce sejour
                if (((chat.getIdUser1() == idSender && chat.getIdUser2() == idReceiver) || (chat.getIdUser1() == idReceiver && chat.getIdUser2() == idSender)) && chat.getIdSejour() == numSejour)
                {
                    for (Message msg : chat.getMessages()) {
                        //On place les messages du chat
                        if ((msg.getSender() == idSender && msg.getReceiver() == idReceiver)) {
                            ((Label) rightVbox.getChildren().get(msg.getNumMessage())).setText(msg.getContent());
                        } else if (msg.getSender() == idReceiver && msg.getReceiver() == idSender) {
                            ((Label) leftVbox.getChildren().get(msg.getNumMessage())).setText(msg.getContent());
                        }
                    }
                    break;
                }
            }
        }
    }

    @FXML
    protected void sendMessage() {
        //Mise à jour graphique
        for (int i = 0; i < nbrMessages - 1; i++) {
            ((Label) leftVbox.getChildren().get(i)).setText(((Label) leftVbox.getChildren().get(i + 1)).getText());
            ((Label) rightVbox.getChildren().get(i)).setText(((Label) rightVbox.getChildren().get(i + 1)).getText());
        }
        String messageContent = messageField.getText();
        ((Label) rightVbox.getChildren().get(nbrMessages - 1)).setText(messageContent);
        ((Label) leftVbox.getChildren().get(nbrMessages - 1)).setText("");

        //Mise à jour des données
        if (mainApp.getChats() != null) {
            for (Chat chat : mainApp.getChats()) {
                if (((chat.getIdUser1() == idSender && chat.getIdUser2() == idReceiver) || (chat.getIdUser1() == idReceiver && chat.getIdUser2() == idSender)) && chat.getIdSejour() == numSejour)
                {
                    Iterator<Message> chatCopy = chat.getMessages().iterator(); //Iterator copie du bon chat permettant de boucler et en même temps supprimer des objets
                    while (chatCopy.hasNext()) {
                        Message msg = chatCopy.next();
                        if ((msg.getSender() == idSender && msg.getReceiver() == idReceiver) || msg.getSender() == idReceiver && msg.getReceiver() == idSender) { //Vérification que le destinataire et l'envoyeur sont dans ce chat
                            if (msg.getNumMessage() > 0) { //Si le message n'est pas tout en haut (numéro 0)
                                msg.setNumMessage(msg.getNumMessage() - 1); //On décalle le numéro du message de 1
                            } else if (msg.getNumMessage() == 0) { //Si le message se situe tout en haut
                                chatCopy.remove(); //On le supprime de notre chat
                            }
                        }
                    }
                    break;
                }
            }
        }

        //Création du nouveau message
        Message newMessage = new Message(idSender, idReceiver, messageContent, 6);
        boolean added = false;
        for (Chat chat : mainApp.getChats()) {
            //Si les envoyeurs et destinataires sont bien ceux de ce chat et le sejour est le bon
            if (((chat.getIdUser1() == idSender && chat.getIdUser2() == idReceiver) || (chat.getIdUser1() == idReceiver && chat.getIdUser2() == idSender)) && chat.getIdSejour() == numSejour) {
                chat.getMessages().add(newMessage);
                added = true;
                break;
            }
        }
        if (!added)
        {
            Chat chat = new Chat(idReceiver, idSender, numSejour);
            chat.getMessages().add(newMessage);
            mainApp.getChats().add(chat);
        }

        messageField.clear();
    }

    @FXML
    protected void close() {
        mainApp.showHome();
    }

    @FXML
    protected void onKeyEvent(KeyEvent event){
        if (event.getCode() == KeyCode.ENTER) {
            this.sendMessage();
        }
    }
}