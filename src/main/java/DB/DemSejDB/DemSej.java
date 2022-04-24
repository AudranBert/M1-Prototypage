package DB.DemSejDB;

import prototyopage.Ressources.SVGpaths;

import java.util.HashMap;

public class DemSej {
    private static final HashMap<Integer, String> VALIDATION_MAP_Str = new HashMap<>();
    private static final HashMap<Integer, HashMap<String, String>> VALIDATION_MAP_SVG = new HashMap<>();

    public static final String EN_ATTENTE = "En Attente";
    public static final String VALIDE = "Validé";
    public static final String REFUSE = "Refusé";

    static {
        VALIDATION_MAP_Str.put(0, EN_ATTENTE);
        VALIDATION_MAP_Str.put(1, VALIDE);
        VALIDATION_MAP_Str.put(2, REFUSE);

        VALIDATION_MAP_SVG.put(0, SVGpaths.WAITING_LOGO);
        VALIDATION_MAP_SVG.put(1, SVGpaths.VALIDATED_LOGO);
        VALIDATION_MAP_SVG.put(2, SVGpaths.REFUSED_LOGO);
    }

    private int demandeId;
    private int voyagerId;
    private int sejourId;
    private int validation;

    public HashMap<String, String> getSVG() {
        return VALIDATION_MAP_SVG.get(this.validation);
    }

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

    public DemSej(int demSejId, int voyagerId, int sejourId, int validation) {
        this.demandeId = demSejId;
        this.voyagerId = voyagerId;
        this.sejourId = sejourId;
        this.validation = validation;
    }

    public DemSej(int voyagerId, int sejourId) {
        this.demandeId = -1;
        this.voyagerId = voyagerId;
        this.sejourId = sejourId;
        this.validation = 0;
    }

    public String validationToString() {
        return VALIDATION_MAP_Str.get(this.validation);
    }

    @Override
    public String toString() {
        return "demandeId : " + this.demandeId
                + ", voyagerId: " + this.voyagerId
                + ", sejourId : " + this.sejourId
                + ", validation : " + validationToString();
    }
}
