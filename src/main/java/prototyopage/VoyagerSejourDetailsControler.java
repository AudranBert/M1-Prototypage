package prototyopage;

import DB.SejourDB.Sejour;
import DB.SejourDB.SejourDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.text.ParseException;

public class VoyagerSejourDetailsControler {
    @FXML
    private Label sejourName;

    @FXML
    private Label locationi;

    @FXML
    private Text dateBegin;

    @FXML
    private Text dateEnd;

    @FXML
    private Button buttoni;

    protected void pageSetup(int id) throws SQLException, ParseException {
        SejourDAO sejourDao = new SejourDAO();
        Sejour sejour = sejourDao.getSejourById(id);
        sejourName.setText(sejour.getName());
        locationi.setText(sejour.getLocation());
        dateBegin.setText(sejour.getStrDateBegin());
        dateEnd.setText(sejour.getStrDateEnd());
    }

    @FXML
    protected void set() throws SQLException, ParseException {
        pageSetup( 1);
    }
}
