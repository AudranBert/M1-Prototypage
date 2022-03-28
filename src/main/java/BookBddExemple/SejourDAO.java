package BookBddExemple;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class SejourDAO {
    private Connexion connexion;

    public SejourDAO() {
        connexion = new Connexion("Database/Database_Sejour.db");
    }

    public void addSejour(Sejour sejour) {
        //System.out.println(aujourdhui.getYear());
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-DD HH:MM:SS.SSS");

        String query = "";
        query += "INSERT INTO Sejour(Name, Location, DateBegin, DateEnd) VALUES (";
        query += "'" + sejour.getName() + "', ";
        query += "'" + sejour.getLocation() + "', ";
        query += "'" + formater.format(sejour.getDateBegin().getTime()) + "', ";
        query += "'" + formater.format(sejour.getDateEnd().getTime()) + "' )";
        connexion.connect();
        connexion.submitQuery(query);
        connexion.close();
    }

    public void printAllSejour() {
        connexion.connect();
        ResultSet resultSet = connexion.query("SELECT * FROM SEJOUR");
        try {
            while (resultSet.next()) {
                System.out.println("SejourId : " + resultSet.getString("SejourId")
                                   + ", Name : " + resultSet.getString("Name")
                                   + ", Location : " + resultSet.getString("Location")
                                   + ", DateBegin : " + resultSet.getString("DateBegin")
                                   + ", DateEnd : " + resultSet.getString("DateEnd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connexion.close();
    }
}
