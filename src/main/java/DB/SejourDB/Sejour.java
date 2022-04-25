package DB.SejourDB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Sejour {
    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");

    private int sejourId;
    private int imageBundle;
    private String name;
    private String location;
    private Calendar DateBegin;
    private Calendar DateEnd;
    private String description;
    private int IdHost;

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Sejour)){
            //implicit null check
            return false;
        }
        return this.sejourId==((Sejour)o).sejourId;
    }

    @Override
    public int hashCode(){
        return this.sejourId;
    }

    public Sejour(int sejourId, int imageBundle, String name, String location, Calendar dateBegin, Calendar dateEnd,String description, int IdHost) {
        this.sejourId = sejourId;
        this.imageBundle = imageBundle;
        this.name = name;
        this.location = location;
        this.DateBegin = dateBegin;
        this.DateEnd = dateEnd;
        this.description=description;
        this.IdHost = IdHost;
    }

    public Sejour(int sejourId, int imageBundle, String name, String location, String dateBegin, String dateEnd,String description, int IdHost) {
        try {
            this.sejourId = sejourId;
            this.imageBundle = imageBundle;
            this.name = name;
            this.location = location;
            this.DateBegin = new GregorianCalendar();
            this.DateBegin.setTime(dateFormater.parse(dateBegin));
            this.DateEnd = new GregorianCalendar();
            this.DateEnd.setTime(dateFormater.parse(dateEnd));
            this.description=description;
            this.IdHost = IdHost;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Sejour(String name, String location, GregorianCalendar dateBegin, GregorianCalendar dateEnd,String description, int IdHost) {
        this.sejourId = -1;
        this.imageBundle = 0;
        this.name = name;
        this.location = location;
        this.DateBegin = dateBegin;
        this.DateEnd = dateEnd;
        this.description =  description;
        this.IdHost = IdHost;
    }

    public Sejour(String name, String location, String dateBegin, String dateEnd,String description, int IdHost) {
        try {
            this.sejourId = -1;
            this.imageBundle = 0;
            this.name = name;
            this.location = location;
            this.DateBegin = new GregorianCalendar();
            this.DateBegin.setTime(dateFormater.parse(dateBegin));
            this.DateEnd = new GregorianCalendar();
            this.DateEnd.setTime(dateFormater.parse(dateEnd));
            this.description =  description;
            this.IdHost = IdHost;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public int getSejourId() {
        return sejourId;
    }

    public int getImageBundle() { return imageBundle; }

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

    public String getStrDateBegin() { return dateFormater.format(this.DateBegin.getTime()); }

    public void setDateBegin(Calendar dateBegin) {
        DateBegin = dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.DateBegin = new GregorianCalendar();
        try {
            this.DateBegin.setTime(dateFormater.parse(dateBegin));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Calendar getDateEnd() {
        return DateEnd;
    }

    public String getStrDateEnd() { return dateFormater.format(this.DateEnd.getTime()); }


    public void setDateEnd(Calendar dateEnd) {
        DateEnd = dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.DateEnd = new GregorianCalendar();
        try {
            this.DateEnd.setTime(dateFormater.parse(dateEnd));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int getIdHost() { return IdHost; }

    public void setIdHost(int idHost) { IdHost = idHost; }

    @Override
    public String toString() {
        return "SejourId : " + this.sejourId
                + ", ImageBundle: " + this.imageBundle
                + ", Name : " + this.name
                + ", Location : " + this.location
                + ", DateBegin : " + dateFormater.format(this.DateBegin.getTime())
                + ", DateEnd : " + dateFormater.format(this.DateEnd.getTime())
                + ", description : " + this.description
                + ", IdHost : " + this.IdHost;
    }
}
