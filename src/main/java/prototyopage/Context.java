package prototyopage;

import DB.DemSejDB.DemSej;
import DB.SejourDB.Sejour;
import DB.SejourDB.SejourDAO;
import DB.UserDB.User;

import java.sql.SQLException;
import java.text.ParseException;

public class Context {
    private static User user = null;
    private static Sejour sejour = null;
    private static DemSej demSej = null;

    public static User getUser() { return user; }

    public static void setUser(User u) { user = u; }

    public static Sejour getSejour() { return sejour; }

    public static void setSejour(Sejour s) { sejour = s; }

    public static void setSejourById(int sejourId){
        SejourDAO sejourDao = new SejourDAO();
        setSejour(sejourDao.getSejourById(sejourId));
    }

    public static DemSej getDemSej() { return demSej; }

    public static void setDemSej(DemSej demSej) { Context.demSej = demSej; }

}
