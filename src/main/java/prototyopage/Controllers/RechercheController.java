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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
    private int maxSejourToLoad=1000;

    private String lastSearch="";
    // end search part



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
                    Thread.sleep(250);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            boxSejour.getChildren().clear();
                        }
                    });
                    for (int i=0; i< nodes.length; i++) {
                        final int index=i;
                        if (!(listDisplayedSejour.contains(listSejourToDisplay.get(index)))) {
                            Thread.sleep(0, 1);
                            addToListDisplayedSejour(listSejourToDisplay.get(index));
                            Text sejour = new Text();
                            String sejourText = "";
                            sejourText += listSejourToDisplay.get(index).getName() + "\nOù? ";
                            sejourText += listSejourToDisplay.get(index).getLocation() + "\n";
                            sejourText += "Quand? De ";
                            sejourText += listSejourToDisplay.get(index).getStrDateBegin();
                            sejourText += " jusqu'à ";
                            sejourText += listSejourToDisplay.get(index).getStrDateEnd();
                            sejour.setText(sejourText);
                            Button button = new Button("", sejour);
                            button.setMaxWidth(10000000);
                            button.setAlignment(Pos.BASELINE_LEFT);
                            button.setId(String.valueOf(listSejourToDisplay.get(index).getSejourId()));
                            button.setOnAction((event) -> {    // lambda expression
                                Context.setSejourById(Integer.parseInt(button.getId()));
                                openSejour();
                                System.out.println("open sejour: " + button.getId());
                            });
                            //addToBoxSejour(button);
                            nodes[index] = button;
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    boxSejour.getChildren().add(nodes[index]);
                                }
                            });
                        }
                    }
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
        listDisplayedSejour.clear();
        boxSejour.getChildren().clear();
        lastSearch=" ";
        listSejourToDisplay=sejourDao.searchSejourByField("Name","");
        maxSejourToLoad=listSejourToDisplay.size();
        service.start();
    }

    @FXML
    private void search() {
        listSejourToDisplay.clear();
        listDisplayedSejour.clear();
        service.cancel();
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                if (searchBar.getLength() > 1) {
                    final String search= searchBar.getText();
                    lastSearch = search;
                    ArrayList<Sejour> names = sejourDao.searchSejourByField("Name", search);
                    Set<Sejour> set = new LinkedHashSet<>(names);
                    ArrayList<Sejour> loc = sejourDao.searchSejourByField("Location", search);
                    set.addAll(loc);
                    ArrayList<Sejour> desc = sejourDao.searchSejourByField("Description", search);
                    set.addAll(desc);
                    ArrayList<Sejour> dateBegin = sejourDao.searchSejourByField("DateBegin", search);
                    set.addAll(dateBegin);
                    ArrayList<Sejour> dateEnd = sejourDao.searchSejourByField("DateEnd", search);
                    set.addAll(dateEnd);
                    listSejourToDisplay = new ArrayList<>(set);
                    maxSejourToLoad = listSejourToDisplay.size();
                    service.restart();

                } else if (lastSearch == "" || lastSearch.length() > 1) {
                    lastSearch = " ";
                    listSejourToDisplay = sejourDao.searchSejourByField("Name", "");
                    maxSejourToLoad = listSejourToDisplay.size();
                    service.restart();
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

