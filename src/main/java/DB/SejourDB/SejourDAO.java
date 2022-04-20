package DB.SejourDB;

import DB.Connexion;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SejourDAO {
    private Connexion connexion;
    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-DD HH:MM:SS.SSS");

    public SejourDAO() {
        connexion = new Connexion("Database/Sejour.db");
    }

    public void addSejour(Sejour sejour) {
        String query = "";
        query += "INSERT INTO Sejour(Name, Location, DateBegin, DateEnd) VALUES (";
        query += "'" + sejour.getName() + "', ";
        query += "'" + sejour.getLocation() + "', ";
        query += "'" + dateFormater.format(sejour.getDateBegin().getTime()) + "', ";
        query += "'" + dateFormater.format(sejour.getDateEnd().getTime()) + "' )";
        connexion.connect();
        connexion.submitQuery(query);
        connexion.close();
    }

    public Sejour resultSetToSejour(ResultSet resultSet) throws SQLException, ParseException {
        Integer sejourId = resultSet.getInt("SejourId");
        String name = resultSet.getString("Name");
        String location = resultSet.getString("Location");
        Calendar dateBegin = GregorianCalendar.getInstance();
        dateBegin.setTime(dateFormater.parse(resultSet.getString("DateBegin")));
        Calendar dateEnd = GregorianCalendar.getInstance();
        dateEnd.setTime(dateFormater.parse(resultSet.getString("DateEnd")));
        return new Sejour(sejourId, name, location, dateBegin, dateEnd);
    }

    public ArrayList<Sejour> getSejours() {
        connexion.connect();
        ResultSet resultSet = connexion.query("SELECT * FROM Sejour");
        ArrayList<Sejour> sejourArrayList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Sejour sejour = resultSetToSejour(resultSet);
                sejourArrayList.add(sejour);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        connexion.close();
        return sejourArrayList;
    }
}