package draft;

import java.math.BigInteger;
import java.util.ArrayList;

public class AusgabeWahlkarten {
    private ArrayList<Buerger> buergerMulti;

    private Wahlbehoerde wahlbehoerde;

    public AusgabeWahlkarten() {
        buergerMulti = new ArrayList<>();
        init();
    }

    private void init() {
        Buerger buerger;
        for (int i = 0; i < 10; i++) {
            buerger = new Buerger();
            buergerMulti.add(buerger);
        }
        wahlbehoerde = new Wahlbehoerde();
    }

    public ArrayList<Buerger> ausgabe() {
        BigInteger y;
        BigInteger t;
        for (int i = 0; i < buergerMulti.size(); i++) {
            y = buergerMulti.get(i).calculateY(wahlbehoerde.getPublicE(), wahlbehoerde.getPublicN());
            t = wahlbehoerde.blindSignature(y);
            buergerMulti.get(i).calculateZ(t, wahlbehoerde.getPublicN());
            buergerMulti.get(i).computeRSA();
            }
        return buergerMulti;

    }
}
