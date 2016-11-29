package draft;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        AusgabeWahlkarten ausg = new AusgabeWahlkarten();
        
        ArrayList<Buerger> buergerListe = ausg.ausgabe();
        EinschreibenWaehlerliste einschr = new EinschreibenWaehlerliste(buergerListe);
        einschr.einschreibungen();
        
    }

}
