package com.example.m1prototyopage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("hello-view.fxml"));
        fxmlLoader.setLocation(MainApp.class.getResource("hello-view.fxml")); //On charge la vue souhaitée
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        //On charge le controlleur associé a la vue
        HelloController controller = fxmlLoader.getController();
        controller.setMainApp(this);

        this.stage = stage;
        this.stage.setTitle("Hello!");
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

    public static void main(String[] args) {
        launch();
    }
}