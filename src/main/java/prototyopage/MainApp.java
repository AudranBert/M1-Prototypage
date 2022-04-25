package prototyopage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import prototyopage.Controllers.*;

import java.io.IOException;
import java.util.Stack;
import java.util.ArrayList;

public class MainApp extends Application {
    Stage stage;
    private ArrayList<Chat> chats = new ArrayList<>();
    private Stack<Runnable> viewHistory = new Stack<>();

    public Stage getStage() { return this.stage; }

    @Override
    public void start(Stage stage) {
        this.stage=stage;
        showHome();
    }

    public void backView() {
        viewHistory.pop();
        Runnable lastView = viewHistory.pop();
        lastView.run();
    }

    public void showConnection() {
        try {
            viewHistory.push(this::showConnection);
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
            viewHistory.push(this::showProfil);
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("ProfilePage.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);

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

    public void showSearchBar() {
        try {
            viewHistory.push(this::showSearchBar);
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("Recherche.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

            scene.getStylesheets().add("Style.css");
            RechercheController controller = fxmlLoader.getController();
            controller.setMainApp(this);
            controller.init();
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

            scene.getStylesheets().add("Style.css");
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

    public void showSejourDetails()  {
        try {
            viewHistory.push(this::showSejourDetails);
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("SejourDetails.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

            SejourDetailsControler controller = fxmlLoader.getController();
            controller.setMainApp(this);

            controller.init();
            fxmlLoader.setController(controller);

            scene.getStylesheets().add("Style.css");
            stage.setTitle("Détail séjour");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showReservationModal() {
        try {
            viewHistory.push(this::showReservationModal);
            //Charger le fichier fxml associé
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MainApp.class.getResource("ReservationModal.fxml")); //On charge la vue souhaitée
            Stage connectionStage = fxmlLoader.load();

            //On charge le controlleur associé a la vue
            ReservationModalController controller = fxmlLoader.getController();
            controller.setMainApp(this);
            controller.init();

            fxmlLoader.setController(controller);

            connectionStage.show(); //Affichage de la fenêtre
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showHoteSejours() {
        try {
            viewHistory.push(this::showHoteSejours);

            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("HoteSejours.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

            HoteSejourController controller = fxmlLoader.getController();
            controller.setMainApp(this);
            controller.init();

            fxmlLoader.setController(controller);

            scene.getStylesheets().add("Style.css");
            stage.setTitle("Détail séjour");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showVoyageurSejours() {
        try {
            viewHistory.push(this::showVoyageurSejours);

            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("VoyageurSejour.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

            VoyageurSejourController controller = fxmlLoader.getController();
            controller.setMainApp(this);
            controller.init();

            fxmlLoader.setController(controller);

            scene.getStylesheets().add("Style.css");
            stage.setTitle("Séjours du voyageur");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showVoyageurVoyage() {
        try {
            viewHistory.push(this::showVoyageurVoyage);

            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("VoyageurVoyage.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

            VoyageurVoyageController controller = fxmlLoader.getController();
            controller.setMainApp(this);
            controller.init();

            fxmlLoader.setController(controller);

            scene.getStylesheets().add("Style.css");
            stage.setTitle("Adeona");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showHome(){
        try {
            viewHistory.push(this::showHome);
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

            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);

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

    public void showDiscussions()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("DiscussionsPage.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

            scene.getStylesheets().add("Style.css");
            DiscussionsController controller = fxmlLoader.getController();
            controller.setMainApp(this);
            controller.init();
            controller.setUserBox();
            stage.setTitle("Adeona");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) { launch(); }

    public ArrayList<Chat> getChats() { return this.chats; }
}
