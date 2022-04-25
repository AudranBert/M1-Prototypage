package prototyopage;

import DB.Connexion;
import DB.SejourDB.Sejour;
import DB.SejourDB.SejourDAO;
import DB.UserDB.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ControllerDemanSej {
    private MainApp mainApp;

    private int id_current_voyageur;

    private int sejourSelected;




    @FXML
    private TableColumn<Sejour, String> DateDeb;

    @FXML
    private TableColumn<Sejour, String> DateEnd;

    @FXML
    private TableColumn<Sejour, String> localisation;

    @FXML
    private TableColumn<Sejour, String> name;
    @FXML
    private Label DateFin;
    @FXML
    private Label dateDeb;
    @FXML
    private Label nomSEJ;
    @FXML
    private ComboBox<String> combobox;


    @FXML
    private TableView<Sejour> Tableplaning  ;



    private ObservableList<Sejour> testdata = FXCollections.observableArrayList();


    @FXML
    private Label emaillabel;
    private Label welText;
    @FXML
    private Label nomlabel;
    @FXML
    private Label prenomlabel;
    @FXML
    private Button buttondecliner;
    @FXML
    private Button buttonvalider;
    @FXML
    private Label telephonelabel;






    //Les méthodes
    @FXML
    protected void showChat() {
        if (mainApp.getUser() != null) {
            mainApp.showChat(mainApp.getUser().getUserId(), id_current_voyageur, sejourSelected);
        }
        else
        {
            System.out.println("Vous n'êtes pas connectés !");
        }
    }

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }

    @FXML
    public void initialize() {


        //connexion
            Connexion connexion = new Connexion("Database/DB.db");
            connexion.connect();

        // initialisé la combobox
            //ResultSet resultSet = connexion.query("SELECT * FROM DemSej WHERE host = '" + 22 + "';");//ici je doit doit mettre l'id de connexion après l'avoir récupéré
            ResultSet resultSet = connexion.query("SELECT * FROM DemSej join sejour on DemSej.sejour = sejour.SejourId WHERE sejour.IdHost = '" + 22 + "' ;");//this.mainApp.getUser().getUserId() ne marche pas le Initialize()
            List<String> col1 = new ArrayList<String>();
            try {
                int i =0;
                while (resultSet.next()) {
                    col1.add(String.valueOf(resultSet.getInt("id_demande")));
               i=i+1;
                }
                combobox.getItems().clear();
                for (int j = 0; j < col1.size(); j++) {
                    System.out.println(col1.get(j));
                    ObservableList obList = FXCollections.observableList(col1);
                    combobox.setItems(obList);
                }

          /****************************INITIALISATION********************************/
          //autoselectionné la première case
                combobox.getSelectionModel().select(0);
                ResultSet resultSINIT= connexion.query("SELECT * FROM DemSej WHERE id_demande = '" + col1.get(0) + "';");//ici je doit doit mettre l'id de connexion après l'avoir récupéré


            //Initialisé la selection voyageur pour la première fois
                int id_current_voyageurINIT=resultSINIT.getInt("voyageur");
                ResultSet resINIT = connexion.query("SELECT * FROM User WHERE  UserId = '" + id_current_voyageurINIT + "';");
                //récupérer son nom prénom et email age telephone
                prenomlabel.setText(resINIT.getString("FirstName"));
                nomlabel.setText(resINIT.getString("LastName"));
                emaillabel.setText(resINIT.getString("email"));
                telephonelabel.setText(resINIT.getString("telephone"));



            // Initialisé le résumé séjour
                ResultSet r_= connexion.query("SELECT * FROM DemSej WHERE id_demande = '" + col1.get(0) + "';");
                int id_current_SejourINIT=r_.getInt("sejour");
                System.out.println(id_current_SejourINIT);
                ResultSet resINIT2 = connexion.query("SELECT * FROM Sejour WHERE  SejourId = '" + id_current_SejourINIT + "';");
                nomSEJ.setText(resINIT2.getString("Name"));
                 dateDeb.setText(resINIT2.getString("DateBegin"));
                DateFin.setText(resINIT2.getString("DateEnd"));


             //Initialisé table view

                //connexion
                Connexion connexion1 = new Connexion("Database/DB.db");
                connexion1.connect();

                //Query
                ResultSet r = connexion1.query("SELECT * FROM DemSej WHERE validation = '" + 1 + "';");
                List<String> col = new ArrayList<String>();

                try {
                    int o = 0;
                    while (r.next()) {
                        col.add(String.valueOf(r.getInt("sejour")));
                        o = o + 1;
                    }//while

                    for (int j = 0; j < col.size(); j++) {

                        ResultSet r1 = connexion1.query("SELECT Name , Location , DateBegin , DateEnd  FROM Sejour WHERE SejourId = '" + col.get(j) + "';");


                        while (r1.next()) {
                            SejourDAO sejourDao = new SejourDAO();
                            Sejour sejour;
                            sejour = sejourDao.getSejourById(Integer.parseInt(col.get(j)));
                            sejour.setDateBeginS(sejour.getStrDateBegin());
                            sejour.setDateEndS(sejour.getStrDateEnd());
                            testdata.add(sejour);

                        }//while


                    }//for

                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                name.setCellValueFactory(new PropertyValueFactory<>("name"));
             localisation.setCellValueFactory(new PropertyValueFactory<>("location"));
             DateDeb.setCellValueFactory(new PropertyValueFactory<>("DateBeginS"));
             DateEnd.setCellValueFactory(new PropertyValueFactory<>("DateEndS"));

             Tableplaning.setItems(testdata);



        } catch (SQLException e) {
            e.printStackTrace();
        }
        connexion.close();
    }




    @FXML
    void getElement(){
    String output = combobox.getSelectionModel().getSelectedItem();
    System.out.println(output);
    //récupérer l'id du voyageur qui a choisie le voyage et afficher ses infor à droite

    Connexion connexion = new Connexion("Database/DB.db");
    connexion.connect(); //
    ResultSet resultSet = connexion.query("SELECT * FROM DemSej WHERE id_demande = '" + output + "';");//ici je doit doit mettre l'id de connexion après l'avoir récupéré

    try {
        id_current_voyageur=resultSet.getInt("voyageur");
        //Info voyageur
        //int id_current_voyageur=resultSet.getInt("voyageur");
        sejourSelected=resultSet.getInt("sejour");
        ResultSet res = connexion.query("SELECT * FROM User WHERE  UserId = '" + id_current_voyageur + "';");
        //récupérer son nom prénom et email age telephone

        prenomlabel.setText(res.getString("FirstName"));
        nomlabel.setText(res.getString("LastName"));
        emaillabel.setText(res.getString("email"));
        telephonelabel.setText(res.getString("telephone"));


        //Info séjours



        ResultSet res2 = connexion.query("SELECT * FROM Sejour WHERE  SejourId = '" + sejourSelected + "';");
        nomSEJ.setText(res2.getString("Name"));
        dateDeb.setText(res2.getString("DateBegin"));
        DateFin.setText(res2.getString("DateEnd"));






    } catch (SQLException e) {
        e.printStackTrace();
    }
    connexion.close();





}

    @FXML
    void Decliner(){

         //ici je vais seulement refuser le voyage et mettre la colonne Etatdemande à refuser
        //et mettre un if == refuser le voyageur saura que il est refusé
        if (!nomlabel.getText().trim().isEmpty() && !prenomlabel.getText().trim().isEmpty() && !telephonelabel.getText().trim().isEmpty() && !emaillabel.getText().trim().isEmpty()) {
            Connexion connexion = new Connexion("Database/DB.db");
            connexion.connect();

            String query = "UPDATE `DemSej` SET `validation` = 2  WHERE `sejour` = " + sejourSelected;
            connexion.submitQuery(query);
            connexion.close();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Refus");
            alert.setHeaderText("Information :");
            alert.setContentText("Réservation refusé !");

            alert.showAndWait();

        }else{

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Validation ");
            alert.setHeaderText("Veuiller selectionner une demande de séjour");
            alert.showAndWait();

        }



    }

   @FXML
    void Valider(){

//ici je vais seulement accepter le voyage et mettre la colonne Etatdemande à valide
// et mettre un if == accepted le voyageur saura que il est accepté
//je valide que si l'hote a selectionner un voyage
    if (!nomlabel.getText().trim().isEmpty() && !prenomlabel.getText().trim().isEmpty() && !telephonelabel.getText().trim().isEmpty() && !emaillabel.getText().trim().isEmpty()) {
        Connexion connexion = new Connexion("Database/DB.db");
        connexion.connect();

        String query = "UPDATE `DemSej` SET `validation` = 1  WHERE `sejour` = " + sejourSelected;
        connexion.submitQuery(query);
        connexion.close();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Validation ");
        alert.setHeaderText("Information :");
        alert.setContentText("Réservation validé !");
        alert.showAndWait();

    }else{
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Validation ");
        alert.setHeaderText("Veuiller selectionner une demande de séjour");

        alert.showAndWait();

    }

    }

    @FXML
     void retour(){
        this.mainApp.showHome();
        System.out.println(this.mainApp.getUser().getUserId());
    }

    @FXML
    void showPlanning() throws Exception {
        Tableplaning.getItems().clear();

//connexion
        Connexion connexion1 = new Connexion("Database/DB.db");
        connexion1.connect();

        //Query
        ResultSet r = connexion1.query("SELECT * FROM DemSej WHERE validation = '" + 1 + "';");
        List<String> col = new ArrayList<String>();

        try {
            int o = 0;
            while (r.next()) {
                col.add(String.valueOf(r.getInt("sejour")));
                o = o + 1;
            }//while

            for (int j = 0; j < col.size(); j++) {

                ResultSet r1 = connexion1.query("SELECT Name , Location , DateBegin , DateEnd  FROM Sejour WHERE SejourId = '" + col.get(j) + "';");


                while (r1.next()) {
                    SejourDAO sejourDao = new SejourDAO();
                    Sejour sejour;
                    sejour = sejourDao.getSejourById(Integer.parseInt(col.get(j)));
                    sejour.setDateBeginS(sejour.getStrDateBegin());
                    sejour.setDateEndS(sejour.getStrDateEnd());
                    testdata.add(sejour);

                }//while


            }//for

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        localisation.setCellValueFactory(new PropertyValueFactory<>("location"));
        DateDeb.setCellValueFactory(new PropertyValueFactory<>("DateBeginS"));
        DateEnd.setCellValueFactory(new PropertyValueFactory<>("DateEndS"));
        Tableplaning.setItems(testdata); }}













