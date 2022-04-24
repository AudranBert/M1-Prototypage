package prototyopage.Controllers;

import DB.SejourDB.Sejour;
import DB.SejourDB.SejourDAO;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import prototyopage.Context;
import prototyopage.MainApp;

import java.util.ArrayList;
import java.util.List;

public class RechercheController {
    private MainApp mainApp;

    // user
    @FXML
    private VBox userBox;
    @FXML
    private javafx.scene.text.Text userIsTravelerText;
    @FXML
    private javafx.scene.text.Text userNameText;

    // search part
    @FXML
    private TextField searchBar;

    @FXML
    private VBox boxSejour;

    private SejourDAO sejourDao = new SejourDAO();

    private ArrayList<Sejour> listSejour=new ArrayList<>();
    private String lastSearch="";
    // end search part


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

    public void init() {
        search();
    }

    private void addSejourToBox(ArrayList<Sejour> list){
        Integer lenght = list.size();
        for (int i=0;i<lenght;i=i+25) {
            List<Sejour> sublist;
            if (i+25<lenght) {
                sublist = list.subList(i, i + 25);
            }
            else {
                sublist = list.subList(i, lenght);
            }
            Thread t = new Thread(new SearchRunnable(sublist, this, this.mainApp));
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
                    addSejourToBox(sejourDao.searchSejourByField("Name",searchBar.getText()));
                    addSejourToBox(sejourDao.searchSejourByField("Location",searchBar.getText()));
                    addSejourToBox(sejourDao.searchSejourByField("Description",searchBar.getText()));
                    addSejourToBox(sejourDao.searchSejourByField("DateBegin",searchBar.getText()));
                    addSejourToBox(sejourDao.searchSejourByField("DateEnd",searchBar.getText()));
                } else if (lastSearch=="" || lastSearch.length()>1){
                    listSejour.clear();
                    boxSejour.getChildren().clear();
                    lastSearch=" ";
                    addSejourToBox(sejourDao.searchSejourByField("Name",""));
                }
                return null;
            }
        };
        task.run();
    }

    @FXML
    private void backToHome(){
        this.mainApp.backView();
    }

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }


    public void openSejour() {
        mainApp.showVoyagerSejourDetails();
    }
}

