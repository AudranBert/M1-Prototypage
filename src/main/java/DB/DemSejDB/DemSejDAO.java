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
        query += "INSERT INTO DemSej(id_demande, voyageur, sejour, validation) VALUES (";
        query += "'" + demSej.getDemandeId() + "', ";
        query += "'" + demSej.getVoyagerId() + "', ";
        query += "'" + demSej.getSejourId() + "', ";
        query += "'" + demSej.getValidation() + "' ,";
        connexion.connect();
        connexion.submitQuery(query);
        connexion.close();
    }

    public DemSej resultSetToSejour(ResultSet resultSet) throws SQLException, ParseException {
        Integer demandeId = resultSet.getInt("id_demande");
        Integer voyageurId = resultSet.getInt("voyageur");
        Integer sejourId = resultSet.getInt("sejour");
        Integer validationId = resultSet.getInt("validation");

        return new DemSej(demandeId, voyageurId, sejourId, validationId);
    }
}
