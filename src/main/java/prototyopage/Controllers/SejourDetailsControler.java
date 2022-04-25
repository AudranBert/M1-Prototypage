package prototyopage.Controllers;

import DB.DemSejDB.DemSej;
import DB.DemSejDB.DemSejDAO;
import DB.SejourDB.Sejour;
import DB.SejourDB.SejourDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import prototyopage.Context;
import prototyopage.MainApp;
import prototyopage.Ressources.SVGpaths;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SejourDetailsControler extends ControllerAbstract {
    private MainApp mainApp;

    // user
    @FXML private VBox userBox;
    @FXML private javafx.scene.text.Text userIsTravelerText;
    @FXML private javafx.scene.text.Text userNameText;
    @FXML private Button connexionButton;

    @FXML private Label sejourName;
    @FXML private Label locationi;
    @FXML private Text description;
    @FXML private Text dateBegin;
    @FXML private Text dateEnd;
    @FXML private Text statusText;
    @FXML private SVGPath locationIcon;
    @FXML private SVGPath statusIcon;
    @FXML private SVGPath dateBeginIcon;
    @FXML private SVGPath dateEndIcon;
    @FXML private ImageView img1;
    @FXML private ImageView img2;
    @FXML private Button modifierImg1Button;
    @FXML private Button modifierImg2Button;
    @FXML private Button actionButton;
    @FXML private Button chatButton;
    @FXML private Group sejourNameGroup;
    @FXML private Group locationGroup;
    @FXML private Group descriptionGroup;
    @FXML private Group dateBeginGroup;
    @FXML private Group dateEndGroup;

    private TextField sejourNameField;
    private TextField locationField;
    private TextArea descriptionField;
    private TextField dateBeginField;
    private TextField dateEndField;

    private Label errorSejourName;
    private Label errorLocation;
    private Label errorDescription;
    private VBox sejourVboxError;

    public static final String VIEW_VOYAGER = "view_voyager";
    public static final String VIEW_HOST = "view_host";
    public static final String VIEW_DISCONNECTED = "view_disconnected";

    public void setMainApp(MainApp mainApp) { this.mainApp = mainApp; }

    @Override
    public void init() {
        modifierImg1Button.setVisible(false);
        modifierImg2Button.setVisible(false);

        sejourNameGroup.getChildren().clear();
        sejourNameGroup.getChildren().add(sejourName);

        locationGroup.getChildren().clear();
        locationGroup.getChildren().add(locationi);

        descriptionGroup.getChildren().clear();
        descriptionGroup.getChildren().add(description);

        dateBeginGroup.getChildren().clear();
        dateBeginGroup.getChildren().add(dateBegin);

        dateEndGroup.getChildren().clear();
        dateEndGroup.getChildren().add(dateEnd);

        Sejour sejour = Context.getSejour();

        sejourName.setText(sejour.getName());
        locationi.setText(sejour.getLocation());
        description.setText(sejour.getDescription());
        dateBegin.setText(sejour.getStrDateBegin());
        dateEnd.setText(sejour.getStrDateEnd());

        locationIcon.setContent(SVGpaths.LOCATION_LOGO.get(SVGpaths.PATH));
        locationIcon.setFill(Paint.valueOf(SVGpaths.LOCATION_LOGO.get(SVGpaths.COLOR)));
        locationIcon.setScaleX(0.1);
        locationIcon.setScaleY(0.1);

        dateBeginIcon.setContent(SVGpaths.DATE_LOGO.get(SVGpaths.PATH));
        dateBeginIcon.setFill(Paint.valueOf(SVGpaths.DATE_LOGO.get(SVGpaths.COLOR)));
        dateBeginIcon.setScaleX(0.3);
        dateBeginIcon.setScaleY(0.3);

        dateEndIcon.setContent(SVGpaths.DATE_LOGO.get(SVGpaths.PATH));
        dateEndIcon.setFill(Paint.valueOf(SVGpaths.DATE_LOGO.get(SVGpaths.COLOR)));
        dateEndIcon.setScaleX(0.3);
        dateEndIcon.setScaleY(0.3);

        File file = new File("src/main/resources/Images/Sejours/" + sejour.getImageBundle() + "/img_1.jpg");
        Image image = new Image(file.toURI().toString());
        img1.setImage(image);

        file = new File("src/main/resources/Images/Sejours/" + sejour.getImageBundle() + "/img_2.jpg");
        image = new Image(file.toURI().toString());
        img2.setImage(image);

        adaptDisplayToContext();
    }

    public void adaptDisplayToContext(){
        // si connecté comme Voyageur ou si un hote visite le séjour d'un tiers
        String viewType;
        if (Context.getUser() != null) {
            if (!Context.getUser().isHost()) {
                viewType = VIEW_VOYAGER;
            } else {
                if (Context.getSejour().getIdHost() == Context.getUser().getUserId()) {
                    viewType = VIEW_HOST;
                } else {
                    viewType = VIEW_VOYAGER;
                }
            }
        } else {
            viewType = VIEW_DISCONNECTED;
        }

        switch (viewType) {
            case VIEW_VOYAGER:
                DemSejDAO demSejDAO = new DemSejDAO();
                DemSej demSej = demSejDAO.getDemSejByVoyagerAndSejour(Context.getUser().getUserId(), Context.getSejour().getSejourId());
                Context.setDemSej(demSej);
                chatButton.setVisible(true);
                if (demSej == null) {  // Si il n'existe pas de reservation
                    statusIcon.setVisible(false);
                    statusText.setVisible(false);
                } else {  // Si il existe une reservation
                    actionButton.setText("Reservation");

                    statusText.setText(demSej.validationToString());
                    statusText.setVisible(true);

                    statusIcon.setContent(demSej.getSVG().get(SVGpaths.PATH));
                    statusIcon.setFill(Paint.valueOf(demSej.getSVG().get(SVGpaths.COLOR)));
                    statusIcon.setScaleX(0.1);
                    statusIcon.setScaleY(0.1);
                }
                break;

            case VIEW_HOST :
                actionButton.setText("Éditer");
                actionButton.setOnAction(t -> modifierSejour());
                actionButton.setVisible(true);
                chatButton.setVisible(false);
                statusIcon.setVisible(false);
                statusText.setVisible(false);
                break;

            case VIEW_DISCONNECTED:
                actionButton.setVisible(false);
                chatButton.setVisible(false);
                statusIcon.setVisible(false);
                statusText.setVisible(false);
                break;
        }
    }

    @FXML
    private void backToPredView(){
        mainApp.backView();
    }

    @FXML
    private void openReservationModal() {
        mainApp.showReservationModal();
    }

    @FXML
    protected void showChat() {
        if (Context.getUser() != null && Context.getSejour() != null) {
            mainApp.showChat(Context.getUser().getUserId(), Context.getSejour().getIdHost(), Context.getSejour().getSejourId());
        }
        else
        {
            System.out.println("Vous n'êtes pas connectés !");
        }
    }

    private void replaceGroupContent(Group group, Node node) {
        float fontSizeRatio = 0.8F;
        Font font;
        try {
            font = ((Labeled)group.getChildren().get(0)).getFont();
        } catch (Exception e) {
            font = ((Text)group.getChildren().get(0)).getFont();
        }

        node.setStyle("-fx-font: " + (font.getSize() * fontSizeRatio) + " " + font.getFamily());
        group.getChildren().clear();
        group.getChildren().add(node);
    }

    @FXML
    protected void modifierSejour() {
        this.actionButton.setText("Appliquer");
        this.actionButton.setOnAction(t -> { verifyAndApplyModifications(); });

        modifierImg1Button.setVisible(true);
        modifierImg2Button.setVisible(true);

        this.sejourNameField = new TextField(Context.getSejour().getName());
        replaceGroupContent(this.sejourNameGroup, this.sejourNameField);
        System.out.println(this.sejourNameGroup.getChildren());

        this.locationField = new TextField(Context.getSejour().getLocation());
        replaceGroupContent(this.locationGroup, this.locationField);
        System.out.println(this.locationGroup.getChildren());

        this.descriptionField = new TextArea(Context.getSejour().getDescription());
        descriptionField.setMaxHeight(20);
        replaceGroupContent(this.descriptionGroup, this.descriptionField);

        this.dateBeginField = new TextField(Context.getSejour().getStrDateBegin());
        replaceGroupContent(this.dateBeginGroup, this.dateBeginField);

        this.dateEndField = new TextField(Context.getSejour().getStrDateEnd());
        replaceGroupContent(this.dateEndGroup, this.dateEndField);
    }

    private Label verifyTextLength(Node field, Group group, Label error){
        int length;

        try {
            length = ((TextField)field).getText().length();
        } catch (Exception f) {
            length = ((TextArea)field).getText().length();
        }
        if (length < 2) {
            if (error == null) {
                error = new Label("Le nombre de caractère doit être >= 2");
                error.setStyle("-fx-text-fill: #ff0000;");
                ObservableList<Node> children = FXCollections.observableArrayList(group.getChildren());
                children.add(error);
                group.getChildren().clear();
                sejourVboxError = new VBox();
                sejourVboxError.getChildren().addAll(children);
                group.getChildren().add(sejourVboxError);
            }
        }  else {
            error = null;
            group.getChildren().clear();
            group.getChildren().add(field);
        }
        return error;
    }

    private void applyModification(){
        saveImage(img1.getImage(), "src/main/resources/Images/Sejours/" + Context.getSejour().getImageBundle() + "/img_1.jpg");
        saveImage(img2.getImage(), "src/main/resources/Images/Sejours/" + Context.getSejour().getImageBundle() + "/img_2.jpg");

        Sejour updatedSejour = Context.getSejour();
        updatedSejour.setName(sejourNameField.getText());
        updatedSejour.setLocation(locationField.getText());
        updatedSejour.setDescription(descriptionField.getText());
        updatedSejour.setDateBegin(dateBeginField.getText());
        updatedSejour.setDateEnd(dateEnd.getText());

        Context.setSejour(updatedSejour);
        SejourDAO sejourDAO = new SejourDAO();
        sejourDAO.updateSejour(updatedSejour);

        init();
    }

    private void verifyAndApplyModifications(){
        System.out.println(errorSejourName);
        errorSejourName = verifyTextLength(sejourNameField, sejourNameGroup, errorSejourName);
        errorLocation = verifyTextLength(locationField, locationGroup, errorLocation);
        errorDescription = verifyTextLength(descriptionField, descriptionGroup, errorDescription);

        System.out.print(errorSejourName);
        System.out.print(errorLocation);
        System.out.print(errorDescription);
        if (errorSejourName == null && errorLocation == null && errorDescription == null) {
            applyModification();
        }
    }

    @FXML
    protected File openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(mainApp.getStage());

        return file;
    }

    private void saveImage(Image image, String filePath) {
        try {
            File outfile = new File(filePath);
            BufferedImage bImage = javafx.embed.swing.SwingFXUtils.fromFXImage(image, null);
            ImageIO.write(bImage, "png", outfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML void modifierImg1(){
        File file = openFileChooser();
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            img1.setImage(image);
        }
    }

    @FXML void modifierImg2(){
        File file = openFileChooser();
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            img2.setImage(image);
        }
    }

    public void setUserBox(){
        if (Context.getUser()!=null){
            userNameText.setText(Context.getUser().getFirstName());
            connexionButton.setVisible(false);
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
            connexionButton.setVisible(true);
        }
    }

    @FXML
    protected void showConnection() {
        mainApp.showConnection();
    }
}
