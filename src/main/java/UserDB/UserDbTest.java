package UserDB;

import BookBddExemple.Connexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDbTest {


    public static void fillDB(){

        UserDAO dao=new UserDAO();
        User user = new User("Guillaume", "Bonenfant", Boolean.FALSE,"gmail",03333,0444);
        dao.addUser(user);
        User user2 = new User("Audran", "Bert", Boolean.TRUE,"amail",3,5);
        dao.addUser(user2);
        User user3 = new User("Sara", "Bensafi", Boolean.FALSE,"smail",3,5);
        dao.addUser(user3);
        User user4 = new User("Nath", "Lefevre", Boolean.FALSE,"nmail",3,5);
        dao.addUser(user4);
    }

    public static void main(String[] args) {
        UserDAO dao=new UserDAO();
        fillDB();


        ArrayList<User> list=dao.getUsers();
        for( var value : list ) {
            System.out.println(value.toString());
        }
    }
}
