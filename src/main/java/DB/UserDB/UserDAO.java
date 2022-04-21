package DB.UserDB;

import DB.Connexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {

    public ArrayList<User> getUsers(){
        Connexion connexion = new Connexion("Database/User.db");
        connexion.connect();
        ResultSet resultSet = connexion.query("SELECT * FROM User");
        ArrayList<User> userArrayList=new ArrayList<>();
        try {
            while (resultSet.next()) {
                Boolean b=resultSet.getInt("Host")==1;
                User u=new User(resultSet.getInt("UserId"),resultSet.getString("FirstName"),resultSet.getString("LastName" ),b,resultSet.getString("email"),resultSet.getInt("age"),resultSet.getInt("telephone"), resultSet.getString("password"));
                userArrayList.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connexion.close();
        return userArrayList;
    }

    public void addUser(User user) {
        Connexion connexion = new Connexion("Database/User.db");
        connexion.connect();
        String query = "";
        query += "INSERT INTO USER(FirstName,LastName,Host,email,age,telephone, password) VALUES (";
        query += "'" + user.getFirstName() + "', ";
        query += "'" + user.getLastName() + "', ";
        int bi=user.isHost() ? 1 : 0;
        query += "'" + bi + "' ,";
        query += "'" + user.getEmail() + "', ";
        query += "'" + user.getAge() + "', ";
        query += "'" + user.getTelephone() + "', ";
        query += "'" + user.getPassword() + "');";
        connexion.submitQuery(query);
        connexion.close();
    }
}
