package prototyopage.Controllers.ComponentControllers;

import DB.SejourDB.Sejour;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import prototyopage.Context;
import prototyopage.Controllers.ControllerAbstract;
import prototyopage.Controllers.VoyageurSejourController;
import prototyopage.Controllers.VoyageurVoyageController;
import prototyopage.MainApp;
import prototyopage.Ressources.SVGpaths;

import java.io.File;
import java.util.concurrent.Callable;

public class SejourVignetteController extends ControllerAbstract {
    private MainApp mainApp;
    private Sejour sejour = null;

    @FXML private Text sejourName;
    @FXML private Text dateBegin;
    @FXML private Text dateEnd;
    @FXML private ImageView image;
    @FXML private Label locationText;
    @FXML private SVGPath locationIcon;
    @FXML private SVGPath removeFromVoyageBtn;
    @FXML private HBox locationBox;

    private VoyageurVoyageController superControler = null;
    private Pane vignette;

    public void setSejour(Sejour sejour) {
        this.sejour = sejour;
    }

    public void setSuperControler(VoyageurVoyageController controler) {
        this.superControler = controler;
    }

    public void setVignette(Pane sejourVignette) {
        vignette = sejourVignette;
    }

    @Override
    public void init() {
        if (sejour != null) {
            sejourName.setText(this.sejour.getName());

            dateBegin.setText(this.sejour.getStrDateBegin());
            dateEnd.setText(this.sejour.getStrDateEnd());

            locationText.setText(this.sejour.getLocation());
            locationIcon.setContent(SVGpaths.LOCATION_LOGO.get(SVGpaths.PATH));
            locationIcon.setFill(Paint.valueOf(SVGpaths.LOCATION_LOGO.get(SVGpaths.COLOR)));
            locationIcon.setScaleX(0.1);
            locationIcon.setScaleY(0.1);

            locationBox.getChildren().clear();
            Node locationIconNode = new Group(locationIcon);
            locationBox.getChildren().addAll(locationIconNode, locationText);
            locationBox.setMargin(locationIconNode, new Insets(0, 0, 0, 25));
            ;
            File file = new File("src/main/resources/Images/Sejours/" + sejour.getImageBundle() + "/img_1.jpg");
            image.setImage(new Image(file.toURI().toString()));

            adaptDisplayToContext();
        } else {
            sejourName.setText("Aucun séjour proposé");
            dateBegin.setText("");
            dateEnd.setText("");
            locationBox.getChildren().clear();

            File file = new File("src/main/resources/Images/Sejours/placeholder.png");
            image.setImage(new Image(file.toURI().toString()));
        }
    }

    @Override
    public void adaptDisplayToContext() {
    }

    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void openSejourDetail() {
        Context.setSejour(this.sejour);
        mainApp.showSejourDetails();
    }

    public void removeFromVoyage() {
        superControler.removeFromVoyage(vignette, sejour);
    }

    public void setClosebuttonVisible() {
        this.removeFromVoyageBtn.setVisible(true);
        this.vignette.setOnMouseClicked(t -> {});
    }
}
