package DB.SejourDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

public class SejourDbTest {
    public static void main(String[] args) {
        SejourDAO sejourDao = new SejourDAO();

        ArrayList<Sejour> list = sejourDao.getSejours();
        for( var value : list ) {
            System.out.println(value.toString());
        }

        System.out.println("\nAjout d'un Séjour (Hello)\n");
        Sejour sejour = new Sejour("Hello", "Paris", new GregorianCalendar(2013, 03, 11, 15, 02), new GregorianCalendar(2013, 03, 11, 15, 02));
        sejourDao.addSejour(sejour);

        list = sejourDao.getSejours();
        for( var value : list ) {
            System.out.println(value.toString());
        }
    }
}

