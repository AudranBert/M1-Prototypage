package prototyopage;

import DB.SejourDB.Sejour;
import DB.SejourDB.SejourDAO;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class RechercheController {
    private MainApp mainApp;

    @FXML
    private TextField searchBar;

    @FXML
    private VBox boxSejour;

    private SejourDAO sejourDao = new SejourDAO();

    private ArrayList<Sejour> listSejour=new ArrayList<>();
    private String lastSearch="";

    public synchronized VBox getBoxSejour(){
        return this.boxSejour;
    }

    public synchronized void addToBoxSejour(Button button){
        VBox bs=this.boxSejour;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                bs.getChildren().add(button);
            }
        });

    }

    public synchronized ArrayList<Sejour> getSejourList(){
        return this.listSejour;
    }

    public synchronized void addToSejourList(Sejour sejour){
        this.listSejour.add(sejour);
    }

    @FXML
    public void initialize() {
        search();
    }

    private void addSejourToBox(ArrayList<Sejour> list){
        Integer lenght = list.size();
        for (int i=0;i<lenght;i=i+10) {
            List<Sejour> sublist;
            if (i+10<lenght) {
                sublist = list.subList(i, i + 10);
            }
            else {
                sublist = list.subList(i, lenght);
            }
            Thread t = new Thread(new SearchRunnable(sublist, this));
            t.start();

        }
    }

    @FXML
    private void search() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                if (searchBar.getLength() > 1) {
                    listSejour.clear();
                    boxSejour.getChildren().clear();
                    lastSearch=searchBar.getText();
                    addSejourToBox(sejourDao.searchSejourByName(searchBar.getText()));
                    addSejourToBox(sejourDao.searchSejourByLocation(searchBar.getText()));
                } else if (lastSearch=="" || lastSearch.length()>1){
                    listSejour.clear();
                    boxSejour.getChildren().clear();
                    lastSearch=" ";
                    addSejourToBox(sejourDao.searchSejourByName(""));
                    addSejourToBox(sejourDao.searchSejourByLocation(""));
                }
                return null;
            }
        };
        task.run();
    }

    @FXML
    private void backToHome(){
        this.mainApp.showHome();
    }

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }


}

