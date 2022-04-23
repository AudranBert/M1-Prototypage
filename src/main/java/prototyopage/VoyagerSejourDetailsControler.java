package prototyopage;

import DB.SejourDB.Sejour;
import DB.SejourDB.SejourDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public class VoyagerSejourDetailsControler implements Initializable {
    private MainApp mainApp;

    private int currentSejourId;
    private Sejour sejour;

    @FXML private Label sejourName;
    @FXML private Label locationi;
    @FXML private Text description;
    @FXML private Text dateBegin;
    @FXML private Text dateEnd;
    @FXML private ImageView img1;
    @FXML private ImageView img2;

    public void setMainApp(MainApp mainApp) { this.mainApp = mainApp; }

    public void setCurrentSejourId(int sejourId) {
        this.currentSejourId = sejourId;
    }

    public int getCurrentSejourId() {
        return this.currentSejourId;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            System.out.println("init: " + this.currentSejourId);
            pageSetup(this.currentSejourId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        try {
            System.out.println("initForced: " + this.currentSejourId);
            pageSetup(this.currentSejourId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected void pageSetup(int id) throws SQLException, ParseException {
        System.out.println("pageSetup: " + id);
        SejourDAO sejourDao = new SejourDAO();
        sejour = sejourDao.getSejourById(id);
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
    }

    @FXML
    private void backToHome(){
        this.mainApp.showHome();
    }

    @FXML
    protected void showChat() {
        if (mainApp.getUser() != null && sejour != null) {
            mainApp.showChat(mainApp.getUser().getUserId(), sejour.getIdHost(), currentSejourId);
        }
        else
        {
            System.out.println("Vous n'êtes pas connectés !");
        }
    }
}
