package prototyopage.Controllers;

import DB.DemSejDB.DemSej;
import DB.DemSejDB.DemSejDAO;
import DB.SejourDB.Sejour;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import prototyopage.Context;
import prototyopage.MainApp;
import prototyopage.Ressources.SVGpaths;

import java.io.File;

public class VoyagerSejourDetailsControler extends ControllerAbstract {
    private MainApp mainApp;

    @FXML private Label sejourName;
    @FXML private Label locationi;
    @FXML private Text description;
    @FXML private Text dateBegin;
    @FXML private Text dateEnd;
    @FXML private Text statusText;
    @FXML private SVGPath statusLogo;
    @FXML private ImageView img1;
    @FXML private ImageView img2;
    @FXML private Button reservationActionButton;

    public void setMainApp(MainApp mainApp) { this.mainApp = mainApp; }

    public void init() {
        Sejour sejour = Context.getSejour();

        sejourName.setText(sejour.getName());
        locationi.setText(sejour.getLocation());
        description.setText(sejour.getDescription());
        dateBegin.setText(sejour.getStrDateBegin());
        dateEnd.setText(sejour.getStrDateEnd());

        File file = new File("src/main/resources/Images/Sejours/" + sejour.getImageBundle() + "/img_1.jpg");
        Image image = new Image(file.toURI().toString());
        img1.setImage(image);

        file = new File("src/main/resources/Images/Sejours/" + sejour.getImageBundle() + "/img_2.jpg");
        image = new Image(file.toURI().toString());
        img2.setImage(image);

        adaptDisplayToContext();
    }

    public void adaptDisplayToContext(){
        if (Context.getUser() != null) {
            DemSejDAO demSejDAO = new DemSejDAO();
            DemSej demSej = demSejDAO.getDemSejByVoyagerAndSejour(Context.getUser().getUserId(), Context.getSejour().getSejourId());
            Context.setDemSej(demSej);

            if (demSej == null) {  // Si il n'existe pas de reservation
                statusLogo.setVisible(false);
                statusText.setVisible(false);
            } else {  // Si il existe une reservation
                reservationActionButton.setText("Reservation");

                statusText.setText(demSej.validationToString());
                statusText.setVisible(true);

                statusLogo.setContent(demSej.getSVG().get(SVGpaths.PATH));
                statusLogo.setFill(Paint.valueOf(demSej.getSVG().get(SVGpaths.COLOR)));
            }
        } else {
            reservationActionButton.setVisible(false);
            statusLogo.setVisible(false);
            statusText.setVisible(false);
        }
    }

    @FXML
    private void backToHome(){
        this.mainApp.showHome();
    }

    @FXML
    private void openReservationModal() {
        mainApp.showReservationModal();
    }
}
