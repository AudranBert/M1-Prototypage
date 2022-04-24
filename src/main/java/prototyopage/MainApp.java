package prototyopage;

import DB.SejourDB.Sejour;
import DB.SejourDB.SejourDAO;
import DB.UserDB.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import prototyopage.Controllers.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Stack;

public class MainApp extends Application {
    Stage stage;

    private Stack<Runnable> viewHistory = new Stack<Runnable>();

    @Override
    public void start(Stage stage) throws IOException {
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
            controller.init();
            controller.setUserBox();
            stage.setTitle("Adeona");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showChat()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("ChatPage.fxml"));

            Scene scene = new Scene(fxmlLoader.load());

            //scene.getStylesheets().add("Style.css");
            ChatController controller = fxmlLoader.getController();
            controller.setMainApp(this);
            controller.initializeValues();
            stage.setTitle("Chat");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showVoyagerSejourDetails()  {
        try {
            viewHistory.push(this::showVoyagerSejourDetails);
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("VoyagerSejourDetails.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

            VoyagerSejourDetailsControler controller = fxmlLoader.getController();
            controller.setMainApp(this);
            //controller.setCurrentSejour(this.sejour);

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
            viewHistory.push(this::showVoyagerSejourDetails);
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

            scene.getStylesheets().add("Style.css");
            stage.setTitle("Demande Sejour");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) { launch(); }

}
