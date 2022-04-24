package prototyopage.Controllers;

import DB.DemSejDB.DemSej;
import DB.DemSejDB.DemSejDAO;
import DB.SejourDB.Sejour;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import prototyopage.Context;
import prototyopage.MainApp;

import java.sql.SQLException;
import java.text.ParseException;

public class ReservationModalController extends ControllerAbstract {
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) { this.mainApp = mainApp; }

    @FXML private Label sejourName;
    @FXML private Label locationi;
    @FXML private Label dateBegin;
    @FXML private Label dateEnd;

    @FXML private Button cancelActionButton;
    @FXML private Button validateActionButton;

    public void init() {
        Sejour sejour = Context.getSejour();
        sejourName.setText(sejour.getName());
        locationi.setText(sejour.getLocation());
        dateBegin.setText(sejour.getStrDateBegin());
        dateEnd.setText(sejour.getStrDateEnd());
        adaptDisplayToContext();
    }

    @Override
    public void adaptDisplayToContext() {
        if (Context.getDemSej() != null) {
            cancelActionButton.setText("Retour");
            cancelActionButton.setOnAction(t -> cancel());
            validateActionButton.setText("Supprimer");
            validateActionButton.setOnAction(t -> deleteReservation());
        } else {
            cancelActionButton.setText("Annuler");
            cancelActionButton.setOnAction(t -> cancel());
            validateActionButton.setText("Réserver");
            validateActionButton.setOnAction(t -> addReservation());
        }
    }

    @FXML
    protected void cancel() {
        Stage stage = (Stage) cancelActionButton.getScene().getWindow();
        stage.close();
        mainApp.backView();
    }

    @FXML
    private void addReservation() {
        int voyagerId = Context.getUser().getUserId();
        DemSejDAO demSejDAO = new DemSejDAO();
        demSejDAO.addDemSej(new DemSej(voyagerId, Context.getSejour().getSejourId()));
        Stage stage = (Stage) cancelActionButton.getScene().getWindow();
        stage.close();
        mainApp.backView();
    }

    @FXML
    private void deleteReservation() {
        DemSejDAO demSejDAO = new DemSejDAO();
        demSejDAO.deleteDemSej(Context.getDemSej());
        Stage stage = (Stage) cancelActionButton.getScene().getWindow();
        stage.close();
        mainApp.backView();
    }
}
