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
    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");

    public SejourDAO() {
        connexion = new Connexion("Database/DB.db");
    }

    public void addSejour(Sejour sejour) {
        String query = "";
        query += "INSERT INTO Sejour(Name, Location, DateBegin, DateEnd,description, IdHost) VALUES (";
        query += "'" + sejour.getName() + "', ";
        query += "'" + sejour.getLocation() + "', ";
        query += "'" + dateFormater.format(sejour.getDateBegin().getTime()) + "', ";
        query += "'" + dateFormater.format(sejour.getDateEnd().getTime()) + "' ,";
        query += "'" + sejour.getDescription()+ "' ,";
        query += "'" + sejour.getIdHost()+ "' );";
        connexion.connect();
        connexion.submitQuery(query);
        connexion.close();
    }

    public Sejour resultSetToSejour(ResultSet resultSet) {
        try {
            Integer sejourId = resultSet.getInt("SejourId");
            Integer imageBundle = resultSet.getInt("imageBundle");
            String name = resultSet.getString("Name");
            String location = resultSet.getString("Location");
            Calendar dateBegin = GregorianCalendar.getInstance();
            dateBegin.setTime(dateFormater.parse(resultSet.getString("DateBegin")));
            Calendar dateEnd = GregorianCalendar.getInstance();
            dateEnd.setTime(dateFormater.parse(resultSet.getString("DateEnd")));
            String description = resultSet.getString("description");
            int IdHost = resultSet.getInt("IdHost");
            return new Sejour(sejourId, imageBundle, name, location, dateBegin, dateEnd,description, IdHost);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connexion.close();
        return sejourArrayList;
    }

    public ArrayList<Sejour> getSejoursByHostId(Integer hostId) {
        System.out.println("HostId" + hostId);
        connexion.connect();
        ResultSet resultSet = connexion.query("SELECT * FROM Sejour WHERE idHost = '" + hostId + "';");
        ArrayList<Sejour> sejourArrayList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Sejour sejour = resultSetToSejour(resultSet);
                sejourArrayList.add(sejour);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connexion.close();
        return sejourArrayList;
    }

    public Sejour getSejourById(int id) {
        connexion.connect();
        ResultSet resultSet = connexion.query("SELECT * FROM Sejour WHERE SejourId LIKE '%" + id + "%';");
        try {
            resultSet.next();
            Sejour sejour = resultSetToSejour(resultSet);
            connexion.close();
            return sejour;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Sejour> resultToSejourList(ResultSet resultSet){
        ArrayList<Sejour> sejourArrayList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Sejour sejour = resultSetToSejour(resultSet);
                sejourArrayList.add(sejour);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sejourArrayList;
    }

    public ArrayList<Sejour> searchSejourByField(String field,String toSearch){
        connexion.connect();
        ResultSet resultSet = connexion.query("SELECT * FROM Sejour WHERE "+field+" LIKE '%"+toSearch+"%';");
        ArrayList<Sejour> sejourArrayList = resultToSejourList(resultSet);
        connexion.close();
        return sejourArrayList;

    }

}
