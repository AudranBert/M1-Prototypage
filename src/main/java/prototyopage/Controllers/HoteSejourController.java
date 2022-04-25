package prototyopage.Controllers;

import DB.SejourDB.Sejour;
import DB.SejourDB.SejourDAO;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import prototyopage.Context;
import prototyopage.Controllers.ComponentControllers.SejourVignetteController;
import prototyopage.MainApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class HoteSejourController extends ControllerAbstract {
    private MainApp mainApp = null;
    private ArrayList<SejourVignetteController> vignettesControlers = new ArrayList();
    private ArrayList<Sejour> sejours = new ArrayList<>();

    // user
    @FXML private VBox userBox;
    @FXML private javafx.scene.text.Text userIsTravelerText;
    @FXML private javafx.scene.text.Text userNameText;

    @FXML private HBox sejourBox;

    @FXML
    private TextField searchBar;

    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void backToPredView(){
        mainApp.backView();
    }

    @Override
    public void init() {
        SejourDAO sejourDAO = new SejourDAO();

        this.sejourBox.getChildren().clear();

        sejours = sejourDAO.getSejoursByHostId(Context.getUser().getUserId());
        sejours.add(0, null);
        /*
        if (sejours.size() == 0) {
            sejours.add(null);
        }
        */

        adaptDisplayToContext();
    }


    @FXML
    private void search() {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Thread.sleep(100);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        sejourBox.getChildren().clear();
                    }
                });
                final String search = searchBar.getText();
                SejourDAO sejourDAO = new SejourDAO();

                ArrayList<Sejour> names = sejourDAO.searchSejourByFieldAndHost(Context.getUser().getUserId(), "Name", search);
                Set<Sejour> set = new LinkedHashSet<>(names);
                ArrayList<Sejour> loc = sejourDAO.searchSejourByFieldAndHost(Context.getUser().getUserId(), "Location", search);
                set.addAll(loc);
                ArrayList<Sejour> desc = sejourDAO.searchSejourByFieldAndHost(Context.getUser().getUserId(), "Description", search);
                set.addAll(desc);
                ArrayList<Sejour> dateBegin = sejourDAO.searchSejourByFieldAndHost(Context.getUser().getUserId(), "DateBegin", search);
                set.addAll(dateBegin);
                ArrayList<Sejour> dateEnd = sejourDAO.searchSejourByFieldAndHost(Context.getUser().getUserId(), "DateEnd", search);
                set.addAll(dateEnd);
                sejours = new ArrayList<>(set);
                if (sejours.size() == 0) {
                    sejours.add(null);
                }
                adaptDisplayToContext();
                return null;
            }
        };
        task.run();
    }

    @Override
    public void adaptDisplayToContext() {
        for (Sejour sejour : sejours) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(mainApp.getClass().getResource("SejourComponents/SejourVignette.fxml"));
                System.out.println(fxmlLoader);
                Pane sejourVignette = fxmlLoader.load();

                SejourVignetteController controller = fxmlLoader.getController();
                controller.setMainApp(this.mainApp);
                controller.setSejour(sejour);
                controller.init();
                vignettesControlers.add(controller);

                fxmlLoader.setController(controller);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        sejourBox.getChildren().add(sejourVignette);
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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
}
