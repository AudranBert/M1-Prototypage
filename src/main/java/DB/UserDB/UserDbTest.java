package DB.UserDB;

import java.util.ArrayList;

public class UserDbTest {


    public static void fillDB(){
        UserDAO dao=new UserDAO();
        User user = new User("Guillaume", "Bonenfant", Boolean.FALSE);
        dao.addUser(user);
        User user2 = new User("Audran", "Bert", Boolean.TRUE);
        dao.addUser(user2);
        User user3 = new User("Sara", "Bensafi", Boolean.FALSE);
        dao.addUser(user3);
        User user4 = new User("Nath", "Lefevre", Boolean.FALSE);
        dao.addUser(user4);
    }

    public static void main(String[] args) {

        //fillDB();
        UserDAO dao=new UserDAO();

        ArrayList<User> list=dao.getUsers();
        for( var value : list ) {
            System.out.println(value.toString());
        }
    }
}
