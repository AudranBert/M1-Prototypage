package BookBddExemple;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Sejour {
    private int SejourId;
    private String name;
    private String location;
    private Calendar DateBegin;
    private Calendar DateEnd;

    public Sejour(int sejourId, String name, String location, GregorianCalendar dateBegin, GregorianCalendar dateEnd) {
        this.SejourId = sejourId;
        this.name = name;
        this.location = location;
        this.DateBegin = dateBegin;
        this.DateEnd = dateEnd;
    }

    public int getSejourId() {
        return SejourId;
    }

    public void setSejourId(int sejourId) {
        SejourId = sejourId;
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

    public Calendar getDateBegin() {
        return DateBegin;
    }

    public void setDateBegin(Calendar dateBegin) {
        DateBegin = dateBegin;
    }

    public Calendar getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(Calendar dateEnd) {
        DateEnd = dateEnd;
    }
}
