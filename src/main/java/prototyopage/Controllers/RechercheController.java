package prototyopage.Controllers;

import DB.SejourDB.Sejour;
import DB.SejourDB.SejourDAO;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import prototyopage.Context;
import prototyopage.MainApp;

import java.io.IOException;
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

    @FXML
    private ScrollPane scrollPane;

    private SejourDAO sejourDao = new SejourDAO();

    private ArrayList<Sejour> listDisplayedSejour =new ArrayList<>();
    private ArrayList<Sejour> listSejourToDisplay =new ArrayList<>();
    private int idxSejour=0;
    private int maxSejourToLoad=10000;

    private String lastSearch="";
    // end search part


    private ArrayList<Thread> threads=new ArrayList<>();

    public synchronized VBox getBoxSejour(){
        return this.boxSejour;
    }


    public synchronized void addToBoxSejour(Button button){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                boxSejour.getChildren().add(button);
            }
        });

    }

    public synchronized ArrayList<Sejour> getListDisplayedSejour(){
        return this.listDisplayedSejour;
    }

    public synchronized void addToListDisplayedSejour(Sejour sejour){
        this.listDisplayedSejour.add(sejour);
    }

    Service<Void> service = new Service<Void>()
    {
        @Override
        protected Task<Void> createTask()
        {
            Node[] nodes = new Node[maxSejourToLoad];
            return new Task<Void>() {
                protected Void call() throws Exception {
                    for (int i=0; i< nodes.length; i++) {
                        Thread.sleep(0,1);
                        final int index=i+idxSejour;
                        addToListDisplayedSejour(listSejourToDisplay.get(index));
                        Text sejour = new Text();
                        String sejourText="";
                        sejourText+=listSejourToDisplay.get(index).getName()+"\nOù? ";
                        sejourText+=listSejourToDisplay.get(index).getLocation()+"\n";
                        sejourText+="Quand? De ";
                        sejourText+=listSejourToDisplay.get(index).getStrDateBegin();
                        sejourText+=" jusqu'à ";
                        sejourText+=listSejourToDisplay.get(index).getStrDateEnd();
                        sejour.setText(sejourText);
                        Button button=new Button("",sejour);
                        button.setMaxWidth(10000000);
                        button.setAlignment(Pos.BASELINE_LEFT);
                        button.setId(String.valueOf(listSejourToDisplay.get(index).getSejourId()));
                        button.setOnAction((event) -> {    // lambda expression
                            Context.setSejourById(Integer.parseInt(button.getId()));
                            openSejour();
                            System.out.println("open sejour: " + button.getId());
                        });
                        //addToBoxSejour(button);
                        nodes[index]=button;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                boxSejour.getChildren().add(nodes[index]);
                            }
                        });
                    }
                    idxSejour=idxSejour+maxSejourToLoad;

                    return null;
                }
            };
        }
    };

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

//    private void addSejourToBox(ArrayList<Sejour> list){
//        Integer lenght = list.size();
//
//        for (int i=0;i<lenght;i=i+25) {
//            List<Sejour> sublist;
//            if (i+25<lenght) {
//                sublist = list.subList(i, i + 25);
//            }
//            else {
//                sublist = list.subList(i, lenght);
//            }
//            Thread t = new Thread(new SearchRunnable(sublist, this, this.mainApp));
//            t.start();
//            threads.add(t);
//        }
//    }

//    private void addSejourToBox(){
//        Integer lenght = listSejourToDisplay.size();
//        if (lenght<endSejour){
//            endSejour=lenght;
//        }
//        for (int i=startSejour;i<endSejour;i=i+25) {
//
//            List<Sejour> sublist;
//            if (i+25<lenght) {
//                sublist = listSejourToDisplay.subList(i, i + 25);
//            }
//            else {
//                sublist = listSejourToDisplay.subList(i, lenght);
//            }
//            Thread t = new Thread(new SearchRunnable(sublist, this, this.mainApp));
//            t.start();
//            threads.add(t);
//        }
//        startSejour=endSejour;
//        endSejour+=100;
//    }

    @FXML
    private void search() {
        if (searchBar.getLength() > 1) {
            listDisplayedSejour.clear();
            boxSejour.getChildren().clear();
            lastSearch=searchBar.getText();
            listSejourToDisplay=sejourDao.searchSejourByField("Name",searchBar.getText());
            listSejourToDisplay=sejourDao.searchSejourByField("Location",searchBar.getText());
            listSejourToDisplay=sejourDao.searchSejourByField("Description",searchBar.getText());
            listSejourToDisplay=sejourDao.searchSejourByField("Description",searchBar.getText());
            listSejourToDisplay=sejourDao.searchSejourByField("Description",searchBar.getText());
        } else if (lastSearch=="" || lastSearch.length()>1){
            listDisplayedSejour.clear();
            boxSejour.getChildren().clear();
            idxSejour=0;
            lastSearch=" ";
            listSejourToDisplay=sejourDao.searchSejourByField("Name","");
            service.start();
        }
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

