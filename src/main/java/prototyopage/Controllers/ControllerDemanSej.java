package prototyopage.Controllers;

import DB.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControllerDemanSej {
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private Label emaillabel;

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



    @FXML
    public void initialize() throws SQLException, ClassNotFoundException{

        Connexion connexion = new Connexion("Database/DB.db");
        connexion.connect(); //
//        ResultSet resultSet = connexion.query("SELECT * FROM DemSej WHERE host = '" + 22 + "';");//ici je doit doit mettre l'id de connexion après l'avoir récupéré
        ResultSet resultSet = connexion.query("SELECT * FROM DemSej join sejour on DemSej.sejour = sejour.SejourId WHERE sejour.IdHost = '" + 22 + "' ;");//ici je doit doit mettre l'id de connexion après l'avoir récupéré

        List<String> col = new ArrayList<String>();
        try {
            int i =0;

            while (resultSet.next()) {
                col.add(String.valueOf(resultSet.getInt("id_demande")));

           i=i+1;
            }

            combobox.getItems().clear();
            for (int j = 0; j < col.size(); j++) {

                System.out.println(col.get(j));
                ObservableList obList = FXCollections.observableList(col);
                combobox.setItems(obList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connexion.close();


    }
    int sejourSelected;
@FXML
    void getElement(){
    String output = combobox.getSelectionModel().getSelectedItem();
    System.out.println(output);
    //récupérer l'id du voyageur qui a choisie le voyage et afficher ses infor à droite

    Connexion connexion = new Connexion("Database/DB.db");
    connexion.connect(); //
    ResultSet resultSet = connexion.query("SELECT * FROM DemSej WHERE id_demande = '" + output + "';");//ici je doit doit mettre l'id de connexion après l'avoir récupéré

    try {
        int id_current_voyageur=resultSet.getInt("voyageur");
        sejourSelected=resultSet.getInt("sejour");
        ResultSet res = connexion.query("SELECT * FROM User WHERE  UserId = '" + id_current_voyageur + "';");
        //récupérer son nom prénom et email age telephone

        prenomlabel.setText(res.getString("FirstName"));
        nomlabel.setText(res.getString("LastName"));
        emaillabel.setText(res.getString("email"));
        telephonelabel.setText(res.getString("telephone"));





    } catch (SQLException e) {
        e.printStackTrace();
    }
    connexion.close();





}


    @FXML
    void Decliner(){

//ici je vais seulement refuser le voyage et mettre la colonne Etatdemande à refuser
        //et mettre un if == refuser le voyageur saura que il est refusé

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




    }
@FXML
    void Valider(){

//ici je vais seulement accepter le voyage et mettre la colonne Etatdemande à valide
        //et mettre un if == accepted le voyageur saura que il est accepté


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



    }
}
