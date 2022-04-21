package prototyopage;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    Stage stage;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("Accueil.fxml"));
        fxmlLoader.setLocation(MainApp.class.getResource("Accueil.fxml")); //On charge la vue souhaitée
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

        //On charge le controlleur associé a la vue
        AccueilController controller = fxmlLoader.getController();
        controller.setMainApp(this);

        this.stage = stage;
        this.stage.setTitle("Accueil");
        this.stage.setScene(scene);
        this.stage.show();
    }



    public void showConnection()
    {
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

    public void showSearchBar()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("Recherche.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

            scene.getStylesheets().add("Style.css");
            RechercheController controller = fxmlLoader.getController();
            controller.setMainApp(this);
            stage.setTitle("Recherche");
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
            stage.setTitle("Accueil");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}