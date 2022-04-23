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
    private String description;
    private int IdHost;


    public Sejour(int sejourId, String name, String location, Calendar dateBegin, Calendar dateEnd,String description, int IdHost) {
        this.sejourId = sejourId;
        this.name = name;
        this.location = location;
        this.DateBegin = dateBegin;
        this.DateEnd = dateEnd;
        this.description=description;
        this.IdHost = IdHost;
    }

    public Sejour(String name, String location, GregorianCalendar dateBegin, GregorianCalendar dateEnd,String description, int IdHost) {
        this.sejourId = -1;
        this.name = name;
        this.location = location;
        this.DateBegin = dateBegin;
        this.DateEnd = dateEnd;
        this.description =  description;
        this.IdHost = IdHost;
    }


    public int getSejourId() {
        return sejourId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getIdHost() { return IdHost; }

    public void setIdHost(int idHost) { IdHost = idHost; }

    @Override
    public String toString() {
        return "SejourId : " + this.sejourId
                + ", Name : " + this.name
                + ", Location : " + this.location
                + ", DateBegin : " + DATE_FORMATER.format(this.DateBegin.getTime())
                + ", DateEnd : " + DATE_FORMATER.format(this.DateEnd.getTime())
                + ", description : " + this.description
                + ", IdHost : " + this.IdHost;
    }
}
