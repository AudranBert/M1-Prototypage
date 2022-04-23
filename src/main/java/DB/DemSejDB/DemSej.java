package DB.DemSejDB;

import prototyopage.SearchRunnable;

import java.util.HashMap;

public class DemSej {
    private int demandeId;
    private int voyagerId;
    private int sejourId;

    public int getDemandeId() {
        return demandeId;
    }

    public int getVoyagerId() {
        return voyagerId;
    }

    public int getSejourId() {
        return sejourId;
    }

    public int getValidation() {
        return validation;
    }

    private int validation;

    private static final HashMap<Integer, String> VALIDATION_MAP = new HashMap<Integer, String>();

    public DemSej(int demSejId, int voyagerId, int sejourId, int validation) {
        this.demandeId = demSejId;
        this.voyagerId = voyagerId;
        this.sejourId = sejourId;
        this.validation = validation;
    }

    public DemSej(int voyagerId, int sejourId, int validation) {
        this.demandeId = -1;
        this.voyagerId = voyagerId;
        this.sejourId = sejourId;
        this.validation = validation;
    }

    @Override
    public String toString() {
        return "demandeId : " + this.demandeId
                + ", voyagerId: " + this.voyagerId
                + ", sejourId : " + this.sejourId
                + ", validation : " + this.validation;
    }

}
