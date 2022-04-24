package prototyopage.Controllers;

import DB.Connexion;
import DB.DemSejDB.DemSej;
import DB.DemSejDB.DemSejDAO;
import DB.SejourDB.Sejour;
import DB.SejourDB.SejourDAO;
import DB.UserDB.User;
import DB.UserDB.UserDAO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import prototyopage.Chat;
import prototyopage.Context;
import prototyopage.MainApp;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DiscussionsController {
    private MainApp mainApp;

    // user
    @FXML
    private VBox userBox;
    @FXML
    private javafx.scene.text.Text userIsTravelerText;
    @FXML
    private javafx.scene.text.Text userNameText;

    //Discussions
    @FXML
    private VBox boxDiscussions;

    public void setUserBox(){
        if (Context.getUser()!=null){
            userNameText.setText(Context.getUser().getFirstName());
            if (Context.getUser().isHost()){
                userIsTravelerText.setText("Hote");
            }
            else{
                userIsTravelerText.setText("Voyageur");
            }
            userBox.setVisible(true);
        }
        else {
            userBox.setVisible(false);
        }
    }

    public void init() {
        //to do
        SejourDAO sejourDao = new SejourDAO();
        UserDAO userDao = new UserDAO();
        DemSejDAO demSejDAO = new DemSejDAO();
        boolean chatExist = false;
        for (Chat chat : mainApp.getChats())
        {
            if (chat.getIdUser1() == Context.getUser().getUserId() || chat.getIdUser2() == Context.getUser().getUserId())
            {
                chatExist = true;
                Button button = new Button();

                //Récupération du nom du séjour
                Sejour sejour = null;
                String sejourName = "";
                ArrayList<Sejour> list = sejourDao.getSejours();
                for(Sejour sejourFromList : list ) {
                    if (sejourFromList.getSejourId() == chat.getIdSejour())
                    {
                        sejour = sejourFromList;
                        sejourName = sejourFromList.getName();
                    }
                }

                //Récupération de l'id du séjour SOUS FORME FINAL
                final int idSejour;
                if (sejour != null) {
                    idSejour = sejour.getSejourId();
                }
                else
                {
                    idSejour = -1;
                }

                //Récupération de l'id de l'autre personne du chat
                int idOtherPerson;
                String otherPersonName = "";
                if (Context.getUser().getUserId() == chat.getIdUser1())
                {
                    idOtherPerson = chat.getIdUser2();
                }
                else
                {
                    idOtherPerson = chat.getIdUser1();
                }

                //Récupération du nom de l'autre personne du chat
                ArrayList<User> userList = userDao.getUsers();
                for(User user : userList) {
                    if (idOtherPerson == user.getUserId())
                    {
                        otherPersonName = user.getFirstName() + " " + user.getLastName();
                    }
                }

                String buttonText = sejourName + " - " + otherPersonName; //Texte du bouton

                //Récupération de la demande de voyage s'il y en a eu une
                DemSej demSej;
                if (Context.getUser().isHost())
                {
                    demSej = demSejDAO.getDemSejByVoyagerAndSejour(idOtherPerson, idSejour);
                }
                else
                {
                    demSej = demSejDAO.getDemSejByVoyagerAndSejour(Context.getUser().getUserId(), idSejour);
                }
                if (demSej != null)
                {
                    if (demSej.getValidation() == 0)
                    {
                        buttonText += " - En attente";
                    }
                    else if (demSej.getValidation() == 1)
                    {
                        buttonText += " - Validée";
                    }
                    else if (demSej.getValidation() == 2)
                    {
                        buttonText += " - Refusée";
                    }
                }


                if (idSejour != -1) {
                    button.setText(buttonText);
                    button.setId("" + chat.getIdChat());
                    button.setAlignment(Pos.BASELINE_CENTER);
                    button.setMaxWidth(400);
                    button.setFont(new Font(18));
                    button.setOnAction((event) -> {    // lambda expression
                        mainApp.showChat(Context.getUser().getUserId(), idOtherPerson, idSejour);
                    });
                    boxDiscussions.setAlignment(Pos.BASELINE_CENTER);
                    boxDiscussions.getChildren().add(button);
                }
            }
        }
        if (!chatExist)
        {
            Label label = new Label();
            label.setText("Vous n'avez aucune discussion pour le moment");
            label.setAlignment(Pos.BASELINE_CENTER);
            label.setMaxWidth(400);
            label.setFont(new Font(18));
            boxDiscussions.setAlignment(Pos.BASELINE_CENTER);
            boxDiscussions.getChildren().add(label);        }
    }

    @FXML
    private void backToHome(){
        this.mainApp.showHome();
    }

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }
}

