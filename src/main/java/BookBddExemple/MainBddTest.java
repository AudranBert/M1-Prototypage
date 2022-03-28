package BookBddExemple;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class MainBddTest {

    public static void main(String[] args) {
        Connexion connexion = new Connexion("Database/Database.db");
        connexion.connect();
        ResultSet resultSet = connexion.query("SELECT * FROM BOOK");
        try {
            while (resultSet.next()) {
                System.out.println("Titre : "+resultSet.getString("Title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("\nAjout d'un livre (Ahahah Android)\n");
        Book book = new Book("3ff883e6-dfc0-4149-902a-4dbdfa22a408",
                "Ahahah Android", "", 450, Date.valueOf("2012-11-01"),
                "");
        connexion.addBook(book);

        resultSet = connexion.query("SELECT * FROM BOOK");
        try {
            while (resultSet.next()) {
                System.out.println("Titre : "+resultSet.getString("Title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }




        connexion.close();
    }

}
