package BookBddExemple;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.GregorianCalendar;

public class MainSejourTest {
    public static void main(String[] args) {
        SejourDAO sejourDao = new SejourDAO();

        sejourDao.printAllSejour();

        System.out.println("\nAjout d'un Séjour (Hello)\n");
        Sejour sejour = new Sejour(12, "Hello", "Paris", new GregorianCalendar(2013, 03, 11, 15, 02), new GregorianCalendar(2013, 03, 11, 15, 02));
        sejourDao.addSejour(sejour);

        sejourDao.printAllSejour();
    }
}

