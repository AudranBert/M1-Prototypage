package prototyopage;

import DB.SejourDB.Sejour;
import DB.UserDB.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainApp extends Application {
    Stage stage;
    private User user = null;
    private HashMap<Integer, ArrayList<Message>> chat = new HashMap<Integer, ArrayList<Message>>();
    Sejour sejour;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage=stage;
        showHome();
    }

    public void showConnection() {
        try {
            //Charger le fichier fxml associé
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ConnectionPage.fxml")); //On charge la vue souhaitée
            Stage connectionStage = loader.load();

            //On charge le controlleur associé a la vue
            ConnectionController controller = loader.getController();
            controller.setMainApp(this);

            connectionStage.show(); //Affichage de la fenêtre
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showProfil()  {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("ProfilePage.fxml"));

            Scene scene = null;
            scene = new Scene(fxmlLoader.load(), 1000, 600);

            ControllerProfile controller = fxmlLoader.getController();
            controller.setMainApp(this);
            controller.setProfileValues();

            scene.getStylesheets().add("Style.css");
            stage.setTitle("Profil");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showSearchBar()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("Recherche.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

            scene.getStylesheets().add("Style.css");
            RechercheController controller = fxmlLoader.getController();
            controller.setMainApp(this);
            controller.setUserBox();
            stage.setTitle("Adeona");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showChat(int idSender, int idReceiver, int numSejour)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("ChatPage.fxml"));

            Scene scene = new Scene(fxmlLoader.load());

            //scene.getStylesheets().add("Style.css");
            ChatController controller = fxmlLoader.getController();
            controller.setMainApp(this);
            controller.initializeValues(idSender, idReceiver, numSejour);
            stage.setTitle("Chat");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showHome(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("Accueil.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            scene.getStylesheets().add("Style.css");
            AccueilController controller = fxmlLoader.getController();
            controller.setMainApp(this);
            controller.setUserBox();
            stage.setTitle("Accueil");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void showDemSej()  {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("DemanSej.fxml"));

            Scene scene = null;
            scene = new Scene(fxmlLoader.load(), 1000, 600);

            ControllerDemanSej controller = fxmlLoader.getController();
            controller.setMainApp(this);

            scene.getStylesheets().add("Style.css");
            stage.setTitle("Demande Sejour");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser()
    {
        return this.user;
    }

    public HashMap<Integer, ArrayList<Message>> getChat() { return this.chat; }
}
