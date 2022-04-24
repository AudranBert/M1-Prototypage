package DB.DemSejDB;

import DB.Connexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class DemSejDAO {
    private Connexion connexion;

    public DemSejDAO() {
        connexion = new Connexion("Database/DB.db");
    }

    public void addDemSej(DemSej demSej) {
        String query = "";
        query += "INSERT INTO DemSej(voyageur, sejour, validation) VALUES (";
        query += "'" + demSej.getVoyagerId() + "', ";
        query += "'" + demSej.getSejourId() + "', ";
        query += "'" + demSej.getValidation() + "')";

        System.out.println("query " + query);
        connexion.connect();
        connexion.submitQuery(query);
        connexion.close();
    }

    public void deleteDemSej(DemSej demSej) {
        String query = "DELETE FROM DemSej WHERE id_demande = '" + demSej.getDemandeId() + "';";

        System.out.println("query " + query);
        connexion.connect();
        connexion.submitQuery(query);
        connexion.close();
    }

    public DemSej resultSetToDemSej(ResultSet resultSet) {
        Integer demandeId = null;
        Integer voyageurId = null;
        Integer sejourId = null;
        Integer validationId = null;
        try {
            demandeId = resultSet.getInt("id_demande");
            voyageurId = resultSet.getInt("voyageur");
            sejourId = resultSet.getInt("sejour");
            validationId = resultSet.getInt("validation");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new DemSej(demandeId, voyageurId, sejourId, validationId);
    }

    public DemSej getDemSejByVoyagerAndSejour(int voyagerId, int sejourId) {
        connexion.connect();
        ResultSet resultSet = connexion.query("SELECT * FROM DemSej WHERE voyageur LIKE '%" + voyagerId + "%' AND sejour LIKE '%" + sejourId + "%';");
        Boolean exist = null;
        try {
            exist = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.print("resultSet exist: ");
        System.out.println(exist);
        if (exist) {
            DemSej demSej = resultSetToDemSej(resultSet);
            connexion.close();
            return demSej;
        }
        return null;
    }
}
