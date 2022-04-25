package prototyopage.Controllers;

import prototyopage.MainApp;

import java.sql.SQLException;
import java.text.ParseException;

public abstract class ControllerAbstract {
    public abstract void adaptDisplayToContext();

    public abstract void init();

    public abstract void setMainApp(MainApp mainApp);
}
