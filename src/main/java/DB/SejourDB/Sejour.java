package DB.SejourDB;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Sejour {
    private static SimpleDateFormat DATE_FORMATER = new SimpleDateFormat("yyyy-MM-dd");

    private int sejourId;
    private String name;
    private String location;
    private Calendar DateBegin;
    private Calendar DateEnd;

    public Sejour(int sejourId, String name, String location, Calendar dateBegin, Calendar dateEnd) {
        this.sejourId = sejourId;
        this.name = name;
        this.location = location;
        this.DateBegin = dateBegin;
        this.DateEnd = dateEnd;
    }

    public Sejour(String name, String location, GregorianCalendar dateBegin, GregorianCalendar dateEnd) {
        this.sejourId = -1;
        this.name = name;
        this.location = location;
        this.DateBegin = dateBegin;
        this.DateEnd = dateEnd;
    }

    public int getSejourId() {
        return sejourId;
    }

    public void setSejourId(int sejourId) {
        this.sejourId = sejourId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Calendar getDateBegin() { return DateBegin; }

    public String getStrDateBegin() { return DATE_FORMATER.format(this.DateBegin.getTime()); }

    public void setDateBegin(Calendar dateBegin) {
        DateBegin = dateBegin;
    }

    public Calendar getDateEnd() {
        return DateEnd;
    }

    public String getStrDateEnd() { return DATE_FORMATER.format(this.DateEnd.getTime()); }


    public void setDateEnd(Calendar dateEnd) {
        DateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return "SejourId : " + this.sejourId
                + ", Name : " + this.name
                + ", Location : " + this.location
                + ", DateBegin : " + DATE_FORMATER.format(this.DateBegin.getTime())
                + ", DateEnd : " + DATE_FORMATER.format(this.DateEnd.getTime());
    }
}
