package prototyopage;

import DB.SejourDB.Sejour;
import DB.SejourDB.SejourDAO;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class RechercheController {
    private MainApp mainApp;

    @FXML
    private TextField searchBar;

    @FXML
    private VBox boxSejour;

    private SejourDAO sejourDao = new SejourDAO();

    private ArrayList<Sejour> listSejour=new ArrayList<>();

    @FXML
    private void search() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                if (searchBar.getLength() > 1) {
                    ArrayList<Sejour> list = sejourDao.getSejours();
                    for (int i = 0; i < list.size(); i++) {
                        Boolean find = false;
                        for (int j = 0; j < listSejour.size(); j++) {
                            if (listSejour.get(j).getSejourId() == list.get(i).getSejourId()) {
                                find = true;
                            }
                        }
                        if (find == false) {
                            listSejour.add(list.get(i));
                            Text sejour = new Text();
                            sejour.setText(list.get(i).toString());
                            boxSejour.getChildren().add(sejour);
                        }
                    }
                }
                return null;
            }
        };
        task.run();
    }
    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }
}