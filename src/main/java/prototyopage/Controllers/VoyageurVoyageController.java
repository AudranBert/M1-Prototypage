package prototyopage.Controllers;

import DB.DemSejDB.DemSejDAO;
import DB.SejourDB.Sejour;
import DB.SejourDB.SejourDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import prototyopage.Context;
import prototyopage.Controllers.ComponentControllers.SejourSmallVignetteController;
import prototyopage.Controllers.ComponentControllers.SejourVignetteController;
import prototyopage.MainApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class VoyageurVoyageController extends ControllerAbstract {
    private MainApp mainApp = null;
    private ArrayList<Sejour> sejours = new ArrayList<>();
    private ArrayList<Sejour> voyage = new ArrayList<>();

    @FXML private VBox sejourBox;
    @FXML private HBox voyageBox;

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
        adaptDisplayToContext();
    }

    @Override
    public void adaptDisplayToContext() {
        SejourDAO sejourDAO = new SejourDAO();
        sejours = sejourDAO.getSejoursfromDemSejByVoyageurId(Context.getUser().getUserId());
        if (sejours.size() == 0) {
            sejours.add(null);
        }
        voyage = sejourDAO.getVoyageFromDemSejByVoyagerId(Context.getUser().getUserId());

        sortVoyageByDate();
        this.voyageBox.getChildren().clear();
        for (Sejour voyageStep : voyage) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(mainApp.getClass().getResource("SejourComponents/SejourVignette.fxml"));
                Pane voyageVignette = fxmlLoader.load();

                SejourVignetteController controller = fxmlLoader.getController();
                controller.setMainApp(this.mainApp);
                controller.setSejour(voyageStep);
                controller.setSuperControler(this);
                controller.setVignette(voyageVignette);
                controller.setClosebuttonVisible();
                controller.init();

                fxmlLoader.setController(controller);

                this.voyageBox.getChildren().add(voyageVignette);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.sejourBox.getChildren().clear();
        for (Sejour sejour : sejours) {
            System.out.println("Reviewing sejour");
            System.out.println(sejour);
            if (!voyageContient(sejour)) {
                System.out.println("Adding the sejour to Sejours");

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(mainApp.getClass().getResource("SejourComponents/SejourSmallVignette.fxml"));
                    Pane sejourVignette = fxmlLoader.load();

                    SejourSmallVignetteController controller = fxmlLoader.getController();
                    controller.setMainApp(this.mainApp);
                    controller.setSejour(sejour);
                    controller.setSuperControler(this);
                    controller.setVignetteAction("superControllerAddSejourToVoyage");
                    controller.setVignette(sejourVignette);
                    controller.init();

                    fxmlLoader.setController(controller);

                    this.sejourBox.getChildren().add(sejourVignette);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean voyageContient(Sejour sejour) {
        for (Sejour voyageStep : voyage) {
            if (voyageStep.getSejourId() == sejour.getSejourId()) {
                return true;
            }
        }
        return false;
    }

    class sejourDateComparator implements Comparator<Sejour> {
        public int compare(Sejour s1, Sejour s2)
        {
            if (s1.getDateBegin().equals(s2.getDateBegin()))
                return 0;
            else if (s1.getDateBegin().after(s2))
                return 1;
            else
                return -1;
        }
    }

    private void sortVoyageByDate() {
        voyage.sort(new sejourDateComparator());
    }


    public void addSejourToVoyage(Pane vignette, Sejour sejour) {
        DemSejDAO demSejDAO = new DemSejDAO();
        demSejDAO.markDemSejAsPartOfTrip(Context.getUser().getUserId(), sejour.getSejourId());

        this.sejourBox.getChildren().remove(vignette);
        this.voyage.add(sejour);
        this.adaptDisplayToContext();
    }

    public void removeFromVoyage(Pane vignette, Sejour sejour) {
        DemSejDAO demSejDAO = new DemSejDAO();
        demSejDAO.unmarkDemSejAsPartOfTrip(Context.getUser().getUserId(), sejour.getSejourId());
        this.voyageBox.getChildren().remove(vignette);
        this.sejours.add(sejour);

        this.adaptDisplayToContext();
    }

}
