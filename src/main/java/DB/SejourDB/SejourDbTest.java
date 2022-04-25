package DB.SejourDB;

import java.util.*;
import java.util.Random;

public class SejourDbTest {

    public static void create10000Sejours(){
        SejourDAO sejourDao = new SejourDAO();
        ArrayList<String> places = new ArrayList<String>();
        Random random = new Random();
        places.add("Paris");
        places.add("Italie");
        places.add("Australie");
        places.add("Espagne");
        places.add("Vietnam");
        places.add("Congo");
        places.add("Guyane");
        for (int i=0;i<10000;i++){

            Sejour sejour=new Sejour(String.valueOf(i), places.get(random.nextInt(places.size())), new GregorianCalendar(2013, 03, 11), new GregorianCalendar(2013, 03, 11),"description", 22);
            sejourDao.addSejour(sejour);
        }
    }


    public static void main(String[] args) {
        SejourDAO sejourDao = new SejourDAO();

        ArrayList<Sejour> list = sejourDao.getSejours();
        for( var value : list ) {
            System.out.println(value.toString());
        }
        create10000Sejours();
        /*
        System.out.println("\nAjout d'un Séjour (Hello)\n");
        Sejour sejour = new Sejour("Hello", "Paris", new GregorianCalendar(2013, 03, 11), new GregorianCalendar(2013, 03, 11),"un voyage magnifique au bord de mer avec le soleil en afrique du nord", 17);
        Sejour sejour1 = new Sejour("soleil", "avignon", new GregorianCalendar(2011, 05, 12), new GregorianCalendar(2011, 05, 20),"un voyage magnifique au bord de mer avec le soleil en Austarlie", 17);
        sejourDao.addSejour(sejour);
        sejourDao.addSejour(sejour1);
        list = sejourDao.getSejours();
        for( var value : list ) {
            System.out.println(value.toString());
        }

         */
    }
}

